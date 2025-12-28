package com.example.soundplayer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "songs")
public class SongEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String artist;

    // برای فایل داخل res/raw
    public int audioResId;

    // برای عکس داخل drawable
    public int coverResId;

    public SongEntity(String title, String artist, int audioResId, int coverResId) {
        this.title = title;
        this.artist = artist;
        this.audioResId = audioResId;
        this.coverResId = coverResId;
    }
}
