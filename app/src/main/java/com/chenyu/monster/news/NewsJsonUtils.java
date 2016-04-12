package com.chenyu.monster.news;

import com.chenyu.monster.model.NewsBean;
import com.chenyu.monster.model.NewsDetailBean;
import com.chenyu.monster.util.JsonUtils;
import com.chenyu.monster.util.LogUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyu on 16/4/11.
 */
public class NewsJsonUtils {
    private final static String TAG = "NewsJsonUtils";

    /**
     * 解析新闻列表
     *
     * @param json
     * @param id
     * @return
     */
    public static List<NewsBean> readJsonNewsBean(String json, String id) {
        List<NewsBean> beanList = new ArrayList<>();
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(json).getAsJsonObject();
            JsonElement element = jsonObject.get(id);
            if (element == null) return null;
            JsonArray array = element.getAsJsonArray();
            int size = array.size();
            for (int i = 0; i < size; i++) {
                JsonObject object = array.get(i).getAsJsonObject();
                if (object.has("skipType") && "special".equals(object.get("skipType").getAsString())) {
                    continue;
                }
                if (object.has("TAGS") && !object.has("TAG")) {
                    continue;
                }
                if (!object.has("imgextra")) {
                    NewsBean news = JsonUtils.deserialize(object, NewsBean.class);
                    beanList.add(news);
                }
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "readJsonNewsBeans error", e);
        }
        return beanList;
    }

    /**
     * 解析新闻详情
     *
     * @param json
     * @param newsId
     * @return
     */
    public static NewsDetailBean readJsonNewsDetailBean(String json, String newsId) {
        NewsDetailBean newsDetailBean = null;
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(json).getAsJsonObject();
            JsonElement jsonElement = jsonObject.get(newsId);
            if (jsonElement == null) {
                return null;
            }
            newsDetailBean = JsonUtils.deserialize(jsonElement.getAsJsonObject(), NewsDetailBean.class);
        } catch (Exception e) {
            LogUtils.e(TAG, "readJsonNewsBeans error", e);
        }
        return newsDetailBean;
    }
}
