package com.example.lenovo.retrofithelper2.appinfo.entity;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/5/9.
 */

public class BaseBean<T> implements Serializable {

    public static final int SUCCESS = 1;

    private int status;
    private String message;
    private T data;


    public boolean isSuccess() {
        return (status == SUCCESS);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
