package com.example.myapplication.managers.quiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.myapplication.R;
import com.example.myapplication.managers.db.FireBaseManager;

public class QuizManager extends View {
    private  RadioButton radioButtonYes;
    private  RadioButton radioButtonNo;
    private boolean yesIsCorrect = false;
    private boolean noIsCorrect = false;

    private boolean  clicked = false;

    private boolean isReview = false;


    // The QuizManager Class Manages all the quiz# classes
    public QuizManager(Context context, RadioButton radioButtonYes, RadioButton radioButtonNo, boolean yesIsCorrect, boolean noIsCorrect, boolean isReview) {
        super(context);
        this.radioButtonYes = radioButtonYes;
        this.radioButtonNo = radioButtonNo;
        this.yesIsCorrect = yesIsCorrect;
        this.noIsCorrect = noIsCorrect;
        this.isReview = isReview;



        if(!isReview) onClickManager();
        else {
            radioButtonNo.setEnabled(false);
            radioButtonYes.setEnabled(false);
            fillAnswer();
            onClickManagerReview();
        }
    }




    // The onClickManager, manages the radio onClick events
    private void onClickManager() {
        radioItemNoOnClick();
        radioItemYesOnClick();
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
        if(isReview) nextReview(btnNext, newActivity);
        else nextQuiz(btnNext, newActivity);
    }

    private void nextReview(Button btnNext, Class newActivity) {
        btnNext.setOnClickListener(view -> {
            getContext().startActivity(new Intent(getContext(), newActivity).putExtra("isReview", true));
            ScoreManager.incrementQuestionNumber();
            Log.d("Question_Number", "nextButton: "+ ScoreManager.getQuestionsNumber());

        });
    }

    private void nextQuiz(Button btnNext, Class newActivity) {
        btnNext.setOnClickListener(view -> {
            FireBaseManager fbManager = new FireBaseManager(getContext());
            if(!itemIsChosen()) return;
            fbManager.updateQuizAnswers(ScoreManager.getQuestionsNumber(), ScoreManager.choseCorrectly());
            ScoreManager.nextButtonBehavior();
            getContext().startActivity(new Intent(getContext(), newActivity));
            Log.d("Question_Number", "nextButton: "+ ScoreManager.getQuestionsNumber());
        });
    }
// ============================================= Review Answers ===========================================
    public QuizManager(Context context) {
        super(context);
    }

    private void onClickManagerReview() {

    }
    // Show the user the latest results
    public  void fillAnswer() {
        FireBaseManager fireBaseManager = new FireBaseManager(getContext());
        fireBaseManager.getAnswers(ScoreManager.getQuestionsNumber(), radioButtonYes, radioButtonNo, yesIsCorrect, noIsCorrect);

//        ScoreManager.incrementQuestionNumber();
    }
}
