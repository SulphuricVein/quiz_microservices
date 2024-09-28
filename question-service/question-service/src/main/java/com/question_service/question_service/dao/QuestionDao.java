package com.question_service.question_service.dao;


import com.question_service.question_service.models.question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<question,Integer> {
    List<question> findByCategory(String category);
    @Query(value = "Select id from question where category= :category order by random() limit :count",nativeQuery = true)
    List<Integer> findRandomByCategory(String category, Integer count);
}
