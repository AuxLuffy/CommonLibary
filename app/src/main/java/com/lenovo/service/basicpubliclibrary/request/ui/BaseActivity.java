package com.lenovo.service.basicpubliclibrary.request.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by cx on 2017/9/18.
 * 只用来演示网络框架的请求使用
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        ButterKnife.bind(this);
        setWidget();
    }

    /**
     * to show your loading view
     */
    public void showLoading() {
    }

    /**
     * to close your loading view
     */
    public void dismissLoading() {
    }

    protected abstract int bindLayout();

    protected abstract void setWidget();

}
