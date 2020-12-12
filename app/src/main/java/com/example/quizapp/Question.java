package com.example.quizapp;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Question implements Serializable {
    public String category;
    public String difficulty;
    private String question;
    private String correctAnswer;
    private String[] wrongAnswer;

    public Question(JSONObject jsonObject) throws JSONException {
        category = jsonObject.getString("category");
        difficulty = jsonObject.getString("difficulty");
        question = jsonObject.getString(  "question");
        correctAnswer = jsonObject.getString("correct_answer");
        wrongAnswer = getStringArray(jsonObject.getJSONArray("incorrect_answers"));
    }

    private String[] getStringArray(JSONArray jsonArray) throws JSONException {
        Gson gson = new Gson();
        String[] strings = gson.fromJson(String.valueOf(jsonArray), String[].class);
        return strings;
    }

    public String[] getAllAnswers(){
        String[] allAnswers = new String[4];
        setCorrectAnsAtRandom(allAnswers);
        return allAnswers;
    }

    private void setCorrectAnsAtRandom(String [] answers){
        int randomBtw0and4 = (int) (Math.random() * 4);
        for(int i=0, j=0; i < 4; i++, j++) {
            if(i == randomBtw0and4) {
                answers[randomBtw0and4] = correctAnswer;
                j--;
            }
            else
                answers[i] = wrongAnswer[j];
        }
    }

    public Boolean checkAnswer(String answer){
        if(answer.equals(correctAnswer) )
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    public String getQuestion() {
        return question;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty(){
        return difficulty;
    }
}
