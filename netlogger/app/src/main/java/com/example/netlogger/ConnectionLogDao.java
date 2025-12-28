package com.example.netlogger;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ConnectionLogDao {

    @Insert
    long insert(ConnectionLog log);

    @Query("SELECT * FROM connection_logs ORDER BY epochMillis DESC")
    LiveData<List<ConnectionLog>> getAll();
}
