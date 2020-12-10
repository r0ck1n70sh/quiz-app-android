package com.example.quizapp;

import java.io.Serializable;

public class QuizInstance implements Serializable {
    public int totalNumQuestion;
    public int currQuestion;
    public int score;
    public User user;
    public Question[] questions;

    public QuizInstance(User user){
        totalNumQuestion = 10;
        currQuestion = 0;
        score = 0;
        this.user = user;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public Question getQuestion(){
        int curr = currQuestion;
        currQuestion += 1;
        return questions[curr];
    }

    public void updateScore(int pointScored) {
        score += pointScored;
    }

}
