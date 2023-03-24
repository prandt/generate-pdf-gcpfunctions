package com.example.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Exam {
    private String name;
    private String description;
    private String courseName;
    private List<Question> questions = new ArrayList<Question>();
}
