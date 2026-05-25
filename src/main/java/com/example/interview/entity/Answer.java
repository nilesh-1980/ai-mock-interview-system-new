package com.example.interview.entity;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10000)
    private String answer;

    @Column(length = 5000)
    private String feedback;

    private int score;

    @ManyToOne
    private Interview interview;

    @ManyToOne
    private Question question;

    public Answer() {
    }

    public Long getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getScore() {
        return score;
    }

    public Interview getInterview() {
        return interview;
    }

    public Question getQuestion() {
        return question;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}