package com.example.searchat;

import android.app.Application;

import com.example.searchat.util.LoginManager;

public class SearChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LoginManager.init(this);
    }
}
