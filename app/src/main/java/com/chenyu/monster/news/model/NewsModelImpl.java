package com.chenyu.monster.news.model;

import com.chenyu.monster.commons.Urls;
import com.chenyu.monster.model.NewsBean;
import com.chenyu.monster.model.NewsDetailBean;
import com.chenyu.monster.news.NewsJsonUtils;
import com.chenyu.monster.news.NewsType;
import com.chenyu.monster.util.OkHttpUtil;

import java.util.List;

/**
 * Created by chenyu on 16/4/11.
 */
public class NewsModelImpl implements NewsModel {

    @Override
    public void loadNews(String url, final int type, final OnLoaderNewsListListener listener) {
        OkHttpUtil.get(url, new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(NewsJsonUtils.readJsonNewsBean(response, getId(type)));
            }

            @Override
            public void onFail(Exception e) {
                listener.onFail("load news list fail", e);
            }
        });
    }

    @Override
    public void loadNewsDetail(final String newsId, final OnLoaderNewsDetailListener listener) {
        String url = getDetailUrl(newsId);
        OkHttpUtil.get(url, new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(NewsJsonUtils.readJsonNewsDetailBean(response, newsId));
            }

            @Override
            public void onFail(Exception e) {
                listener.onFail("load news detail info fail.", e);
            }
        });
    }

    /**
     * 获取ID
     *
     * @param type
     * @return
     */
    private String getId(int type) {
        String id;
        switch (type) {
            case NewsType.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;
            case NewsType.NEWS_TYPE_NBA:
                id = Urls.NBA_ID;
                break;
            case NewsType.NEWS_TYPE_CARS:
                id = Urls.CAR_ID;
                break;
            case NewsType.NEWS_TYPE_JOKES:
                id = Urls.JOKE_ID;
                break;
            default:
                id = Urls.TOP_ID;
                break;
        }
        return id;
    }

    /**
     * 拼详情url
     *
     * @param newsId
     * @return
     */
    private String getDetailUrl(String newsId) {
        StringBuffer url = new StringBuffer(Urls.NEW_DETAIL);
        url.append(newsId).append(Urls.END_DETAIL_URL);
        return url.toString();
    }

    /**
     * 加载新闻list
     */
    public interface OnLoaderNewsListListener {
        void onSuccess(List<NewsBean> newsBeanList);

        void onFail(String msg, Exception e);
    }

    /**
     * 加载新闻详情
     */
    public interface OnLoaderNewsDetailListener {
        void onSuccess(NewsDetailBean newsDetailBean);

        void onFail(String msg, Exception e);
    }
}
