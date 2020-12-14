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
        checkStrings(strings);
        return strings;
    }

    public void checkStrings(String[] strings){
        if (strings.length < 3) {
            String[] newString = new String[3];
            for(int i=0; i<strings.length; i++)
                newString[i] = strings[i];
            strings = newString;
        }
    }

    public String[] getAllAnswers(){
        String[] allAnswers = setCorrectAnsAtRandom();
        return allAnswers;
    }

    private String[] setCorrectAnsAtRandom(){
        int numOptions = wrongAnswer.length + 1;
        String[] answers = new String[numOptions];

        int randomBtw0and4 = (int) (Math.random() * numOptions);
        for(int i=0, j=0; i < numOptions; i++, j++) {
            if(i == randomBtw0and4) {
                answers[randomBtw0and4] = correctAnswer;
                j--;
            }
            else
                answers[i] = wrongAnswer[j];
        }
        return answers;
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
