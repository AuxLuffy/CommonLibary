package com.lenovo.service.basicpubliclibrary.request.model;

import android.util.Log;

import com.google.gson.JsonObject;
import com.lenovo.service.basicpubliclibrary.linechart.common.OnRequestCallback;
import com.lenovo.service.basicpubliclibrary.linechart.model.UploadRecord;
import com.lenovo.service.basicpubliclibrary.request.net.ObservableN;
import com.lenovo.service.basicpubliclibrary.request.net.ServiceGenerator;
import com.lenovo.service.basicpubliclibrary.request.net.SubscriberN;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cx on 2017/9/18.
 */

public class RequestModel implements IRequestModel{

    @Override
    public void getUploadRecordData(String id, final OnRequestCallback<UploadRecord> callback) {

        ServiceGenerator.getApiService().getReportRecordwithRxjava(id).compose(ObservableN.<JsonObject> applySchedulers()).subscribe(new SubscriberN<UploadRecord>(){

            @Override
            public void onResult(UploadRecord response) {
                callback.onSuccess(response);
            }

            @Override
            public void onError(String errMsg) {
                callback.onFailure(errMsg);
            }
        });
    }

}
