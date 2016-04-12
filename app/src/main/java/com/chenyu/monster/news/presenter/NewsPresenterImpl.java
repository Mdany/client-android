package com.chenyu.monster.news.presenter;

import com.chenyu.monster.commons.Urls;
import com.chenyu.monster.model.NewsBean;
import com.chenyu.monster.news.NewsType;
import com.chenyu.monster.news.model.NewsModel;
import com.chenyu.monster.news.model.NewsModelImpl;
import com.chenyu.monster.news.view.NewsView;

import java.util.List;

/**
 * Created by chenyu on 16/4/11.
 */
public class NewsPresenterImpl implements NewsPresenter, NewsModelImpl.OnLoaderNewsListListener {
    private NewsModel newsModel;
    private NewsView newsView;

    public NewsPresenterImpl(NewsView newsView) {
        this.newsModel = new NewsModelImpl();
        this.newsView = newsView;
    }

    @Override
    public void loadNews(int type, int pageIndex) {
        if (pageIndex == 0)
            newsView.showProgress();
        newsModel.loadNews(getUrl(type, pageIndex), type, this);
    }

    /**
     * 根据页面拼url
     *
     * @param type
     * @param pageIndex
     * @return
     */
    private String getUrl(int type, int pageIndex) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NewsType.NEWS_TYPE_TOP:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
            case NewsType.NEWS_TYPE_NBA:
                sb.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsType.NEWS_TYPE_CARS:
                sb.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsType.NEWS_TYPE_JOKES:
                sb.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
                break;
            default:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
        }
        sb.append("/").append(pageIndex).append(Urls.END_URL);
        return sb.toString();
    }

    @Override
    public void onSuccess(List<NewsBean> newsBeanList) {
        newsView.addNews(newsBeanList);
        newsView.hideProgress();
    }

    @Override
    public void onFail(String msg, Exception e) {
        newsView.hideProgress();
        newsView.showFailMsg();
    }
}
