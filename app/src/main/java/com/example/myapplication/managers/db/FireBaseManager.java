package com.example.myapplication.managers.db;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.controllers.MainActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireBaseManager extends View {


    private final FirebaseDatabase database = FirebaseDatabase.getInstance("https://quizapp-900ea-default-rtdb.europe-west1.firebasedatabase.app/");
    private final DatabaseReference myRef = database.getReference();

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser currentUser = mAuth.getCurrentUser();

    public FireBaseManager(Context context) {
        super(context);
    }

    // Updates scores, keeps track of the highest score
    public void updateLeaderBoard(int score) {
        DatabaseReference bestScore = myRef.child("leaderBoard").child("score").child(currentUser.getUid());
        ValueEventListener scoreListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer currScore = snapshot.getValue(Integer.class);
//                Log.d("Score_Info", "updateLeaderBoard: "+currScore +" | "+score);
                if(currScore == null || currScore < score)
                    myRef.child("leaderBoard").child("score").child(currentUser.getUid()).setValue(score);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        bestScore.addValueEventListener(scoreListener);
    }

    // Saves the user's answers to the Database
    public void updateQuizAnswers(Integer index, boolean answer) {
        myRef.child("leaderBoard").child("quizAnswers").child(currentUser.getUid()).child(index.toString()).setValue(answer);
    }
    // applies profile settings (username)
    private void setProfile(UserProfileChangeRequest profileSet) {
        currentUser.updateProfile(profileSet)
                .addOnCompleteListener(task -> {
                    if(!task.isSuccessful())
                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
    // creates a new user account
    public void signUp(String email, String password, String username) {
        UserProfileChangeRequest profileSet = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) getContext(), task -> {
                    if(task.isSuccessful()) {
                        currentUser = mAuth.getCurrentUser();
                        setProfile(profileSet);
                        Toast.makeText(getContext(), "Account created with success", Toast.LENGTH_SHORT).show();
                        getContext().startActivity(new Intent(getContext(), MainActivity.class));
                    }else {
                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
