package com.lenovo.service.basicpubliclibrary.svg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.R;


public class WowActivity extends AppCompatActivity {
    private WowSplashView mWowSplashView;
    private WowView mWowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wow);

        mWowSplashView = (WowSplashView) findViewById(R.id.wowSplash);
        mWowView = (WowView) findViewById(R.id.wowView);


        mWowSplashView.startAnimate();


        mWowSplashView.setOnEndListener(new WowSplashView.OnEndListener() {
            @Override
            public void onEnd(WowSplashView wowSplashView) {
                mWowSplashView.setVisibility(View.GONE);
                mWowView.setVisibility(View.VISIBLE);
                mWowView.startAnimate(wowSplashView.getDrawingCache());

            }
        });
    }


}
