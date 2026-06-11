package com.example.saladefitness.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "measurements")
public class Measurement {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String date;     // format "2026-06-11"
    public double weight;   // greutate corporala (kg)
    public double waist;    // talie (cm), 0 daca nu e completata
    public double chest;    // piept (cm), 0 daca nu e completata
    public double arm;      // brat (cm), 0 daca nu e completata

    public Measurement(String date, double weight, double waist, double chest, double arm) {
        this.date = date;
        this.weight = weight;
        this.waist = waist;
        this.chest = chest;
        this.arm = arm;
    }
}