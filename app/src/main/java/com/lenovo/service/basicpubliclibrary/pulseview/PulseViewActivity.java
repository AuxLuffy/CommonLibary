package com.lenovo.service.basicpubliclibrary.pulseview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.gigamole.library.PulseView;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.gesturelock.util.ToastUtil;

public class PulseViewActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStart;
    private Button btnEnd;
    private PulseView pulseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulse_view);
        initView();
    }

    private void initView() {
        btnStart = (Button) findViewById(R.id.btnStart);
        btnEnd = (Button) findViewById(R.id.btnFinish);
        btnStart.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
        pulseView = (PulseView) findViewById(R.id.mPvView);
        pulseView.setPulseListener(new PulseView.PulseListener() {
            @Override
            public void onStartPulse() {
                ToastUtil.showMessage(PulseViewActivity.this, "开始搜索");
            }

            @Override
            public void onFinishPulse() {
                ToastUtil.showMessage(PulseViewActivity.this, "结束搜索");
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                pulseView.startPulse();
                break;
            case R.id.btnFinish:
                pulseView.finishPulse();
                break;
        }
    }
}
