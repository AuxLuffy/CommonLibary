package com.lenovo.service.basicpubliclibrary.cache;

/**
 * Created by cx on 2017/6/8.
 */

public interface IDataManager<T> {

    void put(String key, T t);

    void delete(String key);

    void update(String key, T t);

    T find(String key);

    void clear();

}
