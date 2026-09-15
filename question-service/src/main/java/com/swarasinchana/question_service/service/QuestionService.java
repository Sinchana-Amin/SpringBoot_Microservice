package com.swarasinchana.question_service.service;

import com.swarasinchana.question_service.dao.QuestionDao;
import com.swarasinchana.question_service.model.Question;
import com.swarasinchana.question_service.model.QuestionWrapper;
import com.swarasinchana.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
             return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e) {
             e.printStackTrace();
         }
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Question added successfully", HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> getQuestionIdsForQuiz(String category, int numQ) {
        try {
            List<Integer> questionIds = questionDao.findQuestionsByCategory(category, numQ);
            return new ResponseEntity<>(questionIds, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(List<Integer> questionIds){
        try {
                List<Question> questions = questionDao.findAllById(questionIds);
                List<QuestionWrapper> questionsForUser = questions.stream()
                        .map(q -> new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4()))
                        .toList();
            return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Integer> calculateScore(List<Response> responses) {
        try {
            int score = 0;
            for (Response response : responses) {
                Question q = questionDao.findById(response.getId()).get();
                    if (response.getResponse().equals(q.getRightAnswer())) {
                        score++;
                }
            }
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
