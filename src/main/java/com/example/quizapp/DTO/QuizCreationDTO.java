package com.example.quizapp.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class QuizCreationDTO {
    @NotNull
    private String question;
    private List<AnswerCreationDTO> answers;
}
