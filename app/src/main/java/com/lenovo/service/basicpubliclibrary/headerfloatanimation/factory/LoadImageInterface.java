package com.lenovo.service.basicpubliclibrary.headerfloatanimation.factory;

import android.content.Context;
import android.widget.ImageView;

/**
 * 产品类
 * Created by lenovo on 2016/4/12.
 */
public interface LoadImageInterface {
    void loadImage(Context context, String url, ImageView imageView);
    void loadImage(Context context, String url, ImageView imageView, int defaultId);
}

