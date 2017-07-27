package com.lenovo.service.basicpubliclibrary.headerfloatanimation.mode;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/21.
 */
public class HomePageModeMapping extends  HomePageMode.CategoryOne.CategoryTwo{
    //如果type为1,则

    public int type;//类型，暂时约定1为title，0为viewpager
    public int rootIndexInOriginData;//在最外层分类CategoryOne的索引，同一个ArrayList<CategoryTwo>此索引相同
   //public String title;//title和其它信息，可要可不要，因为知道了rootIndexInOriginData，就可以通过原始数据的下标去取
   public ArrayList<HomePageMode.CategoryOne.CategoryTwo> banner;

    public int getType() {
        return type;
    }

    public ArrayList<HomePageMode.CategoryOne.CategoryTwo> getBanner() {
        return banner;
    }

    public void setBanner(ArrayList<HomePageMode.CategoryOne.CategoryTwo> banner) {
        this.banner = banner;
    }

    public int getRootIndexInOriginData() {
        return rootIndexInOriginData;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setRootIndexInOriginData(int rootIndexInOriginData) {
        this.rootIndexInOriginData = rootIndexInOriginData;
    }
/* public String getTitle() {
        return title;
    }*/
}
