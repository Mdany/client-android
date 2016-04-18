package com.chenyu.monster.news.widget;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseActivity;
import com.chenyu.monster.model.NewsBean;
import com.chenyu.monster.news.presenter.NewsDetailPresenter;
import com.chenyu.monster.news.presenter.NewsPresenter;
import com.chenyu.monster.news.presenter.NewsPresenterImpl;

import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * Created by chenyu on 16/4/13.
 */
public class NewsDetailActivity extends BaseActivity {
    private static String KEY_NEWS = "news";
    private NewsBean news;
    private NewsDetailPresenter newsDetailPresenter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private HtmlTextView htmlTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.a_news_detail;
    }

    @Override
    protected void initView() {
        news = getIntent().getParcelableExtra(KEY_NEWS);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_layout);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        htmlTextView = (HtmlTextView) findViewById(R.id.htv_news_detail_content);

        setSupportActionBar(toolbar);
        
    }
}
