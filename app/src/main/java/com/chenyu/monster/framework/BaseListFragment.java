package com.chenyu.monster.framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by chenyu on 16/2/4.
 * <p/>
 * 含有recyclerView的fragment
 * LayoutManager处理视图位置，ItemAnimator处理动画。ViewHolder是最后的部分：它的职责是处理发生在RecyclerView中特定item的事件。
 */
public abstract class BaseListFragment<A extends BaseListAdapter, L extends RecyclerView.LayoutManager, I extends RecyclerView.ItemAnimator> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    protected int recyclerId;
    protected int swipeLayoutId;
    protected RecyclerView recyclerView;
    protected A adapter;
    protected L layoutManager;
    protected I itemAnimator;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected List<Entity> data;

    public BaseListFragment(int rootLayoutId, int swipeLayoutId, int recyclerId) {
        super(rootLayoutId);
        this.swipeLayoutId = swipeLayoutId;
        this.recyclerId = recyclerId;
    }

    protected abstract A getAdapter();

    protected abstract L getManager();

    protected abstract I getItemAnimator();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        rootView = inflater.inflate(rootLayoutID, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(swipeLayoutId);
        recyclerView = (RecyclerView) rootView.findViewById(recyclerId);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        adapter = getAdapter();
        layoutManager = getManager();
        itemAnimator = getItemAnimator();

        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout.setOnRefreshListener(this);
        viewDidLoad();
        onRefresh();
        return rootView;
    }

    @Override
    public void onRefresh() {
        refreshData();
    }

    protected void refreshData() {
    }

    protected boolean hasData() {
        return data != null && !data.isEmpty();
    }
}