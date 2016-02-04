package com.chenyu.monster.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by chenyu on 16/2/4.
 */
public class DialogUtils {
    /**
     * 展示自定义dialog
     * @param mContext      上下文
     * @param titleId       标题
     * @param messageId     内容
     * @param confirmId     确认文本
     * @param cancelId      取消文本
     * @param customStyleId 自定义theme、style
     * @param listener      监听
     */
    public static void showCommentDialog(Context mContext, int titleId, int messageId, int confirmId, int cancelId, int customStyleId, final OnWhichListener listener) {
        AlertDialog.Builder builder;
        if (customStyleId != -1) {
            builder = new AlertDialog.Builder(mContext, customStyleId);
        } else {
            builder = new AlertDialog.Builder(mContext);
        }
        builder.setTitle(titleId);
        builder.setMessage(messageId);

        builder.setPositiveButton(confirmId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                listener.which(which);
            }
        });
        builder.setNegativeButton(cancelId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                listener.which(which);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public interface OnWhichListener {
        void which(int position);
    }
}
