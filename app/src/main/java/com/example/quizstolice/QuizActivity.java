package com.example.quizstolice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private boolean isTimerEnabled = false;
    private int timeElapsed = 0;
    private TextView timerTextView;
    private Handler handler;
    private Runnable timerRunnable;

    private List<Question> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;

    private TextView countryName, counterText, correctCounterText, resultText;
    private ImageView flagImage;
    private EditText capitalInput;
    private Button checkButton, nextButton, menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Powiązanie widoków

        countryName = findViewById(R.id.country_name);
        counterText = findViewById(R.id.counter_text);
        correctCounterText = findViewById(R.id.correct_counter_text);
        resultText = findViewById(R.id.result_text);
        flagImage = findViewById(R.id.flag_image);
        capitalInput = findViewById(R.id.capital_input);
        checkButton = findViewById(R.id.btn_check);
        nextButton = findViewById(R.id.btn_next);
        menuButton = findViewById(R.id.btn_menu);

        // Pobierz region i stan timera z poprzedniej aktywności
        String region = getIntent().getStringExtra("region");
        isTimerEnabled = getIntent().getBooleanExtra("timerEnabled", false);

        // Wczytaj pytania z bazy danych
        loadQuestions(region);


        // Obsługa przycisku "Sprawdź"
        checkButton.setOnClickListener(v -> {
            checkAnswer(); // Sprawdź odpowiedź użytkownika
        });

        // Obsługa przycisku "Dalej"
        nextButton.setOnClickListener(v -> {
            if (currentQuestionIndex < questions.size() - 1) {
                currentQuestionIndex++; // Przejdź do kolejnego pytania
                displayCurrentQuestion(); // Wyświetl nowe pytanie
                resetInput(); // Wyczyść pole do wprowadzania odpowiedzi
            } else {
                showResults(); // Pokaż wyniki, jeśli to było ostatnie pytanie
            }
        });

        // Obsługa przycisku "Powrót do menu"
        menuButton.setOnClickListener(v -> {
            // Przejdź do głównego menu
            Intent intent = new Intent(QuizActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }


    private void loadQuestions(String region) {
        QuizDatabase db = QuizDatabase.getInstance(this);

        new Thread(() -> {
            List<Question> fetchedQuestions;
            if ("Cały Świat".equals(region)) {
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

        // Ustaw dane pytania
        countryName.setText(currentQuestion.getCountry());
        counterText.setText((currentQuestionIndex + 1) + "/" + questions.size());
        correctCounterText.setText("Poprawne: " + correctAnswers);
        resultText.setText("");

        // Wyświetl flagę
        int imageResId = getResources().getIdentifier(currentQuestion.getImageName(), "drawable", getPackageName());
        flagImage.setImageResource(imageResId);
    }

    private void checkAnswer() {
        if (questions.isEmpty() || currentQuestionIndex >= questions.size()) {
            return;
        }

        String userAnswer = capitalInput.getText().toString().trim();
        String correctAnswer = questions.get(currentQuestionIndex).getCapital();

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            correctAnswers++;
            correctCounterText.setText("Poprawne: " + correctAnswers);
            resultText.setText("Poprawna odpowiedź!");
            resultText.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            resultText.setText("Błędna odpowiedź! Poprawna: " + correctAnswer);
            resultText.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private void resetInput() {
        capitalInput.setText("");
        resultText.setText("");
    }

    private void showResults() {
        String region = getIntent().getStringExtra("region");
        saveResult(region, correctAnswers, questions.size()); // Zapisanie wyniku

        String message = "Koniec quizu! Poprawne odpowiedzi: " + correctAnswers + "/" + questions.size();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(QuizActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(timerRunnable); // Zatrzymaj timer przy zamknięciu aktywności
        }
    }
    private void saveResult(String region, int correctAnswers, int totalQuestions) {
        QuizDatabase db = QuizDatabase.getInstance(this);
        Result result = new Result(region, correctAnswers, totalQuestions, System.currentTimeMillis());


        new Thread(() -> {
            db.resultDao().insert(result);
            runOnUiThread(() -> {
                Log.d("QuizActivity", "Wynik zapisany: " + region + ", " + correctAnswers + "/" + totalQuestions);

            });
        }).start();
    }


}
