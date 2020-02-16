package com.example.searchat;

public class Chat {
    private String name;
    private String content;
    private String profile;
    private String image;
    private int viewType;

    public void setTextChat(String content){
        this.content = content;
        this.viewType = ItemType.VIEW_TYPE_CHAT_LEFT;
    }

    public void setTextChat(String name, String content, String profile){
        this.name = name;
        this.content = content;
        this.profile = profile;
        this.viewType = ItemType.VIEW_TYPE_CHAT_RIGHT;
    }

    public void setImageChat(String image){
        this.image = image;
        this.viewType = ItemType.VIEW_TYPE_CHAT_IMAGE;
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
