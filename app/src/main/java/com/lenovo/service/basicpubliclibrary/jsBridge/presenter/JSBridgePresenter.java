package com.lenovo.service.basicpubliclibrary.jsBridge.presenter;


import com.lenovo.service.basicpubliclibrary.jsBridge.common.LocalConstant;
import com.lenovo.service.basicpubliclibrary.jsBridge.common.URLConstants;
import com.lenovo.service.basicpubliclibrary.jsBridge.contract.JSBridgeContract;

/**
 * Created by cx on 2017/6/12.
 */

public class JSBridgePresenter implements JSBridgeContract.Presenter {

    private JSBridgeContract.View view;

    public JSBridgePresenter(JSBridgeContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public String getLoadURl(String urlParam) {
        String url = "";
        switch (urlParam) {
            case LocalConstant.H5URL.page_register:
                url = "https://www.baidu.com";
                break;
        }
        return url;
    }

}
