package com.lenovo.service.basicpubliclibrary.threed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.R;

public class ThreedMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threed_main);
    }


    public void startWeatherActivity(View view) {
        startActivity(new Intent(this, WeatherActivity.class));
    }

    public void startUserCenterActivity(View view) {
        startActivity(new Intent(this, UserCenterActivity.class));
    }
}
