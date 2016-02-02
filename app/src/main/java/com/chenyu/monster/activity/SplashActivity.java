package com.chenyu.monster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseActivity;

/**
 * Created by chenyu on 16/1/29.
 */
public class SplashActivity extends BaseActivity {
    private ImageView splashIv;
    private Handler mHandler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.a_splsh;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        splashIv = (ImageView) findViewById(R.id.splash_iv);
        splashIv.setImageResource(R.mipmap.splash);
        delayStartHome();
    }

    private void delayStartHome() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(HomeActivity.class);
            }
        }, 3000);
    }
}
