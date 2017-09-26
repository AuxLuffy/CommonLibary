package com.lenovo.service.basicpubliclibrary.StrategyPattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;


/**
 * 调用策略模式的activity
 * Created by chongyangyang on 2017/9/14.
 */

public class StrategyPatternActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_style);
        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setText("调用策略模式,在日志中查看log");
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.setCache(new DiskCache());
    }
}
