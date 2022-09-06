package com.example.quizapp.DTO;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class UserCreationDTO {
    private String name;
    private String lastName;
    @NotNull
    private String password;
    @NotNull
    private String email;
}
