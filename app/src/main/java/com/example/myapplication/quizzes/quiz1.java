package com.example.myapplication.quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.myapplication.managers.quiz.ScoreManager;
import com.example.myapplication.managers.quiz.QuizManager;
import com.example.myapplication.R;

public class quiz1 extends AppCompatActivity {

    RadioButton radioItemYes;
    RadioButton radioItemNo;

    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        radioItemYes = findViewById(R.id.radioItemYes);
        radioItemNo = findViewById(R.id.radioItemNo);
        btnNext = findViewById(R.id.btnNext);

        QuizManager quizManager = new QuizManager(quiz1.this, radioItemYes, radioItemNo, false, true);
        quizManager.nextButton(btnNext, quiz2.class);

        ScoreManager.setScore(0);
        ScoreManager.setQuestionsNumber(0);

    }



}