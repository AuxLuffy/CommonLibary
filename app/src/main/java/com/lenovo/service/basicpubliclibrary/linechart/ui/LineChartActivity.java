package com.lenovo.service.basicpubliclibrary.linechart.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.linechart.contract.ChartContract;
import com.lenovo.service.basicpubliclibrary.linechart.model.UploadRecord;
import com.lenovo.service.basicpubliclibrary.linechart.presenter.ChartPrestener;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by cx on 2017/8/30.
 * MVP+helloCharts
 * 功能点：1.mvp开发模式  2.hellocharts图表使用，简单封装
 */

public class LineChartActivity extends AppCompatActivity implements ChartContract.View{//

    @BindView(R.id.chart)
    LineChartView chart;

    private ChartContract.Presenter controler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        controler = new ChartPrestener(this);

        controler.start();

    }

    @Override
    public void setPresenter(ChartContract.Presenter presenter) {

        controler = presenter;//此方法适用activity包含多个fragment时赋值，故在本类中没有实际作用。

    }

    @Override
    public void showData(UploadRecord uploadRecord) {

        controler.initChart(chart, uploadRecord.getSevendays());

    }

    @Override
    public void showError(String msg) {

    }
}
