package com.example.quizstolice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private QuizDatabase db;
    private List<Result> results = new ArrayList<>();
    private String selectedRegion = "Wszystkie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        recyclerView = findViewById(R.id.recycler_view_results); // Najpierw znajdź widok
        recyclerView.setNestedScrollingEnabled(false); // Wyłącz przewijanie
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        db = QuizDatabase.getInstance(this);

        // Spinner do wyboru regionu
        Spinner regionSpinner = findViewById(R.id.region_spinner);
        ArrayAdapter<CharSequence> regionAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.region_filters,
                android.R.layout.simple_spinner_item
        );
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionSpinner.setAdapter(regionAdapter);

        // Obsługa wyboru w Spinnerze regionów
        regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRegion = parent.getItemAtPosition(position).toString();
                loadResults(); // Załaduj wyniki na podstawie wybranego regionu
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedRegion = "Wszystkie"; // Domyślnie ustaw wszystkie
                loadResults();
            }
        });


        // Spinner do wyboru sortowania
        Spinner sortingSpinner = findViewById(R.id.sorting_spinner);
        ArrayAdapter<CharSequence> sortingAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.sorting_options,
                android.R.layout.simple_spinner_item
        );
        sortingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortingSpinner.setAdapter(sortingAdapter);

        // Obsługa wyboru w Spinnerze sortowania
        sortingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    sortResultsChronologically();
                } else if (position == 1) {
                    sortResultsByPercentage();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nie robimy nic
            }
        });

        adapter = new ResultAdapter(results, this::showDeleteConfirmationDialog);
        recyclerView.setAdapter(adapter);

        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> finish());

        loadResults(); // Domyślne ładowanie wyników
    }

    private void loadResults() {
        new Thread(() -> {
            try {
                if ("Wszystkie podejścia".equals(selectedRegion)) {
                    results = db.resultDao().getAllResults(); // Pobierz wszystkie wyniki
                } else if ("Cały Świat".equals(selectedRegion)) {
                    results = db.resultDao().getWorldResults(); // Pobierz wyniki dla quizu "Cały Świat"
                } else {
                    results = db.resultDao().getResultsByRegion(selectedRegion); // Pobierz wyniki dla regionu
                }
                runOnUiThread(() -> {
                    if (results.isEmpty()) {
                        Toast.makeText(this, "Brak wyników dla wybranego regionu", Toast.LENGTH_SHORT).show();
                    }
                    adapter.updateResults(results); // Aktualizuj adapter
                });
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(this, "Błąd wczytywania wyników: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start();
    }





    private void sortResultsChronologically() {
        Collections.sort(results, (r1, r2) -> Long.compare(r2.getTimestamp(), r1.getTimestamp()));
        adapter.updateResults(results);
    }

    private void sortResultsByPercentage() {
        Collections.sort(results, (r1, r2) -> {
            double percentage1 = (r1.getCorrectAnswers() * 100.0) / r1.getTotalQuestions();
            double percentage2 = (r2.getCorrectAnswers() * 100.0) / r2.getTotalQuestions();
            return Double.compare(percentage2, percentage1); // Sortowanie malejące
        });
        adapter.updateResults(results);
    }

    private void showDeleteConfirmationDialog(Result result) {
        new AlertDialog.Builder(this)
                .setTitle("Potwierdzenie usunięcia")
                .setMessage("Czy na pewno chcesz usunąć ten wynik?")
                .setPositiveButton("Usuń", (dialog, which) -> deleteResult(result))
                .setNegativeButton("Anuluj", null)
                .show();
    }

    private void deleteResult(Result result) {
        new Thread(() -> {
            db.resultDao().deleteResult(result);
            runOnUiThread(() -> {
                loadResults(); // Odśwież wyniki po usunięciu
                Toast.makeText(this, "Wynik został usunięty", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }
}
