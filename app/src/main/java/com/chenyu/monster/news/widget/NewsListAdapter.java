package com.chenyu.monster.news.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseListAdapter;
import com.chenyu.monster.model.NewsBean;

/**
 * Created by chenyu on 16/4/12.
 */
public class NewsListAdapter extends BaseListAdapter<NewsBean,NewsListAdapter.NewsViewHolder> {
    private Context mContext;

    public NewsListAdapter(Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout,parent);
        return null;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        public NewsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
