package com.example.quizstolice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private List<Result> results;
    private final OnDeleteClickListener deleteClickListener;

    // Interfejs do obsługi kliknięcia na przycisk "Usuń"
    public interface OnDeleteClickListener {
        void onDeleteClick(Result result);
    }

    public ResultAdapter(List<Result> results, OnDeleteClickListener deleteClickListener) {
        this.results = results;
        this.deleteClickListener = deleteClickListener;
    }

    public void updateResults(List<Result> newResults) {
        this.results = newResults;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        Result result = results.get(position);

        // Ustaw dane wyniku
        holder.regionTextView.setText("Region: " + result.getRegion());
        holder.scoreTextView.setText("Wynik: " + result.getCorrectAnswers() + "/" + result.getTotalQuestions());
        holder.percentageTextView.setText("Procent: " +
                (int) ((result.getCorrectAnswers() * 100.0) / result.getTotalQuestions()) + "%");

        // Formatowanie daty
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        String dateString = sdf.format(result.getTimestamp());
        holder.dateTextView.setText("Data: " + dateString);

        // Obsługa przycisku "Usuń"
        holder.deleteButton.setOnClickListener(v -> {
            deleteClickListener.onDeleteClick(result);
        });
    }

    @Override
    public int getItemCount() {
        return results != null ? results.size() : 0;
    }

    public static class ResultViewHolder extends RecyclerView.ViewHolder {
        private final TextView regionTextView;
        private final TextView scoreTextView;
        private final TextView percentageTextView;
        private final TextView dateTextView;
        private final Button deleteButton;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            regionTextView = itemView.findViewById(R.id.text_view_region);
            scoreTextView = itemView.findViewById(R.id.text_view_score);
            percentageTextView = itemView.findViewById(R.id.text_view_percentage);
            dateTextView = itemView.findViewById(R.id.text_view_date);
            deleteButton = itemView.findViewById(R.id.btn_delete);
        }
    }
}
