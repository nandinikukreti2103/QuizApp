package com.nandini.quizapp.service;

import com.nandini.quizapp.dao.QuestionRepository;
import com.nandini.quizapp.dao.QuizRepository;
import com.nandini.quizapp.entity.Question;
import com.nandini.quizapp.entity.QuestionWrapper;
import com.nandini.quizapp.entity.Quiz;
import com.nandini.quizapp.entity.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;


    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionRepository.findRandomQuestionWithCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        // to get question from db
        List<Question> questionsFromDB = quiz.get().getQuestions();

        //to convert each question into question wrapper
        // and return it to user we create a empty array list
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q: questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),
                                                     q.getOption2(),q.getOption3(),q.getOption4());

            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;

        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
