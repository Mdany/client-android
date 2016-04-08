package com.chenyu.monster.Images.widget;

import android.support.v7.widget.RecyclerView;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseListFragment;

/**
 * Created by chenyu on 16/4/8.
 */
public class ImageFragment extends BaseListFragment implements  {

    public ImageFragment() {
        super(R.layout.f_recycle_list, R.id.refresh_srl, R.id.list_rlv);
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
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
}