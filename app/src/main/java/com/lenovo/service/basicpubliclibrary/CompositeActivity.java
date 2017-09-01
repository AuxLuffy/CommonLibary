package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.lenovo.KotlinActivity;
import com.lenovo.service.basicpubliclibrary.databinding.DataBindingActivity;
import com.lenovo.service.basicpubliclibrary.cameraDemo.RecordMainAcitivity;
import com.lenovo.service.basicpubliclibrary.rxjava.RxJavaActivity;
import com.lenovo.service.basicpubliclibrary.videoplayer.VideoplayerActivity;

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
        startActivity(new Intent(this, RxJavaActivity.class));
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
}
