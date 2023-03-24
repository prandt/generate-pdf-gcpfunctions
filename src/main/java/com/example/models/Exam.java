package com.example.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

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

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void convertJsonToExam(JsonObject body) {
        this.name = body.get("name").getAsString().toString();
        this.description = body.get("description").getAsString().toString();
        this.courseName = body.get("courseName").getAsString().toString();
    }
    
}
