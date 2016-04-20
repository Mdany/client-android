package com.chenyu.monster.weather.presenter;

import android.content.Context;

import com.chenyu.monster.model.WeatherBean;
import com.chenyu.monster.util.NetUtils;
import com.chenyu.monster.weather.model.WeatherModel;
import com.chenyu.monster.weather.model.WeatherModelImpl;
import com.chenyu.monster.weather.view.WeatherView;

import java.util.List;

/**
 * Created by chenyu on 16/4/19.
 */
public class WeatherPresenterImpl implements WeatherPresenter, WeatherModelImpl.LoadWeatherListener {
    private Context context;
    private WeatherModel weatherModel;
    private WeatherView weatherView;

    public WeatherPresenterImpl(Context context, WeatherView weatherView) {
        this.context = context;
        this.weatherView = weatherView;
        weatherModel = new WeatherModelImpl();
    }

    @Override
    public void loadWeatherData() {
        weatherView.showProgress();
        if (!NetUtils.checkNetWork(context)) {
            weatherView.hideProgress();
            weatherView.showErrorToast("No Internet");
            return;
        }
        weatherModel.loadLocation(context, new WeatherModelImpl.LoadLocationListener() {
            @Override
            public void onSuccess(String cityName) {
                weatherView.setCity(cityName);
                weatherModel.loadWeatherData(cityName, WeatherPresenterImpl.this);
            }

            @Override
            public void onFailure(String msg, Exception e) {
                weatherView.showErrorToast("定位失败");
                weatherView.setCity("北京");
                weatherModel.loadWeatherData("北京", WeatherPresenterImpl.this);
            }
        });
    }

    @Override
    public void onSuccess(List<WeatherBean> weathers) {
        if (weathers != null && weathers.size() > 0) {
            WeatherBean todayWeather = weathers.remove(0);
            weatherView.setToday(todayWeather.date);
            weatherView.setWeather(todayWeather.weather);
            weatherView.setTemperature(todayWeather.temperature);
            weatherView.setWeatherImage(todayWeather.imageRes);
            weatherView.setWind(todayWeather.wind);
        }
        weatherView.setWeatherData(weathers);
        weatherView.showWeatherLayout();
        weatherView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        weatherView.hideProgress();
        weatherView.showErrorToast("获取天气数据失败");
    }
}