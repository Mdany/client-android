package com.chenyu.monster.Images.model;

import com.chenyu.monster.commons.Urls;
import com.chenyu.monster.model.ImageBean;
import com.chenyu.monster.util.JsonUtils;
import com.chenyu.monster.util.LogUtils;
import com.chenyu.monster.util.OkHttpUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
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
                List<ImageBean> images = readJsonImageBean(response);
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
     * 解析json为图片列表对象
     *
     * @param json
     * @return
     */
    private List<ImageBean> readJsonImageBean(String json) {
        List<ImageBean> images = new ArrayList<>();
        try {
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(json).getAsJsonArray();
            int size = array.size();
            for (int i = 0; i > size; i++) {
                JsonObject object = array.get(i).getAsJsonObject();
                images.add(JsonUtils.deserialize(object, ImageBean.class));
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "read imageJson failure.", e);
        }
        return images;
    }

    /**
     * model对presenter暴露的接口
     */
    public interface OnLoadImageListListener {
        void onSuccess(List<ImageBean> imageBeanList);

        void onFailure(String msg, Exception e);
    }
}
