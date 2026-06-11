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
import com.example.saladefitness.data.ExerciseEntry;
import com.example.saladefitness.ui.ExerciseAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private ExerciseAdapter adapter;
    private TextView textEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(this);

        textEmpty = findViewById(R.id.textEmpty);

        // Lista de exercitii
        RecyclerView recycler = findViewById(R.id.recyclerExercises);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExerciseAdapter();
        recycler.setAdapter(adapter);

        // Apasare lunga pe un exercitiu -> dialog de stergere
        adapter.setOnEntryLongClickListener(entry -> {
            new AlertDialog.Builder(this)
                    .setTitle("Stergere")
                    .setMessage("Stergi \"" + entry.exerciseName + "\"?")
                    .setPositiveButton("Da", (dialog, which) -> {
                        db.exerciseDao().delete(entry);
                        loadTodayEntries();
                    })
                    .setNegativeButton("Nu", null)
                    .show();
        });

        // Butonul + -> dialog de adaugare
        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(v -> showAddDialog());

        loadTodayEntries();
    }

    private String getTodayDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
    }

    private void loadTodayEntries() {
        List<ExerciseEntry> entries = db.exerciseDao().getEntriesForDate(getTodayDate());
        adapter.setEntries(entries);
        textEmpty.setVisibility(entries.isEmpty() ? View.VISIBLE : View.GONE);
    }

    private void showAddDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_exercise, null);
        EditText editName = dialogView.findViewById(R.id.editName);
        EditText editSets = dialogView.findViewById(R.id.editSets);
        EditText editReps = dialogView.findViewById(R.id.editReps);
        EditText editWeight = dialogView.findViewById(R.id.editWeight);

        new AlertDialog.Builder(this)
                .setTitle("Adauga exercitiu")
                .setView(dialogView)
                .setPositiveButton("Salveaza", (dialog, which) -> {
                    String name = editName.getText().toString().trim();
                    String setsStr = editSets.getText().toString().trim();
                    String repsStr = editReps.getText().toString().trim();
                    String weightStr = editWeight.getText().toString().trim();

                    if (name.isEmpty() || setsStr.isEmpty() || repsStr.isEmpty()) {
                        Toast.makeText(this, "Completeaza numele, seriile si repetarile",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int sets = Integer.parseInt(setsStr);
                    int reps = Integer.parseInt(repsStr);
                    double weight = weightStr.isEmpty() ? 0 : Double.parseDouble(weightStr);

                    ExerciseEntry entry = new ExerciseEntry(name, sets, reps, weight, getTodayDate());
                    db.exerciseDao().insert(entry);
                    loadTodayEntries();
                })
                .setNegativeButton("Anuleaza", null)
                .show();
    }
}