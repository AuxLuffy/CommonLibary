package com.lenovo.service.basicpubliclibrary.bottleLoadingView;

import android.app.Activity;
import android.os.Bundle;

import com.lenovo.service.basicpubliclibrary.R;

/**
 * 贝塞尔风景
 * Created by tangrenmei on 2017/9/9.
 */

public class GABottleLoadingViewAcivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottleloading);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((GABottleLoadingView) findViewById(R.id.bottle_view_small)).performAnimation();
        ((GABottleLoadingView) findViewById(R.id.bottle_view_middle)).performAnimation();
        ((GABottleLoadingView) findViewById(R.id.bottle_view_big)).performAnimation();
    }
}
