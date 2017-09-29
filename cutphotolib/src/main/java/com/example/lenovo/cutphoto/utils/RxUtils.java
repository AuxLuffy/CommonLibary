package com.example.lenovo.cutphoto.utils;

import android.content.Context;

/**
 * Created by lenovo on 2017/9/29.
 */

public class RxUtils {
    private static Context context;
    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        RxUtils.context = context.getApplicationContext();
    }

    /**
     * 在某种获取不到 Context 的情况下，即可以使用才方法获取 Context
     * <p>
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("请先调用init()方法");
    }

}
