package com.example.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.interview.entity.Question;
import com.example.interview.service.OpenRouterService;
import com.example.interview.service.QuestionService;

@Controller
public class InterviewController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private OpenRouterService openRouterService;

    // TEXT INTERVIEW

    @GetMapping("/user/interview/{category}")
    public String startInterview(

            @PathVariable String category,
            Model model

    ) {

        Question question =
                questionService
                .getQuestionByCategory(category);

        model.addAttribute("question", question);

        model.addAttribute("category", category);

        return "interview";
    }

    // VOICE INTERVIEW

    @GetMapping("/user/voice-interview/{category}")
    public String voiceInterview(

            @PathVariable String category,
            Model model

    ) {

        Question question =
                questionService
                .getQuestionByCategory(category);

        model.addAttribute("question", question);

        model.addAttribute("category", category);

        return "voice-interview";
    }

    // SUBMIT ANSWER

    @PostMapping("/user/submit-answer")
    public String submitAnswer(

            @RequestParam String question,
            @RequestParam String answer,
            @RequestParam String mode,
            @RequestParam String category,
            Model model

    ) throws Exception {

        String feedback =
                openRouterService.getFeedback(
                        question,
                        answer
                );

        model.addAttribute("feedback", feedback);

        // MODE
        model.addAttribute("mode", mode);

        // CATEGORY
        model.addAttribute("category", category);

        return "result";
    }
}