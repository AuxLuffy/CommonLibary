package com.lenovo.service.basicpubliclibrary.linechart.common;

/**
 * Created by cx on 2016/12/15.
 */

public interface OnRequestCallback<T> {

    void onSuccess(T t);

    void onFailure(String msg);
}
