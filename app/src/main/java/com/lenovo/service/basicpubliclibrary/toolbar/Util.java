package com.lenovo.service.basicpubliclibrary.toolbar;

import android.content.res.Resources;

import com.lenovo.service.basicpubliclibrary.App;

/**
 * Created by lenovo on 2017/9/11.
 */

public class Util {

    /**
     * 获取Resource对象
     *
     * @return
     */
    public static Resources getResources() {
        return App.getContext().getResources();
    }
    /**
     * 获取颜色资源
     *
     * @param resId
     * @return
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }
}
