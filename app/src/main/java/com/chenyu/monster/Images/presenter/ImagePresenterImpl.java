package com.chenyu.monster.Images.presenter;

import com.chenyu.monster.Images.model.ImageModelImpl;
import com.chenyu.monster.model.ImageBean;

import java.util.List;

/**
 * Created by chenyu on 16/4/8.
 */
public class ImagePresenterImpl implements ImagePresenter, ImageModelImpl.OnLoadImageListListener {
    @Override
    public void loadImageList() {

    }

    @Override
    public void onSuccess(List<ImageBean> imageBeanList) {

    }

    @Override
    public void onFailure(String msg, Exception e) {

    }
}
