package com.example.myapplication.managers.quiz;

import android.util.Log;

public class ScoreManager {
    private static int score = 0;
    private static int questionsNumber = 0;
    private static boolean added = false, substracted = true;

    private static int tmp_score = 0;
    public static int getScore() {return score;}

    public static void setScore(int score) {
        ScoreManager.score = score;}



    public static void setQuestionsNumber(int questionsNumber) {
        ScoreManager.questionsNumber = questionsNumber;}
    public static boolean choseCorrectly() {
        Log.d("Correct_Answer", "choseCorrectly: "+tmp_score);
        return tmp_score != 0;
    }
    public static int getQuestionsNumber() {return questionsNumber; }

    //when moving to the nextActivity, the static values are reinitialized
    public static void addQuestionNumber() {
        questionsNumber++;
        score += tmp_score;
        added = false;
        substracted = false;
        tmp_score = 0;
    }

    // The tmp_score keeps track of the current activity, while score manages the score for the whole quiz
    // the added and subsrtacted flag are theere to limit how many the incrementation & decrmentation of tmp_score to 1 for each activity
    // else we would be able to add to the score for how many clicks have been pressed
    public  static void addScore() {
        if(added) return;
        tmp_score++;
        added = true;
        substracted = false;
    }

    public static void susbstractScore() {
        if(tmp_score == 0 || substracted) return;
        tmp_score--;
        substracted = true;
        added = false;

    }


}
