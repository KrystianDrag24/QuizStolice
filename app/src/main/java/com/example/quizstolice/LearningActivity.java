package com.example.quizstolice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LearningActivity extends AppCompatActivity {

    private List<Question> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;

    private TextView countryName, capitalName, counterText;
    private ImageView flagImage;
    private Button nextButton, backButton, menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        // Powiązanie widoków
        countryName = findViewById(R.id.country_name);
        capitalName = findViewById(R.id.capital_name);
        counterText = findViewById(R.id.counter_text);
        flagImage = findViewById(R.id.flag_image);
        nextButton = findViewById(R.id.btn_next);
        backButton = findViewById(R.id.btn_back);
        menuButton = findViewById(R.id.btn_menu);

        // Pobierz region z intencji
        String region = getIntent().getStringExtra("region");

        // Załaduj pytania z bazy danych
        loadQuestions(region);

        // Obsługa przycisku "Dalej"
        nextButton.setOnClickListener(v -> {
            if (currentQuestionIndex < questions.size() - 1) {
                currentQuestionIndex++;
                displayCurrentQuestion();
            }
        });

        // Obsługa przycisku "Powrót"
        backButton.setOnClickListener(v -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                displayCurrentQuestion();
            }
        });

        // Obsługa przycisku "Menu"
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(LearningActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void loadQuestions(String region) {
        QuizDatabase db = QuizDatabase.getInstance(this);

        new Thread(() -> {
            List<Question> fetchedQuestions;
            if ("Wszystkie".equals(region)) {
                // Pobierz wszystkie pytania, jeśli region to "Wszystkie"
                fetchedQuestions = db.questionDao().getAllQuestions();
            } else {
                // Pobierz pytania tylko dla wybranego regionu
                fetchedQuestions = db.questionDao().getQuestionsByRegion(region);
            }

            runOnUiThread(() -> {
                questions.addAll(fetchedQuestions);
                if (!questions.isEmpty()) {
                    displayCurrentQuestion();
                } else {
                    Toast.makeText(this, "Brak pytań dla wybranego regionu", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }).start();
    }


    private void displayCurrentQuestion() {
        if (questions.isEmpty() || currentQuestionIndex >= questions.size()) {
            return;
        }

        Question currentQuestion = questions.get(currentQuestionIndex);

        // Ustaw teksty
        countryName.setText(currentQuestion.getCountry());
        capitalName.setText(currentQuestion.getCapital());
        counterText.setText((currentQuestionIndex + 1) + "/" + questions.size());

        // Ustaw flagę
        int imageResId = getResources().getIdentifier(currentQuestion.getImageName(), "drawable", getPackageName());
        flagImage.setImageResource(imageResId);
    }
}
