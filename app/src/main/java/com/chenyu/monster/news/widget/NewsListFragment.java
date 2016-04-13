package com.chenyu.monster.news.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chenyu.monster.R;
import com.chenyu.monster.commons.Urls;
import com.chenyu.monster.framework.BaseListFragment;
import com.chenyu.monster.model.NewsBean;
import com.chenyu.monster.news.presenter.NewsPresenter;
import com.chenyu.monster.news.presenter.NewsPresenterImpl;
import com.chenyu.monster.news.view.NewsView;
import com.chenyu.monster.util.SnackUtil;

import java.util.List;

/**
 * Created by chenyu on 16/4/12.
 */
public class NewsListFragment extends BaseListFragment<NewsListAdapter, LinearLayoutManager, DefaultItemAnimator> implements NewsView {
    private static String KEY_TYPE = "type";
    private static String KEY_NEWS = "news";
    private NewsPresenter newsPresenter;
    /**
     * 页码
     */
    private int pageIndex = 0;
    /**
     * 哪种 新闻列表
     */
    private int type;

    public NewsListFragment() {
        super(R.layout.f_recycle_list, R.id.refresh_srl, R.id.list_rlv);
    }

    public static NewsListFragment newInstance(int type) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected NewsListAdapter getAdapter() {
        return new NewsListAdapter(mActivity);
    }

    @Override
    protected LinearLayoutManager getManager() {
        return new LinearLayoutManager(mActivity);
    }

    @Override
    protected DefaultItemAnimator getItemAnimator() {
        return new DefaultItemAnimator();
    }

    @Override
    public void viewDidLoad() {
        type = getArguments().getInt(KEY_TYPE);
        newsPresenter = new NewsPresenterImpl(this);

        adapter.setItemClickListener(new NewsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsBean news = adapter.getItem(position);
                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra(KEY_NEWS, news);
                //Material Design页面动画
                View animationView = view.findViewById(R.id.iv_news_avatar);
                ActivityOptionsCompat optionsCompat
                        = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, animationView, getString(R.string.transition_news_avatar));
                ActivityCompat.startActivity(mActivity, intent, optionsCompat.toBundle());
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * 最后一个可见view
             */
            private int lastVisibleItemPosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition == adapter.getItemCount() - 1
                        && adapter.isShowFooter()) {//加载更多
                    newsPresenter.loadNews(type, pageIndex += Urls.PAZE_SIZE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addNews(List<NewsBean> beanList) {
        adapter.setIsShowFooter(true);
        if (pageIndex == 0) {// 第一次加载数据
            adapter.setData(beanList);
        } else {
            if (beanList == null) {//没有更多数据不显示footer
                adapter.setIsShowFooter(false);
            }
            adapter.addItems(beanList);
        }
        pageIndex += Urls.PAZE_SIZE;
    }

    @Override
    protected void refreshData() {
        pageIndex = 0;
        newsPresenter.loadNews(type, pageIndex);
    }

    @Override
    public void showFailMsg() {
        SnackUtil.show(mActivity == null ? recyclerView.getRootView() : mActivity.findViewById(R.id.drawer_layout), R.string.data_load_fail);
    }
}
