package com.example.quizapp.config;

import com.example.quizapp.models.User;
import com.example.quizapp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner2(UserRepository repository){
        return args ->{
            if (repository.count() == 0) {
                User first = new User("name1", "surname1", "email1", "password1");
                User second = new User("name2", "surname2", "email2", "password2");
                repository.saveAll(List.of(first, second));
            }
        };
    }
}
