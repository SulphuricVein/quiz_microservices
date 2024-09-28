package com.question_service.question_service.controllers;
import com.question_service.question_service.models.Response;
import com.question_service.question_service.models.question;
import com.question_service.question_service.models.quizDTO;
import com.question_service.question_service.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
@Autowired
QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<question>>  getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<question>>  getCategory(@PathVariable String category){
        return questionService.getCat(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> add(@RequestBody question q){
        return questionService.add(q);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return questionService.delete(id);
    }

//These endpoints are for the quiz service

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestions ){
        return questionService.generate(categoryName, numQuestions);
    }

    @PostMapping("fetch")
    public ResponseEntity<List<quizDTO>> fetch(@RequestBody List<Integer> list){
        return questionService.fetch(list);
    }
    @PostMapping("results")
    public ResponseEntity<Integer> results(@RequestBody List<Response> res){
        return questionService.results(res);
    }
}
