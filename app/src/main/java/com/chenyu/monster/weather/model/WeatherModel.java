package com.chenyu.monster.weather.model;

import android.content.Context;

/**
 * Created by chenyu on 16/4/19.
 */
public interface WeatherModel {
    void loadWeatherData(String cityName, WeatherModelImpl.LoadWeatherListener listener);
    void loadLocation(Context context, WeatherModelImpl.LoadLocationListener listener);
}
