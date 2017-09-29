package com.lenovo.service.basicpubliclibrary.netframe.net.cases.base;


import com.lenovo.service.basicpubliclibrary.netframe.net.models.PagingReq;
import com.lenovo.service.basicpubliclibrary.netframe.net.retrofit.NetMgr;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 界面描述：
 * <p>
 */

public abstract class UseCase<T> {
    //用于分页请求
    protected PagingReq pagingReq = new PagingReq();


    protected T ApiClient() {
        return NetMgr.getInstance().getRetrofit("http://guolin.tech/").create(getType());
    }

    //指定观察者与被观察者线程
    protected <T> Observable.Transformer<T, T> normalSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> source) {
                return source.onTerminateDetach().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private Class<T> getType() {
        Class<T> entityClass = null;
        Type t = getClass().getGenericSuperclass();
        Type[] p = ((ParameterizedType) t).getActualTypeArguments();
        entityClass = (Class<T>) p[0];
        return entityClass;
    }
}
