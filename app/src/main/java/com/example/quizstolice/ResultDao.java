package com.example.quizstolice;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface ResultDao {

    // Wstawienie nowego wyniku
    @Insert
    void insert(Result result);

    // Pobranie wszystkich wyników, posortowanych malejąco po ID
    @Query("SELECT * FROM results_table ORDER BY timestamp DESC")
    List<Result> getAllResults();

    // Usunięcie wszystkich wyników z tabeli
    @Query("DELETE FROM results_table")
    void deleteAllResults();

    @Delete
    void deleteResult(Result result);

    @Query("SELECT * FROM results_table WHERE region = :continent ORDER BY timestamp DESC")
    List<Result> getResultsByContinent(String continent);

    @Query("SELECT * FROM results_table WHERE region = :region ORDER BY id DESC")
    List<Result> getResultsByRegion(String region);

    @Query("SELECT * FROM results_table WHERE region = 'Cały Świat' ORDER BY timestamp DESC")
    List<Result> getWorldResults();


}
