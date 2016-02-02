package com.chenyu.monster.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by chenyu on 16/2/1.
 */
public class ToastUtil {
    public static void show(Context mContext,String str){
        Toast.makeText(mContext,str,Toast.LENGTH_SHORT);
    }
}
