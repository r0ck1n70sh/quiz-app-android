package com.example.quizapp;


import org.json.JSONException;
import org.json.JSONObject;

public class Question {
    public String category;
    public String difficulty;
    private String question;
    private String correctAnswer;
    private String[] wrongAnswer;

    public Question questionFromJSON(JSONObject jsonObject) throws JSONException {
        Question q = new Question();
        q.category = jsonObject.getString("category");
        q.difficulty = jsonObject.getString("difficulty");
        q.question = jsonObject.getString(  "question");
        q.correctAnswer = jsonObject.getString("correct_answer");
        q.wrongAnswer = (String[]) jsonObject.get("wrong_answer");

        return q;
    }

    public String[] getAllAnswers(){
        String[] allAnswers = new String[4];
        setCorrectAnsAtRandom(allAnswers);
        return allAnswers;
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

    private void setCorrectAnsAtRandom(String [] answers){
        int randomBtw1and4 = (int) (Math.random() * 4) + 1;
        for(int i=0, j=0; i < 4; i++, j++) {
            if(i == randomBtw1and4) {
                answers[randomBtw1and4] = correctAnswer;
                j--;
            }
            else
                answers[i] = wrongAnswer[j];
        }
    }

}
