package com.example.saladefitness.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Insert
    void insert(ExerciseEntry entry);

    @Update
    void update(ExerciseEntry entry);

    @Delete
    void delete(ExerciseEntry entry);

    @Query("SELECT * FROM exercise_entries WHERE date = :date ORDER BY id DESC")
    List<ExerciseEntry> getEntriesForDate(String date);

    @Query("SELECT * FROM exercise_entries ORDER BY date DESC, id DESC")
    List<ExerciseEntry> getAllEntries();
}