package com.chenyu.monster.Images.view;

import com.chenyu.monster.model.ImageBean;

import java.util.List;

/**
 * Created by chenyu on 16/4/8.
 */
public interface ImageView {
    /**
     * 加载数据
     * @param imageBeanList
     */
    void addImages(List<ImageBean> imageBeanList);

    /**
     * 加载中
     */
    void showProgress();

    /**
     * 加载完毕
     */
    void hideProgress();

    /**
     * 加载失败
     */
    void showLoadFailMsg();
}