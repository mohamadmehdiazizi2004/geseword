package com.example.content;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CredentialDao {

    @Insert
    void insert(CredentialEntity entity);

    @Query("SELECT * FROM credentials ORDER BY id DESC")
    List<CredentialEntity> getAll();

    @Query("DELETE FROM credentials")
    void clearAll();
}
