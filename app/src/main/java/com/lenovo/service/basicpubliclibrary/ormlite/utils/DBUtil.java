package com.lenovo.service.basicpubliclibrary.ormlite.utils;

import com.lenovo.service.basicpubliclibrary.ormlite.bean.Person;
import com.lenovo.service.basicpubliclibrary.ormlite.database.PersonDao;

import java.util.List;

/**
 * Created by xuxiaowei on 2017/8/29.
 */

public class DBUtil {

    private static PersonDao mPersonDao;

    /**
     * 添加一条浏览记录
     */
    public static void addOnePerson(Person bean){
        mPersonDao = PersonDao.getInstance();
        mPersonDao.addPerson(bean);
    }

    /**
     * 查询所有的浏览记录
     */
    public static List<Person> queryAllPerson(){
        mPersonDao = PersonDao.getInstance();
        List<Person> browseBeens = mPersonDao.queryAllPerson();
        return browseBeens;
    }

    public static void deleteAllPerson(Person bean){
        mPersonDao = PersonDao.getInstance();
        mPersonDao.deletePerson(bean);
    }

    /**
     * 修改本地文章的阅读量
     */
    public static void updateOneBrowse(Person bean){
        mPersonDao = PersonDao.getInstance();
        mPersonDao.updateOnePerson(bean);
    }

}
