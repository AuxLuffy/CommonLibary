package com.example.getui;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xuxiaowei on 2017/9/15.
 */

public class ToastUtils {
    private static Context mContext;
    private static ToastUtils instance;
    private Toast toast;

    private ToastUtils() {
        toast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
    }

    public void toast(String str){
        toast.setText(str);
        toast.show();
    }

    public static void init(Context context){
        mContext = context;
    }

    public static ToastUtils getInstance() {
        if (instance == null) {
            synchronized (ToastUtils.class) {
                if (instance == null) {
                    instance = new ToastUtils();
                }
            }
        }
        return instance;
    }

}
