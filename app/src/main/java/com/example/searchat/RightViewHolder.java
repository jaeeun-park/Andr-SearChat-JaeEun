package com.example.searchat;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

public class RightViewHolder extends AbsViewHolder {

    public RightViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        this.name = this.view.findViewById(R.id.item_name);
        this.content = this.view.findViewById(R.id.item_content);
        this.profile = this.view.findViewById(R.id.item_image);
    }

    @Override
    public void setData(Chat data) {
        this.name.setText(data.getName());
        this.content.setText(data.getContent());
        Glide.with(this.view.getContext()).load(data.getProfile()).into(this.profile);
        Log.d("WhyChatDouble", "setData: RightViewHolder");
    }
}
