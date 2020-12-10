package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HandleQuizActivity extends AppCompatActivity {
    User activityUser;
    Question[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_quiz);

        getAll();
        showAll();
    }

    public void getAll() {
        Intent previousActivity = getIntent();
        activityUser = (User) previousActivity.getSerializableExtra("USER");
        questions = (Question[]) previousActivity.getSerializableExtra("QUESTIONS");
    }

    public void showAll() {
        TextView textView = findViewById(R.id.HandleQuizTextView);
        String text = questions[0].getQuestion();
        textView.setText(text);
    }
}