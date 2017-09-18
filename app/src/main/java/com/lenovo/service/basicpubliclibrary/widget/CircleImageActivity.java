package com.lenovo.service.basicpubliclibrary.widget;

import android.app.Activity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.factory.GlideLoader;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 一个简单快速的圆形头像控件，边缘线宽度和颜色空配置
 * 分别加载本地图片和网络图片
 */
public class CircleImageActivity extends Activity {
    private CircleImageView mImage;
    private static String url = "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1206/12/c2/11972653_1339494137342.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_image);
        mImage = findViewById(R.id.image2);
        Glide.with(this).load(url)
                .asBitmap()
                .into(mImage);
    }
}
