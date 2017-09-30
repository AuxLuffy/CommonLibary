package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.ProgressBaranimation.ProgressActivity;
import com.lenovo.service.basicpubliclibrary.bezier.BezierActivity;
import com.lenovo.service.basicpubliclibrary.bezier.DotsLoaderActivity;
import com.lenovo.service.basicpubliclibrary.bottombar.BottomBarActivity;
import com.lenovo.service.basicpubliclibrary.bubbleview.BubbleViewActivity;
import com.lenovo.service.basicpubliclibrary.colorfultoast.ColorfulToastActivity;
import com.lenovo.service.basicpubliclibrary.danmu.DanmuActivity;
import com.lenovo.service.basicpubliclibrary.expandablelvanimated.ExpandableListViewActivity;
import com.lenovo.service.basicpubliclibrary.floatingbutton.FLoatingBtnActivity;
import com.lenovo.service.basicpubliclibrary.floatingmenu.FloatingActionActivity;
import com.lenovo.service.basicpubliclibrary.flowwateranimation.FlowWaterAnimationActivity;
import com.lenovo.service.basicpubliclibrary.guideanim.ShowActivity;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.HeaderFloatActivity;
import com.lenovo.service.basicpubliclibrary.loadingart.Loadingart_activity;
import com.lenovo.service.basicpubliclibrary.loopview.LoopViewActivity;
import com.lenovo.service.basicpubliclibrary.magicsurfaceview.launch.LaunchActivity;
import com.lenovo.service.basicpubliclibrary.meizuweather.MeizuWeacherActivity;
import com.lenovo.service.basicpubliclibrary.roll3dimageview.RollImageActivity;
import com.lenovo.service.basicpubliclibrary.searchbox.SearchBoxActivity;
import com.lenovo.service.basicpubliclibrary.shinebutton.ShineButtonActivity;
import com.lenovo.service.basicpubliclibrary.stikyscrollview.StikyScrollviewActivity;
import com.lenovo.service.basicpubliclibrary.svg.WowActivity;
import com.lenovo.service.basicpubliclibrary.takeaway.TakeawayActivity;
import com.lenovo.service.basicpubliclibrary.threed.ThreedMainActivity;
import com.lenovo.service.basicpubliclibrary.viewexplosion.ViewExplosiionActivity;
import com.lenovo.service.basicpubliclibrary.wowoviewpagerexample.WowoMainActivity;

public class AnimationActivity extends AppCompatActivity {
    TextView text1 = null;
    private TextView mTv_progressBar;
    private TextView mMEIZUWeather;
    private TextView mTvLoopView;
    private TextView mWateranimation;
    private TextView mTvGuideAnim;
    private TextView mTvGuideAnimSvg;
    private TextView mTvRollImage;
    private TextView tv_bezier;
    private TextView mTv_loading_art;
    private TextView mTvTakeaway;
    private TextView mTvColorfulToast;
    private TextView tv_dots_loader;
    private TextView mTvExamplelv;
    private TextView tv_floatingmenu;
    private TextView mTvWowoviewpager;
    private TextView mTvStikyscrollview;
    private TextView mTvshinebutton;
    private TextView mTvMagicsurface;
    private TextView mTvSearchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        text1 = (TextView) findViewById(R.id.text1);
        mTv_progressBar = (TextView) findViewById(R.id.tv_progressBar);
        mMEIZUWeather = (TextView) findViewById(R.id.meizu_weather);
        mTvLoopView = (TextView) findViewById(R.id.tv_loopview);
        mWateranimation = (TextView) findViewById(R.id.water_animation);
        mTvGuideAnim = (TextView) findViewById(R.id.guide_anim);
        mTvGuideAnimSvg = (TextView) findViewById(R.id.guide_anim_svg);
        mTvRollImage = (TextView) findViewById(R.id.roll_image);
        tv_bezier = (TextView) findViewById(R.id.tv_bezier);
        tv_bezier.setOnClickListener(new TextViewClickListener());
        mTv_loading_art = (TextView) findViewById(R.id.tv_loading_art);
        mTvTakeaway = (TextView) findViewById(R.id.tvTakeaway);
        mTvColorfulToast = (TextView) findViewById(R.id.tvColorfulToast);
        tv_dots_loader = (TextView) findViewById(R.id.tv_dots_loader);
        tv_floatingmenu = (TextView)findViewById(R.id.tv_floatingmenu);
        mTvStikyscrollview = (TextView) findViewById(R.id.tvStikyscrollview);
        mTvSearchBox = (TextView) findViewById(R.id.tv_search_box);
        findViewById(R.id.tv_floatbtn).setOnClickListener(new TextViewClickListener());
        findViewById(R.id.tv_bottom_bar).setOnClickListener(new TextViewClickListener());
        findViewById(R.id.tv_danmu).setOnClickListener(new TextViewClickListener());
        findViewById(R.id.tv_threed).setOnClickListener(new TextViewClickListener());
        findViewById(R.id.bubbleview).setOnClickListener(new TextViewClickListener());
        findViewById(R.id.tv_viewexplosion).setOnClickListener(new TextViewClickListener());

        mTvExamplelv = (TextView)findViewById(R.id.tv_examplelv);
        mTvWowoviewpager = (TextView)findViewById(R.id.wowo_viewpager);
        mTvshinebutton = (TextView) findViewById(R.id.tv_shinebutton);
        mTvMagicsurface = (TextView) findViewById(R.id.tv_magicsurface);

        text1.setOnClickListener(new TextViewClickListener());
        mTvLoopView.setOnClickListener(new TextViewClickListener());
        mTv_progressBar.setOnClickListener(new TextViewClickListener());
        mMEIZUWeather.setOnClickListener(new TextViewClickListener());
        mWateranimation.setOnClickListener(new TextViewClickListener());
        mTvGuideAnim.setOnClickListener(new TextViewClickListener());
        mTvGuideAnimSvg.setOnClickListener(new TextViewClickListener());
        mTvRollImage.setOnClickListener(new TextViewClickListener());
        mTv_loading_art.setOnClickListener(new TextViewClickListener());
        mTvTakeaway.setOnClickListener(new TextViewClickListener());
        mTvColorfulToast.setOnClickListener(new TextViewClickListener());
        tv_dots_loader.setOnClickListener(new TextViewClickListener());
        mTvExamplelv.setOnClickListener(new TextViewClickListener());
        tv_floatingmenu.setOnClickListener(new TextViewClickListener());
        mTvWowoviewpager.setOnClickListener(new TextViewClickListener());
        mTvStikyscrollview.setOnClickListener(new TextViewClickListener());
        mTvshinebutton.setOnClickListener(new TextViewClickListener());
        mTvMagicsurface.setOnClickListener(new TextViewClickListener());
        mTvSearchBox.setOnClickListener(new TextViewClickListener());
    }

    public class TextViewClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()) {

                case R.id.text1:

                    intent.setClass(AnimationActivity.this, HeaderFloatActivity.class);
                    startActivity(intent);
                    break;

                // 进度条的艺术
                case R.id.tv_progressBar:
                    intent.setClass(AnimationActivity.this, ProgressActivity.class);
                    startActivity(intent);
                    break;

                case R.id.meizu_weather:
                    intent.setClass(AnimationActivity.this, MeizuWeacherActivity.class);
                    startActivity(intent);
                    break;

                case R.id.tv_loopview:
                    intent.setClass(AnimationActivity.this, LoopViewActivity.class);
                    startActivity(intent);
                    break;

                case R.id.water_animation:
                    intent.setClass(AnimationActivity.this, FlowWaterAnimationActivity.class);
                    startActivity(intent);
                    break;

                case R.id.guide_anim:
                    startActivity(new Intent(AnimationActivity.this, ShowActivity.class));
                    break;

                case R.id.guide_anim_svg:
                    startActivity(new Intent(AnimationActivity.this, WowActivity.class));
                    break;
                case R.id.roll_image:
                    startActivity(new Intent(AnimationActivity.this, RollImageActivity.class));
                    break;
                case R.id.tv_bezier:
                    startActivity(new Intent(AnimationActivity.this, BezierActivity.class));
                    break;

                case R.id.tv_loading_art:
                    startActivity(new Intent(AnimationActivity.this, Loadingart_activity.class));
                    break;

                case R.id.tvTakeaway:
                    startActivity(new Intent(AnimationActivity.this, TakeawayActivity.class));
                    break;

                case R.id.tvColorfulToast:
                    startActivity(new Intent(AnimationActivity.this, ColorfulToastActivity.class));
                    break;
                case R.id.tv_dots_loader:
                    startActivity(new Intent(AnimationActivity.this, DotsLoaderActivity.class));
                    break;
                case R.id.tv_examplelv:
                    startActivity(new Intent(AnimationActivity.this, ExpandableListViewActivity.class));
                    break;

                case R.id.tv_floatbtn:
                    startActivity(new Intent(AnimationActivity.this, FLoatingBtnActivity.class));
                    break;
                case R.id.tv_floatingmenu:
                    startActivity(new Intent(AnimationActivity.this, FloatingActionActivity.class));
                    break;
                case R.id.tv_bottom_bar:
                    startActivity(new Intent(AnimationActivity.this, BottomBarActivity.class));
                    break;
                case R.id.wowo_viewpager:
                    startActivity(new Intent(AnimationActivity.this, WowoMainActivity.class));
                    break;
                case R.id.tv_danmu:
                    startActivity(new Intent(AnimationActivity.this, DanmuActivity.class));
                    break;
                case R.id.tv_threed:
                    startActivity(new Intent(AnimationActivity.this, ThreedMainActivity.class));
                    break;
                case R.id.tvStikyscrollview:
                    startActivity(new Intent(AnimationActivity.this, StikyScrollviewActivity.class));
                    break;
                case R.id.bubbleview:
                    startActivity(new Intent(AnimationActivity.this, BubbleViewActivity.class));
                    break;
                case R.id.tv_shinebutton:
                    startActivity(new Intent(AnimationActivity.this, ShineButtonActivity.class));
                    break;
                case R.id.tv_magicsurface:
                    startActivity(new Intent(AnimationActivity.this, LaunchActivity.class));
                    break;
                case R.id.tv_search_box:
                    startActivity(new Intent(AnimationActivity.this, SearchBoxActivity.class));
                    break;
                case R.id.tv_viewexplosion:
                    startActivity(new Intent(AnimationActivity.this, ViewExplosiionActivity.class));
                    break;
                default:
                    break;
            }

        }
    }
}
