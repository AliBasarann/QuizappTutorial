package com.example.quizapp.services;

import com.example.quizapp.DTO.*;
import com.example.quizapp.mapper.QuizMapper;
import com.example.quizapp.models.Answer;
import com.example.quizapp.models.Quiz;
import com.example.quizapp.repositories.AnswerRepository;
import com.example.quizapp.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final AnswerRepository answerRepository;
    private final QuizMapper quizMapper;

    @Transactional
    public QuizResponseDTO create(QuizCreationDTO quizInsertDTO) {
        Quiz quiz = Quiz.builder()
                .question(quizInsertDTO.getQuestion())
                .build();
        quiz = quizRepository.save(quiz);
        for (AnswerCreationDTO answerInsertDTO : quizInsertDTO.getAnswers()) {
            Answer answer = Answer.builder()
                    .answer(answerInsertDTO.getAnswer())
                    .quiz(quiz)
                    .build();
            answerRepository.save(answer);
        }
        return quizMapper.map(quiz);
    }
    public QuizResponseDTO quizToResponseDTO(Quiz quiz){
        QuizResponseDTO quizDto = new QuizResponseDTO();
        quizDto.setQuestion(quiz.getQuestion());
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
        quizMapper.merge(quiz, quizDto);
        quizRepository.save(quiz);

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
