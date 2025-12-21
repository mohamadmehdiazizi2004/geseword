package com.example.guessword;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "words")
public class WordEntity {

    @PrimaryKey
    public int id;

    public String text;      // کلمه
    public String hint;      // راهنما
    public String category;  // geography
    public boolean blocked;  // مسدود دائمی

    public WordEntity(int id, String text, String hint, String category, boolean blocked) {
        this.id = id;
        this.text = text;
        this.hint = hint;
        this.category = category;
        this.blocked = blocked;
    }
}
