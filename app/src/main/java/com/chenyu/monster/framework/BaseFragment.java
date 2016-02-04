package com.chenyu.monster.framework;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chenyu on 16/2/4.
 */
public abstract class BaseFragment extends Fragment {
    private int rootLayoutID;
    protected View rootView;
    protected Activity mActivity;

    public BaseFragment(int rootLayoutID) {
        super();
        this.rootLayoutID = rootLayoutID;
    }

    public BaseFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(rootLayoutID, container, false);
        mActivity = getActivity();
        return rootView;
    }
}
