package com.example.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.interview.entity.Question;
import com.example.interview.service.QuestionService;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/admin/add-question")
    public String addQuestionPage(Model model) {

        model.addAttribute("question", new Question());

        return "add-question";
    }

    @PostMapping("/admin/save-question")
    public String saveQuestion(@ModelAttribute Question question) {

        questionService.addQuestion(question);

        return "redirect:/admin/questions";
    }

    @GetMapping("/admin/questions")
    public String viewQuestions(Model model) {

        model.addAttribute("questions",
                questionService.getAllQuestions());

        return "questions";
    }
}