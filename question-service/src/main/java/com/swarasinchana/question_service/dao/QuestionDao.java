package com.swarasinchana.question_service.dao;


import com.swarasinchana.question_service.model.Question;
import com.swarasinchana.question_service.model.QuestionWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    //localhost:8080/quiz/create?category=Java&numQ=4&title=JQuiz
    @Query(value = "SELECT * FROM question q WHERE q.category = ?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);

    @Query(value = "SELECT q.id from question q where q.category = ?1", nativeQuery = true)
    List<Integer> findQuestionsByCategory(String category, int numQ);


  //  List<QuestionWrapper> findQuestionsByIds(List<Integer> questionIds);
}
