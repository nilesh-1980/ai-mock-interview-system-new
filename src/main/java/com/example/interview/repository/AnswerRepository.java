package com.example.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.interview.entity.Answer;

public interface AnswerRepository
        extends JpaRepository<Answer, Long> {

}