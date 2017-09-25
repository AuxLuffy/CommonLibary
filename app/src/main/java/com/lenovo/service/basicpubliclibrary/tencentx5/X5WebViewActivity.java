package com.lenovo.service.basicpubliclibrary.tencentx5;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.tencent.smtt.sdk.WebView;

import com.lenovo.service.basicpubliclibrary.R;

/**
 * 版权所有 ,lenovo保留所有版权。<br>
 * 项目描述：集成腾讯X5内核 一般用来解决android内置浏览器兼容性问题
 * 作者：Jiao
 * 日期：2017/9/18
 **/
public class X5WebViewActivity extends Activity {
    WebView mX5WebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //兼容视频播放
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_x5_webview);
        mX5WebView=findViewById(R.id.forum_context);
        mX5WebView.getSettings().setJavaScriptEnabled(true);// 支持js
        mX5WebView.getSettings().setUseWideViewPort(true); //自适应屏幕
        mX5WebView.loadUrl("http://www.baidu.com");
    }
}
