package com.example.quizapp.models;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Builder
public class User extends BaseIdentityEntity {

    @Column(nullable = false, updatable = false, unique = true, length = 30)
    private String username;
    @Column(nullable = false)
    private String password;
    private String name;
    private String lastName;
    private String email;
}
