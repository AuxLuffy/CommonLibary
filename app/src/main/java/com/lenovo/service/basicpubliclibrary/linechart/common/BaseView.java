package com.lenovo.service.basicpubliclibrary.linechart.common;

/**
 * based of V
 * @param <T>
 */
public interface BaseView<T extends BasePresenter>{

    void setPresenter(T presenter);

}
