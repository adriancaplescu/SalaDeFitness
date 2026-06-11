package com.example.saladefitness.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saladefitness.R;
import com.example.saladefitness.data.ExerciseEntry;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    // ===== Callback pentru apasare lunga =====
    public interface OnEntryLongClickListener {
        void onEntryLongClick(ExerciseEntry entry);
    }

    private OnEntryLongClickListener longClickListener;

    public void setOnEntryLongClickListener(OnEntryLongClickListener listener) {
        this.longClickListener = listener;
    }

    // ===== Datele listei =====
    private List<ExerciseEntry> entries = new ArrayList<>();

    private boolean showDate = false;

    public void setShowDate(boolean showDate) {
        this.showDate = showDate;
    }

    public void setEntries(List<ExerciseEntry> newEntries) {
        this.entries = newEntries;
        notifyDataSetChanged();
    }

    // ===== Metodele RecyclerView =====
    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        ExerciseEntry entry = entries.get(position);
        holder.textName.setText(entry.exerciseName);
        holder.textDetails.setText(entry.sets + " serii x " + entry.reps
                + " repetari @ " + entry.weight + " kg");

        if (showDate) {
            holder.textDate.setVisibility(View.VISIBLE);
            holder.textDate.setText(entry.date);
        }

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onEntryLongClick(entry);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    // ===== ViewHolder: tine referintele catre elementele unui rand =====
    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textDetails;
        TextView textDate;

        ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textExerciseName);
            textDetails = itemView.findViewById(R.id.textDetails);
            textDate = itemView.findViewById(R.id.textDate);
        }
    }
}