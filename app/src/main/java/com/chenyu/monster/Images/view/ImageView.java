package com.chenyu.monster.Images.view;

import com.chenyu.monster.model.ImageBean;

import java.util.List;

/**
 * Created by chenyu on 16/4/8.
 */
public interface ImageView {
    void addImages(List<ImageBean> imageBeanList);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg();
}
