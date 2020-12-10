package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HandleQuizActivity extends AppCompatActivity {
    public QuizInstance quizInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_quiz);

        getQuizInstance();
        getPreviousQuestion();
        openNextActivity();
    }

    public void getQuizInstance(){
        if(quizInstance == null) {
            Intent previousActivity = getIntent();
            quizInstance = (QuizInstance) previousActivity.getSerializableExtra("QUIZ_INSTANCE");
        }
    }

    public void getPreviousQuestion(){
        Intent previousQuestion = getIntent();
        if(previousQuestion.getStringExtra("NAME").equals("Question"))
            updateScore(previousQuestion);
    }

    public void updateScore(Intent intent){
        int score = (int) intent.getIntExtra("SCORE", 0);
        quizInstance.updateScore(score);
    }

    public void openNextActivity(){
        if(quizInstance.questionsAvailable())
            startQuizActivity();
        else
            startDisplayScore();
    }

    public void startQuizActivity(){
        Intent quizActivity = new Intent(this, QuestionDisplayActivity.class);
        Question currQuestion = quizInstance.getQuestion();
        quizActivity.putExtra("QUESTION", currQuestion);
        startActivity(quizActivity);
    }

    public void startDisplayScore(){}
}