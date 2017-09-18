package com.lenovo.service.basicpubliclibrary.request.net.rxjava;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.lenovo.service.basicpubliclibrary.request.net.NResponse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import rx.Subscriber;


/**
 * Created by cx on 2017/9/13.
 * rxjava数据统一封装
 */

public abstract class SubscriberN<T> extends Subscriber<JsonObject> {

    public static Gson gson = new Gson();

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onError(e.getMessage());
    }

    @Override
    public void onNext(JsonObject jsonObject) {

        Log.e("http ", jsonObject.toString());

        NResponse<T> nResponse = new NResponse<T>();

        JsonPrimitive jcode = jsonObject.getAsJsonPrimitive("status");

        nResponse.status = jcode.getAsInt();

        JsonPrimitive jmsg = jsonObject.getAsJsonPrimitive("message");

        nResponse.message = jmsg.getAsString();

        if (200 == nResponse.status) {

            if (jsonObject.has("data")) {

                if (jsonObject.get("data") instanceof JsonArray) {

                    T data = gson.fromJson(jsonObject, getGenericClass());

                    onResult(data);

                } else {

                    JsonObject jobjData = jsonObject.getAsJsonObject("data");

                    T data = gson.fromJson(jobjData, getGenericClass());

                    onResult(data);

                }

            } else {
                onResult(null);
            }

        } else {
            showErrorCodeToast(nResponse.status, nResponse.message);
            onError(nResponse.message);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<T> getGenericClass() {
        Type type = getClass().getGenericSuperclass();
        Type[] arguments = ((ParameterizedType) type).getActualTypeArguments();
        return (Class<T>) arguments[0];
    }

    /**
     * 根据code判别返回码信息
     */
    public void showErrorCodeToast(int ErrorCode, String msg) {

        switch (ErrorCode) {
            case 1:
//                ToastUtil.showToast("缺少参数");
                break;

            default:
//                ToastUtil.showToast(msg);
                break;


        }

    }

    public abstract void onResult(T response);

    public abstract void onError(String errMsg);
}
