package com.example.searchat;

import android.view.View;

import androidx.annotation.NonNull;

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
    }
}
