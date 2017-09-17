package com.lenovo.service.basicpubliclibrary.alerter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lenovo.service.basicpubliclibrary.R;
import com.tapadoo.alerter.Alerter;

public class AlerterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnDefaultAlerter;
    private Button mBtnCusBgAlerter;
    private Button mBtnCusDuration;
    private int[] drawables = new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3};
    private int[] durations = new int[]{5, 10, 15};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerter);
        mBtnDefaultAlerter = (Button) findViewById(R.id.btnAlertDefault);
        mBtnCusBgAlerter = (Button) findViewById(R.id.btnAlertCustomBg);
        mBtnCusDuration = (Button) findViewById(R.id.btnAlertCusduration);
        mBtnDefaultAlerter.setOnClickListener(this);
        mBtnCusBgAlerter.setOnClickListener(this);
        mBtnCusDuration.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAlertDefault:
                showDefaultAlerter();
                break;
            case R.id.btnAlertCustomBg:
                showBgtAlerter();
                break;
            case R.id.btnAlertCusduration:
                showDuration();
                break;

        }
    }

    /**
     * 展示默认提示框
     */
    public void showDefaultAlerter() {
        Alerter.create(this)
                .setTitle("Alert Title")
                .setText("Alert text...")
                .show();
    }

    /**
     * 展示背景提示框
     */
    public void showBgtAlerter() {
        int select = (int)((Math.abs((long)Math.random()))%3);
        Alerter.create(this)
                .setTitle("Alert Title")
                .setText("Alert text...")
                .setBackgroundResource(drawables[select])
                .show();
    }
    /**
     * 设置提示时长
     */
    public void showDuration() {
        int select = (int)((Math.abs((long)Math.random()))%3);
        Alerter.create(this)
                .setTitle("Alert Title")
                .setText("Alert text...")
                .setBackgroundResource(drawables[select])
                .setDuration(durations[select])
                .show();
    }
}
