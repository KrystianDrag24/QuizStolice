package com.example.quizstolice;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Definicja tabeli
@Entity(tableName = "results_table")
public class Result {

    // Klucz główny
    @PrimaryKey(autoGenerate = true)
    private int id;

    // Kolumny tabeli
    private String region;
    private int correctAnswers;
    private int totalQuestions;
    private long timestamp; // Pole do przechowywania daty i czasu

    // Konstruktor
    public Result(String region, int correctAnswers, int totalQuestions, long timestamp) {
        this.region = region;
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
        this.timestamp = timestamp;
    }

    // Gettery i settery
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
