package com.example.quizstolice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class RegionSelectionActivity extends AppCompatActivity {

    private boolean isQuizMode = false;
    private boolean isTimerEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_selection);

        // Powiązanie widoków
        Button btnEurope = findViewById(R.id.btn_europe);
        Button btnAsia = findViewById(R.id.btn_asia);
        Button btnAfrica = findViewById(R.id.btn_africa);
        Button btnAustralia = findViewById(R.id.btn_australia);
        Button btnNorthAmerica = findViewById(R.id.btn_north_america);
        Button btnSouthAmerica = findViewById(R.id.btn_south_america);
        Button btnAll = findViewById(R.id.btn_all);
        Button btnBackToMenu = findViewById(R.id.btn_back_to_menu);

        // Sprawdź, jaki tryb został przekazany z MainActivity
        Intent incomingIntent = getIntent();
        String mode = incomingIntent.getStringExtra("mode");
        isQuizMode = "quiz".equals(mode); // Tryb Quiz



        // Obsługa przycisków wyboru kontynentu
        btnEurope.setOnClickListener(v -> startNextActivity("Europa"));
        btnAsia.setOnClickListener(v -> startNextActivity("Azja"));
        btnAfrica.setOnClickListener(v -> startNextActivity("Afryka"));
        btnAustralia.setOnClickListener(v -> startNextActivity("Australia i Oceania"));
        btnNorthAmerica.setOnClickListener(v -> startNextActivity("Ameryka Północna"));
        btnSouthAmerica.setOnClickListener(v -> startNextActivity("Ameryka Południowa"));
        btnAll.setOnClickListener(v -> startNextActivity("Wszystkie"));

        // Powrót do menu głównego
        btnBackToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(RegionSelectionActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void startNextActivity(String region) {
        Intent intent;
        if (isQuizMode) {
            intent = new Intent(RegionSelectionActivity.this, QuizActivity.class);
            intent.putExtra("timerEnabled", isTimerEnabled); // Przekazanie stanu timera
        } else {
            intent = new Intent(RegionSelectionActivity.this, LearningActivity.class);
        }
        intent.putExtra("region", region); // Przekazanie wybranego kontynentu
        startActivity(intent);
    }
}
