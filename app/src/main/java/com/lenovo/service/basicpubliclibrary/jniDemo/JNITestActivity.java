package com.lenovo.service.basicpubliclibrary.jniDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;

/**
 * Created by cx on 2017/9/14.
 * jni初步探究，此类将持续更新。。。
 */

public class JNITestActivity extends Activity {

    TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_jnidemo);
        initView();
        setListener();
    }

    private void setListener() {
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText(myJNI.sayHello());
            }
        });
    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);
    }

}
