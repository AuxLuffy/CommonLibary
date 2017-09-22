package com.lenovo.service.basicpubliclibrary.jniDemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;

/**
 * Created by cx on 2017/9/14.
 * jni初步探究，此类将持续更新。。。
 * 9.21添加c调用java的功能,完成java与c的互调过程
 */

public class JNITestActivity extends Activity {

    static TextView text,text2;

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
                myJNI.set("xingxing");
            }
        });
    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);
        text2 = (TextView) findViewById(R.id.text2);
    }

    public static void calledbyjni(String mgs){
        text2.setText(mgs);
        Log.e("jni","jni called this method and say :"+mgs);
    }

}
