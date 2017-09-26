package com.lenovo.service.basicpubliclibrary.StrategyPattern;

import android.util.Log;

/**
 * 磁盘缓存类
 * Created by zyy on 2017/9/26.
 */

public class DiskCache implements CacheStrategy {
    @Override
    public void cache() {
        Log.e("CacheStrategy", "DiskCache");
    }
}
