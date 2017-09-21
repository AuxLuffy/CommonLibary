package com.lenovo.service.basicpubliclibrary.request.contract;

import com.lenovo.service.basicpubliclibrary.linechart.common.BasePresenter;
import com.lenovo.service.basicpubliclibrary.linechart.common.BaseView;
import com.lenovo.service.basicpubliclibrary.linechart.model.UploadRecord;

/**
 * Created by cx on 2017/9/18.
 */

public interface RequestContract {

    interface View extends BaseView<Presenter>{

        void showLoading();

        void dismissLoading();

        String getId();

        void showData(String uploadRecord);

    }

    interface Presenter extends BasePresenter{

        void getData(String id);

    }

}
