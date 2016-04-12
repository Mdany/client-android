package com.chenyu.monster.news.widget;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseListAdapter;
import com.chenyu.monster.framework.BaseListFragment;

/**
 * Created by chenyu on 16/4/12.
 */
public class NewsListFragment extends BaseListFragment {
    private static String KEY_TYPE = "type";

    public NewsListFragment() {
        super(R.layout.f_recycle_list, R.id.refresh_srl, R.id.list_rlv);
    }

    public static NewsListFragment newInstance(int type) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TYPE,type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected BaseListAdapter getAdapter() {
        return null;
    }

    @Override
    protected RecyclerView.LayoutManager getManager() {
        return null;
    }

    @Override
    protected RecyclerView.ItemAnimator getItemAnimator() {
        return null;
    }

    @Override
    public void viewDidLoad() {
        int type = getArguments().getInt(KEY_TYPE);
    }
}
