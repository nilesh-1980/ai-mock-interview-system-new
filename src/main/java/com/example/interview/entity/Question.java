package com.example.interview.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "question")
public class Question {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String category;

@Column(name = "question_text", length = 5000)
private String questionText;

public Question() {
}

public Question(Long id, String category, String questionText) {
    this.id = id;
    this.category = category;
    this.questionText = questionText;
}

public Long getId() {
    return id;
}

public String getCategory() {
    return category;
}

public String getQuestionText() {
    return questionText;
}

public void setId(Long id) {
    this.id = id;
}

public void setCategory(String category) {
    this.category = category;
}

public void setQuestionText(String questionText) {
    this.questionText = questionText;
}


}
