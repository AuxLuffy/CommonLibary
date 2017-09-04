package com.lenovo.service.basicpubliclibrary.scratchview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.R;

/**
 * Created by xuxiaowei on 2017/9/1.
 */

public class ScratchViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch);


    }

    public void onTextViewDemoClick(View v) {
        startActivity(new Intent(this, DemoClothingActivity.class));
    }

    public void onImageViewDemoClick(View v) {
        startActivity(new Intent(this, CaptchaActivity.class));
    }
}
