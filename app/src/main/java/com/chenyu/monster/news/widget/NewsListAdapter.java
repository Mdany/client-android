package com.chenyu.monster.news.widget;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseListAdapter;
import com.chenyu.monster.model.NewsBean;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by chenyu on 16/4/12.
 */
public class NewsListAdapter extends BaseListAdapter<NewsBean, RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Context mContext;
    private boolean isShowFooter = true;
    private OnItemClickListener itemClickListener;

    public NewsListAdapter(Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if (!isShowFooter) {
            return TYPE_ITEM;
        }
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = null;
        if (viewType == TYPE_ITEM) {
            contentView = LayoutInflater.from(mContext).inflate(R.layout.c_news_list, parent);
            return new NewsViewHolder(contentView);
        } else {
            contentView = LayoutInflater.from(mContext).inflate(R.layout.footer, parent);
            return new FooterViewHolder(contentView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder) {
            NewsBean newsBean = data.get(position);
            if (newsBean == null) return;
            ((NewsViewHolder) holder).title.setText(newsBean.title);
            ((NewsViewHolder) holder).content.setText(newsBean.digest);
            ((NewsViewHolder) holder).avatar.setImageURI(Uri.parse(newsBean.imgsrc));
        }
    }

    @Override
    public int getItemCount() {
        int footCount = isShowFooter ? 1 : 0;
        if (data == null) {
            return footCount;
        }
        return data.size() + footCount;
    }

    public NewsBean getItem(int position) {
        return data == null ? null : data.get(position);
    }

    /**
     * 设置是否分页
     *
     * @param isShowFooter 分页标识位
     */
    public void setIsShowFooter(boolean isShowFooter) {
        this.isShowFooter = isShowFooter;
    }

    /**
     * 是否分页
     *
     * @return 是否分页
     */
    public boolean isShowFooter() {
        return isShowFooter;
    }

    /**
     * 设置文本监听
     *
     * @param itemClickListener
     */
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * footer view holder
     */
    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * item view holder
     */
    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, content;
        private SimpleDraweeView avatar;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_news_title);
            content = (TextView) itemView.findViewById(R.id.tv_news_content);
            avatar = (SimpleDraweeView) itemView.findViewById(R.id.iv_news_avatar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    /**
     * item点击事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
