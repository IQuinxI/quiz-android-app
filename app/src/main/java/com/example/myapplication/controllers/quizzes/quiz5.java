package com.example.myapplication.controllers.quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.myapplication.controllers.Hub;
import com.example.myapplication.managers.quiz.QuizManager;
import com.example.myapplication.R;
import com.example.myapplication.controllers.Results;

public class quiz5 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz5);
        RadioButton radioItemYes = findViewById(R.id.radioItemYes);
        RadioButton radioItemNo = findViewById(R.id.radioItemNo);
        Button btnNext = findViewById(R.id.btnNext);
        boolean isReview = getIntent().getBooleanExtra("isReview", false);
        QuizManager quizManager =  new QuizManager(quiz5.this, radioItemYes, radioItemNo, false, true, isReview);
        if(!isReview)
            quizManager.nextButton(btnNext, Results.class);
        else {
            btnNext.setText("Main menu");
            btnNext.setOnClickListener(view -> {
                startActivity(new Intent(this, Hub.class));
            });
        }
    }
}