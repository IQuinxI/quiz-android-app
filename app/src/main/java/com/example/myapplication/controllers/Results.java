package com.example.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.managers.db.FireBaseManager;
import com.example.myapplication.managers.quiz.ScoreManager;
import com.example.myapplication.R;
import com.example.myapplication.controllers.quizzes.quiz1;

public class Results extends AppCompatActivity {
    private Button btnTryAgain;
    private Button btnMainMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        btnTryAgain = findViewById(R.id.btnTryAgain);
        btnMainMenu = findViewById(R.id.btnMainMenu);


        scoreCalculation();
        btnTryAgainOnClick();
        btnMainMenuOnClick();
    }

    private void scoreCalculation() {
        int score = (ScoreManager.getScore() * 100) / ScoreManager.getQuestionsNumber();

        FireBaseManager fbManager = new FireBaseManager(Results.this);
//        fbManager.updateLeaderBoard(score);
//        fbManager.getBestScore();

        TextView TVScore = findViewById(R.id.TVScore);
        TVScore.setText(score + "%");
    }
    private void btnTryAgainOnClick() {
        btnTryAgain.setOnClickListener(view -> {
            startActivity(new Intent(Results.this, quiz1.class));
        });
    }

    private void btnMainMenuOnClick() {
        btnMainMenu.setOnClickListener(view -> {
            startActivity(new Intent(Results.this, Hub.class));
        });
    }
    @Override
    public void onBackPressed() {

    }
}