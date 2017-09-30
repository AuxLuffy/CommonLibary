package com.lenovo.service.basicpubliclibrary.netframe.net.extension;

import rx.Subscriber;

/**
 * 界面描述： BaseSubscriber基类，处理返回数据
 * <p>
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }
}
