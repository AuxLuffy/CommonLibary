package com.lenovo.service.basicpubliclibrary.jsBridge.contract;


import com.lenovo.service.basicpubliclibrary.linechart.common.BasePresenter;
import com.lenovo.service.basicpubliclibrary.linechart.common.BaseView;

/**
 * Created by cx on 2017/6/12.
 */

public interface JSBridgeContract {

    interface Presenter extends BasePresenter {

        String getLoadURl(String url);

    }

    interface View extends BaseView<Presenter> {

        String getIntentURl();
        void showLoading();
        void dismissLoading();
        void finishActivity();
        Presenter getPresenter();

    }

}
