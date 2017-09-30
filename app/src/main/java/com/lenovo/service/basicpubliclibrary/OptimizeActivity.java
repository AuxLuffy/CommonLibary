package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.fragmenttabhost.FragTabHostActivity;
import com.example.lenovo.gridview_drag.ChannelActivity;
import com.example.lenovo.retrofithelper2.RetrofitHelperActivity;
import com.lenovo.service.basicpubliclibrary.StrategyPattern.StrategyPatternActivity;
import com.lenovo.service.basicpubliclibrary.bezier.ThreadPoolActivity;
import com.lenovo.service.basicpubliclibrary.builderpattern.BuilderPatternActivity;
import com.lenovo.service.basicpubliclibrary.factorypattern.FactoryActivity;
import com.lenovo.service.basicpubliclibrary.jniDemo.JNITestActivity;
import com.lenovo.service.basicpubliclibrary.jobservice.JobServiceActivity;
import com.lenovo.service.basicpubliclibrary.keyboardpanelswitch.KeyboardPanelSwitchActivity;
import com.lenovo.service.basicpubliclibrary.leakcanary.LeakCanaryActivity;
import com.lenovo.service.basicpubliclibrary.netframe.NetTestActivity;
import com.lenovo.service.basicpubliclibrary.networkokhttp.NetokhttpActivity;
import com.lenovo.service.basicpubliclibrary.safekeyboard.SafeKeyBoardActivity;
import com.lenovo.service.basicpubliclibrary.schedule.ScheduleActivity;

public class OptimizeActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView mTv_okhttpEncapsulation;
    private TextView mTvRetrofitHelper;
    private TextView mTvKeyboard;
    private TextView mTvStrategyPattern;
    private TextView mTvFactoryPattern;
    private TextView mTvBuilderPattern;
    private TextView tv_threadPool;
    private TextView nTvNetFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimize);
        initview();
        initlistener();
    }


    private void initview() {

        mTv_okhttpEncapsulation = (TextView) findViewById(R.id.tv_okhttpEncapsulation);
        mTvRetrofitHelper = (TextView) findViewById(R.id.tvRetrofitHelper);
        mTvKeyboard = (TextView) findViewById(R.id.tv_keyboard);
        mTvStrategyPattern = (TextView) findViewById(R.id.tv_strategypattern);
        mTvFactoryPattern = (TextView) findViewById(R.id.tv_factory_pattern);
        mTvBuilderPattern = (TextView) findViewById(R.id.tv_builder_pattern);
        tv_threadPool = (TextView) findViewById(R.id.tv_threadPool);
        nTvNetFrame = (TextView) findViewById(R.id.tv_netframe);

    }


    private void initlistener() {
        mTv_okhttpEncapsulation.setOnClickListener(this);
        mTvRetrofitHelper.setOnClickListener(this);
        mTvKeyboard.setOnClickListener(this);
        mTvStrategyPattern.setOnClickListener(this);
        mTvFactoryPattern.setOnClickListener(this);
        mTvBuilderPattern.setOnClickListener(this);
        tv_threadPool.setOnClickListener(this);
        nTvNetFrame.setOnClickListener(this);
        findViewById(R.id.tv_jnidemo).setOnClickListener(this);
        findViewById(R.id.tv_customkeyboard).setOnClickListener(this);
        findViewById(R.id.tvFragTabHost).setOnClickListener(this);
        findViewById(R.id.tvGridViewDrag).setOnClickListener(this);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.job_service:
                Intent intent=new Intent(this, JobServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.leak_canary:
                intent=new Intent(this, LeakCanaryActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_okhttpEncapsulation:
                intent=new Intent(this, NetokhttpActivity.class);
                startActivity(intent);
                break;
            case R.id.schedule_service:
                intent=new Intent(this,ScheduleActivity.class);
                startActivity(intent);
                break;
            case R.id.tvRetrofitHelper:
                intent=new Intent(this, RetrofitHelperActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_keyboard:
                intent=new Intent(this, KeyboardPanelSwitchActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_jnidemo:
                intent = new Intent(this, JNITestActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_strategypattern:
                intent = new Intent(this, StrategyPatternActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_factory_pattern:
                intent = new Intent(this, FactoryActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_builder_pattern:
                intent = new Intent(this, BuilderPatternActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_threadPool:
                intent = new Intent(this, ThreadPoolActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_customkeyboard:
                intent = new Intent(this, SafeKeyBoardActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_netframe:
                intent = new Intent(this, NetTestActivity.class);
                startActivity(intent);
                break;
            case R.id.tvFragTabHost:
                intent=new Intent(this, FragTabHostActivity.class);
                startActivity(intent);
                break;
            case R.id.tvGridViewDrag:
                 intent=new Intent(this, ChannelActivity.class);
                 startActivity(intent);
                break;
        }
    }


}
