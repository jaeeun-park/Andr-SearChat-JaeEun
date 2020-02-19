package com.example.searchat.util;

import android.app.Activity;
import android.content.Context;

import com.example.searchat.activity.LoginActivity;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import java.util.logging.Handler;

public class LoginManager {
    public static final String OAUTH_CLIENT_ID = "F8Q8vkacjL1qgs04C_YI";
    public static final String OAUTH_CLIENT_SECRET = "csqxFHKFRq";
    public static final String OAUTH_CLIENT_NAME = "SearChat";

    public static OAuthLogin oAuthLogin;
    public static void init(Context context){
        oAuthLogin = OAuthLogin.getInstance();
        oAuthLogin.init(
                context
                ,OAUTH_CLIENT_ID
                ,OAUTH_CLIENT_SECRET
                ,OAUTH_CLIENT_NAME
        );
    }

    public static void login(Activity activity, OAuthLoginHandler oAuthLoginHandler){
        oAuthLogin.startOauthLoginActivity(activity, oAuthLoginHandler);
    }

}
