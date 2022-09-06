package com.example.quizapp.models;




import javax.persistence.*;

@Table
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String answer;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;

    public Answer(long id, String answer) {
        this.id = id;
        this.answer = answer;
    }
    public Answer(String answer) {
        this.answer = answer;
    }
    public Answer() {
    }

    public long getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }


    public Quiz getQuiz() {
        return quiz;
    }
    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + answer + '\'' +
                '}';
    }


}
