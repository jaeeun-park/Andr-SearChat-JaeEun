package com.example.searchat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.data.OAuthErrorCode;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

public class LoginActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    private OAuthLogin mOAuthLoginModule;
    private OAuthLoginButton mOAuthLoginButton;
    private OAuthLoginHandler mOAuthLoginHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        naverLogin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermission();
    }

    private void naverLogin(){
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                LoginActivity.this
                ,"F8Q8vkacjL1qgs04C_YI"
                ,"csqxFHKFRq"
                ,"SearChat"
        );

        mOAuthLoginHandler = getmOAuthLoginHandler();


        mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
    }

    @SuppressLint("HandlerLeak")
    private OAuthLoginHandler getmOAuthLoginHandler() {
        return new OAuthLoginHandler() {
            @Override
            public void run(boolean success) {
                if(success){
                    String token = mOAuthLoginModule.getAccessToken(LoginActivity.this);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("TOKEN", token);
                    startActivity(intent);
                    finish();
                }
                else{
                    OAuthErrorCode errorCode = mOAuthLoginModule.getLastErrorCode(LoginActivity.this);
                    String errorDesc = mOAuthLoginModule.getLastErrorDesc(LoginActivity.this);
                    Log.d("LoginActivity11", "run: "+errorCode.getCode() +", "+errorDesc);
                }
            }
        };
    }

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.INTERNET)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
        }
    }
}
