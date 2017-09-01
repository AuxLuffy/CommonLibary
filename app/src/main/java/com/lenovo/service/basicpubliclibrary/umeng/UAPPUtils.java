package com.lenovo.service.basicpubliclibrary.umeng;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/**
 * 友盟统计SDK
 * Created by cyy on 2017/5/22.
 */

public class UAPPUtils {
    /**
     *只是activity的resume方法
     * @param mContext
     * @param key
     */
    public static void ActivityResume(Context mContext, String key){
        MobclickAgent.onPageStart(key);
        MobclickAgent.onResume(mContext);
    }

    /**
     * 只是activity的pause方法
     * @param mContext
     * @param key
     */
    public static void ActivityPause(Context mContext, String key){
        MobclickAgent.onPageEnd(key);
        MobclickAgent.onPause(mContext);
    }

    /**
     * 点击事件计数的方法
     * @param mContext
     * @param key
     */
    public static void ClickEvent(Context mContext, String key){
        MobclickAgent.onEvent(mContext,key);
    }

    /**
     * activity与fragment交互时activity中Resume调用的方法
     * @param mContext
     */
    public static void FragmentActivityResume(Context mContext){
        MobclickAgent.onResume(mContext);
    }

    /**
     * activity与fragment交互时activity中Pause调用的方法
     * @param mContext
     */
    public static void FragmentActivityPause(Context mContext){
        MobclickAgent.onPause(mContext);
    }

    /**
     * activity与fragment交互时fragment中Resume调用的方法
     * @param key
     */
    public static void FragmentResume(String key){
        MobclickAgent.onPageStart(key);
    }

    /**
     * activity与fragment交互时fragment中Pause调用的方法
     * @param key
     */
    public static void FragmentPause(String key){
        MobclickAgent.onPageEnd(key);
    }


    /**
     * 设置两次启动的时间间隔
     * @param time
     */
    public static void setSessionContinueMillis(long time){
        MobclickAgent.setSessionContinueMillis(time);
    }
}
