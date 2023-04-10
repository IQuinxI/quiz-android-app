package com.example.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.controllers.quizzes.quiz1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Hub extends AppCompatActivity {

    CardView cardQuiz;
    CardView cardLB;
    CardView cardTimer;
    CardView cardRLA;
    TextView tvUsername;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        cardQuiz = findViewById(R.id.cardQuiz);
        cardLB = findViewById(R.id.cardLeaderBoard);
        cardRLA = findViewById(R.id.cardReviewScore);
        tvUsername = findViewById(R.id.tvUsername);

        user = FirebaseAuth.getInstance().getCurrentUser();

        quizOnClick();
        leaderBoardOnClick();
        reviewOnClick();
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkSession();
        tvUsername.setText(user.getDisplayName());
    }


    private void checkSession() {
        if(user == null) {
            Toast.makeText(this, "Couldn't initialize user data, try again later!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    @Override
    public void onBackPressed() {

    }

    private void quizOnClick() {
        cardQuiz.setOnClickListener(view -> {
            startActivity(new Intent(this, quiz1.class).putExtra("isReview", false));
        });
    }

    private void reviewOnClick() {
        cardRLA.setOnClickListener(view -> {
            startActivity(new Intent(this, quiz1.class).putExtra("isReview", true));
        });
    }

    private void leaderBoardOnClick() {
        cardLB.setOnClickListener(view -> {
            startActivity(new Intent(this, LeaderBoard.class));
        });
    }



}