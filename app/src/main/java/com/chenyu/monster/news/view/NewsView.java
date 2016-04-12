package com.chenyu.monster.news.view;

import com.chenyu.monster.model.NewsBean;

import java.util.List;

/**
 * Created by chenyu on 16/4/11.
 */
public interface NewsView {
    void showProgress();
    void hideProgress();
    void addNews(List<NewsBean> beanList);
    void showFailMsg();
}
