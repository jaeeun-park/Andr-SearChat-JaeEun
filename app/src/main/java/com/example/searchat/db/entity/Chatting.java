package com.example.searchat.db.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Chatting {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "user_name")
    @Nullable
    public String name;

    @ColumnInfo(name = "content")
    @Nullable
    public String content;

    @ColumnInfo(name = "profile")
    @Nullable
    public String profile;

    @ColumnInfo(name = "image")
    @Nullable
    public String image;

    @ColumnInfo(name = "item_type")
    public int itemType;
}
