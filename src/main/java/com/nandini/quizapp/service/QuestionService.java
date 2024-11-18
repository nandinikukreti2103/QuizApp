package com.nandini.quizapp.service;

import com.nandini.quizapp.dao.QuestionRepository;
import com.nandini.quizapp.entity.Question;
import lombok.RequiredArgsConstructor;
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
}
