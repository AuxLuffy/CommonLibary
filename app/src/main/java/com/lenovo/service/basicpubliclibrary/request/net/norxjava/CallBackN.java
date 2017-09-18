package com.lenovo.service.basicpubliclibrary.request.net.norxjava;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.lenovo.service.basicpubliclibrary.request.net.NResponse;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by cx on 2016/6/13.
 * 数据统一返回处理类
 */
public abstract class CallBackN<T> implements Callback<JsonObject> {

    public static Gson gson = new Gson();

    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        try {

            JsonObject jsonObject = response.body();

            if (jsonObject == null) {

                ResponseBody responseBody = response.errorBody();

                byte[] bytes = responseBody.bytes();

                String errorResponse = new String(bytes);

                Log.e("http", errorResponse);

                JSONObject errorJsonObject = new JSONObject(errorResponse);

                String status_code = errorJsonObject.getString("status");

                String message = errorJsonObject.getString("message");

//                ToastUtil.showToast(message);

                onError("");

            } else {
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

        } catch (Exception e) {
//            ToastUtil.showToast("数据解析异常");
            onError("");
            e.printStackTrace();
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

    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
        try {
            onError("网络异常！");
//            ToastUtil.showToast("网络异常！");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public abstract void onResult(T response);


    public abstract void onError(String errMsg);

}
