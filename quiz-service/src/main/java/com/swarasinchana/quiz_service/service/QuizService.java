package com.swarasinchana.quiz_service.service;

import com.swarasinchana.quiz_service.dao.QuizDao;
import com.swarasinchana.quiz_service.feign.QuizInterface;
import com.swarasinchana.quiz_service.model.QuestionWrapper;
import com.swarasinchana.quiz_service.model.Quiz;
import com.swarasinchana.quiz_service.model.Response;
import org.bouncycastle.util.Integers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
           List<Integer> questionIds = quizInterface.getQuestionIdsForQuiz(category, numQ).getBody();
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionIds(questionIds);
            quizDao.save(quiz);

            return new ResponseEntity<>("Quiz created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating quiz", e);
            return new ResponseEntity<>("Error creating quiz", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        try {
            Quiz quiz = quizDao.findById(Long.valueOf(id))
                    .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));
            List<Integer> questionIds = quiz.getQuestionIds();
            ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsForQuiz(questionIds);
            return questions;
        } catch (Exception e) {
            logger.error("Error creating quiz", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        try {
            ResponseEntity<Integer> score = quizInterface.calculateScore(responses);
            return score;
        } catch (Exception e) {
            logger.error("Error submitting quiz", e);
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
    }
}
