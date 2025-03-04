package com.example.quizstolice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Powiązanie przełącznika
        Switch darkModeSwitch = findViewById(R.id.dark_mode_switch);


        // Ustaw przełącznik zgodnie z aktualnym trybem
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        darkModeSwitch.setChecked(nightMode == AppCompatDelegate.MODE_NIGHT_YES);

        // Obsługa zmiany trybu
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            recreate(); // Odświeżenie aktywności
        });

        // Powiązanie przycisków z widokiem
        Button btnLearning = findViewById(R.id.btn_learning);
        Button btnQuiz = findViewById(R.id.btn_quiz);
        Button btnResults = findViewById(R.id.btn_results);

        // Obsługa przycisku "Nauka"
        btnLearning.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegionSelectionActivity.class);
            intent.putExtra("mode", "learning"); // Przekazanie trybu: nauka
            startActivity(intent);
        });

        // Obsługa przycisku "Quiz"
        btnQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegionSelectionActivity.class);
            intent.putExtra("mode", "quiz"); // Przekazanie trybu: quiz
            startActivity(intent);
        });

        // Obsługa przycisku "Wyniki"
        btnResults.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
            startActivity(intent);
        });
    }
}
