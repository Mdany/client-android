package com.chenyu.monster.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by chenyu on 16/5/23.
 * picasso
 */
public class ImageLoaderUtils {
    /**
     * 加载path文件:url file等
     * @param context
     * @param path
     * @param imageView
     */
    public static void load(Context context, String path, ImageView imageView) {
        Picasso.with(context)
                .load(path)
                .into(imageView);
    }
    /**
     * 加载path文件:url file等
     * @param context
     * @param path
     * @param width
     * @param height
     * @param imageView
     */
    public static void load(Context context, String path, int width, int height, ImageView imageView) {
        Picasso.with(context)
                .load(path)
                .centerCrop()
                .resize(width, height)
                .into(imageView);
    }
    /**
     * 加载资源图片
     * @param context
     * @param resId
     * @param width
     * @param height
     * @param imageView
     */
    public static void load(Context context, int resId, int width, int height, ImageView imageView) {
        Picasso.with(context)
                .load(resId)
                .centerCrop()
                .resize(width, height)
                .into(imageView);
    }
    /**
     * 加载资源图片
     * @param context
     * @param resId
     * @param defaultId
     * @param errorId
     * @param width
     * @param height
     * @param imageView
     */
    public static void load(Context context, int resId, int defaultId, int errorId, int width, int height, ImageView imageView) {
        Picasso.with(context)
                .load(resId)
                .centerCrop()
                .resize(width, height)
                .placeholder(defaultId)
                .error(errorId)
                .into(imageView);
    }

    /**
     * 加载path文件:url file等
     * @param context
     * @param path
     * @param defaultId
     * @param errorId
     * @param width
     * @param height
     * @param imageView
     */
    public static void load(Context context, String path, int defaultId, int errorId, int width, int height, ImageView imageView) {
        Picasso.with(context)
                .load(path)
                .centerCrop()
                .resize(width, height)
                .placeholder(defaultId)
                .error(errorId)
                .into(imageView);
    }

    /**
     * 加载资源图片
     * @param context
     * @param resId
     * @param defaultDrawable
     * @param errorDrawable
     * @param width
     * @param height
     * @param imageView
     */
    public static void load(Context context, int resId, Drawable defaultDrawable, Drawable errorDrawable, int width, int height, ImageView imageView) {
        Picasso.with(context)
                .load(resId)
                .centerCrop()
                .resize(width, height)
                .placeholder(defaultDrawable)
                .error(errorDrawable)
                .into(imageView);
    }

    /**
     * 加载path文件:url file等
     * @param context
     * @param path
     * @param defaultDrawable
     * @param errorDrawable
     * @param width
     * @param height
     * @param imageView
     */
    public static void load(Context context, String path, Drawable defaultDrawable, Drawable errorDrawable, int width, int height, ImageView imageView) {
        Picasso.with(context)
                .load(path)
                .centerCrop()
                .resize(width, height)
                .placeholder(defaultDrawable)
                .error(errorDrawable)
                .into(imageView);
    }

    /**
     * 加载file文件
     * @param context
     * @param file
     * @param defaultId
     * @param errorId
     * @param width
     * @param height
     * @param imageView
     */
    public static void load(Context context, File file, int defaultId, int errorId, int width, int height, ImageView imageView) {
        Picasso.with(context)
                .load(file)
                .centerCrop()
                .resize(width, height)
                .placeholder(defaultId)
                .error(errorId)
                .into(imageView);
    }

    /**
     * 加载uri下的文件
     * @param context
     * @param uri
     * @param imageView
     */
    public static void load(Context context, Uri uri, ImageView imageView) {
        Picasso.with(context)
                .load(uri)
                .into(imageView);
    }

    /**
     * 加载uri下的文件
     * @param context
     * @param uri
     * @param width
     * @param height
     * @param imageView
     */
    public static void load(Context context, Uri uri, int width, int height, ImageView imageView) {
        Picasso.with(context)
                .load(uri)
                .centerCrop()
                .resize(width, height)
                .into(imageView);
    }

    /**
     * 加载uri下的文件
     * @param context
     * @param uri
     * @param defaultId
     * @param errorId
     * @param width
     * @param height
     * @param imageView
     */
    public static void load(Context context, Uri uri, int defaultId, int errorId, int width, int height, ImageView imageView) {
        Picasso.with(context)
                .load(uri)
                .centerCrop()
                .resize(width, height)
                .placeholder(defaultId)
                .error(errorId)
                .into(imageView);
    }
}
