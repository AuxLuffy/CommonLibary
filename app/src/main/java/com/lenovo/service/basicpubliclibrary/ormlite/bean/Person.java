package com.lenovo.service.basicpubliclibrary.ormlite.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by xuxiaowei on 2017/8/28.
 */
@DatabaseTable(tableName = "tb_person")
public class Person {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "height")
    private String height;
    @DatabaseField(columnName = "weight")
    private String weight;

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public Person(String name, String height, String weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public Person() {
    }
}

