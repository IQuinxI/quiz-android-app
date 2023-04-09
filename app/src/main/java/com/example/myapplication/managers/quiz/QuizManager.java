package com.example.myapplication.managers.quiz;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.managers.db.FireBaseManager;

public class QuizManager extends View {
    private final RadioButton radioButtonYes;
    private final RadioButton radioButtonNo;
    private boolean yesIsCorrect = false;
    private boolean noIsCorrect = false;

    private boolean  clicked = false;

    // The QuizManager Class Manages all the quiz# classes
    public QuizManager(Context context, RadioButton yes, RadioButton no, boolean yesIsCorrect, boolean noIsCorrect) {
        super(context);
        radioButtonYes = yes;
        radioButtonNo = no;
        this.yesIsCorrect = yesIsCorrect;
        this.noIsCorrect = noIsCorrect;

        onClickManager();
    }

    // The onClickManager, manages the radio onClick events
    private void onClickManager() {
        radioItemNoOnClick();
        radioItemYesOnClick();
//        nextQuiz();
    }

    private void radioItemYesOnClick() {
        // if the 'yes' radio button is clicked
        radioButtonYes.setOnClickListener(view -> {
            // checks if yes is a correct answer, adds to the score if yes substracts if no
            if(yesIsCorrect) ScoreManager.addScore();
            else ScoreManager.susbstractScore();
//            Log.d("Score", "radioItemYesOnClick: "+ ScoreManager.getScore());
//            Log.d("tmp_Score", "radioItemYesOnClick: "+ ScoreManager.choseCorrectly());
            // flags the buttons as clicked
            clicked = true;
        });
    }

    private void radioItemNoOnClick() {
        radioButtonNo.setOnClickListener(view -> {
            if(noIsCorrect) ScoreManager.addScore();
            else ScoreManager.susbstractScore();
//            Log.d("Score", "radioItemYesOnClick: "+ ScoreManager.getScore());
//            Log.d("tmp_Score", "radioItemYesOnClick: "+ ScoreManager.choseCorrectly());
            clicked = true;
        });
    }

    //checks if the clicked flag has been set, if false show Toast message
    private boolean itemIsChosen() {
        if(!clicked) Toast.makeText(getContext(), "No option has been selected", Toast.LENGTH_SHORT).show();
        return clicked;
    }


    public void nextButton(Button btnNext, Class newActivity) {
        // Increments the question Number and moves to the next question
        btnNext.setOnClickListener(view -> {
            FireBaseManager fbManager = new FireBaseManager(getContext());
            if(!itemIsChosen()) return;
            fbManager.updateQuizAnswers(ScoreManager.getQuestionsNumber(), ScoreManager.choseCorrectly());
            ScoreManager.addQuestionNumber();
            getContext().startActivity(new Intent(getContext(), newActivity));
            Log.d("Question Number", "nextButton: "+ ScoreManager.getQuestionsNumber());
        });
    }



}
