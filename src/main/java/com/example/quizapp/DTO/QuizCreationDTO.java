package com.example.quizapp.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QuizCreationDTO {
    @NotNull
    private String question;
}
