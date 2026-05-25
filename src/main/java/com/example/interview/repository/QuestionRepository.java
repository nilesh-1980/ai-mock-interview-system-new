package com.example.interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.interview.entity.Question;

public interface QuestionRepository
        extends JpaRepository<Question, Long>{

    @Query(value =
        "SELECT * FROM question WHERE category=?1 ORDER BY RAND() LIMIT 1",
        nativeQuery = true)

    Question getRandomQuestionByCategory(String category);
}