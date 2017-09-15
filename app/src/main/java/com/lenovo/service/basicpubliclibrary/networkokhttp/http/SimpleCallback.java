package com.lenovo.service.basicpubliclibrary.networkokhttp.http;

import android.content.Context;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by lenovo on 2017/8/29.
 */

public abstract class SimpleCallback<T> extends BaseCallback<T>  {



    protected Context mContext;

    public SimpleCallback(Context context){

        mContext = context;

    }

    @Override
    public void onBeforeRequest(Request request) {

    }

    @Override
    public void onFailure(Request request, Exception e) {

    }

    @Override
    public void onResponse(Response response) {

    }





}
