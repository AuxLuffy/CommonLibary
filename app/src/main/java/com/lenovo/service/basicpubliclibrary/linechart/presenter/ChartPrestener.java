package com.lenovo.service.basicpubliclibrary.linechart.presenter;

import com.lenovo.service.basicpubliclibrary.linechart.common.OnRequestCallback;
import com.lenovo.service.basicpubliclibrary.linechart.contract.ChartContract;
import com.lenovo.service.basicpubliclibrary.linechart.model.ChartHelper;
import com.lenovo.service.basicpubliclibrary.linechart.model.ChartModelImpl;
import com.lenovo.service.basicpubliclibrary.linechart.model.IChartModel;
import com.lenovo.service.basicpubliclibrary.linechart.model.UploadRecord;

import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by cx on 2017/8/30.
 */

public class ChartPrestener implements ChartContract.Presenter,OnRequestCallback<UploadRecord> {

    private ChartContract.View myView;

    private IChartModel iChartModel;

    private ChartHelper chartHelper;

    public ChartPrestener(ChartContract.View myView){

        this.myView = myView;

        this.iChartModel = new ChartModelImpl();

    }

    @Override
    public void start() {

        loadData();

    }

    @Override
    public void loadData() {

        iChartModel.requestData(this);

    }

    @Override
    public void initChart(LineChartView lineChartView, UploadRecord.SevendaysBean chartList) {

        chartHelper = new ChartHelper(lineChartView,chartList);

    }

    @Override
    public void onSuccess(UploadRecord uploadRecord) {

        myView.showData(uploadRecord);

    }

    @Override
    public void onFailure(String msg) {

        myView.showError(msg);

    }
}
