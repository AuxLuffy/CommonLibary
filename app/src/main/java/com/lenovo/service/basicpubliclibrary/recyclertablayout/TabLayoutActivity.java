package com.lenovo.service.basicpubliclibrary.recyclertablayout;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.recyclertablayout.adapter.NBAPageAdapter;
import com.lenovo.service.basicpubliclibrary.recyclertablayout.view.RecyclerTabLayout;

import java.util.ArrayList;

/**
 * 用recyclerview实现的TabLayout效果
 *
 * 用法和tablayout一样
 *
 * 每个item的布局更加灵活
 *
 * 可以实现完全自定义
 *
 * 实现tablayout没有的功能
 *
 * 比如选中时字体放大
 *
 * 每个item之间的间距是一样的
 */

public class TabLayoutActivity extends AppCompatActivity {

    private ArrayList<String> list = new ArrayList<>();
    private RecyclerTabLayout mRecyclerTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        initData();

        NBAPageAdapter adapter = new NBAPageAdapter(list, this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        mRecyclerTabLayout = (RecyclerTabLayout)
                findViewById(R.id.recycler_tab_layout);
        mRecyclerTabLayout.setUpWithViewPager(viewPager);
    }

    public void initData(){
        list.add("乔丹");
        list.add("詹姆斯");
        list.add("科比");
        list.add("韦德");
        list.add("奥尼尔");
        list.add("加内特");
        list.add("姚明");
        list.add("特雷西");
        list.add("艾弗森");
        list.add("保罗");
    }
}
