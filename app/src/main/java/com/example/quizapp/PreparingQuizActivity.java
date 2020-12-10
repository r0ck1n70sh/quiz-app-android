package com.example.quizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
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
    public QuizInstance quizInstance;
    public User activityUser;

    public Question[] questions;
    private String URL = "https://opentdb.com/api.php?amount=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparing_quiz);
    }

    public void onClickedNext(View view) throws JSONException {
        addJsonRequestInQueue();
    }

    public void addJsonRequestInQueue(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    setResponseToQuestions(response);
                    callUser();
                    buildQuizInstance();
                    sendQuizInstance();
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

    public void callUser(){
        Intent previousIntent = getIntent();
        User user = (User) previousIntent.getSerializableExtra("USER");
        activityUser = user;
    }

    public void buildQuizInstance(){
        quizInstance = new QuizInstance(activityUser);
        quizInstance.setQuestions(questions);
    }

    public void sendQuizInstance() {
        Intent nextActivity = new Intent(this, HandleQuizActivity.class);
        prepareIntent(nextActivity);
        startActivity(nextActivity);
    }

    public void prepareIntent(Intent intent){
        intent.putExtra("NAME", "Preparing");
        intent.putExtra("QUIZ_INSTANCE", quizInstance);
    }
}