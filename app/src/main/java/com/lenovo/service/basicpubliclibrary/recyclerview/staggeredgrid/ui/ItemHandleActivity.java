package com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.adapter.WeatherAdapter;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.data.Weather;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.listener.ItemTouchHelperCallback;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.listener.OnDragListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.listener.OnItemClickListener;
import com.lenovo.service.basicpubliclibrary.utils.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 瀑布流条目操作演示界面
 * @author:袁东华 created at 2016/8/25 0025 下午 5:12
 */

public class ItemHandleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    private ArrayList<Weather> list;
    private Activity activity = ItemHandleActivity.this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_item);
        getSupportActionBar().setTitle("瀑布流条目操作");
        getSupportActionBar().setSubtitle("长按拖动条目,滑动删除条目");
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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        weatherAdapter = new WeatherAdapter(activity);
        recyclerView.setAdapter(weatherAdapter);
        //点击条目
        weatherAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(activity, "点击了条目" + (position + 1), Toast.LENGTH_SHORT).show();

            }
        });


        //historyAdapter就是RecyclerView的适配器,historyAdapter实现了接口OnMoveAndSwipedListener
        ItemTouchHelperCallback callback = new ItemTouchHelperCallback(weatherAdapter);
        //ItemTouchHelper的构造器需要传入callback,拖拽和滑动事件需要回调callback中的3个方法
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        //把RecyclerView和ItemTouchHelper关联起来用此方法
        itemTouchHelper.attachToRecyclerView(recyclerView);
        //设置条目拖拽接口
        weatherAdapter.setOnDragListener(new OnDragListener() {
            /**
             * @param viewHolder
             * @description:当条目需要拖拽的时候,适配器调用onDrag
             * @author:袁东华 created at 2016/8/31 0031 下午 1:26
             */
            @Override
            public void startDrag(RecyclerView.ViewHolder viewHolder) {
                itemTouchHelper.startDrag(viewHolder);
                LogUtil.e("开始拖拽");
            }
        });

    }


}
