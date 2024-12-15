package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CountDao {
    @Insert
    long insert(CountModel countModel);

    @Query("SELECT * FROM CountModel WHERE id = :id")
    CountModel getCountById(int id);

    @Query("UPDATE CountModel SET count = :count WHERE id = :id")
    int updateCount(int id, int count);
}