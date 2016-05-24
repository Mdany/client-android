package com.chenyu.monster;

import android.app.Application;

import com.chenyu.monster.model.DUser;
import com.chenyu.monster.util.PreUtils;
import com.google.gson.Gson;

/**
 * Created by chenyu on 16/1/29.
 */
public class DoitApplication extends Application {
    public static DoitApplication application;
    private DUser user;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public DUser getUser() {
        if (user == null) {
            String userJson = PreUtils.getString(this, PreUtils.KEY_USER);
            user = new Gson().fromJson(userJson, DUser.class);
            return user;
        } else {
            return user;
        }
    }

    public void setUser(DUser user) {
        this.user = user;
        PreUtils.saveString(this, PreUtils.KEY_USER, new Gson().toJson(user));
    }
}
