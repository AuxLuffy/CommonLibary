package com.lenovo.service.basicpubliclibrary.shadowmasking.utils;

import android.content.Context;

/**
 * Created by hxy on 2016/10/25.
 */
public class ScreenUtils {


    public static int px2dp(Context context, float value) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (value / density + 0.5f);
    }

    public static int dp2px(Context context, float value) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (value * density + 0.5f);
    }

    /**
     * 测量通知栏高度
     */
    public static int getstatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
