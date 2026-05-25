package com.example.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.interview.entity.Answer;
import com.example.interview.repository.AnswerRepository;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public void saveAnswer(Answer answer) {

        answerRepository.save(answer);
    }
}