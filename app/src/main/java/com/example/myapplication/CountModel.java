package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CountModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int count;

    public CountModel(int count) {
        this.count = count;
    }
}