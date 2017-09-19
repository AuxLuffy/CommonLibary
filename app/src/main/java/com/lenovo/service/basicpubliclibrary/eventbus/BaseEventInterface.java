package com.lenovo.service.basicpubliclibrary.eventbus;

public interface BaseEventInterface<T> {
    void setObject(T item);

    T getObject();

    void setCode(int code);

    int getCode();

    void setMessage(String msg);

    String getMessage();

    // ... 其他事件定义
}


class BaseEvent implements BaseEventInterface {

    @Override
    public void setObject(Object item) {

    }

    @Override
    public Object getObject() {
        return null;
    }

    @Override
    public void setCode(int code) {

    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public void setMessage(String msg) {

    }

    @Override
    public String getMessage() {
        return null;
    }


    //事件定义
    static class SomeOtherEvent extends BaseEvent {

    }

    static class MessageEvent extends BaseEvent {

    }


}

