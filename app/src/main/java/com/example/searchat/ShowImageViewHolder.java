package com.example.searchat;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ShowImageViewHolder extends RecyclerView.ViewHolder {

    public View view;
    public ImageView image;

    public ShowImageViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        this.image = view.findViewById(R.id.showimg_image);
    }

    public void setData(String url){
        Glide.with(this.view.getContext()).load(url).into(this.image);
    }

}
