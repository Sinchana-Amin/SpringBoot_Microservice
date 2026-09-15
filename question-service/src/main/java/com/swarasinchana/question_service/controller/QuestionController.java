package com.swarasinchana.question_service.controller;

import com.swarasinchana.question_service.model.Question;
import com.swarasinchana.question_service.model.QuestionWrapper;
import com.swarasinchana.question_service.model.Response;
import com.swarasinchana.question_service.service.QuestionService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PostMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionIdsForQuiz(@RequestParam String category, @RequestParam int numQ){
        return questionService.getQuestionIdsForQuiz(category,numQ);
    }

    @PostMapping("/getQuestionsForQuiz")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> questionIds) {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsForQuiz(questionIds);
    }

   /* @PostMapping("/getQuestionsFromQuizId")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromQuizId(List<Integer> questionIds ) {
        return questionService.getQuestionsForQuiz(questionIds);
    }*/

    @PostMapping("/getScore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses){
        return questionService.calculateScore(responses);
    }
}
