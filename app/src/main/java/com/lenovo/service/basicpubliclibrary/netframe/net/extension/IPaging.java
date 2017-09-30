package com.lenovo.service.basicpubliclibrary.netframe.net.extension;

import java.util.List;


/**
 * 界面描述：提供给分页模型的接口，提供类型转换成UralPaging
 * <p>
 */

public interface IPaging<T> {

    List<T> getItems();

    int getPage();

    int getPageSize();

    long getTotal();

    int getPageCount();
}
