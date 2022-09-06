package com.example.quizapp.DTO;


import lombok.Data;

@Data
public class UserEditDTO {
    private String name;
    private String lastName;
    private String password;
    private String email;
}
