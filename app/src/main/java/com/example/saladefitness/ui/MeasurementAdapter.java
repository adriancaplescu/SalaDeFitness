package com.example.saladefitness.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saladefitness.R;
import com.example.saladefitness.data.Measurement;

import java.util.ArrayList;
import java.util.List;

public class MeasurementAdapter extends RecyclerView.Adapter<MeasurementAdapter.MeasureViewHolder> {

    public interface OnMeasurementLongClickListener {
        void onMeasurementLongClick(Measurement measurement);
    }

    private OnMeasurementLongClickListener longClickListener;

    public void setOnMeasurementLongClickListener(OnMeasurementLongClickListener listener) {
        this.longClickListener = listener;
    }

    private List<Measurement> measurements = new ArrayList<>();

    public void setMeasurements(List<Measurement> newMeasurements) {
        this.measurements = newMeasurements;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MeasureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_measurement, parent, false);
        return new MeasureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeasureViewHolder holder, int position) {
        Measurement m = measurements.get(position);
        holder.textDate.setText(m.date);

        StringBuilder details = new StringBuilder();
        details.append("Greutate: ").append(m.weight).append(" kg");
        if (m.waist > 0) details.append("  •  Talie: ").append(m.waist).append(" cm");
        if (m.chest > 0) details.append("  •  Piept: ").append(m.chest).append(" cm");
        if (m.arm > 0) details.append("  •  Brat: ").append(m.arm).append(" cm");
        holder.textDetails.setText(details.toString());

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onMeasurementLongClick(m);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return measurements.size();
    }

    static class MeasureViewHolder extends RecyclerView.ViewHolder {
        TextView textDate;
        TextView textDetails;

        MeasureViewHolder(@NonNull View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.textMeasureDate);
            textDetails = itemView.findViewById(R.id.textMeasureDetails);
        }
    }
}