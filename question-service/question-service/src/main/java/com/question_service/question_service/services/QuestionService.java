package com.question_service.question_service.services;



import com.question_service.question_service.dao.QuestionDao;
import com.question_service.question_service.models.Response;
import com.question_service.question_service.models.question;
import com.question_service.question_service.models.quizDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    QuestionDao qd;
    public ResponseEntity<List<question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(qd.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<question>> getCat(String category) {
        try{
            return new ResponseEntity<>(qd.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<String> add(question q) {
        try{
            qd.save(q);
            return new ResponseEntity<>("Created",HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failure",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> delete(int id) {

        try{
            qd.deleteById(id);
            return new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failure",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Integer>> generate(String category, Integer count) {
        List<Integer> list=qd.findRandomByCategory(category,count);
        return new ResponseEntity<>(list,HttpStatus.CREATED);
    }
    public ResponseEntity<List<quizDTO>> fetch(List<Integer> questionIds) {
        List<quizDTO> wrappers = questionIds.stream()
                .map(id -> {
                    question question = qd.findById(id).orElseThrow();
                    return new quizDTO(
                            question.getId(),
                            question.getQuestionTitle(),
                            question.getOption1(),
                            question.getOption2(),
                            question.getOption3(),
                            question.getOption4()
                    );
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> results(List<Response> res) {
        int right=0;
        for(Response r:res){
            question q=qd.findById(r.getId()).get();
            if(r.getRightAns().equals(q.getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.ACCEPTED);
    }
}
