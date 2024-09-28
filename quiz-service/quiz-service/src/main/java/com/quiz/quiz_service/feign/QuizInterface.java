package com.quiz.quiz_service.feign;
import com.quiz.quiz_service.models.QuizWrapper;
import com.quiz.quiz_service.models.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient( "question-service")
public interface QuizInterface {
    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestions );

    @PostMapping("question/fetch")
    public ResponseEntity<List<QuizWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("question/results")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
