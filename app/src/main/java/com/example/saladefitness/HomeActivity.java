package com.example.saladefitness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView textDateTime = findViewById(R.id.textDateTime);
        SimpleDateFormat format = new SimpleDateFormat(
                "EEEE, d MMMM yyyy • HH:mm", new Locale("ro"));
        textDateTime.setText(format.format(new Date()));

        findViewById(R.id.cardToday).setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class)));

        findViewById(R.id.cardHistory).setOnClickListener(v ->
                startActivity(new Intent(this, HistoryActivity.class)));


        findViewById(R.id.cardCalendar).setOnClickListener(v ->
                Toast.makeText(this, "In curand!", Toast.LENGTH_SHORT).show());

        findViewById(R.id.cardMeasurements).setOnClickListener(v ->
                startActivity(new Intent(this, MeasurementsActivity.class)));


        FloatingActionButton fab = findViewById(R.id.fabAddHome);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("openAddDialog", true);
            startActivity(intent);
        });
    }
}