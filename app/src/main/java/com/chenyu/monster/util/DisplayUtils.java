package com.chenyu.monster.util;

import android.content.Context;
import android.util.DisplayMetrics;

import com.chenyu.monster.DoitApplication;

/**
 * Created by chenyu on 16/4/20.
 */
public class DisplayUtils {
    private static DisplayMetrics metrics;

    public static DisplayMetrics getInstance() {
        if (metrics == null) {
            metrics = DoitApplication.application.getResources().getDisplayMetrics();
        }
        return metrics;
    }

    public static int getScreenWidth() {
        return getInstance().widthPixels;
    }

    public static int getScreenHeight() {
        return getInstance().heightPixels;
    }

    public static int dp2px(int dp) {
        float density = getInstance().density;
        return (int) (dp * density + 0.5f);
    }

    public static int px2dp(int px) {
        float density = getInstance().density;
        return (int) (px / density + 0.5f);
    }

    /**
     * 反射获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
        }
        return statusHeight;
    }
}
