package com.example.quizapp.services;

import com.example.quizapp.DTO.*;
import com.example.quizapp.models.Answer;
import com.example.quizapp.models.Quiz;
import com.example.quizapp.repositories.AnswerRepository;
import com.example.quizapp.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final AnswerRepository answerRepository;
    @Autowired
    public QuizService( QuizRepository quizRepository, AnswerRepository answerRepository){
        this.quizRepository = quizRepository ;
        this.answerRepository = answerRepository;
    }

    public Quiz dtoToQuiz(QuizCreationDTO quizDto){
        Quiz quiz = new Quiz();
        quiz.setQuestion(quizDto.getQuestion());
        return quiz;
    }

    public AnswerResponseDTO answerToDto(Answer answer){
        AnswerResponseDTO dto = new AnswerResponseDTO();
        dto.setAnswer(answer.getAnswer());
        dto.setId(answer.getId());
        return dto;
    }

    public void create(Quiz quiz) {
        quizRepository.save(quiz);
        System.out.println(quiz.toString() + "is successfully added");
    }

    public QuizResponseDTO quizToResponseDTO(Quiz quiz){
        QuizResponseDTO quizDto = new QuizResponseDTO();
        quizDto.setQuestion(quiz.getQuestion());
        quizDto.setAnswers(quiz.getAnswers().stream().map(this::answerToDto).collect(Collectors.toSet()));
        quizDto.setId(quiz.getId());
        return quizDto;
    }
    public List<QuizResponseDTO> get() {
        return quizRepository.findAll().stream().map(this::quizToResponseDTO).collect(Collectors.toList());
    }

    //update answer
    public void delete(long id) {
        quizRepository.deleteById(id);
        System.out.println("quiz is successfully deleted");
    }

    @Transactional
    public void edit(long id, QuizEditDTO quizDto) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() ->new IllegalStateException("There is no quiz with id "+ id));
        if(quizDto.getQuestion() != null  && !Objects.equals(quizDto.getQuestion(), quiz.getQuestion())){
            quiz.setQuestion(quizDto.getQuestion());
        }

        System.out.println("Quiz is successfully edited");
    }

    public QuizResponseDTO getDetail(long id) {
        return quizToResponseDTO(quizRepository.findById(id).orElseThrow(()->new IllegalStateException("Quiz with ID "+id+"does not exist")));
    }

    @Transactional
    public void assignQuizToAnswer(long answerId, long quizId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(()->new IllegalStateException("Wrong answer ID"));
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(()-> new IllegalStateException("Wrong quiz ID"));
        answer.setQuiz(quiz);
    }


    public void deleteAnswer(long id) {
        answerRepository.deleteById(id);
        System.out.println("answer is successfully deleted");
    }

    @Transactional
    public void updateAnswer(long quizId, long answerId, AnswerEditDTO answerUpdateDTO) {
        Answer answer = answerRepository.findByQuizIdAndId(quizId, answerId).orElseThrow(() -> new IllegalStateException("Answer with quizId " + quizId + " and id " + answerId + " does not exist"));
        answer.setAnswer(answerUpdateDTO.getAnswer());
        answerRepository.save(answer);
    }

}
