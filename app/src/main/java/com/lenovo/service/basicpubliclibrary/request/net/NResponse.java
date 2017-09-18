package com.lenovo.service.basicpubliclibrary.request.net;

/**
 * Created by cx on 2016/6/12.
 */
public class NResponse<T> {

    public int status;//状态码

    public String message;//返回信息

    public T data;

}
