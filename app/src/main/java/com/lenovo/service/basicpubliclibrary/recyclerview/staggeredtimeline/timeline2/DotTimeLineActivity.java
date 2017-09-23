package com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.timeline2;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;


import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.itemdecoration.DotItemDecoration;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.itemdecoration.SpanIndexListener;
import com.lenovo.service.basicpubliclibrary.utils.DateUtils;
import com.lenovo.service.basicpubliclibrary.utils.LogUtil;
import com.lenovo.service.basicpubliclibrary.utils.TimeUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *@description: 瀑布流式时光轴效果(任务列表)演示界面
 *@author:袁东华
 *@time:2017/9/18 下午6:06
 */
public class DotTimeLineActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;

    List<Event> mList = new ArrayList<>();
    DotTimeLineAdapter mAdapter;
    DotItemDecoration mItemDecoration;


    String[] events = new String[]{
            "起床洗漱",
            "出门,坐地铁",
            "到达西直门,转13号线",
            "到达上地站,步行去公司",
            "到达公司,打卡",
            "到公司第一件事,打开电脑登陆微信"
    };
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("瀑布流式时光轴效果2");
        setContentView(R.layout.dot_timeline_layout);
        // 获取Calendar实例
        Calendar calendar = Calendar.getInstance();
        String time = calendar.get(Calendar.YEAR) + "." + (calendar.get(Calendar.MONTH)+1 )+ "." + calendar.get(Calendar.DAY_OF_MONTH) + " 上午 " + "09:00";
        LogUtil.e("time:"+time);

        try {
            long baseTime= TimeUtil.stringToLong(time,"yyyy.MM.dd a HH:mm");
            long[] times = {
                     baseTime - 120 * 60 * 1000,
                     baseTime - 90 * 60 * 1000,
                     baseTime - 40 * 60 * 1000,
                     baseTime - 20 * 60 * 1000,
                     baseTime,
                     baseTime + 2 * 60 * 1000
             };


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mItemDecoration = new DotItemDecoration
                .Builder(this)
                .setOrientation(DotItemDecoration.VERTICAL)//if you want a horizontal item decoration,remember to set horizontal orientation to your LayoutManager
                .setItemStyle(DotItemDecoration.STYLE_DRAW)
                .setTopDistance(20)//dp
                .setItemInterVal(10)//dp
                .setItemPaddingLeft(20)//default value equals to item interval value
                .setItemPaddingRight(20)//default value equals to item interval value
                .setDotColor(Color.WHITE)
                .setDotRadius(2)//dp
                .setDotPaddingTop(0)
                .setDotInItemOrientationCenter(false)//set true if you want the dot align center
                .setLineColor(Color.RED)
                .setLineWidth(1)//dp
                .setEndText("END")
                .setTextColor(Color.WHITE)
                .setTextSize(10)//sp
                .setDotPaddingText(2)//dp.The distance between the last dot and the end text
                .setBottomDistance(40)//you can add a distance to make bottom line longer
                .create();
        mItemDecoration.setSpanIndexListener(new SpanIndexListener() {
            @Override
            public void onSpanIndexChange(View view, int spanIndex) {
                Log.i("Info","view:"+view+"  span:"+spanIndex);
                view.setBackgroundResource(spanIndex == 0 ? R.drawable.pop_left : R.drawable.pop_right);
            }
        });
        mRecyclerView.addItemDecoration(mItemDecoration);

        for (int i = 0; i < times.length; i++) {
            Event event = new Event();
            event.setTime(times[i]);
            LogUtil.e("times[i]:"+times[i]);
            LogUtil.e("events[i]:"+events[i]);
            event.setEvent(events[i]);
            mList.add(event);
        }

        mAdapter = new DotTimeLineAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);

        } catch (ParseException e) {
           LogUtil.e("异常:"+e.getMessage());
        }


    }
}
