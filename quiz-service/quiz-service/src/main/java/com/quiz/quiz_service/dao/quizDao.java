package com.quiz.quiz_service.dao;


import com.quiz.quiz_service.models.quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface quizDao extends JpaRepository<quiz,Integer> {
}
