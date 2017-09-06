package com.lenovo.service.basicpubliclibrary.linechart.model;

import com.lenovo.service.basicpubliclibrary.linechart.common.OnRequestCallback;

/**
 * Created by cx on 2016/12/15.
 */

public interface IChartModel {

    void requestData(OnRequestCallback<UploadRecord> callback);

    int getRandomNum();

}
