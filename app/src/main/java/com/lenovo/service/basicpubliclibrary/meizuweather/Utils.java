package com.lenovo.service.basicpubliclibrary.meizuweather;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuz on 17/8/8.
 */

public class Utils {

    /**
     * 时间转换（不带日期）
     *
     * @param date
     * @return
     */
    public static String Date2StringNoHaveDate(Date date) {
        String str;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:ss");
        str = sdf.format(date);
        return str;
    }

}
