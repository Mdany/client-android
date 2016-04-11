package com.chenyu.monster.model;

import com.chenyu.monster.framework.Entity;

import java.util.List;

/**
 * Created by chenyu on 16/3/31.
 */
public class NewsDetailBean extends Entity {
    public String docid;
    public String title;
    public String source;
    public String body;
    public String ptime;
    public String cover;
    public List<String> imgList;
}
