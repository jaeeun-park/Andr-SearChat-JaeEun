package com.example.searchat.view.viewholder;

import android.view.View;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.searchat.R;
import com.example.searchat.view.item.Chat;

public class LeftViewHolder extends AbsViewHolder {
    public LeftViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        this.name = this.view.findViewById(R.id.item_name);
        this.content = this.view.findViewById(R.id.item_content);
        this.profile = this.view.findViewById(R.id.item_image);
    }

    @Override
    public void setData(Chat data) {
        this.content.setText(data.getContent());
        Glide.with(this.profile).load(R.drawable.naver_icon).apply(new RequestOptions().circleCrop()).into(this.profile);
    }
}
