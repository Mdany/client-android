package com.chenyu.monster.framework;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyu on 16/2/4.
 */
public abstract class BaseListAdapter<M extends Entity, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected Context mContext;
    protected List<M> data;

    public BaseListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public BaseListAdapter(Context mContext, List<M> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    /**
     * 添加item
     *
     * @param item
     */
    public void addItem(M item) {
        if (data == null)
            data = new ArrayList<>();
        data.add(0, item);
        notifyItemInserted(0);
    }

    /**
     * 批量添加items
     *
     * @param items
     */
    public void addItems(List<M> items) {
        if (data == null)
            data = new ArrayList<>();
        data.addAll(0, items);
        notifyItemRangeInserted(0, items.size());
    }

    /**
     * 移除指定item
     *
     * @param position
     */
    public void removeItem(int position) {
        if (data != null) {
            data.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 填充数据
     *
     * @param data
     */
    public void setData(List<M> data) {
        if (this.data != null) {
            this.data.clear();
        }
        this.data = data;
        notifyDataSetChanged();
    }
}
