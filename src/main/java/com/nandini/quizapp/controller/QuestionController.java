package com.nandini.quizapp.controller;

import com.nandini.quizapp.entity.Question;
import com.nandini.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @GetMapping("category/{category}")
    public List<Question> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }

    @GetMapping("/findByWord")
    public List<Question> getQuestionsByStartWord(@RequestParam String start) {
        return questionService.getQuestionsStartingWith(start);
    }

    @GetMapping("/search")
    public List<Question> getQuestionsSearchWithAnyLetter(@RequestParam String word){
        return questionService.getQuestionsSearchWithAnyLetter(word);
    }
}
