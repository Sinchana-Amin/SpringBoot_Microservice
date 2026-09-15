package com.swarasinchana.quiz_service.feign;

import com.swarasinchana.quiz_service.model.QuestionWrapper;
import com.swarasinchana.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "QUESTION-SERVICE", url = "http://localhost:8080/question")
public interface QuizInterface {
    @PostMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionIdsForQuiz(@RequestParam String category, @RequestParam int numQ);

    @PostMapping("/getQuestionsForQuiz")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> questionIds);

    @GetMapping("/getQuestionsFromQuizId")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromQuizId(List<Integer> questionIds);

    @PostMapping("/getScore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses);
}
