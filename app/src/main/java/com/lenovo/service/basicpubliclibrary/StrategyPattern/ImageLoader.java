package com.lenovo.service.basicpubliclibrary.StrategyPattern;

/**
 * 图片加载类
 * Created by zyy on 2017/9/26.
 */

public class ImageLoader {
    public void setCache(CacheStrategy cacheStrategy) {
        if (cacheStrategy != null) {
            cacheStrategy.cache();
        }
    }
}
