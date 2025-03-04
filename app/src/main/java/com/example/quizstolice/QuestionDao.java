package com.example.quizstolice;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void insert(Question question);

    @Query("SELECT * FROM questions_table WHERE region = :region")
    List<Question> getQuestionsByRegion(String region);

    @Query("SELECT * FROM questions_table")
    List<Question> getAllQuestions();
}

