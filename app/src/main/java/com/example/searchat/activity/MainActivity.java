package com.example.searchat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.searchat.ItemType;
import com.example.searchat.R;
import com.example.searchat.adapter.RecyclerAdapter;
import com.example.searchat.api.NaverApiService;
import com.example.searchat.db.AppDatabase;
import com.example.searchat.db.entity.Chatting;
import com.example.searchat.view.item.Chat;
import com.example.searchat.api.data.SearchImage;
import com.example.searchat.api.data.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    //recycler
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;

    //user 정보
    private String token;
    private User.Response userInfo;
    private ArrayList<Chat> dataList = new ArrayList<>();

    //검색 정보
    private ArrayList<SearchImage.Item> searchImages;
    private String query;

    //view
    private ImageView searchBtn;
    private EditText textbox;

    //db
    private AppDatabase database;
    private List<Chatting> savedDataList;

    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view
        initView();

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "searchat.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        //startChat
        initChat();

        // 회원 정보 받아오기
        NaverApiService.getUserInfo(this, userCallback);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause: ");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("lifecycle", "onSaveInstanceState: ");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        Log.d("lifecycle", "onStop: ");
        super.onStop();
    }

    Handler insertHandler = new Handler(Looper.getMainLooper());
    @Override
    protected void onDestroy() {
        Log.d("lifecycle", "onDestroy: ");
        super.onDestroy();
        insertHandler.post(new Runnable() {
            Chatting[] save = new Chatting[dataList.size()];
            @Override
            public void run() {
                for(int i = 0; i < dataList.size(); i++){
                    Chat chat = dataList.get(i);
                    Chatting data = new Chatting();
                    data.name = chat.getName();
                    data.itemType = chat.getItemType();
                    data.content = chat.getContent();
                    data.image = chat.getImage();
                    data.profile = chat.getProfile();
                    save[i] = data;
                }
                database.chattingDao().insertAll(save);
            }
        });
    }

    //listener
    View.OnClickListener searchBtnListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String keyword = textbox.getText().toString();
            String profile = userInfo.profileImage;

            //사용자 채팅 추가
            Chat chat = new Chat();
            chat.setTextChat(userInfo.name, keyword , profile);
            addChatItem(chat);

            //검색중이다 채팅 추가
            Chat announce = new Chat();
            announce.setTextChat("[ "+keyword+" ] 검색중 입니다.");
            addChatItem(announce);

            //데이터 찾으라고 쿼리를 날리기
            // 이미지 정보 검색해오기
            query = textbox.getText().toString();
            NaverApiService.searchImage(query,100, 1, searchImageCallback);

            textbox.setText("");
        }
    };


    //이미지 더보기 리스너
    RecyclerAdapter.OnItemBtnClickListener showImageMoreListener = new RecyclerAdapter.OnItemBtnClickListener() {
        @Override
        public void onItemBtnClickListener() {
            Intent intent = new Intent(MainActivity.this, ShowImageActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    };

    //retrofit callback
    Callback<User> userCallback = new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            userInfo = response.body().response;
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {

        }
    };

    Handler searchHandler = new Handler();
    Callback<SearchImage> searchImageCallback = new Callback<SearchImage>() {
        @Override
        public void onResponse(Call<SearchImage> call, Response<SearchImage> response) {
            if(response.isSuccessful()){
                searchImages = response.body().item;
                //이미지 검색 결과 보여주기
                if(searchImages.size() > 0) {
                    searchHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Chat chat = new Chat();
                            chat.setImageChat(searchImages.get(0).link);
                            addChatItem(chat);
                        }
                    }, 1000);
                }
            }
        }

        @Override
        public void onFailure(Call<SearchImage> call, Throwable t) {}
    };

    private void initView(){
        textbox = findViewById(R.id.main_search_text);
        searchBtn = findViewById(R.id.main_search_btn);
        searchBtn.setOnClickListener(searchBtnListener);

        //Adapter
        adapter = new RecyclerAdapter();
        adapter.setOnItemBtnClickListener(showImageMoreListener);

        //layout manager
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);

        //recyclerView
        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setData(dataList);
    }

    private void initChat(){
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
                savedDataList = database.chattingDao().getAll();
                if(savedDataList.size() > 0){
                    for(Chatting chat: savedDataList){
                        Chat savedChat = new Chat();
                        switch (chat.itemType){
                            case ItemType.VIEW_TYPE_CHAT_RIGHT:
                                savedChat.setTextChat(chat.name, chat.content, chat.profile);
                                break;
                            case ItemType.VIEW_TYPE_CHAT_LEFT:
                                savedChat.setTextChat(chat.content);
                                break;
                            case ItemType.VIEW_TYPE_CHAT_IMAGE:
                                savedChat.setImageChat(chat.image);
                                break;
                            default:
                                break;
                        }
                        addChatItem(savedChat);
                    }
                } else{
                    Chat chat = new Chat();
                    chat.setTextChat("검색어를 입력해주세요.");
                    addChatItem(chat);
                }
//            }
//        });

    }

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








