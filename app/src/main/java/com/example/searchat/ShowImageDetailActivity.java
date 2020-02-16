package com.example.searchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class ShowImageDetailActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ImageRecyclerAdapter adapter;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_detail);

        Intent intent = getIntent();
        data = intent.getStringArrayListExtra("IMAGES");
        int position = intent.getIntExtra("FOCUS", 0);

        initView(position);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("POSITION", viewPager.getCurrentItem());
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }

    private void initView(int position){
        adapter = new ImageRecyclerAdapter();
        adapter.setGridMode(false);
        adapter.setData(data);
        adapter.notifyDataSetChanged();

        viewPager = findViewById(R.id.showimg_view_pager);
        viewPager.setAdapter(adapter);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setCurrentItem(position);
    }

}
