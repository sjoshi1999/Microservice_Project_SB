package com.quiz.QuizService.serviceImpl;

import com.quiz.QuizService.entities.Quiz;
import com.quiz.QuizService.repositories.quizRepository;
import com.quiz.QuizService.services.QuestionClient;
import com.quiz.QuizService.services.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private quizRepository quizRepo;

    private QuestionClient questionClient;

    public QuizServiceImpl(quizRepository quizRepo, QuestionClient questionClient) {
        this.quizRepo = quizRepo;
        this.questionClient = questionClient;
    }


    @Override
    public Quiz add(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    @Override
    public List<Quiz> get() {
        List<Quiz> quizzes = quizRepo.findAll();
       List<Quiz> newQuiz = quizzes.stream().map(quiz -> {
            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
            return quiz;
        }).collect(Collectors.toList());
        return newQuiz;
    }

    @Override
    public Quiz get(Long id) {
        Quiz quiz = quizRepo.findById(id).orElseThrow(()->new RuntimeException("Quiz not found"));
        quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
        return quiz;
    }
}
