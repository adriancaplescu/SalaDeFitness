package com.example.saladefitness.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_entries")
public class ExerciseEntry {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String exerciseName;  // ex: "Impins la piept"
    public int sets;             // numar de serii
    public int reps;             // repetari per serie
    public double weight;        // greutate in kg
    public String date;          // format "2026-06-11"

    public ExerciseEntry(String exerciseName, int sets, int reps, double weight, String date) {
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.date = date;
    }
}