package com.lenovo.service.basicpubliclibrary.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by 彤 on 2017/9/17.
 * 定义Modle类，继承自RealmObject类
 */

public class RealmUser extends RealmObject {
    @PrimaryKey()
    private Long id;
    @Required
    private String userName;
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
