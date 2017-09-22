package com.lenovo.service.basicpubliclibrary.smallchart;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.smallchart.chart.LineChart;
import com.lenovo.service.basicpubliclibrary.smallchart.data.LineData;
import com.lenovo.service.basicpubliclibrary.smallchart.iData.ILineData;

import java.util.ArrayList;

public class LineChartActivity extends BaseActivity {
    private LineData mLineData = new LineData();
    private ArrayList<ILineData> mDataList = new ArrayList<>();
    private ArrayList<PointF> mPointArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        initData();
        LineChart lineChart = (LineChart) findViewById(R.id.lineChart);
//        lineChart.setData(mLineData);
        lineChart.setDataList(mDataList);
    }

    private void initData() {
        for (int i = 0; i < 8; i++) {
            mPointArrayList.add(new PointF(points[i][0], points[i][1]));
        }
        mLineData.setValue(mPointArrayList);
        mLineData.setColor(Color.MAGENTA);
        mLineData.setPaintWidth(pxTodp(3));
        mLineData.setTextSize(pxTodp(10));
        mDataList.add(mLineData);
    }
}
