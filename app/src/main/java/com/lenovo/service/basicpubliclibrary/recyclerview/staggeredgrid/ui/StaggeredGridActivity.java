package com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.adapter.WeatherAdapter;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.data.Weather;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 利用RecyclerView实现瀑布流效果演示界面
 * @author:袁东华 created at 2016/8/25 0025 下午 5:12
 */

public class StaggeredGridActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    private ArrayList<Weather> list;
    private Activity activity = StaggeredGridActivity.this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_item);
        getSupportActionBar().setTitle("利用RecyclerView实现瀑布流效果");
        initView();
        initData();
    }

    /**
     * 设置数据
     */
    @TargetApi(Build.VERSION_CODES.N)
    private void initData() {

        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.timg1);
        list.add(R.drawable.timg2);
        list.add(R.drawable.timg3);
        list.add(R.drawable.timg4);
        list.add(R.drawable.timg5);
        list.add(R.drawable.timg6);
        list.add(R.drawable.timg7);
        list.add(R.drawable.timg8);
        list.add(R.drawable.timg9);
        list.add(R.drawable.timg10);
        list.add(R.drawable.timg11);
        list.add(R.drawable.timg12);
        list.add(R.drawable.timg13);
        list.add(R.drawable.timg14);
        list.add(R.drawable.timg15);
        weatherAdapter.setList(list);
    }


    public void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        weatherAdapter = new WeatherAdapter(activity);
        recyclerView.setAdapter(weatherAdapter);
        //点击条目
        weatherAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(activity, "点击了条目" + (position+1), Toast.LENGTH_SHORT).show();

            }
        });




    }




}
