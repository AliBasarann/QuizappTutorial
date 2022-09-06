package com.example.quizapp.models;


import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quizzes")
@FieldNameConstants
@Builder
public class Quiz extends BaseIdentityEntity {
    private String name;
    private String question;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quiz")
    @OrderBy("id ASC")
    private Set<Answer> answers;


}
