package com.example.searchat.view.viewholder;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.searchat.view.item.Chat;
import com.example.searchat.R;

public class ImageViewHolder extends AbsViewHolder {
    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        this.image = this.view.findViewById(R.id.item_content_img);
        this.btn = this.view.findViewById(R.id.item_content_btn);
        Log.d("ImageViewHolder", "setData: "+this.image.getId());
    }

    @Override
    public void setData(Chat data) {
        Glide.with(this.view.getContext()).load(data.getImage()).into(this.image);
        Glide.with(this.profile).load(R.drawable.naver_icon).apply(new RequestOptions().circleCrop()).into(this.profile);
    }
}
