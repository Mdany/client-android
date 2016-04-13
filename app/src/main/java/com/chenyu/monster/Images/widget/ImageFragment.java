package com.chenyu.monster.Images.widget;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chenyu.monster.Images.ImageAdapter;
import com.chenyu.monster.Images.presenter.ImagePresenter;
import com.chenyu.monster.Images.presenter.ImagePresenterImpl;
import com.chenyu.monster.Images.view.ImageView;
import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseListFragment;
import com.chenyu.monster.model.ImageBean;
import com.chenyu.monster.util.SnackUtil;

import java.util.List;

/**
 * Created by chenyu on 16/4/8.
 * 图片列表fragment
 */
public class ImageFragment extends BaseListFragment<ImageAdapter, LinearLayoutManager, DefaultItemAnimator> implements ImageView {
    private ImagePresenter imagePresenter;

    public ImageFragment() {
        super(R.layout.f_recycle_list, R.id.refresh_srl, R.id.list_rlv);
    }

    @Override
    public void viewDidLoad() {
        imagePresenter = new ImagePresenterImpl(this);
    }

    @Override
    protected ImageAdapter getAdapter() {
        return new ImageAdapter(mActivity);
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
    public void addImages(List<ImageBean> imageBeanList) {
        if (imageBeanList != null && adapter != null) {
            adapter.setData(imageBeanList);
        }
    }

    @Override
    protected void refreshData() {
        imagePresenter.loadImageList();
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
    public void showLoadFailMsg() {
        View view = mActivity == null ? recyclerView.getRootView() : mActivity.findViewById(R.id.drawer_layout);
        SnackUtil.show(view, R.string.data_load_fail);
    }
}