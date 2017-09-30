package com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.timeline3.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnItemClickListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.timeline3.adapter.WeatherAdapter;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.timeline3.view.TimeLineItemDecoration;
import com.lenovo.service.basicpubliclibrary.shadowmasking.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: RecylerView的itemDecoration演示Activity
 * @author:袁东华
 * @time:2017/9/15 下午4:05
 */
public class TimeLine3Activity extends AppCompatActivity {
    //今天的主角RecyclerView
    private RecyclerView recyclerView;
    private Context context = TimeLine3Activity.this;

    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("利用ItemDecoration实现时光轴效果");
        setContentView(R.layout.activity_recyclerview_item_decoration);
        initView();
        initData();


    }

    private void initView() {
        //获取recyclerView对象
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //创建时光轴
        TimeLineItemDecoration timeLineItemDecoration = new TimeLineItemDecoration();
        timeLineItemDecoration.setColor(getResources().getColor(R.color.colorAccent));
        timeLineItemDecoration.setItemViewLeft(ScreenUtils.dp2px(this, 60));
        timeLineItemDecoration.setNodeRadius(ScreenUtils.dp2px(this, 10));
        timeLineItemDecoration.setDividerHeight(ScreenUtils.dp2px(this, 0));
        timeLineItemDecoration.setTextSize(ScreenUtils.dp2px(this, 10));
        timeLineItemDecoration.setTextColor(getResources().getColor(R.color.colorAccent));
//        timeLineItemDecoration.setStrokeWidth(ScreenUtils.dp2px(this,4));
        //添加分割线
        recyclerView.addItemDecoration(timeLineItemDecoration);
        weatherAdapter = new WeatherAdapter(this, handler);
        recyclerView.setAdapter(weatherAdapter);
        //点击条目
        weatherAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(TimeLine3Activity.this, "点击了条目" + (position + 1), Toast.LENGTH_SHORT).show();

            }
        });


    }

    /**
     * 设置数据
     */
    @TargetApi(Build.VERSION_CODES.N)
    private void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("这是一个任务,可以编辑");
        }


        weatherAdapter.setList(list);
    }

    public Handler handler = new Handler() {
        /**
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //获取电影列表成功
                case 1:
//                    Bundle data = msg.getData();
//                    if (data != null) {
//                        list = data.getParcelableArrayList("list");
//                        weatherAdapter.setList(list);
//                    }

                    break;
                //获取电影列表失败
                case -1:
//                    Bundle errorData = msg.getData();
//                    if (errorData != null) {
//                        String message = errorData.getString("message");
//                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
//                    }
                    break;
            }
        }
    };
}
