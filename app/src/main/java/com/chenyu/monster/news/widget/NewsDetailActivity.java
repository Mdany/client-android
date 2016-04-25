package com.chenyu.monster.news.widget;

import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseActivity;
import com.chenyu.monster.model.NewsBean;
import com.chenyu.monster.news.presenter.NewsDetailPresenter;
import com.chenyu.monster.news.presenter.NewsDetailPresenterImpl;
import com.chenyu.monster.news.view.NewsDetailView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * Created by chenyu on 16/4/13.
 */
public class NewsDetailActivity extends BaseActivity implements NewsDetailView {
    private static String KEY_NEWS = "news";
    private NewsBean news;
    private NewsDetailPresenter newsDetailPresenter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ProgressBar progressBar;
    private SimpleDraweeView newsAvatar;
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
        newsAvatar = (SimpleDraweeView) findViewById(R.id.iv_news_detail_avatar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        htmlTextView = (HtmlTextView) findViewById(R.id.htv_news_detail_content);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initData();
    }

    private void initData() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_layout);
        if (news != null && !TextUtils.isEmpty(news.title)) {
            collapsingToolbarLayout.setTitle(news.title);
        } else {
            collapsingToolbarLayout.setTitle("");
        }

        if (!TextUtils.isEmpty(news.imgsrc)) {
            newsAvatar.setImageURI(Uri.parse(news.imgsrc));
        } else {
            newsAvatar.setVisibility(View.GONE);
        }

        newsDetailPresenter = new NewsDetailPresenterImpl(this);
        newsDetailPresenter.loadNewsDetail(news.docid);
    }

    @Override
    public void showNewsDetailContent(String body) {
        htmlTextView.setHtmlFromString(body, new HtmlTextView.LocalImageGetter());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
