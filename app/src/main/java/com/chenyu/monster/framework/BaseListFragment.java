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
public abstract class BaseListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private int recyclerId;
    private int swipeLayoutId;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Entity> data;

    public BaseListFragment(int rootLayoutId, int swipeLayoutId, int recyclerId) {
        super(rootLayoutId);
        this.swipeLayoutId = swipeLayoutId;
        this.recyclerId = recyclerId;
    }

    protected abstract RecyclerView.Adapter getAdapter();

    protected abstract RecyclerView.LayoutManager getManager();

    protected abstract RecyclerView.ItemAnimator getItemAnimator();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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