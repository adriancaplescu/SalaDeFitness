package com.example.saladefitness;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saladefitness.data.AppDatabase;
import com.example.saladefitness.data.Measurement;
import com.example.saladefitness.ui.MeasurementAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MeasurementsActivity extends AppCompatActivity {

    private AppDatabase db;
    private MeasurementAdapter adapter;
    private TextView textEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurements);

        db = AppDatabase.getInstance(this);
        textEmpty = findViewById(R.id.textMeasureEmpty);

        RecyclerView recycler = findViewById(R.id.recyclerMeasurements);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeasurementAdapter();
        recycler.setAdapter(adapter);

        adapter.setOnMeasurementLongClickListener(m -> {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Stergere")
                    .setMessage("Stergi masuratoarea din " + m.date + "?")
                    .setPositiveButton("Da", (d, which) -> {
                        db.measurementDao().delete(m);
                        loadMeasurements();
                    })
                    .setNegativeButton("Nu", null)
                    .create();
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_dialog_rounded);
            dialog.show();
        });

        FloatingActionButton fab = findViewById(R.id.fabAddMeasurement);
        fab.setOnClickListener(v -> showAddDialog());

        loadMeasurements();
    }

    private void loadMeasurements() {
        List<Measurement> list = db.measurementDao().getAll();
        adapter.setMeasurements(list);
        textEmpty.setVisibility(list.isEmpty() ? View.VISIBLE : View.GONE);
    }

    private double parseOrZero(String s) {
        return s.isEmpty() ? 0 : Double.parseDouble(s);
    }

    private void showAddDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_measurement, null);
        EditText editWeight = dialogView.findViewById(R.id.editWeightBody);
        EditText editWaist = dialogView.findViewById(R.id.editWaist);
        EditText editChest = dialogView.findViewById(R.id.editChest);
        EditText editArm = dialogView.findViewById(R.id.editArm);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Adauga masuratoare")
                .setView(dialogView)
                .setPositiveButton("Salveaza", (d, which) -> {
                    String weightStr = editWeight.getText().toString().trim();

                    if (weightStr.isEmpty()) {
                        Toast.makeText(this, "Completeaza greutatea",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String today = new SimpleDateFormat("yyyy-MM-dd", Locale.US)
                            .format(new Date());

                    Measurement m = new Measurement(
                            today,
                            Double.parseDouble(weightStr),
                            parseOrZero(editWaist.getText().toString().trim()),
                            parseOrZero(editChest.getText().toString().trim()),
                            parseOrZero(editArm.getText().toString().trim()));

                    db.measurementDao().insert(m);
                    loadMeasurements();
                })
                .setNegativeButton("Anuleaza", null)
                .create();

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_dialog_rounded);
        dialog.show();
    }
}