package com.swarasinchana.quizapp.service;

import com.swarasinchana.quizapp.dao.QuestionDao;
import com.swarasinchana.quizapp.dao.QuizDao;
import com.swarasinchana.quizapp.model.Question;
import com.swarasinchana.quizapp.model.QuestionWrapper;
import com.swarasinchana.quizapp.model.Quiz;
import com.swarasinchana.quizapp.model.Response;
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
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
            List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);

            return new ResponseEntity<>("Quiz created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating quiz", e);
            return new ResponseEntity<>("Error creating quiz", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Long id) {
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        try {
            Optional<Quiz> quiz = quizDao.findById(id);
           // List<Question> questions = quiz.map(Quiz::getQuestions).orElse(null);
            List<Question> questionFromDb = quiz.get().getQuestions();
            for (Question q : questionFromDb) {
                QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
                questionsForUser.add(qw);
            }
            return new ResponseEntity<>(questionsForUser, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating quiz", e);
            return new ResponseEntity<>(questionsForUser, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        try {
            Optional<Quiz> quiz = quizDao.findById(Long.valueOf(id));
            List<Question> questions = quiz.map(Quiz::getQuestions).orElse(null);
            int score = 0;
            for (Response response : responses) {
                for (Question question : questions) {
                    if(response.getResponse().equals(question.getRightAnswer())){
                        score++;
                    }

                }
            }
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error submitting quiz", e);
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
    }
}
