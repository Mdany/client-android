package com.chenyu.monster.Images.model;

import com.chenyu.monster.Images.ImageJsonUtils;
import com.chenyu.monster.commons.Urls;
import com.chenyu.monster.model.ImageBean;
import com.chenyu.monster.util.OkHttpUtil;

import java.util.List;

/**
 * Created by chenyu on 16/4/8.
 */
public class ImageModelImpl implements ImageModel {
    private static final String TAG = "ImageModelImpl";

    @Override
    public void loadImageList(final OnLoadImageListListener listener) {
        String url = Urls.IMAGES_URL;
        OkHttpUtil.ResultCallback<String> loadImagesCallback = new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<ImageBean> images = ImageJsonUtils.readJsonImageBeans(response);
                listener.onSuccess(images);
            }

            @Override
            public void onFail(Exception e) {
                listener.onFailure("load image list failure.", e);
            }
        };

        OkHttpUtil.get(url, loadImagesCallback);
    }

    /**
     * model对presenter暴露的接口
     */
    public interface OnLoadImageListListener {
        void onSuccess(List<ImageBean> imageBeanList);

        void onFailure(String msg, Exception e);
    }
}
