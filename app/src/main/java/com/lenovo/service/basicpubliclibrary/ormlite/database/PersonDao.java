package com.lenovo.service.basicpubliclibrary.ormlite.database;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.lenovo.service.basicpubliclibrary.App;
import com.lenovo.service.basicpubliclibrary.ormlite.TextSplite;
import com.lenovo.service.basicpubliclibrary.ormlite.bean.Person;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by xuxiaowei on 2017/8/28.
 */

public class PersonDao {
    /**
     * 构造函数
     *
     * @param context
     */
    private Context context;
    private TextSplite lenovoSqlite;
    private Dao<Person, Integer> stationDao;
    private static PersonDao mPersonDao;
    private List browseBeens;

    public PersonDao(Context context) {
        this.context = context;
        lenovoSqlite = lenovoSqlite.getInstance(context);
        stationDao = lenovoSqlite.getDaoInstance(Person.class);
    }

    public synchronized static PersonDao getInstance(){
        if(mPersonDao == null){
            mPersonDao = new PersonDao(App.getContext());
        }
        return mPersonDao;
    }

    /**
     * 删除一条数据
     */
    public void deletePerson(Person bean){
        try {
            stationDao.delete(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一条数据
     */
    public void addPerson(Person bean){
        try {
            stationDao.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有
     */
    public List<Person> queryAllPerson(){
        try {
            return stationDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新数据库资源
     */
    public void updateOnePerson(Person bean){
        try {
            stationDao.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
