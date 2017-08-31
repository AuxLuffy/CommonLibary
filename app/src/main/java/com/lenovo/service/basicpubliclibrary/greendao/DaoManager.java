package com.lenovo.service.basicpubliclibrary.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lenovo.service.basicpubliclibrary.greendao.entity.BaseEntity;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;

import java.util.List;
import java.util.Objects;

/**
 * Created by 彤 on 2017/8/29.]
 * 获取数据库操作的单例
 */

public class DaoManager {
    private static DaoManager daoInstance;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    /**
     * 私有构造方法
     */
    private DaoManager(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), "your_db_name", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    /**
     * 获取数据库对象
     */
    public static DaoManager getDaoInstance(Context context) {
        if (daoInstance == null) {
            synchronized (context) {
                daoInstance = new DaoManager(context.getApplicationContext());
            }
        }
        return daoInstance;
    }

    /**
     * 获得session对象后即可通过该对象取得相应数据表
     * 通过获取Session对象，进行更多扩展操作
     *
     * @return
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * 获取不同的dao对象，还可添加若干类似方法
     * 在此获取UserDao
     */
    public static UserDao getUserDao(Context context) {
        UserDao userDao = getDaoInstance(context).getDaoSession().getUserDao();
        return userDao;
    }

    /**
     * 添加数据
     *
     * @param dao    操作的数据库对象
     * @param entity 数据实体
     */
    public static void addData(AbstractDao dao, BaseEntity entity) {
        dao.insert(entity);
    }

    /**
     * 删除数据
     *
     * @param objects 将要删除的数据，如果传null，将删除全部数据
     */
    public static void deleteData(AbstractDao dao, Property property, Object objects) {
        if (objects == null) {
            dao.deleteAll();
        } else {
            List<BaseEntity> be = dao.queryBuilder().where(property.eq(objects)).build().list();
            if (be != null) {
                for (BaseEntity entity : be) {
                    dao.delete(entity);
                }
            }
        }
    }

    /**
     * 根据参数查询数据
     */
    public static List<BaseEntity> queryData(AbstractDao dao, Property property, Object objects) {
        List<BaseEntity> datas = dao.queryBuilder().where(property.eq(objects)).build().list();
        return datas;
    }

    /**
     * 查询表中所有数据
     */
    public static List<BaseEntity> queryAllData(AbstractDao dao) {
        List<BaseEntity> datas = dao.queryBuilder().build().list();
        return datas;
    }
}
