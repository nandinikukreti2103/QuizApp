package com.nandini.quizapp.controller;

import com.nandini.quizapp.entity.Question;
import com.nandini.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Question> getQuestionByCategory(@PathVariable("category") String category) {
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

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        String response = questionService.addQuestion(question); // Call service to add question
        return new ResponseEntity<>(response, HttpStatus.CREATED); // Wrap the response with HTTP status
    }


    @DeleteMapping("/delete/{id}")
    public String deleteQuestionById(@PathVariable("id") int theId){

        boolean isDeleted = questionService.deleteQuestionById(theId);

        if(isDeleted){
            return "Question with Id: " + theId + "deleted successfully.";
        }
        else{
            return  "Question with Id: " + theId + "not found.";
        }
    }

    @PutMapping("/update/{id}")
    public String updateQuestion(@PathVariable("id") int theId, @RequestBody Question question) {
        boolean isUpdated = questionService.updateQuestion(theId, question);

        if (isUpdated) {
            return "Question with ID: " + theId + " updated successfully";
        } else {
            return "Question with ID: " + theId + " not found";
        }
    }

}
