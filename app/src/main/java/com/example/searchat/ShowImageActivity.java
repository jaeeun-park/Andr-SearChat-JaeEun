package com.example.searchat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ShowImageActivity extends AppCompatActivity {

    //recyclerview
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private ImageRecyclerAdapter adapter;

    //intent request code
    private int REQUEST_CODE = 1000;

    //data
    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        initView();
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
        Intent intent = getIntent();
        data = intent.getStringArrayListExtra("IMAGES");

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
}
