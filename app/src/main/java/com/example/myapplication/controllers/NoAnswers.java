package com.example.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;

public class NoAnswers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_answers);

        findViewById(R.id.btnMainMenu)
                .setOnClickListener(view -> {
                    startActivity(new Intent(this, Hub.class));
                });
    }
}