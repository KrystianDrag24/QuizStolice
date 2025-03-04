package com.example.quizstolice;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Definicja tabeli dla pytań
@Entity(tableName = "questions_table")
public class Question {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String country;  // Nazwa państwa
    private String capital;  // Stolica
    private String region;   // Region
    private String imageName; // Nazwa pliku flagi

    // Konstruktor
    public Question(String country, String capital, String region, String imageName) {
        this.country = country;
        this.capital = capital;
        this.region = region;
        this.imageName = imageName;
    }

    // Gettery i Settery
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}





