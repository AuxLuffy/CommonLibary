package com.lenovo.service.basicpubliclibrary.timepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.hp.hpl.sparta.Text;
import com.lenovo.service.basicpubliclibrary.R;

/**
 * Created by cx on 2017/9/12.
 * 以前项目用到的组件，日期选择器，非本人原创，原创作者：李玉江
 */

public class TimePickerActivity extends AppCompatActivity {

    private TextView time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker);
        time = (TextView)findViewById(R.id.timepicker);
        showTimePicker();
    }

   public void showTimePicker(){
       NResponse<RequireAppoint> response = new NResponse<RequireAppoint>();
       RequireAppoint ra = new RequireAppoint();
       ra.isRechckDateNess = false;
       ra.isNextStepDateNesss = true;
       ra.nextStepName = "next";
       CheckOrderPicker picker = new CheckOrderPicker(this,response.data);
       picker.setOnDateSelListner(new CheckOrderPicker.OnYMDhPickListener() {
           @Override
           public void onDatePicked(String year, String month, String day, String hour) {
               String fjtime = year + "-" + month + "-" + day + " 00:00:00";
               String nexttime = year + "-" + month + "-" + day + " 00:00:00";
               String compareTime = year + "-" + month + "-" + day + " 24:00:00";
               //此处做出你需要的判断
               //判断预约时间是否小于当前时间
//               if (timeCanUse(compareTime)) {
//                   if (!response.data.isNextStepDateNesss) {
//                       nexttime = "";
//                   }
//                   if (!response.data.isRechckDateNess) {
//                       fjtime = "";
//                   }
//                   confirmOrder(fjtime, nexttime);
//               }else{
//                   Toast.makeText(TimePickerActivity.this,"时间选择错误！请重新选择",Toast.LENGTH_SHORT).show();
//               }
               time.setText(fjtime);
           }
       });
       picker.show();
   }
}
