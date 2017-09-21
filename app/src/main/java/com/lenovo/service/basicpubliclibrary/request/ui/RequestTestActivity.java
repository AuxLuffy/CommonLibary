package com.lenovo.service.basicpubliclibrary.request.ui;

import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.request.contract.RequestContract;
import com.lenovo.service.basicpubliclibrary.request.presenter.RequestPresenter;

import butterknife.BindView;

/**
 * Created by cx on 2017/9/18.
 * <p>
 * 网络框架封装，持续维护和更新
 * <p>
 * 网络框架 Okhttp3 + retrofit2 + http/https + mvp
 * <p>
 * 网络框架 Okhttp3 + retrofit2 + http/https + rxjava + mvp
 * <p>
 * 多种方式供你使用，调用简单，一句合成
 * <p>
 * 本类演示https连接，返回方式html
 */

public class RequestTestActivity extends BaseActivity implements RequestContract.View {

    @BindView(R.id.text)
    TextView text;

    private RequestContract.Presenter presenter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_requesttest;
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
    public void showData(String result) {

        text.setText(result);

    }
}
