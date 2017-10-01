package com.lenovo.service.basicpubliclibrary.cache;

/**
 * Created by cx on 2017/6/8.
 */

public class LocalDataFactory {

    public enum LocalDataType{
        FILE,
        SP,
        DB,
        CP
    }
    public static <T> IDataManager<T> getManager(LocalDataType type){

        IDataManager<T> dataManager = null;

        switch (type) {
            case FILE:
                dataManager = new CacheFileManager<>();
                break;

            case SP:
                dataManager = new SPManager<>();
                break;

            case DB:
                dataManager = new DBManager<>();
                break;

            case CP:
                dataManager = new CPManager<>();
                break;
        }
        return dataManager;
    }
}
