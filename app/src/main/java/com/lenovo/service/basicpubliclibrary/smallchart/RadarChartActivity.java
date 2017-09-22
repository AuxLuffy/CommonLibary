package com.lenovo.service.basicpubliclibrary.smallchart;

import android.graphics.Color;
import android.os.Bundle;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.smallchart.chart.RadarChart;
import com.lenovo.service.basicpubliclibrary.smallchart.data.RadarData;
import com.lenovo.service.basicpubliclibrary.smallchart.iData.IRadarData;

import java.util.ArrayList;

public class RadarChartActivity extends BaseActivity {
    private RadarData mRadarData = new RadarData();
    private RadarData mRadarData2 = new RadarData();
    private ArrayList<IRadarData> radarDataList = new ArrayList<>();
    private ArrayList<Float> radarValue = new ArrayList<>();
    private ArrayList<Float> radarValue2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart);
        initData();
        RadarChart radarChart = (RadarChart) findViewById(R.id.radarChart);
        radarChart.setDataList(radarDataList);
        radarChart.setTypes(new String[]{"Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H"});
    }

    private void initData() {
        for (int i = 0; i < 8; i++) {
            radarValue.add(points[i][1]);
            radarValue2.add(points2[i][1]);
        }
        mRadarData.setValue(radarValue);
        mRadarData.setColor(Color.MAGENTA);
        mRadarData.setPaintWidth(pxTodp(3));
        mRadarData2.setValue(radarValue2);
        mRadarData2.setColor(Color.CYAN);
        mRadarData2.setPaintWidth(pxTodp(3));
        radarDataList.add(mRadarData);
        radarDataList.add(mRadarData2);
    }
}
