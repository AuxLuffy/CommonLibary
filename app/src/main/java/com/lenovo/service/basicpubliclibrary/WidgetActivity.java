package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.AppStartGuide.AppStartGuide;
import com.lenovo.service.basicpubliclibrary.LoadMoreRecyclerView.MyRecyclerViewActivity;
import com.lenovo.service.basicpubliclibrary.Tastytoast.ToastActivity;
import com.lenovo.service.basicpubliclibrary.UserGuideView.UserGuideActivity;
import com.lenovo.service.basicpubliclibrary.avatarlabelview.AvatarLabelActivity;
import com.lenovo.service.basicpubliclibrary.bgabanner.GuideActivity;
import com.lenovo.service.basicpubliclibrary.boommenu.BoomMenuMainActivity;
import com.lenovo.service.basicpubliclibrary.bottleLoadingView.GABottleLoadingViewAcivity;
import com.lenovo.service.basicpubliclibrary.cardstatcview.CardStackActivty;
import com.lenovo.service.basicpubliclibrary.cardview.CardActivity;
import com.lenovo.service.basicpubliclibrary.chartview.ChartViewActivity;
import com.lenovo.service.basicpubliclibrary.colordialog.ColorDialogActivity;
import com.lenovo.service.basicpubliclibrary.dialogfragment.DialogActivity;
import com.lenovo.service.basicpubliclibrary.doodle.DoodleViewActivity;
import com.lenovo.service.basicpubliclibrary.flowlayout.CategoryActivity;
import com.lenovo.service.basicpubliclibrary.dropdownmenu.DropDownActivity;
import com.lenovo.service.basicpubliclibrary.flycotablayout.ui.SimpleHomeActivity;
import com.lenovo.service.basicpubliclibrary.gesturelock.GestureLockActivity;
import com.lenovo.service.basicpubliclibrary.jike.JikeGalleryActivity;
import com.lenovo.service.basicpubliclibrary.loaddata.LoadDataActivity;
import com.lenovo.service.basicpubliclibrary.pickerview.PickerActivity;
import com.lenovo.service.basicpubliclibrary.popup.PopUpActivity;
import com.lenovo.service.basicpubliclibrary.questionnaire.QuestionActivity;
import com.lenovo.service.basicpubliclibrary.recyclertablayout.TabLayoutActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.banner.ui.BannerActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.ui.RecyclerViewActivity;
import com.lenovo.service.basicpubliclibrary.roundiamge.RoundImageActivity;
import com.lenovo.service.basicpubliclibrary.scoringstrip.ScoringStripActivity;
import com.lenovo.service.basicpubliclibrary.scratchview.ScratchViewActivity;
import com.lenovo.service.basicpubliclibrary.smilepraiseview.SmilePraiseActivity;
import com.lenovo.service.basicpubliclibrary.stepview.StepViewActivity;
import com.lenovo.service.basicpubliclibrary.tagviewgroup.TagViewGroupActivity;
import com.lenovo.service.basicpubliclibrary.timepicker.TimePickerActivity;
import com.lenovo.service.basicpubliclibrary.toolbar.TestToolbarActivity;
import com.lenovo.service.basicpubliclibrary.ultraviewpager.UPVDemoActivity;
import com.lenovo.service.basicpubliclibrary.weekview.WeekViewActivity;
import com.lenovo.service.basicpubliclibrary.zoomheader.ZoomHeaderActivity;



public class WidgetActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mText1;
    private TextView smileview;
    private TextView tvRoundImage;
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
    private TextView mTvZoomheader;
    private TextView mTvDoodle;
    private TextView mTvPop;
    private TextView mTvToast;
    private TextView tvJike;
    private TextView tv_gesture_lock;
    private TextView mUserGuide;
    private TextView tagview;
    private TextView mCard;
    private TextView mAvatarLabel;
    private TextView mTvBottleloading;
    private TextView mTvWeekview;
    private TextView mTvDialogfragment;
    private TextView mTvFlowflag;
    private TextView mTvViewpager;
    private TextView mTvDropDownMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        init_view();
        init_listener();
    }


    private void init_view() {
        tvJike = (TextView) findViewById(R.id.tvJike);
        mText1 = (TextView) findViewById(R.id.text1);
        smileview = (TextView) findViewById(R.id.smileview);
        loaddata = (TextView) findViewById(R.id.loaddata);
        tvRoundImage = (TextView) findViewById(R.id.tvRoundImage);
        mTvRatingbar = (TextView) findViewById(R.id.tv_ratingbar);
        mTextQuestionnaire = (TextView) findViewById(R.id.questionnaire);
        mCardStack = (TextView) findViewById(R.id.card_stack);
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
        mTvZoomheader = (TextView) findViewById(R.id.tv_zoomheader);
        mTvDoodle = (TextView) findViewById(R.id.tv_doodle);
        mTvPop = (TextView) findViewById(R.id.tv_popup);
        mTvToast = (TextView) findViewById(R.id.tv_toast);
        mUserGuide = (TextView) findViewById(R.id.user_guide);
        tv_gesture_lock = (TextView) findViewById(R.id.tv_gesture_lock);
        tagview = (TextView) findViewById(R.id.tv_tagview);
        mCard = (TextView) findViewById(R.id.card);
        mAvatarLabel = (TextView) findViewById(R.id.avatar_label);
        mTvBottleloading = (TextView) findViewById(R.id.tv_bottleloading);
        mTvWeekview= (TextView) findViewById(R.id.tv_weekview);
        mTvDialogfragment = (TextView) findViewById(R.id.tv_dialogfragment);
        mTvWeekview = (TextView) findViewById(R.id.tv_weekview);
        mTvFlowflag = (TextView) findViewById(R.id.tv_flowflag);
        mTvViewpager = (TextView) findViewById(R.id.tv_viewpager);
        mTvDropDownMenu = (TextView) findViewById(R.id.tv_dropdownmenu);
    }

    private void init_listener() {
        mText1.setOnClickListener(this);
        tvRoundImage.setOnClickListener(this);
        smileview.setOnClickListener(this);
        tvJike.setOnClickListener(this);
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
        mTvZoomheader.setOnClickListener(this);
        mTvDoodle.setOnClickListener(this);
        mTvPop.setOnClickListener(this);
        mTvToast.setOnClickListener(this);
        findViewById(R.id.tv_recyclerview).setOnClickListener(this);
        findViewById(R.id.tv_toolbar).setOnClickListener(this);
        findViewById(R.id.tv_timepicker).setOnClickListener(this);
        mUserGuide.setOnClickListener(this);
        tv_gesture_lock.setOnClickListener(this);
        tagview.setOnClickListener(this);
        mCard.setOnClickListener(this);
        mAvatarLabel.setOnClickListener(this);
        mTvBottleloading.setOnClickListener(this);
        mTvWeekview.setOnClickListener(this);
        mTvDialogfragment.setOnClickListener(this);
        mTvFlowflag.setOnClickListener(this);
        mTvViewpager.setOnClickListener(this);
        mTvDropDownMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text1:
                Intent intent = new Intent();
                intent.setClass(WidgetActivity.this,
                        AppStartGuide.class);
                startActivity(intent);
                break;

            case R.id.smileview:
                Intent intent2 = new Intent();
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
                Intent intent4 = new Intent();
                intent4.setClass(WidgetActivity.this,
                        QuestionActivity.class);
                startActivity(intent4);
                break;
            case R.id.loaddata:
                Intent loadDataIntent = new Intent();
                loadDataIntent.setClass(WidgetActivity.this,
                        LoadDataActivity.class);
                startActivity(loadDataIntent);
                break;
            case R.id.card_stack:
                intent = new Intent(WidgetActivity.this, CardStackActivty.class);
                startActivity(intent);
                break;

            case R.id.color_dialog:
                intent = new Intent(WidgetActivity.this, ColorDialogActivity.class);
                startActivity(intent);
                break;
            case R.id.recycler_tablayout:
                intent = new Intent(WidgetActivity.this, TabLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.fly_tab:
                intent = new Intent(WidgetActivity.this, SimpleHomeActivity.class);
                startActivity(intent);
                break;
            //点击RecyclerView条目操作按钮
            case R.id.recyclerview_item_opt_tv:
                intent = new Intent(WidgetActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            //点击RecyclerView条目操作按钮
            case R.id.recyclerview_banner_tv:
                intent = new Intent(WidgetActivity.this, BannerActivity.class);
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
                intent = new Intent(WidgetActivity.this, ChartViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_scratch:
                intent = new Intent(WidgetActivity.this, ScratchViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_guide:
                intent = new Intent(WidgetActivity.this, GuideActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_step_view:
                intent = new Intent(WidgetActivity.this, StepViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tvRoundImage:
                intent = new Intent(WidgetActivity.this, RoundImageActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_zoomheader:
                startActivity(new Intent(WidgetActivity.this, ZoomHeaderActivity.class));
                break;
            case R.id.tv_doodle:
                startActivity(new Intent(WidgetActivity.this, DoodleViewActivity.class));
                break;
            case R.id.tv_popup://弹出视图页面
                startActivity(new Intent(WidgetActivity.this, PopUpActivity.class));
                break;
            case R.id.tv_toast://土司
                startActivity(new Intent(WidgetActivity.this, ToastActivity.class));
                break;
            case R.id.tvJike:
                startActivity(new Intent(WidgetActivity.this, JikeGalleryActivity.class));
                break;
            case R.id.tv_recyclerview:
                startActivity(new Intent(WidgetActivity.this, MyRecyclerViewActivity.class));
                break;
            case R.id.user_guide:
                startActivity(new Intent(WidgetActivity.this, UserGuideActivity.class));
                break;
            case R.id.tv_gesture_lock:
                startActivity(new Intent(WidgetActivity.this, GestureLockActivity.class));
                break;
            case R.id.tv_tagview:
                startActivity(new Intent(WidgetActivity.this, TagViewGroupActivity.class));
                break;
            case R.id.card:
                startActivity(new Intent(WidgetActivity.this, CardActivity.class));
                break;
            case R.id.avatar_label:
                startActivity(new Intent(WidgetActivity.this, AvatarLabelActivity.class));
                break;
            case R.id.tv_bottleloading:
                startActivity(new Intent(WidgetActivity.this, GABottleLoadingViewAcivity.class));
                break;
            case R.id.tv_toolbar:
                startActivity(new Intent(WidgetActivity.this, TestToolbarActivity.class));
                break;
            case R.id.tv_timepicker:
                startActivity(new Intent(WidgetActivity.this, TimePickerActivity.class));
                break;
            case R.id.tv_weekview:
                startActivity(new Intent(WidgetActivity.this, WeekViewActivity.class));
                break;
            case R.id.tv_dialogfragment:
                startActivity(new Intent(WidgetActivity.this, DialogActivity.class));
                break;
            case R.id.tv_flowflag:
                startActivity(new Intent(WidgetActivity.this, CategoryActivity.class));
                break;
            case R.id.tv_viewpager:
                startActivity(new Intent(WidgetActivity.this, UPVDemoActivity.class));
                break;
            case R.id.tv_dropdownmenu:
                startActivity(new Intent(WidgetActivity.this, DropDownActivity.class));
                break;
        }
    }
}
