package com.chenyu.monster.Images;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseListAdapter;
import com.chenyu.monster.model.ImageBean;
import com.chenyu.monster.util.DisplayUtils;
import com.chenyu.monster.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by chenyu on 16/4/11.
 * 图片列表adapter
 */
public class ImageAdapter extends BaseListAdapter<ImageBean, ImageAdapter.ImageViewHolder> {
    private int mMaxWidth;
    private int mMaxHeight;

    public ImageAdapter(Context mContext) {
        super(mContext);
        mMaxWidth = DisplayUtils.getScreenWidth() - 20;
        mMaxHeight = DisplayUtils.getScreenHeight() - DisplayUtils.getStatusHeight(mContext) -
                DisplayUtils.dp2px(96);
    }

    public ImageAdapter(Context mContext, List<ImageBean> data) {
        super(mContext, data);
        this.data = data;
        mMaxWidth = DisplayUtils.getScreenWidth() - 20;
        mMaxHeight = DisplayUtils.getScreenHeight() - DisplayUtils.getStatusHeight(mContext) -
                DisplayUtils.dp2px(96);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.c_image, parent, false);
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
        float scale = (float) image.width / (float) mMaxWidth;
        int height = (int) (image.height / scale);
        if (height > mMaxHeight) {
            height = mMaxHeight;
        }
        ImageLoaderUtils.load(mContext, Uri.parse(image.thumburl), holder.image);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;

        public ImageViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_image_title);
            image = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }
}
