package com.example.lenovo.retrofithelper2.network;

import android.content.Context;

import com.example.lenovo.retrofithelper2.manager.Constant;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 主要用于Retrofit的初始化
 * @Author 李巷阳
 * Created at 2017/9/18 17:24
 */
public class RetrofitHelper {

        private Context mContext;
        private static RetrofitHelper instance;
        private Retrofit mRetrofit = null;
        OkHttpClient client = new OkHttpClient();
        GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());

    public RetrofitHelper(Context context) {
            mContext = context;
            init();
        }


    public static RetrofitHelper getInstance(Context context){
            if(instance==null)
            {
                instance=new RetrofitHelper(context);
            }
            return instance;
        }
    private void init() {
        resetApp();


    }

    private void resetApp() {

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }
    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }

}
