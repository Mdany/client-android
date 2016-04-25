package com.chenyu.monster.util;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenyu on 16/4/5.
 * 网络请求工具类
 */
public class OkHttpUtil {
    private static final String TAG = "OkHttpUtil";
    private static OkHttpUtil mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public OkHttpUtil() {
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        mBuilder.connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
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

    /**
     * get请求
     *
     * @param url
     * @param callback
     */
    private void getRequest(String url, final ResultCallback callback) {
        Request request = new Request.Builder().url(url).build();
        deliveryResult(callback, request);

    }

    /**
     * post请求
     *
     * @param url
     * @param callback
     * @param params
     */
    private void postRequest(String url, ResultCallback callback, List<Param> params) {
        Request request = new Request.Builder().url(url).build();
        deliveryResult(callback, request);
    }

    /**
     * G\P\D\U统一请求
     *
     * @param callback
     * @param request
     */
    private void deliveryResult(final ResultCallback callback, Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailCallback(callback, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                try {
                    if (callback.mType == String.class) {
                        sendSuccessCallback(callback, str);
                    } else {
                        Object obj = JsonUtils.deserialize(str, callback.mType);
                        sendSuccessCallback(callback, obj);
                    }
                } catch (Exception e) {
                    LogUtils.e(TAG, "convert json failure", e);
                    sendFailCallback(callback, e);
                }
            }
        });
    }

    /**
     * 请求成功后刷新UI
     */
    private void sendSuccessCallback(final ResultCallback callback, final Object obj) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(obj);
                }
            }
        });
    }

    private void sendFailCallback(final ResultCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFail(e);
                }
            }
        });
    }

    //外部调用

    /**
     * get请求
     * @param url
     * @param callback
     */
    public static void get(String url, ResultCallback callback) {
        getInstance().getRequest(url, callback);
    }

    /**
     * post请求
     * @param url
     * @param callback
     * @param params
     */
    public static void post(String url, ResultCallback callback, List<Param> params) {
        getInstance().postRequest(url, callback, params);
    }

    /**
     * @param <T>
     */
    public static abstract class ResultCallback<T> {
        Type mType;

        public ResultCallback() {
            this.mType = getSuperclassTypeParameter(getClass());
        }

        private Type getSuperclassTypeParameter(Class<?> subClass) {
            Type superClass = subClass.getGenericSuperclass();
            if (superClass instanceof Class) {
                throw new RuntimeException("Missing type parameter");
            }
            ParameterizedType parameterized = (ParameterizedType) superClass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        /**
         * 请求成功
         *
         * @param response
         */
        public abstract void onSuccess(T response);

        /**
         * 请求失败
         *
         * @param e
         */
        public abstract void onFail(Exception e);
    }

    /**
     * post请求参数
     */
    public static class Param {
        public String key;
        public String value;

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public Param() {
        }
    }
}
