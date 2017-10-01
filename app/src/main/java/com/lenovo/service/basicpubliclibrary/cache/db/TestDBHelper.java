package com.lenovo.service.basicpubliclibrary.cache.db;

import android.content.Context;

/**
 * Created by lenovo on 2017/10/1.
 */

public class TestDBHelper extends DataBaseHelper {

    private static TestDBHelper mTestDBHelper;

    private TestDBHelper(Context context){
        super(context);
    }

    public static TestDBHelper getInstance(Context context){
        if (mTestDBHelper==null){
            synchronized (DataBaseHelper.class){
                if (mTestDBHelper==null){
                    mTestDBHelper = new TestDBHelper(context);
                    if (mTestDBHelper.getDB()==null||!mTestDBHelper.getDB().isOpen()){
                        mTestDBHelper.open();
                    }
                }
            }
        }
        return mTestDBHelper;
    }

    @Override
    protected int getMDbVersion(Context context) {
        return 1;
    }

    @Override
    protected String getDbName(Context context) {
        return "test.db";
    }

    @Override
    protected String[] getDbCreateSql(Context context) {
        String[] a = new String[1];
        a[0] = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,gender TEXT,age INTEGER)";
        return a;
    }

    @Override
    protected String[] getDbUpdateSql(Context context) {
        return new String[0];
    }

//    @Override
//    protected int getMDbVersion(Context context) {
//        return Integer.valueOf(context.getResources().getStringArray(R.array.DATABASE_INFO)[1]);
//    }
//
//    @Override
//    protected String getDbName(Context context) {
//        return context.getResources().getStringArray(R.array.DATABASE_INFO)[0];
//    }
//
//    @Override
//    protected String[] getDbCreateSql(Context context) {
//        return context.getResources().getStringArray(R.array.CREATE_TABLE_SQL);
//    }
//
//    @Override
//    protected String[] getDbUpdateSql(Context context) {
//        return context.getResources().getStringArray(R.array.UPDATE_TABLE_SQL);
//    }
}
