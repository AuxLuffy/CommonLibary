package com.lenovo.service.basicpubliclibrary.networkokhttp.http;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by lenovo on 2017/8/29.
 */

public abstract class BaseCallback<T> {


    public Type mType;

    static Type getSuperclassTypeParameter(Class<?> subclass)
    {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class)
        {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    // 构造函数
    public BaseCallback()
    {
        mType = getSuperclassTypeParameter(getClass());
    }

    // 在请求之前
    public abstract void onBeforeRequest(Request request);



    // 网络出错调用onFailure
    public abstract  void onFailure(Request request, Exception e) ;


    /**
     *请求成功时调用此方法
     * @param response
     */
    public abstract  void onResponse(Response response);

    /**
     *
     * 状态码大于200，小于300 时调用此方法
     * @param response
     * @param t
     */
    public abstract void onSuccess(Response response,T t) ;

    /**
     * 状态码400，404，403，500等时调用此方法
     * @param response
     * @param code
     * @param e
     */
    public abstract void onError(Response response, int code,Exception e) ;

    /**
     * Token 验证失败。状态码401,402,403 等时调用此方法
     * @param response
     * @param code

     */
    public abstract void onTokenError(Response response, int code);





}
