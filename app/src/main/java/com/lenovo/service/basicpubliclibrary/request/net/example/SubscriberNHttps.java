package com.lenovo.service.basicpubliclibrary.request.net.example;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import rx.Subscriber;


/**
 * Created by cx on 2017/9/20.
 *
 * 此类只用于演示https连接，因为返回格式不是json，故建立此类才能正常显示html结果。
 * 其他返回正常json格式的
 */

public abstract class SubscriberNHttps<T> extends Subscriber<ResponseBody> {

    public static Gson gson = new Gson();

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onError(e.getMessage());
    }

    @Override
    public void onNext(ResponseBody jsonObject) {

        try {
            if(jsonObject.contentType().subtype().equals("json")){

                Log.e("http https", jsonObject.string());

            }else{
                byte[] bytes = jsonObject.bytes();

                String myResponse = new String(bytes);

                Log.e("http https", myResponse);

                onResult(myResponse);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    private Class<T> getGenericClass() {
        Type type = getClass().getGenericSuperclass();
        Type[] arguments = ((ParameterizedType) type).getActualTypeArguments();
        return (Class<T>) arguments[0];
    }

    public abstract void onResult(String response);

    public abstract void onError(String errMsg);
}
