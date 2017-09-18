package com.lenovo.service.basicpubliclibrary.recyclerview.item.decoration.timeline.ui;

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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.decoration.line.adapter.WeatherAdapter;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.decoration.line.view.DividerItemDecoration;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.decoration.timeline.view.TimeLineItemDecoration;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.data.Weather;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.ItemTouchHelperCallback;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnDragListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnItemClickListener;
import com.lenovo.service.basicpubliclibrary.shadowmasking.utils.ScreenUtils;

import java.util.ArrayList;

/**
 * @description: RecylerView的itemDecoration演示Activity
 * @author:袁东华
 * @time:2017/9/15 下午4:05
 */
public class TimeLineActivity extends AppCompatActivity {
    //今天的主角RecyclerView
    private RecyclerView recyclerView;
    private Context context=TimeLineActivity.this;
    //图片资源
    private int[] imgs = {R.drawable.icon_cloudy, R.drawable.icon_cloudy_nighttime,
            R.drawable.icon_gale, R.drawable.icon_heavy_rain,
            R.drawable.icon_heavy_snow, R.drawable.icon_meteor,
            R.drawable.icon_moon, R.drawable.icon_mostly_cloudy,
            R.drawable.icon_rain, R.drawable.icon_setting_sun,
            R.drawable.icon_snow, R.drawable.icon_stars,
            R.drawable.icon_sun, R.drawable.icon_sunrise_by_the_sea,
            R.drawable.icon_tornado, R.drawable.icon_rainbow,
            R.drawable.icon_rain_nighttime, R.drawable.icon_thundershower,
            R.drawable.icon_thunderstorm, R.drawable.icon_heavysnow};
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
        timeLineItemDecoration.setItemViewLeft(ScreenUtils.dp2px(this,60));
        timeLineItemDecoration.setNodeRadius(ScreenUtils.dp2px(this,10));
        timeLineItemDecoration.setDividerHeight(ScreenUtils.dp2px(this,0));
        timeLineItemDecoration.setTextSize(ScreenUtils.dp2px(this,10));
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
                Toast.makeText(TimeLineActivity.this, "点击了条目" + (position+1), Toast.LENGTH_SHORT).show();

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
            }
        });
    }

    /**
     * 设置数据
     */
    @TargetApi(Build.VERSION_CODES.N)
    private void initData() {
        // 获取Calendar实例
        Calendar calendar = Calendar.getInstance();

        ArrayList<Weather> weatherList = new ArrayList<>();


        for (int i = 0; i < 20; i++) {
            Weather weather = new Weather();
            weather.setTitle("我是条目" + (i + 1) + "的标题");
            weather.setDescr("我是条目" + (i + 1) + "的描述");
            weather.setThumb(imgs[i]);
            weather.setTimeShow(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " +
                    calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
            weatherList.add(weather);
        }


        weatherAdapter.setList(weatherList);
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
