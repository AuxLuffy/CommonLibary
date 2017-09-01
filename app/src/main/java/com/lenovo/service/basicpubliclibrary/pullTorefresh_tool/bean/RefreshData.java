package com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.bean;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/6/14.
 */

public class RefreshData implements Serializable {


    private String phone;
    private String nick_name;
    private String head_img;
    private int sex;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
