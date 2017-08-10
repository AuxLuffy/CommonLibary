package com.lenovo.service.basicpubliclibrary.maillistananimation;

/**
 * Created by lenovo on 2017/8/10.
 */

public class AppException extends RuntimeException {
    int errorCode = -1;

    public AppException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
