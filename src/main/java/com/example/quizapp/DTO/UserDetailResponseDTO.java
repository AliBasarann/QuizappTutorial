package com.example.quizapp.DTO;

import lombok.Data;

@Data
public class UserDetailResponseDTO extends UserResponseDTO {
    private String identifier;
    private String password;
}
