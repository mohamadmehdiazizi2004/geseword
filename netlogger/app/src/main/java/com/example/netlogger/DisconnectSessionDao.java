package com.example.netlogger;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DisconnectSessionDao {

    @Insert
    long insert(DisconnectSession session);

    @Query("UPDATE disconnect_sessions SET endMillis=:endMillis, durationSec=:durationSec WHERE id=:id")
    void close(long id, long endMillis, long durationSec);

    @Query("SELECT * FROM disconnect_sessions ORDER BY startMillis DESC")
    LiveData<List<DisconnectSession>> getAll();

    @Query("SELECT * FROM disconnect_sessions WHERE endMillis IS NULL ORDER BY startMillis DESC LIMIT 1")
    DisconnectSession getOpenSync(); // فقط در Thread
}
