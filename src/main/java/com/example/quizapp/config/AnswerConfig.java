package com.example.quizapp.config;

import com.example.quizapp.models.Answer;
import com.example.quizapp.models.Quiz;
import com.example.quizapp.repositories.AnswerRepository;
import com.example.quizapp.repositories.QuizRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AnswerConfig {
    @Bean
    CommandLineRunner commandLineRunner3(AnswerRepository repository, QuizRepository quizRepository){
        return args ->{
            if (repository.count() == 0) {
                Quiz quiz1 = new Quiz("What is your name?");
                Quiz quiz2 = new Quiz("How old are you?");
                Answer answer1 = new Answer("Ali");
                Answer answer2 = new Answer("Veli");
                Answer answer3 = new Answer("1");
                Answer answer4 = new Answer("2");
                answer1.setQuiz(quiz1);
                answer2.setQuiz(quiz1);
                answer3.setQuiz(quiz2);
                answer4.setQuiz(quiz2);
                quizRepository.saveAll(List.of(quiz1,quiz2));
                repository.saveAll(List.of(answer1, answer2, answer3, answer4));

            }
        };
    }
}
