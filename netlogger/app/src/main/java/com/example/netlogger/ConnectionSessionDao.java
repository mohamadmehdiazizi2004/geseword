package com.example.netlogger;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ConnectionSessionDao {

    @Insert
    long insert(ConnectionSession session);

    @Query("UPDATE connection_sessions SET endMillis=:endMillis, durationSec=:durationSec WHERE id=:id")
    void closeSession(long id, long endMillis, long durationSec);

    @Query("SELECT * FROM connection_sessions ORDER BY startMillis DESC")
    LiveData<List<ConnectionSession>> getAll();

    @Query("SELECT * FROM connection_sessions ORDER BY startMillis DESC LIMIT 1")
    ConnectionSession getLastSync(); // فقط برای Thread پس‌زمینه
}
