package com.lenovo.service.basicpubliclibrary.request.net;


import com.lenovo.service.basicpubliclibrary.App;
import com.lenovo.service.basicpubliclibrary.request.net.https.HTTPSUtils;

import retrofit2.Retrofit;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cx on 2016/2/25.
 * okhttp+retrofit
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
        }

        return retrofit.create(ApiService.class);

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
