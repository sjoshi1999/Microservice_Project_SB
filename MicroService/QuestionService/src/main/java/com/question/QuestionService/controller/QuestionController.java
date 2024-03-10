package com.question.QuestionService.controller;

import com.question.QuestionService.entities.Question;
import com.question.QuestionService.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    //Create
    @PostMapping
    public Question create(@RequestBody Question question){
        return questionService.create(question);
    }

    //get question
    @GetMapping
    public List<Question> getAll(){
        return questionService.get();
    }

    //get question by id
    @GetMapping("/{questionId}")
    public Question getOne(@PathVariable Long questionId){
        return questionService.getOne(questionId);
    }

    //get specific quetions of quiz id
    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuiz(@PathVariable Long quizId){
        return  questionService.getQuestionsOfQuiz(quizId);
    }

}
