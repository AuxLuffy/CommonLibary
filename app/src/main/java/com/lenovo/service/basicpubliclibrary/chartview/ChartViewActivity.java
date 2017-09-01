package com.lenovo.service.basicpubliclibrary.chartview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.db.chart.model.Bar;
import com.db.chart.model.BarSet;
import com.db.chart.model.LineSet;
import com.db.chart.model.Point;
import com.db.chart.view.BarChartView;
import com.db.chart.view.HorizontalBarChartView;
import com.db.chart.view.LineChartView;
import com.lenovo.service.basicpubliclibrary.R;

import static com.lenovo.service.basicpubliclibrary.R.id.barchart;

/**
 * 版权所有 ,lenovo保留所有版权。<br>
 * 项目描述：图表
 * 作者：焦禹铭
 * 日期：2017/8/30
 **/
public class ChartViewActivity extends Activity {
    //各种形状的图标
    LineChartView mLineChart;
    BarChartView mBarChartView;
    HorizontalBarChartView mHorizontalBarChartView;
    private final static String[] mLabels = {"ANT", "GNU", "OWL", "APE", "COD","YAK", "RAM", "JAY"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);
        //线条型图标
        mLineChart =  findViewById(R.id.linechart);
        mLineChart.reset();
        //添加每一个点
        LineSet  data = new LineSet();
        data.addPoint(new Point(mLabels[0],0.6f));
        data.addPoint(new Point(mLabels[2],0.1f));
        data.addPoint(new Point(mLabels[3],0.9f));
        data.addPoint(new Point(mLabels[3],0.2f));
        data.addPoint(new Point(mLabels[4],0.5f));
        //填充颜色
//        data.setFill(Color.parseColor("#3388c6c3"));
        //线条颜色
        data.setColor(Color.parseColor("#ff0000"));
        //设置点与点之间是柔性连接
        data.setSmooth(true);
        int[] colors = {Color.parseColor("#3388c6c3"), Color.TRANSPARENT};
        //填充区域 渐变 从一个色值到另一个色值
        data.setGradientFill(colors, null);
        mLineChart.addData(data);
        //第二根线
        LineSet  data2 = new LineSet();
        data2.addPoint(new Point(mLabels[0],0.7f));
        data2.addPoint(new Point(mLabels[2],0.9f));
        data2.addPoint(new Point(mLabels[3],0.4f));
        data2.addPoint(new Point(mLabels[3],0.9f));
        data2.addPoint(new Point(mLabels[4],0.1f));
        //data.setFill(Color.parseColor("#3388c6c3"));
        int[] colors2 = {Color.parseColor("#338866c3"), Color.TRANSPARENT};
        //填充区域 渐变 从一个色值到另一个色值
        data.setGradientFill(colors2, null);
        mLineChart.addData(data2);
        mLineChart.show();

        //柱状图标
        mBarChartView=findViewById(R.id.barchart);
        mBarChartView.reset();
        BarSet  barSet=new BarSet();
        barSet.addBar(new Bar(mLabels[0],0.6f));
        barSet.addBar(new Bar(mLabels[1],0.1f));
        barSet.addBar(new Bar(mLabels[2],0.9f));
        barSet.addBar(new Bar(mLabels[3],0.6f));
        barSet.addBar(new Bar(mLabels[4],0.6f));
        mBarChartView.addData(barSet);
        mBarChartView.show();
        //横向柱状图标
        mHorizontalBarChartView=findViewById(R.id.horizontalbarchart);
        mHorizontalBarChartView.reset();
        BarSet  hbarSet=new BarSet();
        hbarSet.addBar(new Bar(mLabels[0],0.6f));
        hbarSet.addBar(new Bar(mLabels[1],0.1f));
        hbarSet.addBar(new Bar(mLabels[2],0.9f));
        hbarSet.addBar(new Bar(mLabels[3],0.6f));
        hbarSet.addBar(new Bar(mLabels[4],0.6f));
        mHorizontalBarChartView.addData(hbarSet);
        mHorizontalBarChartView.show();
    }
}

