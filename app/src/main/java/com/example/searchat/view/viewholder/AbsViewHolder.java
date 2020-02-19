package com.example.searchat.view.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchat.view.item.Chat;

public abstract class AbsViewHolder extends RecyclerView.ViewHolder {

    public AbsViewHolder(@NonNull View itemView) { super(itemView); }
    public View view;
    public TextView name;
    public TextView content;
    public ImageView profile;
    public ImageView image;
    public Button btn;

    public abstract void setData(Chat data);


}
