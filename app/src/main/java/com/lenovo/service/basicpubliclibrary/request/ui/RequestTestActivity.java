package com.lenovo.service.basicpubliclibrary.request.ui;

import com.lenovo.service.basicpubliclibrary.linechart.model.UploadRecord;
import com.lenovo.service.basicpubliclibrary.request.contract.RequestContract;
import com.lenovo.service.basicpubliclibrary.request.presenter.RequestPresenter;

/**
 * Created by cx on 2017/9/18.
 *
 * 网络框架封装，持续维护和更新
 *
 * 网络框架 Okhttp3 + retrofit2 + http/https + mvp
 *
 * 网络框架 Okhttp3 + retrofit2 + http/https + rxjava + mvp
 *
 * 多种方式供你使用，调用简单，一句合成
 */

public class RequestTestActivity extends BaseActivity implements RequestContract.View{

    private RequestContract.Presenter presenter;

    @Override
    protected int bindLayout() {
        return 0;
    }

    @Override
    protected void setWidget() {

        presenter = new RequestPresenter(this);

        presenter.start();
    }

    @Override
    public void setPresenter(RequestContract.Presenter presenter) {

        this.presenter = presenter;

    }

    @Override
    public String getId() {

        return "1";

    }

    @Override
    public void showData(UploadRecord uploadRecord) {

    }
}
