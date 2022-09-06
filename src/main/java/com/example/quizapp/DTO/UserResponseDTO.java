package com.example.quizapp.DTO;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class UserResponseDTO {
    private long id;
    private String name;
    private String lastName;
}
