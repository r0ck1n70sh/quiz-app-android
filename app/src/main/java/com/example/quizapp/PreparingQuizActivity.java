package com.example.quizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PreparingQuizActivity extends AppCompatActivity {
    public User activityUser;

    public Question[] questions;
    private String URL = "https://opentdb.com/api.php?amount=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparing_quiz);

        callUser();
    }
    public void callQuestions(View view) throws JSONException {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    setResponseToQuestions(response);
                    sendUserAndQuestions();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );

        SingletonQueue.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public void setResponseToQuestions(JSONObject response) throws JSONException {
        JSONArray jsonQuestions = response.getJSONArray("results");
        questions = new Question[10];
        for(int i=0; i<10; i++) {
            JSONObject q = jsonQuestions.getJSONObject(i);
            questions[i] = new Question(q);
        }
    }

    public void sendUserAndQuestions() {
        Intent nextActivity = new Intent(this, HandleQuizActivity.class);
        nextActivity.putExtra("USER", activityUser);
        nextActivity.putExtra("QUESTIONS", questions);
        startActivity(nextActivity);
    }

    public void callUser(){
        Intent previousIntent = getIntent();
        User user = (User) previousIntent.getSerializableExtra("USER");
        activityUser = user;
    }
}