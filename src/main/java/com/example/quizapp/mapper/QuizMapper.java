package com.example.quizapp.mapper;


import com.example.quizapp.models.Quiz;
import com.example.quizapp.DTO.QuizEditDTO;
import com.example.quizapp.DTO.QuizDetailResponseDTO;
import com.example.quizapp.DTO.QuizResponseDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = SharedConfig.class)
public interface QuizMapper {
    @Named(value = "quizResponseDTO")
    QuizResponseDTO map(Quiz quiz);

    QuizDetailResponseDTO mapDetail(Quiz user);

    @IterableMapping(qualifiedByName = "quizResponseDTO")
    List<QuizResponseDTO> mapList(List<Quiz> user);

    void merge(@MappingTarget Quiz quiz, QuizEditDTO quizUpdateDTO);

}