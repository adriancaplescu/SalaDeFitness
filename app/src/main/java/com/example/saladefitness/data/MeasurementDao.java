package com.example.saladefitness.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MeasurementDao {

    @Insert
    void insert(Measurement measurement);

    @Delete
    void delete(Measurement measurement);

    @Query("SELECT * FROM measurements ORDER BY date DESC, id DESC")
    List<Measurement> getAll();
}