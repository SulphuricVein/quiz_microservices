package com.quiz.quiz_service.services;


import com.quiz.quiz_service.dao.quizDao;
import com.quiz.quiz_service.feign.QuizInterface;
import com.quiz.quiz_service.models.Response;
import com.quiz.quiz_service.models.question;
import com.quiz.quiz_service.models.quiz;
import com.quiz.quiz_service.models.QuizWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {
    @Autowired
    quizDao qd;
    @Autowired
    QuizInterface qi;


    public ResponseEntity<String> setup(String category, int count, String title) {
        List<Integer>quesId=qi.getQuestionsForQuiz(category,count).getBody();
        quiz q=new quiz();
        q.setTitle(title);
        q.setQues(quesId);
        qd.save(q);
        return new ResponseEntity<>("Created",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuizWrapper>> getQuiz(int id) {
        quiz q=qd.findById(id).get();
        List<Integer> qIds=q.getQues();
      ResponseEntity<List<QuizWrapper>> questions = qi.getQuestionsFromId(qIds);
        return questions;
    }

    public ResponseEntity<Integer> calc(Integer id, List<Response> res) {
        ResponseEntity<Integer> score = qi.getScore(res);
        return score;
    }
}
