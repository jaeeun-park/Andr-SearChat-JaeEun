package com.example.searchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ShowImageDetailActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ImageRecyclerAdapter adapter;
    private int position;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_detail);

        Intent intent = getIntent();

        data = intent.getStringArrayListExtra("IMAGES");
        position = intent.getIntExtra("FOCUS", 0);

        Log.d("ShowImageActivityDetail", "onCreate: "+data.size());

        adapter = new ImageRecyclerAdapter();
        adapter.setOnItemClickListener(itemClickListener);
        adapter.setData(data);
        adapter.notifyDataSetChanged();

        viewPager = findViewById(R.id.showimg_view_pager);
        viewPager.setAdapter(adapter);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setCurrentItem(position);


    }

    @Override
    public void onBackPressed() {
        adapter.setGridMode(true);
        super.onBackPressed();
    }

    ImageRecyclerAdapter.OnItemClickListener itemClickListener = new ImageRecyclerAdapter.OnItemClickListener() {
        @Override
        public void onItemClickListener(int position) {

        }
    };
}
