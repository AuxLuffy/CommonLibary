package com.lenovo.service.basicpubliclibrary.leakcanary;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.App;
import com.lenovo.service.basicpubliclibrary.R;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.leakcanary.internal.DisplayLeakActivity;

/**
 * 版权所有 ,lenovo保留所有版权。<br>
 * 项目描述：LeakCanary 内存泄漏检测
 * 作者：Jiao
 * 日期：2017/9/14
 **/
public class LeakCanaryActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher=  App.getRefWatcher(this);
        refWatcher.watch(this);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {              //模拟耗时操作
                            Thread.sleep( 15000 );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                finish();
                break;
            case R.id.button2:
                Intent intent=new Intent(this,DisplayLeakActivity.class);
                startActivity(intent);
                break;
        }
    }
}
