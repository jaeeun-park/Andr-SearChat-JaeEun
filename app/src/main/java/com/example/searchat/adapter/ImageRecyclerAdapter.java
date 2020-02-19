package com.example.searchat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchat.ItemType;
import com.example.searchat.R;
import com.example.searchat.view.viewholder.ShowImageViewHolder;

import java.util.ArrayList;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ShowImageViewHolder> {

    ArrayList<String> data;

    //flag
    private boolean isGridMode;

    //Tag - position
    private final int POSITION_TAG = 0xFFFFFFF1;

    //onItemClickListener
    public interface OnItemClickListener{
        public void onItemClickListener(int position);
    }
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public ShowImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = null;
        if(viewType == ItemType.VIEW_TYPE_SHOW_IMAGE_GRID){
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item_show_image, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item_show_image_detail, parent, false);
        }
        return new ShowImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowImageViewHolder holder, int position) {
        holder.setData(data.get(position));
        holder.image.setTag(POSITION_TAG, position);
        if(isGridMode){
            holder.image.setOnClickListener(clickListener);
        } else{
            holder.view.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(isGridMode){
            return ItemType.VIEW_TYPE_SHOW_IMAGE_GRID;
        } else{
            return ItemType.VIEW_TYPE_SHOW_IMAGE_DETAIL;
        }
    }

    //set/get
    public void setData(ArrayList<String> data){
        this.data = new ArrayList<>();
        this.data.addAll(data);
    }

    public void setGridMode(boolean isGrid){
        this.isGridMode = isGrid;
    }


    //listener
    View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            listener.onItemClickListener((int)v.getTag(POSITION_TAG));
        }
    };

}
