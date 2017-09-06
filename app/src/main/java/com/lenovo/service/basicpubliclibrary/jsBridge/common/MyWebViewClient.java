package com.lenovo.service.basicpubliclibrary.jsBridge.common;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;

import com.lenovo.service.basicpubliclibrary.jsBridge.contract.JSBridgeContract;


/**
 * Created by cx on 2017/6/12.
 */

public class MyWebViewClient extends BridgeWebViewClient{

    JSBridgeContract.View myView;

    BridgeWebView myWebView;

    public MyWebViewClient(BridgeWebView webView, JSBridgeContract.View view) {
        super(webView);
        myWebView = webView;
        myView = view;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        myView.showLoading();
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        myView.dismissLoading();
    }
    //打开网页时不调用系统浏览器， 而是在本WebView中显示
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        if(this.onPageHeaders(url) != null) {
//            view.loadUrl(url, this.onPageHeaders(url));
//        }
//        view.loadUrl(url);
        return super.shouldOverrideUrlLoading(view,url);
    }

    /**
     * 加在失败进入错误页面
     */
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//        switch (errorCode){
//            case HttpStatus.SC_NOT_FOUND:
//                view.loadUrl("file:///android_assets/error_handle.html");
//                break;
//        }
        myWebView.failUrl = failingUrl;
       view.loadUrl(URLConstants.PAGE_ERROR);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();//接受证书
    }

}
