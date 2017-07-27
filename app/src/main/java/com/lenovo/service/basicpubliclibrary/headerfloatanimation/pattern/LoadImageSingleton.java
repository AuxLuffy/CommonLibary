package com.lenovo.service.basicpubliclibrary.headerfloatanimation.pattern;

import android.content.Context;
import android.widget.ImageView;

import com.lenovo.service.basicpubliclibrary.headerfloatanimation.factory.LoadImageFactory;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.factory.LoadImageInterface;


/**
 * Created by shengtao on 2016/5/18.
 */
public class LoadImageSingleton {
    //私有构造方法，确保不能随意new出来
    private static LoadImageFactory loadImageFactory=null;
    private static LoadImageInterface loader=null;
    private LoadImageSingleton(){
        //1创建工厂方法
        loadImageFactory = new LoadImageFactory();
        // 2根据传入参数不同，生成不同生产类，传入glide
        loader = loadImageFactory.createLoader(LoadImageFactory.glide);

    };
    //自己定义自己，是不是感觉怪怪的？呵呵
    private static volatile LoadImageSingleton instance=null;
    //get方法，使用了双重检查锁定的方式，其实还有更优的方法，但是这个在JDK1.3后，足够用了（为什么是1.3,这个自己查吧)
    public static LoadImageSingleton getInstance(){
        if (instance == null) {
            synchronized (LoadImageSingleton.class) {
                if (instance == null) {
                    instance = new LoadImageSingleton();
                }
            }
        }
        return instance;
    }
    public void displayImage(Context context,String url, ImageView imageview, int resId){
        loader.loadImage(context, url, imageview,resId);
    }
    public void displayImage(Context context,String url, ImageView imageview) {
        loader.loadImage(context, url, imageview);
    }


}
