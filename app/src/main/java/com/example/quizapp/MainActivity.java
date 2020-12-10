package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public static final String USERNAME= "com.example.quizapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickedNextButton(View view){
        Intent nextActivity = new Intent(this, PreparingQuizActivity.class);
        prepareNextActivityIntent(nextActivity, view);
        startActivity(nextActivity);
    }

    private void prepareNextActivityIntent(Intent intent, View view){
        String username = getUserNameString(view);
        User user = new User(username);
        intent.putExtra("USER", user);
    }

    private String getUserNameString(View view) {
        EditText editText= (EditText) findViewById(R.id.mainPlainTextUsernameInput);
        String userNameString = editText.getText().toString();
        return userNameString;
    }
}