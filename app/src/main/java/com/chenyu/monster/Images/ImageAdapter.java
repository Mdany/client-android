package com.chenyu.monster.Images;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseListAdapter;
import com.chenyu.monster.model.ImageBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by chenyu on 16/4/11.
 * 图片列表adapter
 */
public class ImageAdapter extends BaseListAdapter<ImageBean, ImageAdapter.ImageViewHolder> {
    private List<ImageBean> data;
    private Context mContext;

    public ImageAdapter(Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    public ImageAdapter(Context mContext, List<ImageBean> data) {
        super(mContext, data);
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.c_image, parent, false);
        ImageViewHolder imageItem = new ImageViewHolder(view);
        return imageItem;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        ImageBean image = data.get(position);
        if (image == null) {
            return;
        }
        holder.title.setText(image.title);
        holder.image.setImageURI(Uri.parse(image.thumburl));
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private SimpleDraweeView image;

        public ImageViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_image_title);
            image = (SimpleDraweeView) itemView.findViewById(R.id.iv_image);
        }
    }
}
