package com.example.interview.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    private LocalDateTime interviewDate;

    @ManyToOne
    private User user;

    public Interview() {
    }

    public Long getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getInterviewDate() {
        return interviewDate;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setInterviewDate(LocalDateTime interviewDate) {
        this.interviewDate = interviewDate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}