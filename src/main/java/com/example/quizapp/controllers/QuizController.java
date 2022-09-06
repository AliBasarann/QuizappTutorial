package com.example.quizapp.controllers;


import com.example.quizapp.DTO.*;
import com.example.quizapp.models.Answer;
import com.example.quizapp.models.Quiz;
import com.example.quizapp.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/quiz")
public class QuizController {
    private final QuizService quizService;
    @Autowired
    QuizController(QuizService quizService){
            this.quizService = quizService;
        }

        @PostMapping
        public void create(@RequestBody QuizCreationDTO quizDto){
            Quiz quiz = quizService.dtoToQuiz(quizDto);
            quizService.create(quiz);
        }
        /*
        @PostMapping
        public void createAnswer(@RequestBody AnswerCreationDTO answerCreationDTO){
            Answer answer = new Answer();
            answer.setAnswer(answerCreationDTO.getAnswer());
            quizService.createAnswer(answer);
        }
*/
        @GetMapping
        public List<QuizResponseDTO> get(){
            return quizService.get();
        }
//get details
        @GetMapping(path="/{quizId}")
        public QuizResponseDTO getDetail(@PathVariable("quizId") long id){
            return quizService.getDetail(id);
        }

        @DeleteMapping(path = "/{quizId}")
        public void delete(@PathVariable ("quizId") long id){
            quizService.delete(id);
        }
        @DeleteMapping(path = "/{answerId}")
        public void deleteAnswer(@PathVariable ("answerId") long id){
            quizService.deleteAnswer(id);
        }

        @PutMapping(path = "/{quizId}")
        public void edit(@PathVariable("quizId") long id,
                         @RequestBody QuizEditDTO quizEditDTO){
            quizService.edit(id, quizEditDTO);
        }

        @PutMapping("/{answerId}/{quizId}")
        public void assignQuizToAnswer(@PathVariable("answerId") long answerId,@PathVariable("quizId") long quizId){
            quizService.assignQuizToAnswer(answerId,quizId);
        }
        @PutMapping(path = "/{quizId}/answer/{answerId}")
        public void updateAnswer(@PathVariable("quizId") long quizId, @PathVariable("answerId") long answerId, @RequestBody AnswerEditDTO answerEditDTO) {
            quizService.updateAnswer(quizId, answerId, answerEditDTO);
        }

}
