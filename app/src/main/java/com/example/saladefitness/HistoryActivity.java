package com.example.saladefitness;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saladefitness.data.AppDatabase;
import com.example.saladefitness.ui.ExerciseAdapter;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        AppDatabase db = AppDatabase.getInstance(this);

        RecyclerView recycler = findViewById(R.id.recyclerHistory);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        ExerciseAdapter adapter = new ExerciseAdapter();
        adapter.setShowDate(true);
        recycler.setAdapter(adapter);

        adapter.setEntries(db.exerciseDao().getAllEntries());
    }
}