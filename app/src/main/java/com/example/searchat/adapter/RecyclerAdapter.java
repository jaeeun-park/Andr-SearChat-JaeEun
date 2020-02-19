package com.example.searchat.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchat.view.item.ItemType;
import com.example.searchat.R;
import com.example.searchat.view.item.Chat;
import com.example.searchat.view.viewholder.AbsViewHolder;
import com.example.searchat.view.viewholder.ImageViewHolder;
import com.example.searchat.view.viewholder.LeftViewHolder;
import com.example.searchat.view.viewholder.RightViewHolder;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<AbsViewHolder> {

    private ArrayList<Chat> data;

    public interface OnItemBtnClickListener{
        public void onItemBtnClickListener();
    }

    private OnItemBtnClickListener listener;

    public void setOnItemBtnClickListener(OnItemBtnClickListener listener){ this.listener = listener; }


    @NonNull
    @Override
    public AbsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("WhyChatDouble", "onCreateViewHolder");
        if(viewType == ItemType.VIEW_TYPE_CHAT_LEFT){
            View v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item_left, parent, false);
            return new LeftViewHolder(v);
        } else if(viewType == ItemType.VIEW_TYPE_CHAT_RIGHT){
            View v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item_right, parent, false);
            return new RightViewHolder(v);
        } else{
            View v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item_image, parent, false);
            return new ImageViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AbsViewHolder holder, int position) {
        Log.d("WhyChatDouble", "onBindViewHolder");
        holder.setData(data.get(position));
        if(holder.getItemViewType() == ItemType.VIEW_TYPE_CHAT_IMAGE){
            holder.btn.setOnClickListener(btnClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getItemType();
    }

    public void setData(ArrayList<Chat> data){
        this.data = new ArrayList<>();
        this.data.addAll(data);
        Log.d("WhyChatDouble", "setData: "+this.data.size());
    }

    public void addData(Chat data){
        this.data.add(data);
        Log.d("WhyChatDouble", "setData: "+this.data.size());
    }

    //listener
    View.OnClickListener btnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            listener.onItemBtnClickListener();
        }
    };
}
