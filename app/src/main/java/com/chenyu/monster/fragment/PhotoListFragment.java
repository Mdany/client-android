package com.chenyu.monster.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.chenyu.monster.R;
import com.chenyu.monster.adapter.PhotoListAdapter;
import com.chenyu.monster.framework.BaseListFragment;
import com.chenyu.monster.model.DPhoto;

import java.util.ArrayList;

/**
 * Created by chenyu on 16/2/4.
 */
public class PhotoListFragment extends BaseListFragment<PhotoListAdapter, LinearLayoutManager, DefaultItemAnimator> {
    private PhotoListAdapter adapter;

    public PhotoListFragment() {
        super(R.layout.f_recycle_list, R.id.refresh_srl, R.id.list_rlv);
    }

    @Override
    public void viewDidLoad() {
    }

    @Override
    protected PhotoListAdapter getAdapter() {
        adapter = new PhotoListAdapter(mActivity, new ArrayList<DPhoto>());
        return adapter;
    }

    @Override
    protected LinearLayoutManager getManager() {
        return new LinearLayoutManager(mActivity);
    }

    @Override
    protected DefaultItemAnimator getItemAnimator() {
        return new DefaultItemAnimator();
    }
}
