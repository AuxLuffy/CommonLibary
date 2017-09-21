package com.lenovo.service.basicpubliclibrary.request.presenter;

import com.lenovo.service.basicpubliclibrary.linechart.common.OnRequestCallback;
import com.lenovo.service.basicpubliclibrary.linechart.model.UploadRecord;
import com.lenovo.service.basicpubliclibrary.request.contract.RequestContract;
import com.lenovo.service.basicpubliclibrary.request.model.IRequestModel;
import com.lenovo.service.basicpubliclibrary.request.model.RequestModel;

/**
 * Created by cx on 2017/9/18.
 */

public class RequestPresenter implements RequestContract.Presenter,OnRequestCallback<String> {

    private RequestContract.View view;

    private IRequestModel iRequestModel;

    public RequestPresenter(RequestContract.View view){

        this.view = view;

        iRequestModel = new RequestModel();

    }

    @Override
    public void start() {

        getData(view.getId());

    }

    @Override
    public void getData(String id) {

        view.showLoading();

//        iRequestModel.getUploadRecordData(id,this);

        iRequestModel.getHttpsInfo(id, this);

    }

    @Override
    public void onSuccess(String result) {

        view.dismissLoading();

        view.showData(result);
    }

    @Override
    public void onFailure(String msg) {

        view.dismissLoading();

    }
}
