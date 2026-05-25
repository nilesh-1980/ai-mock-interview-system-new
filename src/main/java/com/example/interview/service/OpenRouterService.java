package com.example.interview.service;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenRouterService {

    @Value("${openrouter.api.key}")
    private String apiKey;

    public String getFeedback(String question, String answer) {

        OkHttpClient client = new OkHttpClient();

        // PROMPT
        String prompt =
                "You are an interview evaluator.\n" +
                "Question: " + question +
                "\nAnswer: " + answer +
                "\n\nGive:\n" +
                "1. Score out of 10\n" +
                "2. Short feedback";

        // SAFE STRING
        String safePrompt = prompt
                .replace("\"", "\\\"")
                .replace("\n", "\\n");

        // JSON BODY
        String json = """
        {
          "model": "openai/gpt-3.5-turbo",
          "messages": [
            {
              "role": "user",
              "content": "%s"
            }
          ]
        }
        """.formatted(safePrompt);

        // REQUEST BODY
        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json")
        );

        // REQUEST
        Request request = new Request.Builder()
                .url("https://openrouter.ai/api/v1/chat/completions")
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try {

            Response response =
                    client.newCall(request).execute();

            String result =
                    response.body().string();

            System.out.println(result);

            // EXTRACT AI MESSAGE
            int start =
                    result.indexOf("\"content\":\"") + 11;

            int end =
                    result.indexOf("\",\"refusal\"");

            String feedback =
                    result.substring(start, end);

            // CLEAN TEXT
            feedback = feedback
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"");

            return feedback;

        } catch (Exception e) {

            e.printStackTrace();

            return "Error: " + e.getMessage();
        }
    }
}