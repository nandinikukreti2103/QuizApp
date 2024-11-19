package com.nandini.quizapp.controller;

import com.nandini.quizapp.entity.QuestionWrapper;
import com.nandini.quizapp.entity.Response;
import com.nandini.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable("id") int id){
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable("id") int id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }
}
