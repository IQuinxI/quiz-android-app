package com.example.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.managers.db.FireBaseManager;
import com.example.myapplication.managers.quiz.InputsManager;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private Button btnSignUp;
    private EditText etPassword;
    private EditText etRePassword;
    private EditText etUsername;
    private EditText etEmail;

    Map<EditText, TextView> inputs = new HashMap<EditText, TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = findViewById(R.id.btnSignUp);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        InitInput();
        BtnSignUpOnClick();

    }


    private void InitInput() {
        inputs.put(etEmail, (TextView) findViewById(R.id.emailError));
        inputs.put(etPassword,(TextView) findViewById(R.id.passwordError));
        inputs.put(etRePassword,(TextView) findViewById(R.id.rePasswordError));
        inputs.put(etUsername,(TextView) findViewById(R.id.usernameError));
    }


    private boolean similarPassword() {
        if(etPassword.getText().toString().equals(etRePassword.getText().toString())) {
            findViewById(R.id.passwordDiffError).setVisibility(View.GONE);
            return true;
        }
        findViewById(R.id.passwordDiffError).setVisibility(View.VISIBLE);
        return false;
    }

    private void BtnSignUpOnClick() {
        InputsManager inputsManager = new InputsManager();
        btnSignUp.setOnClickListener(view -> {

            findViewById(R.id.passwordDiffError).setVisibility(View.GONE);

            if(inputsManager.CheckIfEmpty(inputs)) return;
            if(!similarPassword()) return;



            FireBaseManager fireBaseManager = new FireBaseManager(SignUp.this);
            fireBaseManager.signUp(etEmail.getText().toString().trim(),
                    etPassword.getText().toString().trim(),
                    etUsername.getText().toString().trim());


        });
    }
}