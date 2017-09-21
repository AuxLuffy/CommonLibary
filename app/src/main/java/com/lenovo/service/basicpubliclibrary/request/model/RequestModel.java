package com.lenovo.service.basicpubliclibrary.request.model;

import com.google.gson.JsonObject;
import com.lenovo.service.basicpubliclibrary.linechart.common.OnRequestCallback;
import com.lenovo.service.basicpubliclibrary.linechart.model.UploadRecord;
import com.lenovo.service.basicpubliclibrary.request.net.example.SubscriberNHttps;
import com.lenovo.service.basicpubliclibrary.request.net.norxjava.CallBackN;
import com.lenovo.service.basicpubliclibrary.request.net.rxjava.ObservableN;
import com.lenovo.service.basicpubliclibrary.request.net.ServiceGenerator;
import com.lenovo.service.basicpubliclibrary.request.net.rxjava.SubscriberN;

import okhttp3.ResponseBody;

/**
 * Created by cx on 2017/9/18.
 * 两种方式：含rxjava   不含rxjava
 */

public class RequestModel implements IRequestModel{

    @Override
    public void getUploadRecordData(String id, final OnRequestCallback<UploadRecord> callback) {

        /**
         * 含有rxjava
         */
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

        /**
         * 不含有rxjava
         */
        ServiceGenerator.getApiService().getReportRecord(id).enqueue(new CallBackN<UploadRecord>() {

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

    @Override
    public void getHttpsInfo(String id,final OnRequestCallback<String> callback) {

        ServiceGenerator.getApiService().testHttps(id).compose(ObservableN.<ResponseBody> applySchedulers()).subscribe(new SubscriberNHttps<String>(){

                    @Override
                    public void onResult(String response) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(String errMsg) {
                        callback.onFailure(errMsg);
                    }
        });

    }

}
