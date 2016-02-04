package com.chenyu.monster.framework;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by chenyu on 16/2/4.
 */
public abstract class BaseListAdapter<M, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected Context mContext;
    protected List<M> data;

    public BaseListAdapter(Context mContext, List<M> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public abstract void addItem(M item);
    public abstract void removeItem(int position);
    public abstract void setData(List<M> data);
}
