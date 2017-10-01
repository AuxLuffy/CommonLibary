package com.lenovo.service.basicpubliclibrary.cache;


import java.io.Serializable;

/**
 * Created by cx on 2017/10/1.
 */

public class UserTest implements Serializable {

    String name;

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "{"+name+"}";
    }


}
