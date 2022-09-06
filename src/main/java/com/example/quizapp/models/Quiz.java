package com.example.quizapp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String question;



    // başka entity daha yap one to many quizAnswerda many to one tut
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "quiz")
    private final Set<Answer> answers = new HashSet<>();

    /*
    @JsonIgnore
    @ManyToMany(mappedBy = "quiz")
    private final Set<User> users = new HashSet<>();
*/

    public long getId(){
        return id;
    }
    public String getQuestion() {
        return question;
    }
    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public void setId(long id){
        this.id = id;
    }

    public Quiz(long id , String question) {
        this.question = question;

        this.id =id;
    }


    public Quiz(String question) {
        this.question = question;
    }


    public Quiz() {
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", question='" + question +
                '}';
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
    }


}
