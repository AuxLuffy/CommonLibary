package com.example.lenovo.retrofithelper2.exception;

/**
 * Created by lenovo on 2017/5/9.
 */

public class ApiException extends BaseException {


    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
