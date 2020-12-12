package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class HandleQuizActivity extends AppCompatActivity {
    public QuizInstance quizInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_quiz);

        if(quizInstance == null)
            getQuizInstance();
        openNextActivity();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        Log.i("myTag", "onSaveInstanceState");
        savedInstanceState.putSerializable("QUIZ_INSTANCE", quizInstance);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        Log.i("myTag", "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        quizInstance = (QuizInstance) savedInstanceState.getSerializable("QUIZ_INSTANCE");
    }

    public void getQuizInstance(){
        Intent previousActivity = getIntent();
        quizInstance = (QuizInstance) previousActivity.getSerializableExtra("QUIZ_INSTANCE");
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
        startActivityForResult(quizActivity, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent questionIntent){
        super.onActivityResult(requestCode, resultCode, questionIntent);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                int score = questionIntent.getIntExtra("SCORE", 0);
                updateScore(score);
                openNextActivity();
            }
        }
    }

    public void updateScore(int score){
        quizInstance.updateScore(score);
    }

    public void startDisplayScore(){

    }
}