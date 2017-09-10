package com.lenovo.service.basicpubliclibrary.tagviewgroup.utils;

import android.content.Context;

/**
 * Created by tangrenmei on 2017/9/8.
 */

public class DipConvertUtils {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
