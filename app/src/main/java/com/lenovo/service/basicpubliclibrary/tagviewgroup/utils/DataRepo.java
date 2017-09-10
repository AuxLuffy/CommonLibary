package com.lenovo.service.basicpubliclibrary.tagviewgroup.utils;

import com.lenovo.service.basicpubliclibrary.tagviewgroup.TagGroupModel;
import com.lenovo.service.basicpubliclibrary.tagviewgroup.view.DIRECTION;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangrenmei on 2017/9/8.
 */

//没有网络请求，tags数据存在内存中
public class DataRepo {
    public static List<TagGroupModel> tagGroupList = new ArrayList<>();

    public static List<String> imgList = new ArrayList<>();

    public static void initData() {
        TagGroupModel model = new TagGroupModel();
        TagGroupModel model2 = new TagGroupModel();

        TagGroupModel.Tag tag0 = new TagGroupModel.Tag();
        tag0.setDirection(DIRECTION.RIGHT_TOP.getValue());
        tag0.setLink("http://www.baidu.com");
        tag0.setName("头层牛皮");

        TagGroupModel.Tag tag1 = new TagGroupModel.Tag();
        tag1.setDirection(DIRECTION.RIGHT_CENTER.getValue());
        tag1.setLink("http://blog.licrafter.com");
        tag1.setName("英伦加绒青年棕色保暖鞋子。");

        TagGroupModel.Tag tag2 = new TagGroupModel.Tag();
        tag2.setDirection(DIRECTION.RIGHT_BOTTOM.getValue());
        tag2.setLink("http://mc.licrafter.com");
        tag2.setName("PLAYBOY/花花公子");

        TagGroupModel.Tag tag3 = new TagGroupModel.Tag();
        tag3.setDirection(DIRECTION.RIGHT_CENTER.getValue());
        tag3.setLink("http://mc.licrafter.com");
        tag3.setName("很不错的鞋子");

        model.getTags().add(tag0);
        model.getTags().add(tag1);
        model.getTags().add(tag2);
        model.setPercentX(0.4f);
        model.setPercentY(0.5f);

        model2.getTags().add(tag3);
        model2.setPercentY(0.2f);
        model2.setPercentX(0.3f);

        tagGroupList.add(model);
        tagGroupList.add(model2);

        imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495086393352&di=34b56305a066e6e41b5c130c582e2bd2&imgtype=0&src=http%3A%2F%2Fimg.sj33.cn%2Fuploads%2Fallimg%2F201302%2F1-130201105055.jpg");
        imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495086393352&di=cf405d1e53f6eddaf4ee15e364e274cb&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F05e5e1554af04100000115a8236351.jpg");
        imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495086442229&di=a21f2b58706c9778bddb110f0176b29b&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D2369900843%2C1093290712%26fm%3D214%26gp%3D0.jpg");
    }
}
