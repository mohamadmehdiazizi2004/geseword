package com.example.content;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "credentials")
public class CredentialEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String username;
    public String password;

    public long createdAt;

    public CredentialEntity(String username, String password, long createdAt) {
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }
}
