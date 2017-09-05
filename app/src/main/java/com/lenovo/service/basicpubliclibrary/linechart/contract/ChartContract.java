package com.lenovo.service.basicpubliclibrary.linechart.contract;

import com.lenovo.service.basicpubliclibrary.linechart.common.BasePresenter;
import com.lenovo.service.basicpubliclibrary.linechart.common.BaseView;
import com.lenovo.service.basicpubliclibrary.linechart.model.UploadRecord;

import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by cx on 2017/8/30.
 */

public interface ChartContract {

    interface View extends BaseView<Presenter> {

        void showData(UploadRecord uploadRecord);

        void showError(String msg);

    }

    interface Presenter extends BasePresenter {

        void loadData();

        void initChart(LineChartView lineChartView, UploadRecord.SevendaysBean chartList);

    }
}
