package com.example.searchat;

import android.media.Image;
import android.net.Uri;

public class Chat {
    private String name;
    private String content;
    private String profile;
    private String image;
    private int viewType;

    public void setTextChat(String content, int viewType){
        this.content = content;
        this.viewType = viewType;
    }

    public void setTextChat(String name, String content, String profile, int viewType){
        this.name = name;
        this.content = content;
        this.profile = profile;
        this.viewType = viewType;
    }

    public void setImageChat(String image, int viewType){
        this.image = image;
        this.viewType = viewType;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public int getViewType() {
        return viewType;
    }

    public String getProfile() {
        return profile;
    }

    public String getImage() {
        return image;
    }
}
