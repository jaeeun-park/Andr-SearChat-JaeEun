package com.example.searchat.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.searchat.db.dao.ChattingDao;
import com.example.searchat.db.entity.Chatting;

@Database(entities = {Chatting.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ChattingDao chattingDao();
}
