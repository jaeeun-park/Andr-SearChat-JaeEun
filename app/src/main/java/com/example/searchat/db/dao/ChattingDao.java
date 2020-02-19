package com.example.searchat.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.searchat.db.entity.Chatting;
import com.example.searchat.view.item.Chat;

import java.util.List;

@Dao
public interface ChattingDao {

    @Query("SELECT uid, user_name, content, profile, image, item_type FROM chatting")
    public List<Chatting> getAll();

    @Insert
    public void insertAll(Chatting... chattings);
}
