package com.example.lenovo.retrofithelper2.rx.Subscriber;

/**
 * Created by lenovo on 2017/5/10.
 */


import com.example.lenovo.retrofithelper2.exception.BaseException;
import com.example.lenovo.retrofithelper2.rx.RxErrorHandler;

/**
 * 对错误信息回调方法的重写
 *
 * @param <T>
 */
public abstract class ErrorHandlerSubscriber<T> extends DefualtSubscriber<T> {


    private RxErrorHandler mRxErrorHandler;

    public ErrorHandlerSubscriber(RxErrorHandler rxErrorHandler) {
        mRxErrorHandler = rxErrorHandler;
    }

    @Override
    public void onError(Throwable e) {
        // 获取Throwable对应的异常和提示信息,
        // 并且打印出来即可。
        BaseException mBaseException = mRxErrorHandler.handleError(e);
        mRxErrorHandler.showExceptionMessage(mBaseException);

    }

}
