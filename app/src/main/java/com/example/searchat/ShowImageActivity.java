package com.example.searchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    //data
    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        Intent intent = getIntent();

        data = intent.getStringArrayListExtra("IMAGES");
        Log.d("ShowImageActivity", "onCreate: "+data.size());

        //recyclerview setting
        adapter = new ImageRecyclerAdapter();

        layoutManager = new GridLayoutManager(this, 3);

        recyclerView = findViewById(R.id.showimg_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setData(data);
        adapter.setOnItemClickListener(itemClickListener);
        adapter.notifyDataSetChanged();

    }

    //listener
    ImageRecyclerAdapter.OnItemClickListener itemClickListener = new ImageRecyclerAdapter.OnItemClickListener() {
        @Override
        public void onItemClickListener(int position) {
            Intent intent = new Intent(ShowImageActivity.this, ShowImageDetailActivity.class);
            intent.putExtra("IMAGES", data);
            intent.putExtra("FOCUS", position);
            startActivity(intent);
            adapter.setGridMode(false);
        }
    };
}
