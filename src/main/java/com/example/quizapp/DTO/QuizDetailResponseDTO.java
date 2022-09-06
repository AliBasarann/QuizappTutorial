package com.example.quizapp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class QuizDetailResponseDTO extends QuizResponseDTO {
    private List<AnswerResponseDTO> answers;
}
