package com.chenyu.monster.news.view;

/**
 * Created by chenyu on 16/4/19.
 */
public interface NewsDetailView {
    void showNewsDetailContent(String body);

    void showProgress();

    void hideProgress();
}
