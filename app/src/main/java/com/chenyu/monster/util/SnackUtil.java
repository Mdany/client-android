package com.chenyu.monster.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by chenyu on 16/2/1.
 */
public class SnackUtil {
    public static void show(View view,int strId){
        Snackbar.make(view, strId, Snackbar.LENGTH_SHORT).show();
    }
}
