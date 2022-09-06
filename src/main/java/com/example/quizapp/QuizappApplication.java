package com.example.quizapp;

import com.example.quizapp.mapper.QuizMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class QuizappApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizappApplication.class, args);
    }

}
