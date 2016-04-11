package com.chenyu.monster.Images.presenter;

import com.chenyu.monster.Images.model.ImageModel;
import com.chenyu.monster.Images.model.ImageModelImpl;
import com.chenyu.monster.Images.view.ImageView;
import com.chenyu.monster.model.ImageBean;

import java.util.List;

/**
 * Created by chenyu on 16/4/8.
 */
public class ImagePresenterImpl implements ImagePresenter, ImageModelImpl.OnLoadImageListListener {
    /**
     * model 回调
     */
    private ImageModel imageModel;
    /**
     * view 回调
     */
    private ImageView imageView;

    public ImagePresenterImpl(ImageView imageView) {
        this.imageModel = new ImageModelImpl();
        this.imageView = imageView;
    }

    @Override
    public void loadImageList() {
        imageModel.loadImageList(this);
        imageView.showProgress();
    }

    @Override
    public void onSuccess(List<ImageBean> imageBeanList) {
        imageView.hideProgress();
        imageView.addImages(imageBeanList);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        imageView.hideProgress();
        imageView.showLoadFailMsg();
    }
}
