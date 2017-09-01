package com.lenovo.service.basicpubliclibrary.utils;

import android.content.Context;
import android.support.annotation.DimenRes;

import com.lenovo.service.basicpubliclibrary.App;

/**
 *@description: 屏幕尺寸转换工具类
 *@author:袁东华
 *@time:2017/9/1 下午3:54
 */
public class DisplayUtils {
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * @param context
     * @param pxValue
     * @return 转换后的值
     */
    public static int pxTodip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * @param context。。
     * @param dipValue
     * @return 转换后的值
     */
    public static int dipTopx(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param context
     * @param pxValue
     * @return 转换后的值
     */
    public static int pxTosp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param context
     * @param spValue
     * @return 转换后的值
     */
    public static int spTopx(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取资源id对应的dimention
     *
     * @param id dimention中的资源id
     * @return 返回单位为px
     */
    public static int getDimention(@DimenRes int id) {
        int dimention = (int) App.getContext().getResources().getDimension(id);
        return dimention;
    }
}
