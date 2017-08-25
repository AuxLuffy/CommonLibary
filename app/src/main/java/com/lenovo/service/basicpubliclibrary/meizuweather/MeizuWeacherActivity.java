package com.lenovo.service.basicpubliclibrary.meizuweather;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.R;

import java.util.Calendar;

@SuppressLint("NewApi")
public class MeizuWeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meizu_weacher_activity);
        WeatherView weatherView = (WeatherView) findViewById(R.id.view_weather);
        Calendar c = Calendar.getInstance();
        int minute = c.get(Calendar.HOUR_OF_DAY) * 60 + c.get(Calendar.MINUTE);
        weatherView.setCurrentTime(minute);
    }
}
