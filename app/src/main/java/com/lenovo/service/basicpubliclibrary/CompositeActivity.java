package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.SADL.SADLActivity;
import com.lenovo.service.basicpubliclibrary.cameraDemo.RecordMainAcitivity;
import com.lenovo.service.basicpubliclibrary.camerakit.CamerakitActivity;
import com.lenovo.service.basicpubliclibrary.databinding.DataBindingActivity;
import com.lenovo.service.basicpubliclibrary.jniDemo.JNITestActivity;
import com.lenovo.service.basicpubliclibrary.jsBridge.activity.JSBridgeActivity;
import com.lenovo.service.basicpubliclibrary.jsBridge.common.LocalConstant;
import com.lenovo.service.basicpubliclibrary.linechart.ui.LineChartActivity;
import com.lenovo.service.basicpubliclibrary.rxjava.RxJavaActivity;
import com.lenovo.service.basicpubliclibrary.validation.ValidateActivity;
import com.lenovo.service.basicpubliclibrary.videoplayer.VideoplayerActivity;
import com.lenovo.service.basicpubliclibrary.zxing.activity.ZxingActivity;

//import com.lenovo.KotlinActivity;

public class CompositeActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView[] textviews;

    private int[] ids = new int[]{R.id.helloChart,R.id.jsBridge,R.id.SADL,R.id.ilog_demo,
            R.id.tv_jnidemo,R.id.scan_code
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composite);
        initView();
    }

    private void initView() {
        textviews = new TextView[ids.length];
        for(int i = 0;i < textviews.length; i ++){
            textviews[i] = (TextView) findViewById(ids[i]);
            textviews[i].setOnClickListener(this);
        }
    }

    public void startKotlin(View view) {
//        startActivity(new Intent(this, KotlinActivity.class));
    }

    public void startRx(View view) {
        startActivity(new Intent(this, RxJavaActivity.class));
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()){

            case R.id.helloChart:

                intent.setClass(this, LineChartActivity.class);

                break;

            case R.id.jsBridge:

                intent.setClass(this,JSBridgeActivity.class);
                intent.putExtra("extra1", LocalConstant.H5URL.page_register);

                break;
            case R.id.SADL:
                intent.setClass(this, SADLActivity.class);
                break;
            case R.id.ilog_demo:
                intent.setClass(this,ALogActivity.class);
                break;

            case  R.id.tv_jnidemo:

                intent.setClass(this,JNITestActivity.class);

                break;

            case  R.id.scan_code:
                intent.setClass(this,ZxingActivity.class);
                break;
        }
        startActivity(intent);
    }

    public void startDataBinding(View view) {
        startActivity(new Intent(this, DataBindingActivity.class));
    }
    public void recorder_video(View v) {
        startActivity(new Intent(this,RecordMainAcitivity.class));
    }

    public void startVideoPlayer(View v) {
        startActivity(new Intent(this, VideoplayerActivity.class));
    }
    public void startValidateActivity(View v) {
        startActivity(new Intent(this, ValidateActivity.class));
    }

    public  void  startCamerkitActivity(View view){
        startActivity(new Intent(this, CamerakitActivity.class));
    }
}
