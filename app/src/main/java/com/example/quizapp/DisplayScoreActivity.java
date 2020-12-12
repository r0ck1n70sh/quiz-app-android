package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayScoreActivity extends AppCompatActivity {
    public int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_score);

        getScoreToDisplay();
        DisplayScore();
    }

    public void getScoreToDisplay(){
        Intent previousActivity = getIntent();
        score = previousActivity.getIntExtra("TOT_SCORE", 0);
    }

    public void DisplayScore(){
        TextView scoreDisplay = (TextView) findViewById(R.id.DisplayScoreTextView2);
        String scoreText = Integer.toString(score);
        scoreDisplay.setText(scoreText);
    }

    public void onClickedButton1(View view){
        Intent returnToMain = new Intent(this, MainActivity.class);
        startActivity(returnToMain);
        finish();
    }
}