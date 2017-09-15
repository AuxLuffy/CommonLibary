package com.lenovo.service.basicpubliclibrary.schedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;

import java.util.Calendar;

/**
 * 定时执行任务，前后台都可以，建议书写时，在service中调用
 */

public class ScheduleActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        tv=(TextView)findViewById(R.id.schedule_text) ;
        TimerScheduleClient mTimerScheduleClient=TimerScheduleClient.getInstance(ScheduleActivity.this, new TimerScheduleClient.TimerScheduleCallback() {
            @Override
            public void doSchedule() {
                Calendar calendar=Calendar.getInstance();  //获取当前时间，作为图标的名字
                String year=calendar.get(Calendar.YEAR)+"";
                String month=calendar.get(Calendar.MONTH)+1+"";
                String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
                String hour=calendar.get(Calendar.HOUR_OF_DAY)+"";
                String minute=calendar.get(Calendar.MINUTE)+"";
                String second=calendar.get(Calendar.SECOND)+"";
                String time="年："+year+"月："+month+"日："+day+"时："+hour+"分："+minute+"秒："+second;
                tv.setText(time);
            }
        });
        //mTimerScheduleClient.start(0,5*60*1000,5*60*100,5*60*1000/2);
        mTimerScheduleClient.start(1,1,1,1);
    }
}
