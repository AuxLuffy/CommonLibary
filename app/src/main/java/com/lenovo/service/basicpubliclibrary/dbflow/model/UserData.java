package com.lenovo.service.basicpubliclibrary.dbflow.model;

import com.lenovo.service.basicpubliclibrary.dbflow.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Administrator on 2017/9/8.
 */
@Table(database = AppDatabase.class)
public class UserData extends BaseModel{
    @PrimaryKey(autoincrement = true)//ID自增
    public long id;

    /**
     * 姓名
     */
    @Column
    public String name;

    /**
     * 年龄
     */
    @Column
    public int age;

    /**
     * 性别
     */
    @Column
    public int sex;

//备注：DBFlow会根据你的类名自动生成一个表明，以此为例：
//这个类对应的表名为：UserData_Table，


    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
