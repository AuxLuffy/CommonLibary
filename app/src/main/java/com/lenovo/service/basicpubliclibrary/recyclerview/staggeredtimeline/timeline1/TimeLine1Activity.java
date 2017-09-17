package com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.timeline1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


import com.lenovo.service.basicpubliclibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 瀑布流式时光轴效果演示界面
 * @author:袁东华
 * @time:2017/9/17 下午5:46
 */
public class TimeLine1Activity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    int[] resId = {
            R.drawable.image_1,
//            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6,
            R.drawable.image_7,
            R.drawable.image_8
    };

    List<Item> mList = new ArrayList<>();
    TimeLineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("瀑布流式时光轴效果");
        setContentView(R.layout.activity_staggered_timeline1);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new ItemDecoration(this, 100));

        for (int i = 0; i < 7; i++) {
            Item item = new Item();
            item.setResId(resId[i]);
            item.setText("我从未见过如此厚颜无耻之人");
            mList.add(item);
        }

        mAdapter = new TimeLineAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
