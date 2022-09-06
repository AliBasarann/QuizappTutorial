package com.example.quizapp.DTO;

import com.example.quizapp.models.Answer;
import lombok.Data;

import java.util.Set;

@Data
public class QuizResponseDTO {
    private long id;
    private String question;
}
