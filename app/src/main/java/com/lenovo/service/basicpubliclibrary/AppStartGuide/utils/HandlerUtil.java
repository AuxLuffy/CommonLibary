package com.lenovo.service.basicpubliclibrary.AppStartGuide.utils;

import android.content.Context;
import android.os.Handler;

import java.lang.ref.WeakReference;

/**
 * 加入软引用的Handler
 * @Author 李巷阳
 * Created at 2017/7/28 18:13
 */
public class HandlerUtil extends Handler {

    private static HandlerUtil instance = null;
    WeakReference<Context> mActivityReference;

    public static HandlerUtil getInstance(Context context) {
        if (instance == null) {
            instance = new HandlerUtil(context.getApplicationContext());
        }
        return instance;
    }

    HandlerUtil(Context context) {
        mActivityReference = new WeakReference<>(context);
    }
}
