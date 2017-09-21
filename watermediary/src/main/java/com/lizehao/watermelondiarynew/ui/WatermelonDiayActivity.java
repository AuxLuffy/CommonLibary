package com.lizehao.watermelondiarynew.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lizehao.watermelondiarynew.R;
import com.lizehao.watermelondiarynew.R2;
import com.lizehao.watermelondiarynew.bean.DiaryBean;
import com.lizehao.watermelondiarynew.db.DiaryDatabaseHelper;
import com.lizehao.watermelondiarynew.event.StartUpdateDiaryEvent;
import com.lizehao.watermelondiarynew.utils.AppManager;
import com.lizehao.watermelondiarynew.utils.GetDate;
import com.lizehao.watermelondiarynew.utils.SpHelper;
import com.lizehao.watermelondiarynew.utils.StatusBarCompat;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WatermelonDiayActivity extends AppCompatActivity {


    @BindView(R2.id.common_iv_back)
    ImageView mCommonIvBack;
    @BindView(R2.id.common_tv_title)
    TextView mCommonTvTitle;
    @BindView(R2.id.common_iv_test)
    ImageView mCommonIvTest;
    @BindView(R2.id.common_title_ll)
    LinearLayout mCommonTitleLl;
    @BindView(R2.id.main_iv_circle)
    ImageView mMainIvCircle;
    @BindView(R2.id.main_tv_date)
    TextView mMainTvDate;
    @BindView(R2.id.main_tv_content)
    TextView mMainTvContent;
    @BindView(R2.id.item_ll_control)
    LinearLayout mItemLlControl;
    @BindView(R2.id.main_rv_show_diary)
    RecyclerView mMainRvShowDiary;
    @BindView(R2.id.main_fab_enter_edit)
    FloatingActionButton mMainFabEnterEdit;
    @BindView(R2.id.main_rl_main)
    RelativeLayout mMainRlMain;
    @BindView(R2.id.item_first)
    LinearLayout mItemFirst;
    @BindView(R2.id.main_ll_main)
    LinearLayout mMainLlMain;
    private List<DiaryBean> mDiaryBeanList;
    private DiaryDatabaseHelper mHelper;
    private static String IS_WRITE = "true";
    private int mEditPosition = -1;

    /**
     * 标识今天是否已经写了日记
     */
    private boolean isWrite = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watermelondiary);
        init_assembly();
        getDiaryBeanList();
        initTitle();
        mMainRvShowDiary.setLayoutManager(new LinearLayoutManager(this));
        mMainRvShowDiary.setAdapter(new DiaryAdapter(this, mDiaryBeanList));
    }



    // 初始化数据
    private void init_assembly() {
        AppManager.getAppManager().addActivity(this);// 添加Activity管理器
        ButterKnife.bind(this);// 添加注解依赖注入
        StatusBarCompat.compat(this, Color.parseColor("#6495ED"));// 添加StatusBar的颜色
        mHelper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);// 添加数据库管理器
        ActionBar actionBar = getSupportActionBar();// 添加actionbar
        actionBar.hide();
        EventBus.getDefault().register(this);// 添加eventBus的广播接收
        SpHelper spHelper = SpHelper.getInstance(this);// sp工具类
    }

    // 设置title信息
    private void initTitle() {
        mMainTvDate.setText("今天，" + GetDate.getDate());
        mCommonTvTitle.setText("日记");
        mCommonIvBack.setVisibility(View.INVISIBLE);
        mCommonIvTest.setVisibility(View.INVISIBLE);
    }

    // 获取所有日记
    private List<DiaryBean> getDiaryBeanList() {
        mDiaryBeanList = new ArrayList<>();
        List<DiaryBean> diaryList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = mHelper.getWritableDatabase();
        // 获取数据库中的值
        Cursor cursor = sqLiteDatabase.query("Diary", null, null, null, null, null, null);
        // 如果数据库中有数据,则把第一行数据删除
        if (cursor.moveToFirst()) {
            do {
                // 当前日期
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String dateSystem = GetDate.getDate().toString();
                if (date.equals(dateSystem)) {
                    mMainLlMain.removeView(mItemFirst);
                    break;
                }
            } while (cursor.moveToNext());
        }
        // 遍历所有的数据
        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndex("date"));// 时间
                String title = cursor.getString(cursor.getColumnIndex("title"));// 名字
                String content = cursor.getString(cursor.getColumnIndex("content"));// 内容
                String tag = cursor.getString(cursor.getColumnIndex("tag"));// 排列
                mDiaryBeanList.add(new DiaryBean(date, title, content, tag));
            } while (cursor.moveToNext());
        }
        cursor.close();

        for (int i = mDiaryBeanList.size() - 1; i >= 0; i--) {
            diaryList.add(mDiaryBeanList.get(i));
        }

        mDiaryBeanList = diaryList;
        return mDiaryBeanList;
    }

    // 点击修改,跳转修改页面
    @Subscribe
    public void startUpdateDiaryActivity(StartUpdateDiaryEvent event) {
        String title = mDiaryBeanList.get(event.getPosition()).getTitle();
        String content = mDiaryBeanList.get(event.getPosition()).getContent();
        String tag = mDiaryBeanList.get(event.getPosition()).getTag();
        UpdateDiaryActivity.startActivity(this, title, content, tag);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R2.id.main_fab_enter_edit)
    public void onClick() {
        AddDiaryActivity.startActivity(this);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, WatermelonDiayActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        AppManager.getAppManager().finishAllActivity();
    }
}