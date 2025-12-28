package com.example.content;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CredentialEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CredentialDao credentialDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "app_db"
                            )
                            .allowMainThreadQueries() // برای پروژه ساده/دانشجویی
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
