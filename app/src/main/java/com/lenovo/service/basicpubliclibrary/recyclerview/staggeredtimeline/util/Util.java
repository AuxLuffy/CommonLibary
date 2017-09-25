package com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.util;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {
    /**
     * long转成2015.01.03格式
     *
     * @param time 单位ms s的话需要*1000
     * @return
     */
    public static String LongtoStringFormat(long time) {
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd a HH:mm");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static int Dp2Px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int Sp2Px(Context context, float spValue) {
        return (int) (spValue * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }
}
