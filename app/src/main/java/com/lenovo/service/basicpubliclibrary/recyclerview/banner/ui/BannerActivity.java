package com.lenovo.service.basicpubliclibrary.recyclerview.banner.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.recyclerview.banner.view.RecyclerBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 利用RecyclerView实现图片轮播效果演示Activity
 * @author:袁东华
 * @time:2017/9/1 下午4:25
 */
public class BannerActivity extends AppCompatActivity {
    private RecyclerBanner recycler_banner1,recycler_banner2;
    private List<String> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        getSupportActionBar().setTitle("利用RecyclerView实现图片轮播效果");
        getSupportActionBar().setSubtitle("有定时滑动也有手动滑动");
        initView();
        initData();


    }

    private void initView() {
        recycler_banner1 = (RecyclerBanner) findViewById(R.id.recycler_banner1);
        recycler_banner2 = (RecyclerBanner) findViewById(R.id.recycler_banner2);

    }

    private void initData() {
        //创建广告数据
        datas = new ArrayList<>();
        datas.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3031017365,3138089828&fm=11&gp=0.jpg");
        datas.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504265618272&di=b78696c6d6e8cae784cb2058f4274626&imgtype=0&src=http%3A%2F%2Fa1.hoopchina.com.cn%2Fattachment%2FDay_090705%2F649_535127_14439728ab64906.jpg");
        datas.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1756722581,4222230078&fm=27&gp=0.jpg");
        datas.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504265618270&di=7531dec6c0392765cad30ea03fbb3655&imgtype=0&src=http%3A%2F%2Fimg1.3lian.com%2F2015%2Fa1%2F29%2Fd%2F96.jpg");
        datas.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504265618270&di=169848a8c00f1d60ca47c285fb1619bd&imgtype=0&src=http%3A%2F%2Fimg1.pconline.com.cn%2Fpiclib%2F200808%2F05%2Fbatch%2F1%2F6469%2F1217920892433ynmsijd0zw.jpg");

        //给RecyclerBanner设置广告数据
        recycler_banner1.setDatas(datas);
        recycler_banner2.setDatas(datas);

    }


}
