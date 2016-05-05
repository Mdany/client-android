package com.chenyu.monster.weather.widget;

import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseFragment;
import com.chenyu.monster.model.WeatherBean;
import com.chenyu.monster.weather.presenter.WeatherPresenter;
import com.chenyu.monster.weather.presenter.WeatherPresenterImpl;
import com.chenyu.monster.weather.view.WeatherView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyu on 16/4/19.
 */
public class WeatherFragment extends BaseFragment implements WeatherView {
    private WeatherPresenter weatherPresenter;
    private TextView mTodayTV;
    private ImageView mTodayWeatherImage;
    private TextView mTodayTemperatureTV;
    private TextView mTodayWindTV;
    private TextView mTodayWeatherTV;
    private TextView mCityTV;
    private ProgressBar mProgressBar;
    private LinearLayout mWeatherLayout;
    private LinearLayout mWeatherContentLayout;

    public WeatherFragment() {
        super(R.layout.f_weather);
    }

    @Override
    public void viewDidLoad() {
        initView();
        initData();
    }

    private void initView() {
        mTodayTV = (TextView) rootView.findViewById(R.id.today);
        mTodayWeatherImage = (ImageView) rootView.findViewById(R.id.weatherImage);
        mTodayTemperatureTV = (TextView) rootView.findViewById(R.id.weatherTemp);
        mTodayWindTV = (TextView) rootView.findViewById(R.id.wind);
        mTodayWeatherTV = (TextView) rootView.findViewById(R.id.weather);
        mCityTV = (TextView) rootView.findViewById(R.id.city);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress);
        mWeatherLayout = (LinearLayout) rootView.findViewById(R.id.weather_layout);
        mWeatherContentLayout = (LinearLayout) rootView.findViewById(R.id.weather_content);
    }

    private void initData() {
        weatherPresenter = new WeatherPresenterImpl(mActivity, this);
        weatherPresenter.loadWeatherData();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showWeatherLayout() {
        mWeatherLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCity(String city) {
        mCityTV.setText(city);
    }

    @Override
    public void setToday(String data) {
        mTodayTV.setText(data);
    }

    @Override
    public void setTemperature(String temperature) {
        mTodayTemperatureTV.setText(temperature);
    }

    @Override
    public void setWind(String wind) {
        mTodayWindTV.setText(wind);
    }

    @Override
    public void setWeather(String weather) {
        mTodayWeatherTV.setText(weather);
    }

    @Override
    public void setWeatherImage(int res) {
        mTodayWeatherImage.setImageResource(res);
    }

    @Override
    public void setWeatherData(List<WeatherBean> lists) {
        for (WeatherBean weatherBean : lists) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.c_weather, null, false);
            TextView dateTV = (TextView) view.findViewById(R.id.date);
            ImageView todayWeatherImage = (ImageView) view.findViewById(R.id.weatherImage);
            TextView todayTemperatureTV = (TextView) view.findViewById(R.id.weatherTemp);
            TextView todayWindTV = (TextView) view.findViewById(R.id.wind);
            TextView todayWeatherTV = (TextView) view.findViewById(R.id.weather);

            dateTV.setText(weatherBean.week);
            todayTemperatureTV.setText(weatherBean.temperature);
            todayWindTV.setText(weatherBean.wind);
            todayWeatherTV.setText(weatherBean.weather);
            todayWeatherImage.setImageResource(weatherBean.imageRes);
            mWeatherContentLayout.addView(view);
        }
    }

    @Override
    public void showErrorToast(String msg) {
        Snackbar.make(getActivity().findViewById(R.id.drawer_layout), msg, Snackbar.LENGTH_SHORT).show();
    }
}
