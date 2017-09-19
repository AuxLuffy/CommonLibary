package com.example.lenovo.retrofithelper2.rx;

/**
 * Created by lenovo on 2017/5/10.
 */

import android.content.Context;
import android.widget.Toast;

import com.example.lenovo.retrofithelper2.exception.ApiException;
import com.example.lenovo.retrofithelper2.exception.BaseException;
import com.example.lenovo.retrofithelper2.exception.ErrorMessageFactory;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * 因为在ErrorHandlerSubscriber的onError需要一个Context类。
 * 所以现在需要创建这个类,来构造方法中传入Context,然后通过注入方式传入进来。
 */
public class RxErrorHandler {

    private Context mContext;

    public RxErrorHandler(Context context) {
        mContext = context;
    }

    public BaseException handleError(Throwable e) {
        BaseException exception = new BaseException();
        // 该异常是
        if (e instanceof ApiException) {
            exception.setCode(((ApiException) e).getCode());
        }
        // 该异常在客户端和服务器端均有可能发生,引起该异常。
        else if (e instanceof SocketException) {
            exception.setCode(BaseException.SOCKET_ERROR);
        } else if (e instanceof SocketTimeoutException) {
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        } else if (e instanceof HttpException) {
            exception.setCode(BaseException.HTTP_ERROR);
        } else {
            exception.setCode(BaseException.UNKNOWN_ERROR);
        }
        exception.setDisplayMessage(ErrorMessageFactory.create(mContext, exception.getCode()));
        return exception;
    }


     public void showExceptionMessage(BaseException exception){

         Toast.makeText(mContext,exception.getDisplayMessage(), Toast.LENGTH_SHORT).show();

     }


}
