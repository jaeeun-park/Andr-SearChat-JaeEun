package com.example.searchat.view.item;

import com.example.searchat.view.item.ItemType;

public class Chat {
    private String name;
    private String content;
    private String profile;
    private String image;
    private int itemType;

    public void setTextChat(String content){
        this.content = content;
        this.itemType = ItemType.VIEW_TYPE_CHAT_LEFT;
    }

    public void setTextChat(String name, String content, String profile){
        this.name = name;
        this.content = content;
        this.profile = profile;
        this.itemType = ItemType.VIEW_TYPE_CHAT_RIGHT;
    }

    public void setImageChat(String image){
        this.image = image;
        this.itemType = ItemType.VIEW_TYPE_CHAT_IMAGE;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public int getItemType() {
        return itemType;
    }

    public String getProfile() {
        return profile;
    }

    public String getImage() {
        return image;
    }
}
