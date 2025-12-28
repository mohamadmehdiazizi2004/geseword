package com.example.quesation;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void insertAll(List<QuizQuestion> list);

    @Query("SELECT COUNT(*) FROM questions")
    int count();

    @Query("SELECT * FROM questions ORDER BY RANDOM() LIMIT :limit")
    List<QuizQuestion> getRandomQuestions(int limit);
}
