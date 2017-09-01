package com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.error;

/**
 * Created by lenovo on 2017/8/10.
 */

public class AppException extends RuntimeException {
    public int errorCode = -1;

    public AppException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
