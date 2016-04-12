package com.chenyu.monster.news.model;

/**
 * Created by chenyu on 16/4/11.
 */
public interface NewsModel {
    void loadNews(String url, int type, NewsModelImpl.OnLoaderNewsListListener listener);

    void loadNewsDetail(String newsId, NewsModelImpl.OnLoaderNewsDetailListener listener);
}
