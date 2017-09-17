package com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.timeline1;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.shadowmasking.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 瀑布流式时光轴效果演示界面
 * @author:袁东华
 * @time:2017/9/17 下午5:46
 */
public class TimeLine1Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private int[] resId = {
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_7,
            R.drawable.image_6,
            R.drawable.image_8
    };

    List<Item> mList = new ArrayList<>();
    TimeLineAdapter mAdapter;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("瀑布流式时光轴效果");
        setContentView(R.layout.activity_staggered_timeline1);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //创建瀑布流Layout
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //创建ItemDecoration
        ItemDecoration itemDecoration = new ItemDecoration(this);
        itemDecoration.setDistanceBetweenScreenAndItem(ScreenUtils.dp2px(this,10));
        itemDecoration.setDistance(ScreenUtils.dp2px(this,30));
        itemDecoration.setNodeDrawable(getDrawable(R.drawable.time));
        itemDecoration.setHorizontalLine(ContextCompat.getDrawable(this, R.drawable.horizontal_line));
        itemDecoration.setVerticalLine(ContextCompat.getDrawable(this, R.drawable.gray_line));
        mRecyclerView.addItemDecoration(itemDecoration);

        for (int i = 0; i < resId.length; i++) {
            Item item = new Item();
            item.setResId(resId[i]);
            item.setText("我从未见过如此厚颜无耻之人");
            mList.add(item);
        }

        mAdapter = new TimeLineAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
