package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.AppStartGuide.AppStartGuide;
import com.lenovo.service.basicpubliclibrary.LoadMoreRecyclerView.MyRecyclerViewActivity;
import com.lenovo.service.basicpubliclibrary.blurry.BlurryTestActivity;
import com.lenovo.service.basicpubliclibrary.Tastytoast.ToastActivity;
import com.lenovo.service.basicpubliclibrary.UserGuideView.UserGuideActivity;
import com.lenovo.service.basicpubliclibrary.addsub.AddSubActivity;
import com.lenovo.service.basicpubliclibrary.alerter.AlerterActivity;
import com.lenovo.service.basicpubliclibrary.avatarlabelview.AvatarLabelActivity;
import com.lenovo.service.basicpubliclibrary.bgabanner.GuideActivity;
import com.lenovo.service.basicpubliclibrary.boommenu.BoomMenuMainActivity;
import com.lenovo.service.basicpubliclibrary.bottleLoadingView.GABottleLoadingViewAcivity;
import com.lenovo.service.basicpubliclibrary.cardstatcview.CardStackActivty;
import com.lenovo.service.basicpubliclibrary.cardview.CardActivity;
import com.lenovo.service.basicpubliclibrary.chartview.ChartViewActivity;
import com.lenovo.service.basicpubliclibrary.counter.CounterActivity;
import com.lenovo.service.basicpubliclibrary.dialog.ColorDialogActivity;
import com.lenovo.service.basicpubliclibrary.dialogfragment.DialogActivity;
import com.lenovo.service.basicpubliclibrary.doodle.DoodleViewActivity;
import com.lenovo.service.basicpubliclibrary.dropdownmenu.DropDownActivity;
import com.lenovo.service.basicpubliclibrary.expandable.ExpandableActivity;
import com.lenovo.service.basicpubliclibrary.flowlayout.CategoryActivity;
import com.lenovo.service.basicpubliclibrary.flycotablayout.ui.SimpleHomeActivity;
import com.lenovo.service.basicpubliclibrary.gesturelock.GestureLockActivity;
import com.lenovo.service.basicpubliclibrary.iconbadge.IconBadgeActivity;
import com.lenovo.service.basicpubliclibrary.jike.JikeGalleryActivity;
import com.lenovo.service.basicpubliclibrary.loaddata.LoadDataActivity;
import com.lenovo.service.basicpubliclibrary.marqueue.MarqueueActivity;
import com.lenovo.service.basicpubliclibrary.materialripple.MaterialRippleActivity;
import com.lenovo.service.basicpubliclibrary.panoramimageview.PanoramImageActivity;
import com.lenovo.service.basicpubliclibrary.pickerview.PickerActivity;
import com.lenovo.service.basicpubliclibrary.popup.PopUpActivity;
import com.lenovo.service.basicpubliclibrary.progressbar.ProgressBarActivity;
import com.lenovo.service.basicpubliclibrary.psdinput.PayPsdViewActivity;
import com.lenovo.service.basicpubliclibrary.pulseview.PhotoViewActivity;
import com.lenovo.service.basicpubliclibrary.pulseview.PulseViewActivity;
import com.lenovo.service.basicpubliclibrary.questionnaire.QuestionActivity;
import com.lenovo.service.basicpubliclibrary.recyclertablayout.TabLayoutActivity;
import com.lenovo.service.basicpubliclibrary.recyclerview.entrance.ui.RecyclerViewActivity;
import com.lenovo.service.basicpubliclibrary.roundiamge.RoundImageActivity;
import com.lenovo.service.basicpubliclibrary.scoringstrip.ScoringStripActivity;
import com.lenovo.service.basicpubliclibrary.scratchview.ScratchViewActivity;
import com.lenovo.service.basicpubliclibrary.signcalendar.SignCalendarActivity;
import com.lenovo.service.basicpubliclibrary.smilepraiseview.SmilePraiseActivity;
import com.lenovo.service.basicpubliclibrary.smileyrating.SmileRatingActivity;
import com.lenovo.service.basicpubliclibrary.stepview.StepViewActivity;
import com.lenovo.service.basicpubliclibrary.tagviewgroup.TagViewGroupActivity;
import com.lenovo.service.basicpubliclibrary.textview.linktextview.LinkTextViewActivity;
import com.lenovo.service.basicpubliclibrary.textview.spantext.SpanTextActivity;
import com.lenovo.service.basicpubliclibrary.textview.spantouchfixtextview.SpanTouchFixTextViewActivity;
import com.lenovo.service.basicpubliclibrary.textview.textstyle.TextActivity;
import com.lenovo.service.basicpubliclibrary.textview.verticaltextview.VerticalTextViewActivity;
import com.lenovo.service.basicpubliclibrary.tickerview.TickerActivity;
import com.lenovo.service.basicpubliclibrary.timepicker.TimePickerActivity;
import com.lenovo.service.basicpubliclibrary.toolbar.TestToolbarActivity;
import com.lenovo.service.basicpubliclibrary.ultraviewpager.UPVDemoActivity;
import com.lenovo.service.basicpubliclibrary.weekview.WeekViewActivity;
import com.lenovo.service.basicpubliclibrary.widget.CircleImageActivity;
import com.lenovo.service.basicpubliclibrary.zoomheader.ZoomHeaderActivity;
import com.lizehao.watermelondiarynew.ui.WatermelonDiayActivity;
import com.wind.windlinkrecycleview.WindlinkRecycleviewActivity;


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
    //RecyclerView相关实现效果入口按钮
    private TextView recyclerview_tv;
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
    private TextView mTvSmileRating;
    private TextView mTvText;
    private TextView mTvSignCalendar;
    private TextView mTvAddSub;
    private TextView mTvHeadView;
    private TextView mTvAlert;
    private TextView verticalTextView;
    private TextView spanTextView;
    private TextView mTvBadge;
    private TextView mTvPanoraimage;
    private TextView mTvWatermediary;
    private TextView mLinkTextView;
    private TextView mTvCount;
    private TextView mSpanTouchFixTextView;
    private TextView mTvMarqueue;
    private TextView mTvWindlink;
    private TextView mTvPulseView;
    private TextView mTvMasicoView;
    private TextView mTvBlurry;
    private TextView mTvTicker;
    private TextView mTvMaterialRipple;

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
        //获取RecyclerView相关实现效果入口按钮对象
        recyclerview_tv = (TextView) findViewById(R.id.recyclerview_tv);
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
        mTvWeekview = (TextView) findViewById(R.id.tv_weekview);
        mTvDialogfragment = (TextView) findViewById(R.id.tv_dialogfragment);
        mTvWeekview = (TextView) findViewById(R.id.tv_weekview);
        mTvFlowflag = (TextView) findViewById(R.id.tv_flowflag);
        mTvViewpager = (TextView) findViewById(R.id.tv_viewpager);
        mTvDropDownMenu = (TextView) findViewById(R.id.tv_dropdownmenu);
        mTvSmileRating = (TextView) findViewById(R.id.tv_smilerating);
        mTvText = (TextView) findViewById(R.id.tv_text);
        mTvSignCalendar = (TextView) findViewById(R.id.sign_calendar);
        mTvAddSub = (TextView) findViewById(R.id.tv_addsub);
        mTvHeadView = (TextView) findViewById(R.id.tv_headView);
        mTvAlert = (TextView) findViewById(R.id.tv_alertDialog);
        verticalTextView = (TextView) findViewById(R.id.verticalTextView);
        spanTextView = (TextView) findViewById(R.id.spanTextView);
        mTvBadge = (TextView) findViewById(R.id.tv_badge);
        mTvPanoraimage = (TextView) findViewById(R.id.tv_panoraimage);
        mTvWatermediary = (TextView) findViewById(R.id.tvWatermediary);
        mLinkTextView = (TextView) findViewById(R.id.linkTextView);
        mTvCount = (TextView) findViewById(R.id.tv_count);
        mSpanTouchFixTextView = (TextView) findViewById(R.id.spanTouchFixTextView);
        mTvMarqueue = (TextView) findViewById(R.id.tv_marqueue);
        mTvWindlink = (TextView) findViewById(R.id.tvWindlink);
        mTvPulseView = (TextView) findViewById(R.id.tvPulseActivity);
        mTvMasicoView = (TextView) findViewById(R.id.tvMasaccioView);
        mTvTicker = (TextView) findViewById(R.id.tvTickerView);
        mTvBlurry = (TextView) findViewById(R.id.tvBlurry);
        mTvMaterialRipple = (TextView) findViewById(R.id.tv_materialripple);
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
        recyclerview_tv.setOnClickListener(this);
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
        findViewById(R.id.tv_expandable).setOnClickListener(this);
        findViewById(R.id.tv_psw_input).setOnClickListener(this);
        findViewById(R.id.tv_progress).setOnClickListener(this);
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
        mTvSmileRating.setOnClickListener(this);
        mTvText.setOnClickListener(this);
        mTvSignCalendar.setOnClickListener(this);
        mTvAddSub.setOnClickListener(this);
        mTvHeadView.setOnClickListener(this);
        mTvAlert.setOnClickListener(this);
        verticalTextView.setOnClickListener(this);
        spanTextView.setOnClickListener(this);
        mTvBadge.setOnClickListener(this);
        mTvPanoraimage.setOnClickListener(this);
        mTvWatermediary.setOnClickListener(this);
        mLinkTextView.setOnClickListener(this);
        mTvCount.setOnClickListener(this);
        mSpanTouchFixTextView.setOnClickListener(this);
        mTvMarqueue.setOnClickListener(this);
        mTvWindlink.setOnClickListener(this);
        mTvPulseView.setOnClickListener(this);
        mTvMasicoView.setOnClickListener(this);
        mTvTicker.setOnClickListener(this);
        mTvBlurry.setOnClickListener(this);
        mTvMaterialRipple.setOnClickListener(this);
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
            //点击RecyclerView相关实现效果入口按钮
            case R.id.recyclerview_tv:
                startActivity(new Intent(WidgetActivity.this, RecyclerViewActivity.class));
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
            case R.id.tv_smilerating:
                startActivity(new Intent(WidgetActivity.this, SmileRatingActivity.class));
                break;
            case R.id.tv_text://文本多样化
                startActivity(new Intent(WidgetActivity.this, TextActivity.class));
                break;
            case R.id.sign_calendar:
                startActivity(new Intent(WidgetActivity.this, SignCalendarActivity.class));
                break;
            case R.id.tv_addsub:
                startActivity(new Intent(WidgetActivity.this, AddSubActivity.class));
                break;
            case R.id.tv_headView:
                startActivity(new Intent(WidgetActivity.this, CircleImageActivity.class));
                break;
            case R.id.tv_alertDialog:
                startActivity(new Intent(WidgetActivity.this, AlerterActivity.class));
                break;
            case R.id.verticalTextView:
                startActivity(new Intent(WidgetActivity.this, VerticalTextViewActivity.class));
                break;
            case R.id.spanTextView:
                startActivity(new Intent(WidgetActivity.this, SpanTextActivity.class));
                break;
            case R.id.tv_expandable:
                startActivity(new Intent(WidgetActivity.this, ExpandableActivity.class));
                break;
            case R.id.tv_badge:
                startActivity(new Intent(WidgetActivity.this, IconBadgeActivity.class));
                break;
            case R.id.tv_panoraimage:
                startActivity(new Intent(WidgetActivity.this, PanoramImageActivity.class));
                break;
            case R.id.tvWatermediary:
                startActivity(new Intent(WidgetActivity.this, WatermelonDiayActivity.class));
                break;
            case R.id.linkTextView:
                startActivity(new Intent(WidgetActivity.this, LinkTextViewActivity.class));
                break;
            case R.id.tv_count:
                startActivity(new Intent(WidgetActivity.this, CounterActivity.class));
                break;
            case R.id.spanTouchFixTextView:
                startActivity(new Intent(WidgetActivity.this, SpanTouchFixTextViewActivity.class));
                break;
            case R.id.tv_marqueue:
                startActivity(new Intent(WidgetActivity.this, MarqueueActivity.class));
                break;
            case R.id.tvWindlink:
                startActivity(new Intent(WidgetActivity.this, WindlinkRecycleviewActivity.class));
                break;
            case R.id.tvPulseActivity:
                startActivity(new Intent(WidgetActivity.this, PulseViewActivity.class));
                break;
            case R.id.tvMasaccioView:
                startActivity(new Intent(WidgetActivity.this, PhotoViewActivity.class));
                break;
            case R.id.tvBlurry:
                startActivity(new Intent(WidgetActivity.this, BlurryTestActivity.class));
                break;
            case R.id.tvTickerView:
                startActivity(new Intent(WidgetActivity.this, TickerActivity.class));
                break;
            case R.id.tv_psw_input:
                startActivity(new Intent(WidgetActivity.this, PayPsdViewActivity.class));
                break;
            case R.id.tv_progress:
                startActivity(new Intent(WidgetActivity.this, ProgressBarActivity.class));
                break;
            case R.id.tv_materialripple:
                startActivity(new Intent(WidgetActivity.this, MaterialRippleActivity.class));
                break;

        }
    }
}
