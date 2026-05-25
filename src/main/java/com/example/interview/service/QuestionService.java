package com.example.interview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.interview.entity.Question;
import com.example.interview.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    // SAVE QUESTION
    public void addQuestion(Question question) {

        questionRepository.save(question);
    }

    // GET ALL QUESTIONS
    public List<Question> getAllQuestions() {

        return questionRepository.findAll();
    }

    // GET QUESTION BY ID
    public Question getQuestionById(Long id) {

        return questionRepository.findById(id).orElse(null);
    }

    // RANDOM QUESTION
    public Question getQuestionByCategory(String category){

        return questionRepository
                .getRandomQuestionByCategory(category);
    }
}