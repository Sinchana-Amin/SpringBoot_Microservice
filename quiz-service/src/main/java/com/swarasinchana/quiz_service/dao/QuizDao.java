package com.swarasinchana.quiz_service.dao;


import com.swarasinchana.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Long>{
}
