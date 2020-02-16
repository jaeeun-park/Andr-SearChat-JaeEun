package com.example.searchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    //recycler
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;

    //user 정보
    private String token;
    private User user;
    private ArrayList<Chat> dataList = new ArrayList<>();

    //검색 정보
    private ArrayList<SearchImage.Item> searchImages;

    //검색어를 입력하세요 Chat
    private Chat askMeChat;

    //view
    private ImageView searchBtn;
    private EditText textbox;

    //retrofit service
    private NaverService service;

    //retrofit
    public interface NaverService {
        @GET("/v1/{service}/{type}")
        Call<User> userRepos(
                @Path("service") String service, @Path("type") String type
        , @Header("Authorization") String headers);

        @Headers({
                "X-Naver-Client-Id: ",
                "X-Naver-Client-Secret: "
        })
        @GET("/v1/search/{type}")
        Call<SearchImage> imageRepos(@Path("type") String type, @Query("query")String query,@Query("display")int display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view
        textbox = findViewById(R.id.main_search_text);
        searchBtn = findViewById(R.id.main_search_btn);
        searchBtn.setOnClickListener(searchBtnListener);

        //Adapter
        adapter = new RecyclerAdapter();

        //layout manager
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);

        //recyclerView
        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        //첫 채팅
        askMeChat = new Chat();
        askMeChat.setTextChat("검색어를 입력해주세요.", ItemType.VIEW_TYPE_CHAT_LEFT);
        dataList.add(askMeChat);
        adapter.setData(dataList);
        adapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(getListLastIndex());
        Log.d("WhyChatDouble", "MainActivity firstChat");



        //레트로핏 설정
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //네이버로 연결
        service = retrofit.create(NaverService.class);

        // 토큰
        Intent intent = getIntent();
        token = intent.getStringExtra("TOKEN").trim();

        // 회원 정보 받아오기
        Call<User> response = service.userRepos("nid", "me", "Bearer "+ token);

        response.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("MainActivity11", "onFailure: " + call);
                Log.d("MainActivity11", "onFailure: " + t);

            }
        });



    }

    //listener
    View.OnClickListener searchBtnListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String keyword = textbox.getText().toString();
            Chat chat = new Chat();
            User.Response target = user.response;
            String profile = target.profileImage;

            chat.setTextChat(target.name, keyword , profile ,ItemType.VIEW_TYPE_CHAT_RIGHT);
            addChatItem(chat);

            Chat announce = new Chat();
            announce.setTextChat("[ "+keyword+" ] 검색중 입니다.", ItemType.VIEW_TYPE_CHAT_LEFT);
            addChatItem(announce);

            //데이터 찾으라고 쿼리를 날리기
            // 이미지 정보 검색해오기
            String q = textbox.getText().toString();
            Call<SearchImage> searchResponse = service.imageRepos("image", q, 100);
            searchResponse.enqueue(new Callback<SearchImage>() {
                @Override
                public void onResponse(Call<SearchImage> call, Response<SearchImage> response) {
                    if(response.isSuccessful()){
                        searchImages = response.body().item;
                        //이미지 검색 결과 보여주기
                        if(searchImages.size() > 0){
                            Chat chat = new Chat();
                            chat.setImageChat(searchImages.get(0).link,ItemType.VIEW_TYPE_CHAT_IMAGE);
                            dataList.add(chat);
                            adapter.addData(chat);
                            adapter.setOnItemBtnClickListener(showImageMoreListener);
                            adapter.notifyItemInserted(getListLastIndex());
                            recyclerView.smoothScrollToPosition(getListLastIndex());

                            //검색어를 입력하세요 채팅 추가
                            addChatItem(askMeChat);
                        }
                    }
                }

                @Override
                public void onFailure(Call<SearchImage> call, Throwable t) {
                    Log.d("MainActivity11", "onFailure: " + t);
                }
            });

            textbox.setText("");
        }
    };


    //이미지 더보기 리스너
    RecyclerAdapter.OnItemBtnClickListener showImageMoreListener = new RecyclerAdapter.OnItemBtnClickListener() {
        @Override
        public void onItemBtnClickListener() {

            ArrayList<String> imageUrls = new ArrayList<>();

            for(SearchImage.Item item: searchImages){
                imageUrls.add(item.link);
            }

            Intent intent = new Intent(MainActivity.this, ShowImageActivity.class);
            intent.putExtra("IMAGES", imageUrls);
            startActivity(intent);
        }
    };

    private int getListLastIndex() {
        return dataList.size() - 1;
    }

    private void addChatItem(Chat chat) {
        dataList.add(chat);
        adapter.addData(chat);
        adapter.notifyItemInserted(getListLastIndex());
        recyclerView.smoothScrollToPosition(getListLastIndex());
    }

}








