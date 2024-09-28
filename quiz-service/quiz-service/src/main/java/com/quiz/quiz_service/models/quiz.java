package com.quiz.quiz_service.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class quiz {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    String title;
    @ElementCollection
     List<Integer> ques;

}
