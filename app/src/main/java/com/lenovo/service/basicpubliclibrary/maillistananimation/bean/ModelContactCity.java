package com.lenovo.service.basicpubliclibrary.maillistananimation.bean;

public class ModelContactCity {

    public String name;
    public String pys;
    public String number;
    public int type;

    public ModelContactCity(String name, String pys, String num) {
        this.name = name;
        this.pys = pys;
        this.number=num;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", pys='" + pys + '\'' +
                ", type=" + type +
                '}';
    }
}
