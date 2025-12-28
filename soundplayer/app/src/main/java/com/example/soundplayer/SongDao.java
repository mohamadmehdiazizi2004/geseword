package com.example.soundplayer;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SongDao {

    @Query("SELECT * FROM songs ORDER BY id ASC")
    List<SongEntity> getAll();

    @Insert
    void insertAll(List<SongEntity> songs);

    @Query("SELECT COUNT(*) FROM songs")
    int count();
}
