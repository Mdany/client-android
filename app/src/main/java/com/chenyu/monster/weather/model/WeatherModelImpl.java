package com.chenyu.monster.weather.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;

import com.chenyu.monster.commons.Urls;
import com.chenyu.monster.model.WeatherBean;
import com.chenyu.monster.util.LogUtils;
import com.chenyu.monster.util.OkHttpUtil;
import com.chenyu.monster.weather.WeatherJsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by chenyu on 16/4/19.
 */
public class WeatherModelImpl implements WeatherModel {
    private static final String TAG = "WeatherModelImpl";

    @Override
    public void loadWeatherData(String cityName, final LoadWeatherListener listener) {
        try {
            String url = Urls.WEATHER + URLEncoder.encode(cityName, "utf-8");
            OkHttpUtil.get(url, new OkHttpUtil.ResultCallback<String>() {
                @Override
                public void onSuccess(String response) {
                    listener.onSuccess(WeatherJsonUtils.getWeatherInfo(response));
                }

                @Override
                public void onFail(Exception e) {
                    listener.onFailure("load weather data fail.", e);
                }
            });
        } catch (UnsupportedEncodingException e) {
            LogUtils.e(TAG, "");
            e.printStackTrace();
        }
    }

    @Override
    public void loadLocation(Context context, final LoadLocationListener listener) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                LogUtils.e(TAG, "location failure.");
                listener.onFailure("location failure.", null);
                return;
            }
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location == null) {
            LogUtils.e(TAG, "location failure.");
            listener.onFailure("location failure.", null);
            return;
        }

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        String url = getLocationUrl(latitude, longitude);
        OkHttpUtil.get(url, new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(WeatherJsonUtils.getCityName(response));
            }

            @Override
            public void onFail(Exception e) {
                listener.onFailure("load location failure.", e);
            }
        });
    }

    /**
     * 把经纬度拼到url上面
     *
     * @param latitude
     * @param longitude
     * @return
     */
    private String getLocationUrl(double latitude, double longitude) {
        StringBuilder sb = new StringBuilder(Urls.INTERFACE_LOCATION);
        sb.append("?output=json").append("&referer=32D45CBEEC107315C553AD1131915D366EEF79B4");
        sb.append("&location=").append(latitude).append(",").append(longitude);
        LogUtils.d(TAG, sb.toString());
        return sb.toString();
    }

    /**
     * 根据定位加载天气数据
     */
    public interface LoadWeatherListener {
        void onSuccess(List<WeatherBean> weathers);

        void onFailure(String msg, Exception e);
    }

    /**
     * 定位，获取当前城市信息
     */
    public interface LoadLocationListener {
        void onSuccess(String cityName);

        void onFailure(String msg, Exception e);
    }
}
