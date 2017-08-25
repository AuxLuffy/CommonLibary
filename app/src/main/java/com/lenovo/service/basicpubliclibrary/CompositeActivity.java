package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lenovo.KotlinActivity;
import com.lenovo.service.basicpubliclibrary.AppStartGuide.AppStartGuide;
import com.lenovo.service.basicpubliclibrary.rxjava.RxJavaActivity;

public class CompositeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composite);
    }

    public void startKotlin(View view) {
        startActivity(new Intent(this, KotlinActivity.class));
    }
    public void startRx(View view) {
        startActivity(new Intent(this,RxJavaActivity.class));
    }
}
