package com.example.searchat.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.searchat.R;
import com.example.searchat.util.LoginManager;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.data.OAuthErrorCode;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        OAuthLoginButton button = findViewById(R.id.buttonOAuthLoginImg);
        LoginManager.login(LoginActivity.this, getOAuthLoginHandler());

    }

    @SuppressLint("HandlerLeak")
    private OAuthLoginHandler getOAuthLoginHandler() {
        return new OAuthLoginHandler() {
            @Override
            public void run(boolean success) {
                if(success){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    OAuthErrorCode errorCode = LoginManager.oAuthLogin.getLastErrorCode(LoginActivity.this);
                    String errorDesc = LoginManager.oAuthLogin.getLastErrorDesc(LoginActivity.this);
                }
            }
        };
    }

}
