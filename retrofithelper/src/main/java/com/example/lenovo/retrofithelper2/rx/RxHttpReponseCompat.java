package com.example.lenovo.retrofithelper2.rx;


import com.example.lenovo.retrofithelper2.appinfo.entity.BaseBean;
import com.example.lenovo.retrofithelper2.exception.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/5/9.
 */

/**
 * 对网络请求返回结果的预处理类
 */
public class RxHttpReponseCompat {

    public static <T> Observable.Transformer<BaseBean<T>,T> compatResult() {
        // 把BaseBean<T>转化为T
        return new Observable.Transformer<BaseBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {
                return baseBeanObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(final BaseBean<T> tBaseBean) {
                        // 判断是否是成功返回
                        if (tBaseBean.isSuccess()) {
                            return Observable.create(new Observable.OnSubscribe<T>() {
                                @Override
                                public void call(Subscriber<? super T> subscriber) {
                                    try {
                                        // 如果无错,往下走。
                                        subscriber.onNext(tBaseBean.getData());
                                        subscriber.onCompleted();
                                    } catch (Exception e) {
                                        // 有错,做异常处理。
                                        subscriber.onError(e);
                                    }
                                }
                            });
                        } else {
                            // 如果为错误则调用异常处理类
                            return Observable.error(new ApiException(tBaseBean.getStatus(), tBaseBean.getMessage()));
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };


    }


}
