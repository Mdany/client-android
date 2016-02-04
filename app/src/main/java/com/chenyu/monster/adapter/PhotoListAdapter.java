package com.chenyu.monster.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseListAdapter;
import com.chenyu.monster.model.DPhoto;

import java.util.List;

/**
 * Created by chenyu on 16/2/4.
 */
public class PhotoListAdapter extends BaseListAdapter<DPhoto, PhotoListAdapter.PhotoViewHolder> {

    public PhotoListAdapter(Context mContext, List<DPhoto> data) {
        super(mContext, data);
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.c_photo, parent, false);
        PhotoViewHolder viewHolder = new PhotoViewHolder(contentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        //TODO 对view的操作
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {
        //TODO 声明itemView控件
        public PhotoViewHolder(View itemView) {
            super(itemView);
            //TODO view = findByID
        }
    }

    @Override
    public void addItem(DPhoto item) {
        data.add(0,item);
        notifyItemInserted(0);
    }

    @Override
    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void setData(List<DPhoto> data) {
        this.data.clear();
        this.data = data;
        notifyDataSetChanged();
    }
}
