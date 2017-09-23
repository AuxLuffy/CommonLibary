package com.lenovo.service.basicpubliclibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 彤 on 2017/9/1.
 * 本类包含常用的一些有关日期格式转换和获取日期的信息相关功能
 */

public class DateUtils {
    private static SimpleDateFormat sf;
    private static SimpleDateFormat sdf;



    public static String getShowTime(long timeStamp) {
        long dayMinute = 24 * 60;
        String showTime = null;
        long duration = System.currentTimeMillis() / 1000 - timeStamp;
        if (duration < 0 || timeStamp == 0) {
            return "";
        }
        int durMinutes = (int) (duration / 60.0f);//计算相差分钟
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        long todayPastMin = currentHour * 60 + currentMinute;
        try {
            if (duration < 60) {
                showTime = "刚刚";
            } else if (durMinutes >= 0 && durMinutes <= 60) {
                showTime = durMinutes + "分钟前";
            } else if (durMinutes >= 60 && durMinutes <= todayPastMin) {//今天发送的消息
//                showTime = "今天 " + (new SimpleDateFormat("HH:mm").format(new Date()));
                showTime = durMinutes / 60 + "小时前";
            } else if (durMinutes > todayPastMin && durMinutes <= dayMinute + todayPastMin) {//昨天发送的消息
                showTime = "昨天";
            } else if (durMinutes > dayMinute + todayPastMin && durMinutes <= dayMinute * 2 + todayPastMin) {
                showTime = "前天";
            } else if (durMinutes > dayMinute * 2 + todayPastMin && durMinutes <= dayMinute * 6 + todayPastMin) {
                showTime = durMinutes / 60 / 24 + "天前";
            } else {
                showTime = new SimpleDateFormat("MM月dd日 HH:mm").format(new Date(timeStamp * 1000));
            }
        } catch (Exception e) {
            showTime = "";
        }

        return showTime;
    }

    /**
     * 获取系统时间 格式为："yyyy/MM/dd "
     **/
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："yyyy "
     **/
    public static String getCurrentYear() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："MM"
     **/
    public static String getCurrentMonth() {
        Date d = new Date();
        sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："dd"
     **/
    public static String getCurrentDay() {
        Date d = new Date();
        sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTime() {
        long d = new Date().getTime() / 1000;
        return d;
    }

    /**
     * 时间戳转换成字符窜
     */
    public static String getDateToString(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /**
     * 时间戳中获取年
     */
    public static String getYearFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }

    /**
     * 时间戳中获取月
     */
    public static String getMonthFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    /**
     * 时间戳中获取日
     */
    public static String getDayFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    /**
     * 将字符串转为时间戳
     */
    public static long getStringToDate(String time) {
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}
