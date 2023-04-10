package com.example.myapplication.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btnSignIn;
    EditText inpEmail, inpPassword;
    TextView linkRegister;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.btnSignIn);
        inpEmail = findViewById(R.id.inpEmail);
        inpPassword = findViewById(R.id.inpPassword);
        linkRegister = findViewById(R.id.linkRegister);

        inpEmail.setText("ab@gmail.com");
        inpPassword.setText("yassine");
        SignInOnClick();
        LinkOnClick();
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private boolean InputChecker() {
        String email = inpEmail.getText().toString().trim();
        String password = inpPassword.getText().toString().trim();
        boolean error = false;
        if(email.equals("")) {
            findViewById(R.id.emailError).setVisibility(View.VISIBLE);
            error = true;
        }
        if (password.equals("")) {
            findViewById(R.id.passwordError).setVisibility(View.VISIBLE);
            error = true;
        }
        return error;
    }
    private void SignInOnClick() {
        btnSignIn.setOnClickListener(view -> {
            String email = inpEmail.getText().toString().trim();
            String password = inpPassword.getText().toString().trim();

            if(InputChecker()) return;

            findViewById(R.id.emailError).setVisibility(View.GONE);
            findViewById(R.id.passwordError).setVisibility(View.GONE);

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                                startActivity(new Intent(MainActivity.this, Hub.class));
                            else
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private void LinkOnClick() {
        linkRegister.setOnClickListener(view -> {
            startActivity(new Intent(this, SignUp.class));
        });
    }
}