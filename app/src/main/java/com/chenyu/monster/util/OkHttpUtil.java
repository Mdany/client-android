package com.chenyu.monster.util;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by chenyu on 16/4/5.
 * 网络请求工具类
 */
public class OkHttpUtil {
    private static OkHttpUtil mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public OkHttpUtil() {
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        mBuilder.connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
        //cookie enabled
        mOkHttpClient = mBuilder.build();
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 单例
     *
     * @return
     */
    public static OkHttpUtil getInstance() {
        if (mInstance == null) {
            mInstance = new OkHttpUtil();
        }
        return mInstance;
    }

    private void getRequest(String url, final ResultCallback) {

    }

    private void postRequest() {
    }

    private void deliveryResult() {
    }

    public static abstract class ResultCallback<T> {
        Type mType;

        public ResultCallback() {
            this.mType = getSuperclassTypeParameter(getClass());
        }

        private Type getSuperclassTypeParameter(Class<?> subClass) {
            Type superClass = subClass.getGenericSuperclass();
            if (subClass instanceof Class) {
                throw new RuntimeException("Missing type parameter");
            }
            ParameterizedType parameterized = (ParameterizedType) superClass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        /**
         * 请求成功
         * @param response
         */
        public abstract  void onSuccess(T response);

        /**
         * 请求失败
         * @param e
         */
        public abstract void onFail(Exception e);
    }
}
