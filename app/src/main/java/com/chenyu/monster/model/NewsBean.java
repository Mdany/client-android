package com.chenyu.monster.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.chenyu.monster.framework.Entity;

/**
 * Created by chenyu on 16/3/31.
 */
public class NewsBean extends Entity implements Parcelable {
    public String docid;
    public String title;
    public String digest;
    public String imgsrc;
    public String source;
    public String ptime;
    public String tag;

    protected NewsBean(Parcel in) {
        docid = in.readString();
        title = in.readString();
        digest = in.readString();
        imgsrc = in.readString();
        source = in.readString();
        ptime = in.readString();
        tag = in.readString();
    }

    public static final Creator<NewsBean> CREATOR = new Creator<NewsBean>() {
        @Override
        public NewsBean createFromParcel(Parcel in) {
            return new NewsBean(in);
        }

        @Override
        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(docid);
        dest.writeString(title);
        dest.writeString(digest);
        dest.writeString(imgsrc);
        dest.writeString(source);
        dest.writeString(ptime);
        dest.writeString(tag);
    }
}
