package com.example.searchat.api.data;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("resultcode")
    public String resultcode;
    @SerializedName("message")
    public String message;
    @SerializedName("response")
    public Response response;

    public class Response {

        @SerializedName("email")
        public String email;
        @SerializedName("nickname")
        public String nickname;
        @SerializedName("profile_image")
        public String profileImage;
        @SerializedName("age")
        public String age;
        @SerializedName("gender")
        public String gender;
        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("birthday")
        public String birthday;

    }
}
