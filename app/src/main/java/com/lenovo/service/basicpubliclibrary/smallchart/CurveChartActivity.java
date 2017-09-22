package com.lenovo.service.basicpubliclibrary.smallchart;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.smallchart.chart.CurveChart;
import com.lenovo.service.basicpubliclibrary.smallchart.data.CurveData;
import com.lenovo.service.basicpubliclibrary.smallchart.data.PointShape;
import com.lenovo.service.basicpubliclibrary.smallchart.iData.ICurveData;

import java.util.ArrayList;

public class CurveChartActivity extends BaseActivity {
    private ArrayList<ICurveData> mDataList = new ArrayList<>();
    private CurveData mCurveData = new CurveData();
    private ArrayList<PointF> mPointArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curve_chart);
        CurveChart curveChart = (CurveChart) findViewById(R.id.curveChart);
        initData();
//        curveChart.setData(mCurveData);
        curveChart.setDataList(mDataList);
    }
    private void initData() {
        for (int i = 0; i < 8; i++) {
            mPointArrayList.add(new PointF(points[i][0], points[i][1]));
        }
        mCurveData.setValue(mPointArrayList);
        mCurveData.setColor(Color.RED);

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
        mCurveData.setDrawable(drawable);

        mCurveData.setPointShape(PointShape.SOLIDROUND);
        mCurveData.setPaintWidth(pxTodp(3));
        mCurveData.setTextSize(pxTodp(10));
        mDataList.add(mCurveData);
    }
}
