package com.chenyu.monster.news.presenter;

import com.chenyu.monster.model.NewsDetailBean;
import com.chenyu.monster.news.model.NewsModel;
import com.chenyu.monster.news.model.NewsModelImpl;
import com.chenyu.monster.news.view.NewsDetailView;

/**
 * Created by chenyu on 16/4/11.
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter, NewsModelImpl.OnLoaderNewsDetailListener {
    private NewsDetailView newsDetailView;
    private NewsModel newsModel;

    public NewsDetailPresenterImpl(NewsDetailView newsDetailView) {
        this.newsDetailView = newsDetailView;
        newsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(String newsId) {
        newsDetailView.showProgress();
        newsModel.loadNewsDetail(newsId, this);
    }

    @Override
    public void onSuccess(NewsDetailBean newsDetailBean) {
        newsDetailView.hideProgress();
        if (newsDetailBean == null) return;
        newsDetailView.showNewsDetailContent(newsDetailBean.body);
    }

    @Override
    public void onFail(String msg, Exception e) {
        newsDetailView.hideProgress();
    }
}
