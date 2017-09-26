package com.lenovo.service.basicpubliclibrary.StrategyPattern;

import android.util.Log;

/**
 * 内存缓存类
 * Created by zyy on 2017/9/26.
 */

public class MemoryCache implements CacheStrategy {
    @Override
    public void cache() {
        Log.e("CacheStrategy", "MemoryCache");
    }
}
