package com.chenyu.monster.weather;

import android.text.TextUtils;

import com.chenyu.monster.model.WeatherBean;
import com.chenyu.monster.util.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyu on 16/4/19.
 */
public class WeatherJsonUtils {
    /**
     * 获取天气信息
     *
     * @param json
     * @return
     */
    public static List<WeatherBean> getWeatherInfo(String json) {
        List<WeatherBean> weathers = new ArrayList<>();
        if (TextUtils.isEmpty(json)) {
            return weathers;
        }
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(json).getAsJsonObject();
        String status = object.get("status").getAsString();
        if ("1000".equals(status)) {
            JsonArray array = object.getAsJsonObject("data").getAsJsonArray("forecast");
            int size = array.size();
            for (int i = 0; i < size; i++) {
                WeatherBean weather = JsonUtils.deserialize(array.get(i).getAsJsonObject(), WeatherBean.class);
                weathers.add(weather);
            }
        }
        return weathers;
    }

    /**
     * 获取城市名字
     *
     * @param json
     * @return
     */
    public static String getCityName(String json) {
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(json).getAsJsonObject();
        JsonElement status = object.get("status");
        if (status != null && "OK".equals(status.getAsString())) {
            JsonObject result = object.getAsJsonObject("result");
            if (result != null) {
                JsonObject address = result.getAsJsonObject("addressComponent");
                if (address != null) {
                    JsonElement city = address.get("city");
                    if (city != null) {
                        return city.getAsString().replace("市", "");
                    }
                }
            }
        }
        return null;
    }
}
