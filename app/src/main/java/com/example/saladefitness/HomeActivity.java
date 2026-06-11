package com.example.saladefitness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.cardToday).setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class)));

        findViewById(R.id.cardHistory).setOnClickListener(v ->
                startActivity(new Intent(this, HistoryActivity.class)));

        // Ecrane in constructie - le facem in pasii urmatori
        findViewById(R.id.cardCalendar).setOnClickListener(v ->
                Toast.makeText(this, "In curand!", Toast.LENGTH_SHORT).show());

        findViewById(R.id.cardMeasurements).setOnClickListener(v ->
                Toast.makeText(this, "In curand!", Toast.LENGTH_SHORT).show());

        // Butonul + deschide direct ecranul de azi, unde adaugi exercitiul
        FloatingActionButton fab = findViewById(R.id.fabAddHome);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("openAddDialog", true);
            startActivity(intent);
        });
    }
}