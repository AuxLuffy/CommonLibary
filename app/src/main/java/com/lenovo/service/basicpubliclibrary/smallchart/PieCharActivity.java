package com.lenovo.service.basicpubliclibrary.smallchart;

import android.graphics.Color;
import android.os.Bundle;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.smallchart.chart.PieChart;
import com.lenovo.service.basicpubliclibrary.smallchart.data.PieData;
import com.lenovo.service.basicpubliclibrary.smallchart.iData.IPieData;

import java.util.ArrayList;

public class PieCharActivity extends BaseActivity {

    private ArrayList<IPieData> mPieDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_char);

        initData();
        PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
        pieChart.setDataList(mPieDataList);
        pieChart.setAxisColor(Color.WHITE);
        pieChart.setAxisTextSize(pxTodp(20));
    }

    private void initData(){
        for (int i=0; i<9; i++){
            PieData pieData = new PieData();
            pieData.setName("区域"+i);
            pieData.setValue((float)i+1);
            pieData.setColor(mColors[i]);
            mPieDataList.add(pieData);
        }
    }
}
