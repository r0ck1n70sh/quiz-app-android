package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {
    public static final String USERNAME= "com.example.quizapp.MESSAGE";
    //RequestQueue singletonQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //singletonQueue = Volley.newRequestQueue(this);
    }

    public void onClickedNext(View view){
        Intent callingdbIntent= new Intent(this, CallingdbActivity.class);
        EditText usernameInputText= (EditText) findViewById(R.id.mainPlainTextUsernameInput);
        String username= usernameInputText.getText().toString();

        callingdbIntent.putExtra(USERNAME, username);
        startActivity(callingdbIntent);
    }
}