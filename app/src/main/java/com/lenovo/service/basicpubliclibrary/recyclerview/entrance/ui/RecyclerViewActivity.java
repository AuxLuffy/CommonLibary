package com.lenovo.service.basicpubliclibrary.recyclerview.entrance.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.recyclerview.banner.ui.BannerActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.discretescrollview.demo.gallery.GalleryActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.discretescrollview.demo.shop.ShopActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.discretescrollview.demo.weather.WeatherActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.entrance.adapter.RecyclerViewAdapter;
import com.lenovo.service.basicpubliclibrary.recyclerview.excel.ExcelActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.decoration.line.ui.DecorationActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.decoration.line.view.DividerItemDecoration;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.decoration.rankinglist.ui.RankingListActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.decoration.timeline.ui.TimeLineActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnItemClickListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.ui.ItemHandleActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.ui.StaggeredGridActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.timeline1.TimeLine1Activity;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.timeline2.DotTimeLineActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.timeline3.ui.TimeLine3Activity;
import com.lenovo.service.basicpubliclibrary.recyclerview.suspension.ui.SuspensionEffectActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.suspension2.ui.SuspensionEffect2Activity;
import com.lenovo.service.basicpubliclibrary.recyclerview.suspension3.ui.SuspensionEffect3Activity;
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
        getSupportActionBar().setTitle("利用ItemDecoration实现城市分类悬浮栏效果");
        initView();
        initData();
    }

    /**
     * 设置数据
     */
    @TargetApi(Build.VERSION_CODES.N)
    private void initData() {

        list.add("利用RecyclerView实现图片轮播效果");
        list.add("列表条目操作:拖拽排序,滑动删除");
        list.add("利用RecyclerView的ItemDecoration实现条目分割线效果");
        list.add("利用RecyclerView的ItemDecoration实现时光轴效果");
        list.add("利用RecyclerView的ItemDecoration实现排行榜效果");
        list.add("利用RecyclerView实现瀑布流效果");
        list.add("瀑布流条目操作:拖拽排序,滑动删除");
        list.add("瀑布流式时光轴效果1");
        list.add("瀑布流式时光轴效果2");
        list.add("瀑布流式时光轴效果3");
        list.add("利用ItemDecoration实现城市分类悬浮栏效果1");
        list.add("利用ItemDecoration实现城市分类悬浮栏效果2");
        list.add("利用ItemDecoration实现城市分类悬浮栏效果3");
        list.add("利用RecyclerView实现Gallery效果");
        list.add("利用RecyclerView实现商品展示效果");
        list.add("利用RecyclerView实现天气预报效果");
        list.add("利用RecyclerView实现Excel效果");
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
                    //点击RecyclerView轮播图效果
                    case 0:
                        startActivity(new Intent(context, BannerActivity.class));
                        break;
                    //点击RecyclerView条目操作
                    case 1:
                        startActivity(new Intent(context, com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.ui.ItemHandleActivity.class));
                        break;
                    //点击RecyclerView条目分割线效果
                    case 2:
                        startActivity(new Intent(context, DecorationActivity.class));
                        break;
                    //点击时光轴效果
                    case 3:
                        startActivity(new Intent(context, TimeLineActivity.class));
                        break;
                    //点击排行榜效果
                    case 4:
                        startActivity(new Intent(context, RankingListActivity.class));
                        break;
                    //点击瀑布流效果
                    case 5:
                        startActivity(new Intent(context, StaggeredGridActivity.class));
                        break;
                    //点击瀑布流条目操作演示
                    case 6:
                        startActivity(new Intent(context, ItemHandleActivity.class));
                        break;
                    //点击瀑布流式时光轴效果
                    case 7:
                        startActivity(new Intent(context, TimeLine1Activity.class));
                        break;
                    //点击瀑布流式时光轴效果2
                    case 8:
                        startActivity(new Intent(context, DotTimeLineActivity.class));
                        break;
                    //点击瀑布流式时光轴效果3
                    case 9:
                        startActivity(new Intent(context, TimeLine3Activity.class));
                        break;
                    //点击利用ItemDecoration实现城市分类悬浮栏效果
                    case 10:
                        startActivity(new Intent(context, SuspensionEffectActivity.class));
                        break;
                    //点击利用ItemDecoration实现城市分类悬浮栏效果2
                    case 11:
                        startActivity(new Intent(context, SuspensionEffect2Activity.class));
                        break;
                    //点击利用ItemDecoration实现城市分类悬浮栏效果3
                    case 12:
                        startActivity(new Intent(context, SuspensionEffect3Activity.class));
                        break;
                    //RecyclerView实现Gallery效果
                    case 13:
                        startActivity(new Intent(context, GalleryActivity.class));
                        break;
                    //RecyclerView实现商品展示效果
                    case 14:
                        startActivity(new Intent(context, ShopActivity.class));
                        break;
                    //RecyclerView天气预报效果
                    case 15:
                        startActivity(new Intent(context, WeatherActivity.class));
                        break;
                    //RecyclerView实现Excel效果
                    case 16:
                        startActivity(new Intent(context, ExcelActivity.class));
                        break;
                }

            }
        });


    }


}
