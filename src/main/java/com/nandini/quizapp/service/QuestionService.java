package com.nandini.quizapp.service;

import com.nandini.quizapp.dao.QuestionRepository;
import com.nandini.quizapp.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public List<Question> getQuestionsStartingWith(String start) {
        return questionRepository.findQuestionsStartingWith(start);
    }

    public List<Question> getQuestionsSearchWithAnyLetter(String word) {
        return questionRepository.findQuestionsSearchWithAnyLetter(word);
    }

    public String addQuestion(Question question) {
        questionRepository.save(question);
        return "success";
    }


    public boolean deleteQuestionById(int theId) {

        if(questionRepository.existsById(theId)){
            questionRepository.deleteById(theId);
            return true;
        }
        return false;
    }

    public boolean updateQuestion(int theId, Question question) {
        // Check if the question with the given ID exists
        if (questionRepository.existsById(theId)) {
            // Set the ID of the updatedQuestion to ensure the update is applied to the correct record
            question.setId(theId);  // Ensure that the ID of the updated question is set to the ID from the path

            // Save the updated question (this performs the update)
            questionRepository.save(question);

            return true; // Indicate that the update was successful
        }
        return false; // Return false if the question was not found
    }

}
