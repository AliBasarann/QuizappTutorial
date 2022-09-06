package com.example.quizapp.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "answers")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer extends BaseIdentityEntity {
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizId")
    private Quiz quiz;
}
