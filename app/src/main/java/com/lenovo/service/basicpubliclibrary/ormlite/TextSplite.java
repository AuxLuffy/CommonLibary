package com.lenovo.service.basicpubliclibrary.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.lenovo.service.basicpubliclibrary.ormlite.bean.Person;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuxiaowei on 2017/8/28.
 */

public class TextSplite extends OrmLiteSqliteOpenHelper {
    public static final String DB_NAME = "text_db";//数据库名称
    public static final int DB_VERSION = 1;//数据库版本
    private static TextSplite lenovoSqlite;//数据库实例
    private static Map<String, Dao> daos = new HashMap<>();

    private TextSplite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 获取数据库单例
     *
     * @param context
     * @return
     */
    public static TextSplite getInstance(Context context) {
        if (lenovoSqlite == null) {
            synchronized (TextSplite.class) {
                if (lenovoSqlite == null) {
                    lenovoSqlite = new TextSplite(context);
                }
            }
        }
        return lenovoSqlite;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Person.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Person.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据表名获取数据库操作对象
     */
    public synchronized Dao getDaoInstance(Class clazz) {
        Dao dao = null;
        String daoName = clazz.getSimpleName();
        if (daos.containsKey(daoName)) {
            dao = daos.get(daoName);
        }
        if (dao == null) {
            try {
                dao = super.getDao(clazz);
                daos.put(daoName, dao);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }

    /**
     * 关闭数据库,释放资源
     */
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
