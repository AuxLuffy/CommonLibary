package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.AppStartGuide.AppStartGuide;
import com.lenovo.service.basicpubliclibrary.bgabanner.GuideActivity;
import com.lenovo.service.basicpubliclibrary.boommenu.BoomMenuMainActivity;
import com.lenovo.service.basicpubliclibrary.cardstatcview.CardStackActivty;
import com.lenovo.service.basicpubliclibrary.chartview.ChartViewActivity;
import com.lenovo.service.basicpubliclibrary.colordialog.ColorDialogActivity;
import com.lenovo.service.basicpubliclibrary.flycotablayout.ui.SimpleHomeActivity;
import com.lenovo.service.basicpubliclibrary.loaddata.LoadDataActivity;
import com.lenovo.service.basicpubliclibrary.questionnaire.QuestionActivity;
import com.lenovo.service.basicpubliclibrary.recyclertablayout.TabLayoutActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.banner.ui.BannerActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.ui.RecyclerViewActivity;
import com.lenovo.service.basicpubliclibrary.scoringstrip.ScoringStripActivity;
import com.lenovo.service.basicpubliclibrary.pickerview.PickerActivity;
import com.lenovo.service.basicpubliclibrary.scratchview.ScratchViewActivity;
import com.lenovo.service.basicpubliclibrary.smilepraiseview.SmilePraiseActivity;
import com.lenovo.service.basicpubliclibrary.stepview.StepViewActivity;

public class WidgetActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mText1;
    private TextView smileview;
    private TextView loaddata;
    private TextView mTvRatingbar;
    private TextView mTextQuestionnaire;
    private TextView mCardStack;
    private TextView color_dialog;
    private TextView recycler_tablayout;
    private TextView flytab;
    private TextView mTxtPicker;
    //RecyclerView条目操作入口按钮
    private TextView recyclerview_item_opt_tv;
    //RecyclerView实现图片轮播效果入口按钮
    private TextView recyclerview_banner_tv;
    private TextView mTvBoomMenu;
    private TextView tvScratch;
    private TextView mTvGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        init_view();
        init_listener();
    }



    private void init_view() {
        mText1 = (TextView) findViewById(R.id.text1);
        smileview=(TextView) findViewById(R.id.smileview);
        loaddata =(TextView) findViewById(R.id.loaddata);
        mTvRatingbar = (TextView) findViewById(R.id.tv_ratingbar);
        mTextQuestionnaire=(TextView) findViewById(R.id.questionnaire);
        mCardStack= (TextView) findViewById(R.id.card_stack);
        color_dialog = (TextView) findViewById(R.id.color_dialog);
        recycler_tablayout = (TextView) findViewById(R.id.recycler_tablayout);
        flytab = (TextView) findViewById(R.id.fly_tab);
        //获取RecyclerView条目操作入口按钮对象
        recyclerview_item_opt_tv = (TextView) findViewById(R.id.recyclerview_item_opt_tv);
        //获取RecyclerView实现图片轮播效果入口按钮对象
        recyclerview_banner_tv = (TextView) findViewById(R.id.recyclerview_banner_tv);
        mTvBoomMenu = (TextView) findViewById(R.id.tv_boommenu);
        mTxtPicker = (TextView) findViewById(R.id.tv_picker);
        tvScratch = (TextView) findViewById(R.id.tv_scratch);
        mTvGuide = (TextView) findViewById(R.id.tv_guide);
    }

    private void init_listener() {
        mText1.setOnClickListener(this);
        smileview.setOnClickListener(this);
        mTvRatingbar.setOnClickListener(this);
        mCardStack.setOnClickListener(this);
        mTextQuestionnaire.setOnClickListener(this);
        loaddata.setOnClickListener(this);
        color_dialog.setOnClickListener(this);
        recycler_tablayout.setOnClickListener(this);
        flytab.setOnClickListener(this);
        recyclerview_item_opt_tv.setOnClickListener(this);
        recyclerview_banner_tv.setOnClickListener(this);
        mTvBoomMenu.setOnClickListener(this);
        mTxtPicker.setOnClickListener(this);
        tvScratch.setOnClickListener(this);
        mTvGuide.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.text1:
                Intent intent=new Intent();
                intent.setClass(WidgetActivity.this,
                        AppStartGuide.class);
                startActivity(intent);
                break;

            case R.id.smileview:
                Intent intent2=new Intent();
                intent2.setClass(WidgetActivity.this,
                        SmilePraiseActivity.class);
                startActivity(intent2);
                break;

            case R.id.tv_ratingbar:
                Intent intent3 = new Intent();
                intent3.setClass(WidgetActivity.this,
                        ScoringStripActivity.class);
                startActivity(intent3);
                break;
            case R.id.questionnaire:
                Intent intent4=new Intent();
                intent4.setClass(WidgetActivity.this,
                        QuestionActivity.class);
                startActivity(intent4);
                break;
            case R.id.loaddata:
                Intent loadDataIntent=new Intent();
                loadDataIntent.setClass(WidgetActivity.this,
                        LoadDataActivity.class);
                startActivity(loadDataIntent);
                break;
            case R.id.card_stack:
                intent=new Intent(WidgetActivity.this, CardStackActivty.class);
                startActivity(intent);
                break;

            case R.id.color_dialog:
                intent=new Intent(WidgetActivity.this, ColorDialogActivity.class);
                startActivity(intent);
                break;
            case R.id.recycler_tablayout:
                intent=new Intent(WidgetActivity.this, TabLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.fly_tab:
                intent=new Intent(WidgetActivity.this, SimpleHomeActivity.class);
                startActivity(intent);
                break;
            //点击RecyclerView条目操作按钮
            case R.id.recyclerview_item_opt_tv:
                intent=new Intent(WidgetActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            //点击RecyclerView条目操作按钮
            case R.id.recyclerview_banner_tv:
                intent=new Intent(WidgetActivity.this, BannerActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_boommenu:
                startActivity(new Intent(WidgetActivity.this, BoomMenuMainActivity.class));
                break;
            case R.id.tv_picker:
                intent = new Intent(WidgetActivity.this, PickerActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_chartview:
                intent=new Intent(WidgetActivity.this, ChartViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_scratch:
                intent=new Intent(WidgetActivity.this, ScratchViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_guide:
                intent=new Intent(WidgetActivity.this, GuideActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_step_view:
                intent=new Intent(WidgetActivity.this, StepViewActivity.class);
                startActivity(intent);
                break;
        }
    }
}
