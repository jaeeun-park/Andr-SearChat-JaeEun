package com.example.searchat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchat.adapter.ImageRecyclerAdapter;
import com.example.searchat.R;
import com.example.searchat.api.NaverApiService;
import com.example.searchat.api.data.SearchImage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowImageActivity extends AppCompatActivity {

    //recyclerview
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private ImageRecyclerAdapter adapter;

    //intent request code
    private int REQUEST_CODE = 1000;

    //data
    ArrayList<String> data;
    ArrayList<SearchImage.Item> searchImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        NaverApiService.searchImage(query, 100, 1, searchImageCallback);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            recyclerView.smoothScrollToPosition(data.getIntExtra("POSITION",0));
        }
    }

    //listener
    ImageRecyclerAdapter.OnItemClickListener itemClickListener = new ImageRecyclerAdapter.OnItemClickListener() {
        @Override
        public void onItemClickListener(int position) {
            Intent intent = new Intent(ShowImageActivity.this, ShowImageDetailActivity.class);
            intent.putExtra("IMAGES", data);
            intent.putExtra("FOCUS", position);
            startActivityForResult(intent, REQUEST_CODE);
        }
    };

    private void initView(){
        //recyclerview setting
        adapter = new ImageRecyclerAdapter();

        layoutManager = new GridLayoutManager(this, 3);

        recyclerView = findViewById(R.id.showimg_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setGridMode(true);
        adapter.setData(data);
        adapter.setOnItemClickListener(itemClickListener);
        adapter.notifyDataSetChanged();

    }

    Callback<SearchImage> searchImageCallback = new Callback<SearchImage>() {
        @Override
        public void onResponse(Call<SearchImage> call, Response<SearchImage> response) {
            if(response.isSuccessful()){
                searchImage = response.body().item;
                data = new ArrayList<>();
                if(searchImage != null){
                    for(SearchImage.Item item : searchImage){
                        data.add(item.link);
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initView();
                    }
                });
            }
        }

        @Override
        public void onFailure(Call<SearchImage> call, Throwable t) {}
    };
}
