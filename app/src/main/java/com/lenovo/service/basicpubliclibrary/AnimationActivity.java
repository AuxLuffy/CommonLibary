package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.ProgressBaranimation.ProgressActivity;
import com.lenovo.service.basicpubliclibrary.colorfultoast.ColorfulToastActivity;
import com.lenovo.service.basicpubliclibrary.flowwateranimation.FlowWaterAnimationActivity;
import com.lenovo.service.basicpubliclibrary.guideanim.ShowActivity;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.HeaderFloatActivity;
import com.lenovo.service.basicpubliclibrary.loadingart.Loadingart_activity;
import com.lenovo.service.basicpubliclibrary.loopview.LoopViewActivity;
import com.lenovo.service.basicpubliclibrary.meizuweather.MeizuWeacherActivity;
import com.lenovo.service.basicpubliclibrary.svg.WowActivity;
import com.lenovo.service.basicpubliclibrary.takeaway.TakeawayActivity;

public class AnimationActivity extends AppCompatActivity {
    TextView text1 = null;
    private TextView mTv_progressBar;
    private TextView mMEIZUWeather;
    private TextView mTvLoopView;
    private TextView mWateranimation;
    private TextView mTvGuideAnim;
    private TextView mTvGuideAnimSvg;
    private TextView mTv_loading_art;
    private TextView mTvTakeaway;
    private TextView mTvColorfulToast;

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
        mTv_loading_art = (TextView) findViewById(R.id.tv_loading_art);
        mTvTakeaway = (TextView) findViewById(R.id.tvTakeaway);
        mTvColorfulToast = (TextView) findViewById(R.id.tvColorfulToast);

        text1.setOnClickListener(new TextViewClickListener());
        mTvLoopView.setOnClickListener(new TextViewClickListener());
        mTv_progressBar.setOnClickListener(new TextViewClickListener());
        mMEIZUWeather.setOnClickListener(new TextViewClickListener());
        mWateranimation.setOnClickListener(new TextViewClickListener());
        mTvGuideAnim.setOnClickListener(new TextViewClickListener());
        mTvGuideAnimSvg.setOnClickListener(new TextViewClickListener());
        mTv_loading_art.setOnClickListener(new TextViewClickListener());
        mTvTakeaway.setOnClickListener(new TextViewClickListener());
        mTvColorfulToast.setOnClickListener(new TextViewClickListener());
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

                case R.id.tv_loading_art:
                    startActivity(new Intent(AnimationActivity.this, Loadingart_activity.class));
                    break;

                case R.id.tvTakeaway:
                    startActivity(new Intent(AnimationActivity.this, TakeawayActivity.class));
                    break;

                case R.id.tvColorfulToast:
                    startActivity(new Intent(AnimationActivity.this, ColorfulToastActivity.class));
                    break;
                default:
                    break;
            }

        }
    }
}
