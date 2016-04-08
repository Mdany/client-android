package com.chenyu.monster.home.presenter;

import com.chenyu.monster.R;
import com.chenyu.monster.home.View.HomeView;

/**
 * Created by chenyu on 16/3/31.
 */
public class HomePresenterImpl implements HomePresenter {
    private HomeView mHomeView;

    public HomePresenterImpl(HomeView mHomeView) {
        this.mHomeView = mHomeView;
    }

    @Override
    public void switchDrawerItem(int id) {
        switch (id) {
            case R.id.nav_news:
                mHomeView.switch2News();
                break;
            case R.id.nav_image:
                mHomeView.switch2Image();
                break;
            case R.id.nav_weather:
                mHomeView.switch2Weather();
                break;
            case R.id.nav_about:
                mHomeView.switch2About();
                break;
        }
    }
}
