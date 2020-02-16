package com.example.searchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.data.OAuthErrorCode;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

public class LoginActivity extends AppCompatActivity {

    private OAuthLogin mOAuthLoginModule;
    private OAuthLoginButton mOAuthLoginButton;
    private OAuthLoginHandler mOAuthLoginHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                LoginActivity.this
                ,"F8Q8vkacjL1qgs04C_YI"
                ,"csqxFHKFRq"
                ,"SearChat"
        );

        mOAuthLoginHandler = new OAuthLoginHandler() {
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


        mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        1000);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

    }
}
