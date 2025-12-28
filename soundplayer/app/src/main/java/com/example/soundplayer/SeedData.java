package com.example.soundplayer;

import java.util.ArrayList;
import java.util.List;

public class SeedData {

    public static void seedIfNeeded(AppDatabase db) {
        if (db.songDao().count() > 0) return;

        List<SongEntity> songs = new ArrayList<>();

        songs.add(new SongEntity("Track 01", "Artist A", R.raw.track_01, R.drawable.cover_01));
        db.songDao().insertAll(songs);
    }
}
