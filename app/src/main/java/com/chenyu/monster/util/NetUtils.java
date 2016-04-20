package com.chenyu.monster.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by chenyu on 16/4/19.
 */
public class NetUtils {
    public static boolean checkNetWork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connectivityManager.getActiveNetworkInfo();
        return network.isAvailable();
    }
}
