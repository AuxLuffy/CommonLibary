package com.lenovo.service.basicpubliclibrary.headerfloatanimation.factory;


import com.lenovo.service.basicpubliclibrary.R;

/**
 * Created by shengtao on 2016/4/12.
 */
public class LoadImageFactory {
    public final static int imageLoader = 0;
    public final static int glide = 1;
    public static int defaultId = R.drawable.ic_launcher;//默认加载图

    public LoadImageInterface createLoader(int type){
        switch (type) {
            case imageLoader:
               // return new ImageLoader();
            case glide:
                return new GlideLoader();
            default:
                throw new IllegalArgumentException("图片加载loadMode错误");
        }

    }


}
