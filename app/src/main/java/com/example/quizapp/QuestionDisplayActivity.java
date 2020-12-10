package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionDisplayActivity extends AppCompatActivity {
    Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);

        getQuestion();
        displayQuestion();
    }

    public void onClickedSubmit(View view){
        if(radioGroupisChecked())
            submitAnswer();
    }

    boolean radioGroupisChecked(){
        RadioGroup radioGroup = findViewById(R.id.options);
        if(radioGroup.getCheckedRadioButtonId() == -1)
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

    public void submitAnswer(){

        int score = getScore();
        Intent nextActivity = new Intent(this, HandleQuizActivity.class);
        prepareActivity(nextActivity);
        startActivity(nextActivity);
    }

    public void prepareActivity(Intent intent){
        int score = getScore();
        intent.putExtra("SCORE", score);
        intent.putExtra("NAME", "Question");
    }

    public int getScore(){
        String checkedOption = getCheckedOption();
        if(question.checkAnswer(checkedOption))
            return 100;
        else
            return 0;
    }

    public String getCheckedOption(){
        RadioGroup radioGroup = findViewById(R.id.options);
        int checkedId = radioGroup.getCheckedRadioButtonId();
        RadioButton checkedRadioButton = findViewById(checkedId);
        String checkedOption = (String) checkedRadioButton.getText();
        return checkedOption;
    }

    public void getQuestion(){
        Intent previousActivity = getIntent();
        question = (Question) previousActivity.getSerializableExtra("QUESTION");
    }

    public void displayQuestion(){
        setQuestionText();
        setOptionText();
    }

    private void setQuestionText() {
        TextView textView = findViewById(R.id.QuestionDisplayTextView);
        String questionString = buildQuestionString();
        textView.setText(questionString);
    }

    private String buildQuestionString(){
        String questionText = question.getQuestion();
        return questionText;
    }

    private void setOptionText(){
        String[] options = question.getAllAnswers();
        for(int i=0; i<4; i++)
            getAndSetTextToWidgets(options[i], i);
    }

    private void getAndSetTextToWidgets(String optionText, int index){
        RadioGroup radioGroup = findViewById(R.id.options);
        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(index);
        radioButton.setText(optionText);
    }
}