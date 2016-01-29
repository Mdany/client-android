package com.chenyu.monster.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chenyu on 16/1/29.
 */
public class PreUtils {
    public final static String NAME = "CONFIG";

    public final static String KEY_USER = "key_user";

    public static String getString(Context mContext, String key) {
        return getString(mContext, key, "");
    }

    public static String getString(Context mContext, String key, String defaultValue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static void saveString(Context mContext, String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
