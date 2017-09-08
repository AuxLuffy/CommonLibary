package com.lenovo.service.basicpubliclibrary.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Administrator on 2017/9/8.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public final class AppDatabase {
    //数据库名称
    public static final String NAME = "AppDatabase";
    //数据库版本号
    public static final int VERSION = 1;
}
