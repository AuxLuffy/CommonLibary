package com.lenovo.service.basicpubliclibrary.maillistananimation.bean;

/**
 * Created by lenovo on 2017/8/3.
 */

public class MaillistBean {

    private String name;
    private String number;
    private String sortKey;
    private int id;
    private String pys;

    public MaillistBean(String name, String number, String sortKey, int id,String pys) {
        this.name = name;
        this.number = number;
        this.sortKey = sortKey;
        this.id = id;
        this.pys=pys;
    }

    public String getPys() {
        return pys;
    }

    public void setPys(String pys) {
        this.pys = pys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
