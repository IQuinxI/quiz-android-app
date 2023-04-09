package com.example.myapplication.quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.myapplication.managers.db.FireBaseManager;
import com.example.myapplication.managers.quiz.QuizManager;
import com.example.myapplication.R;
import com.example.myapplication.controllers.Results;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class quiz5 extends AppCompatActivity {

    private RadioButton radioItemYes;
    private RadioButton radioItemNo;
    private  Button btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz5);
        radioItemYes = findViewById(R.id.radioItemYes);
        radioItemNo = findViewById(R.id.radioItemNo);
        btnNext = findViewById(R.id.btnNext);

        QuizManager quizManager =  new QuizManager(quiz5.this, radioItemYes, radioItemNo, false, true);
        quizManager.nextButton(btnNext, Results.class);




//        myRef.child("users").child("1").setValue("test");
    }
}