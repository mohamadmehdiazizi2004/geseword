package com.example.quesation;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class QuizQuestion {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String question;
    public String optionA;
    public String optionB;
    public String optionC;
    public String optionD;

    // 0..3
    public int correctIndex;

    public QuizQuestion(String question, String optionA, String optionB, String optionC, String optionD, int correctIndex) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctIndex = correctIndex;
    }
}
