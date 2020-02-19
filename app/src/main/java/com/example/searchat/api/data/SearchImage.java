package com.example.searchat.api.data;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchImage {

    @SerializedName("lastBuildDate")
    public String lastBuildDate;

    @SerializedName("total")
    public int total;

    @SerializedName("start")
    public int start;

    @SerializedName("display")
    public int display;

    @SerializedName("items")
    public ArrayList<Item> item;

    public class Item{

        @SerializedName("title")
        public String title;

        @SerializedName("link")
        public String link;

        @SerializedName("thumbnail")
        public String thumbnail;

        @SerializedName("sizeheight")
        public String sizeheight;

        @SerializedName("sizewidth")
        public String sizewidth;
    }
}
