package com.example.lenovo.retrofithelper2.appinfo.view;

import com.example.lenovo.retrofithelper2.appinfo.entity.AppInfo;

import java.util.List;

/**
 * Created by lenovo on 2017/9/19.
 */

public interface AppView extends BaseView{

//    void showLodading();
//
//    void dimissLoading();

    void onSuccess(List<AppInfo> listappinfo);

    void onError(String result);

}
