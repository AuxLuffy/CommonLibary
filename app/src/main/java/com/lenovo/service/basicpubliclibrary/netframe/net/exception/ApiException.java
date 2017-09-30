package com.lenovo.service.basicpubliclibrary.netframe.net.exception;

import java.io.IOException;

/**
 * 界面描述：异常类
 * <p>
 */

public class ApiException extends IOException {
    public ApiException(String message) {
        super(message);
    }
}
