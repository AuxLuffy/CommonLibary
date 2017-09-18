package com.lenovo.service.basicpubliclibrary.request.net;


import android.util.Log;

import com.lenovo.service.basicpubliclibrary.App;
import com.lenovo.service.basicpubliclibrary.request.net.ApiService;
import com.lenovo.service.basicpubliclibrary.request.net.HTTPSUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhaotong on 2016/2/25.
 */
public class ServiceGenerator {

    private static Retrofit retrofit = null;

    public static ApiService getApiService() {

        if (retrofit == null) {

            HTTPSUtils httpsUtils = new HTTPSUtils(App.getContext());

            retrofit = new Retrofit.Builder()
                    .baseUrl(getUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpsUtils.getInstance())
                    .build();
            OkHttpClient request = httpsUtils.getInstance();
            Log.e("http", request.toString());

        }

        ApiService apiService = retrofit.create(ApiService.class);

//        apiService.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
        return apiService;

    }

    public static String getUrl() {
//        if (BuildConfig.apkType == 0) {
//            return URLConstants.API_HOMEADDRESS;
//        }
//        if (BuildConfig.apkType == 1) {
//            return URLConstants.API_HOMEADDRESS_DEBUG;
//        }
//        return URLConstants.API_HOMEADDRESS;
        return "https://kyfw.12306.cn/otn/";
    }

}
