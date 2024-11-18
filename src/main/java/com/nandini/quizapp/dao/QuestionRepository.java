package com.nandini.quizapp.dao;

import com.nandini.quizapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query("SELECT q FROM Question q WHERE q.questionTitle LIKE :start%")
    List<Question> findQuestionsStartingWith(@Param("start") String start);


    @Query("SELECT q FROM Question q WHERE " +
            "q.questionTitle LIKE %:word% OR " +
            "q.category LIKE %:word% OR " +
            "q.difficultyLevel LIKE %:word% OR " +
            "q.option1 LIKE %:word% OR " +
            "q.option2 LIKE %:word% OR " +
            "q.option3 LIKE %:word% OR " +
            "q.option4 LIKE %:word% OR " +
            "q.rightAnswer LIKE %:word%")
    List<Question> findQuestionsSearchWithAnyLetter(@Param("word") String word);
}
