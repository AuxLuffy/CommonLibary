package com.lenovo.service.basicpubliclibrary.recyclerview.entrance.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.WidgetActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.banner.ui.BannerActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.entrance.adapter.RecyclerViewAdapter;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.decoration.line.ui.DecorationActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.decoration.line.view.DividerItemDecoration;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.adapter.WeatherAdapter;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.data.Weather;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.ItemTouchHelperCallback;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnDragListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnItemClickListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.ui.ItemHandleActivity;
import com.lenovo.service.basicpubliclibrary.shadowmasking.utils.ScreenUtils;

import java.util.ArrayList;

/**
 * @description:RecyclerView RecyclerView相关演示界面
 * @author:袁东华 created at 2016/8/25 0025 下午 5:12
 */

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<String> list = new ArrayList<>();
    private Context context = RecyclerViewActivity.this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        getSupportActionBar().setTitle("RecyclerView相关实现效果");
        initView();
        initData();
    }

    /**
     * 设置数据
     */
    @TargetApi(Build.VERSION_CODES.N)
    private void initData() {

        list.add("利用RecyclerView实现图片轮播效果");
        list.add("RecyclerView条目操作:拖拽排序,滑动删除");
        list.add("利用RecyclerView的ItemDecoration实现条目分割线效果");

        recyclerViewAdapter.setList(list);
    }


    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        //创建分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration();
        dividerItemDecoration.setColor(getResources().getColor(R.color.white));
        dividerItemDecoration.setDividerHeight(ScreenUtils.dp2px(context, 1));
        dividerItemDecoration.setFirstItemHasDivider(true);
        //添加分割线
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerViewAdapter = new RecyclerViewAdapter(context);
        recyclerView.setAdapter(recyclerViewAdapter);
        //点击条目
        recyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    //点击RecyclerView轮播图效果按钮
                    case 0:
                        startActivity(new Intent(context, BannerActivity.class));
                        break;
                    //点击RecyclerView条目操作按钮
                    case 1:
                        startActivity(new Intent(context, ItemHandleActivity.class));
                        break;
                    //点击RecyclerView条目分割线效果按钮
                    case 2:
                        startActivity(new Intent(context, DecorationActivity.class));
                        break;
                }

            }
        });


    }


}
