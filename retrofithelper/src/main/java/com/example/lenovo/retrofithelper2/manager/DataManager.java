package com.example.lenovo.retrofithelper2.manager;

import android.content.Context;

import com.example.lenovo.retrofithelper2.appinfo.entity.AppInfo;
import com.example.lenovo.retrofithelper2.appinfo.entity.BaseBean;
import com.example.lenovo.retrofithelper2.appinfo.entity.PageBean;
import com.example.lenovo.retrofithelper2.network.RetrofitHelper;
import com.example.lenovo.retrofithelper2.network.RetrofitService;

import rx.Observable;


/**
 * 接口类的封装类
 * @Author 李巷阳
 * Created at 2017/9/18 17:23
 */
public class DataManager {

    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }


    // 获取推送的产品信息
    public Observable<BaseBean<PageBean<AppInfo>>> getApps() {
        return mRetrofitService.getApps("{'page':0}");
    }




}
