package com.example.myapplication.quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.myapplication.managers.quiz.QuizManager;
import com.example.myapplication.R;

public class quiz3 extends AppCompatActivity {

    RadioButton radioItemYes;
    RadioButton radioItemNo;

    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);
        radioItemYes = findViewById(R.id.radioItemYes);
        radioItemNo = findViewById(R.id.radioItemNo);
        btnNext = findViewById(R.id.btnNext);

        QuizManager quizManager = new QuizManager(quiz3.this, radioItemYes, radioItemNo, true,false);
        quizManager.nextButton(btnNext, quiz4.class);

    }
}