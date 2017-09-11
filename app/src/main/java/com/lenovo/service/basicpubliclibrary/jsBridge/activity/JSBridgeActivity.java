package com.lenovo.service.basicpubliclibrary.jsBridge.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.jsBridge.common.AndroidJSMethod;
import com.lenovo.service.basicpubliclibrary.jsBridge.common.BridgeWebView;
import com.lenovo.service.basicpubliclibrary.jsBridge.common.CallBackFunction;
import com.lenovo.service.basicpubliclibrary.jsBridge.common.JSBridgeUtil;
import com.lenovo.service.basicpubliclibrary.jsBridge.common.MyBridgeHandler;
import com.lenovo.service.basicpubliclibrary.jsBridge.common.MyCallBackFunction;
import com.lenovo.service.basicpubliclibrary.jsBridge.common.NetWorkUtil;
import com.lenovo.service.basicpubliclibrary.jsBridge.common.URLConstants;
import com.lenovo.service.basicpubliclibrary.jsBridge.contract.JSBridgeContract;
import com.lenovo.service.basicpubliclibrary.jsBridge.presenter.JSBridgePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by cx on 2017/6/12.
 * 涉及源码修改，源码功能封装。支持失败页面网络监控，http,https以及add请求头，若需显示加载动画自行在以下方法中添加
 */

public class JSBridgeActivity extends AppCompatActivity implements JSBridgeContract.View, MyBridgeHandler, MyCallBackFunction {

    @BindView(R.id.webview)
    public BridgeWebView webView;

    private JSBridgeContract.Presenter presenter;

    private String firstUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_jsbridge);
        ButterKnife.bind(this);
        init();
    }

    protected void init() {

        presenter = new JSBridgePresenter(this);

        webView = JSBridgeUtil.initWebView(webView, this);

        loadUrl(presenter.getLoadURl(getIntentURl()));

        registerHandlers();

    }

    private void loadUrl(String url) {

        firstUrl = url;

        if (NetWorkUtil.IsNetWorkAvailable(this)) {

            webView.loadUrl(url);

        } else {

            webView.loadUrl(URLConstants.PAGE_ERROR);

        }
    }


    //registerHandler  供JS调用
    private void registerHandlers() {

        webView.registerHandler(AndroidJSMethod.ANDROID_REFRESH, this);

        webView.registerHandler(AndroidJSMethod.ANDROID_NOINTERNET, this);

    }
    //callHandler 调用JS
    private void callHandlers(){

        webView.callHandler(AndroidJSMethod.JS_TEST,"param",this);

    }

    @Override
    public void setPresenter(JSBridgeContract.Presenter presenter) {

    }

    @Override
    public String getIntentURl() {

        if (getIntent() == null) {
            return "";
        }
        return getIntent().getStringExtra("extra1");

    }


    @Override
    public void showLoading() {
//        super.showLoading();
    }

    @Override
    public void dismissLoading() {
//        super.dismissLoading();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public JSBridgeContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void handler(String tag, String data, CallBackFunction function) {
        try {
            switch (tag) {

                case AndroidJSMethod.ANDROID_REFRESH://错误页面刷新

                    action_refresh();

                    break;

                case AndroidJSMethod.ANDROID_NOINTERNET://js没有网络


                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onCallBack(String tag, String data) {
        switch (tag){

            case AndroidJSMethod.JS_TEST:

                //do something

                break;
        }
    }

    private void action_refresh() {

        if (NetWorkUtil.IsNetWorkAvailable(this)) {

            if (TextUtils.isEmpty(webView.failUrl)) {//首次进入没有网络直接进入了error.html，点击刷新

                webView.loadUrl(firstUrl);

            } else {

                webView.loadUrl(webView.failUrl);//加在失败进入失败页面，点击刷新

            }

        } else {
            Toast.makeText(this, "请检查您的网络", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        webView.onResume();
        webView.resumeTimers();
        super.onResume();
    }

    @Override
    public void onPause() {
        webView.onPause();
        webView.pauseTimers();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
        }
    }

}
