package com.example.searchat.api;

import android.content.Context;

import com.example.searchat.api.data.SearchImage;
import com.example.searchat.api.data.User;
import com.example.searchat.util.LoginManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface RetrofitService {

    @GET("/v1/nid/me")
    Call<User> userRepos(
            @Header("Authorization") String headers);

    @Headers({
            "X-Naver-Client-Id: "+LoginManager.OAUTH_CLIENT_ID,
            "X-Naver-Client-Secret: " + LoginManager.OAUTH_CLIENT_SECRET
    })
    @GET("/v1/search/image")
    Call<SearchImage> imageRepos(@Query("query")String query, @Query("display")int display,
                                 @Query("start")int start);
}

public class NaverApiService {

    public static void getUserInfo(Context context, Callback<User> callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //네이버로 연결
        retrofit.create(RetrofitService.class)
                .userRepos("Bearer "+ LoginManager.oAuthLogin.getAccessToken(context))
                .enqueue(callback);
    }

    public static void searchImage(String query, int display, int start, Callback<SearchImage> callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(RetrofitService.class)
                .imageRepos(query, display, start)
                .enqueue(callback);
    }


}
