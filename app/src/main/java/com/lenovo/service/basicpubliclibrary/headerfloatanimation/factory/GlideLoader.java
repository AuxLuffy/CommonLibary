package com.lenovo.service.basicpubliclibrary.headerfloatanimation.factory;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by lenovo on 2016/4/12.
 */
public class GlideLoader implements LoadImageInterface {
    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        loadImage(context, url, imageView, LoadImageFactory.defaultId);

    }

    @Override
    public void loadImage(Context activity, String url, ImageView imageView, int defaultId) {
       Glide.with(activity)
                .load(url)//加载URL
                /*.placeholder(defaultId)//占位图
                .error(defaultId)//加载错误图*/
                .into(imageView);
        Log.e("test","调用GlideLoader");
    }
}
