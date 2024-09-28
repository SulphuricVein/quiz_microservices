package com.question_service.question_service.models;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    int id;
    String rightAns;
}
