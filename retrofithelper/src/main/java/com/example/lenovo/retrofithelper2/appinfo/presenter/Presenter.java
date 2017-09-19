package com.example.lenovo.retrofithelper2.appinfo.presenter;

import android.content.Intent;

import com.example.lenovo.retrofithelper2.appinfo.view.BaseView;

/**
 * @Author 李巷阳
 * Created at 2017/9/18 17:47
 */
public interface Presenter {
    void onCreate();

    void onStart();//暂时没用到

    void onStop();

    void pause();//暂时没用到

    void attachView(BaseView view);

    void attachIncomingIntent(Intent intent);//暂时没用到

}
