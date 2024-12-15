package com.example.myapplication;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CountModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountDao countDao();

    // 添加单例模式的getInstance方法，用于获取数据库实例
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "count_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
