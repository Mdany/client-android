package com.chenyu.monster.weather.view;

import com.chenyu.monster.model.WeatherBean;

import java.util.List;

/**
 * Created by chenyu on 16/4/19.
 */
public interface WeatherView {
    void showProgress();

    void hideProgress();

    void setCity(String city);

    void setToday(String date);

    void setTemperature(String temperature);

    void setWind(String wind);

    void setWeather(String weather);

    void setWeatherImage(int res);

    void setWeatherData(List<WeatherBean> list);

    void showErrorToast(String msg);

    void showWeatherLayout();
}
