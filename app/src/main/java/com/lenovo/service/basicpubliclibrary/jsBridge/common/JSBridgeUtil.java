package com.lenovo.service.basicpubliclibrary.jsBridge.common;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import com.lenovo.service.basicpubliclibrary.jsBridge.contract.JSBridgeContract;

/**
 * Created by cx on 2017/6/13.
 */

public class JSBridgeUtil {

    public static BridgeWebView initWebView(final BridgeWebView webView, final JSBridgeContract.View myView) {

        try {
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            webSettings.setDatabaseEnabled(true);
            webSettings.setBuiltInZoomControls(false);
            webSettings.setDomStorageEnabled(true);
            webSettings.setAppCacheEnabled(false);
            //设置localStorage存储路径
            String localStorageDBPath = webView.getContext().getFilesDir().getAbsolutePath();
            webSettings.setDatabasePath(localStorageDBPath);

            //不使用缓存：
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

            //不允许缩放
            webSettings.setSupportZoom(false);
            // 自适应屏幕大小
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);

            webView.setDefaultHandler(new DefaultHandler());

            webView.setWebViewClient(new MyWebViewClient(webView, myView));

            webView.setWebChromeClient(new WebChromeClient());

            webView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            if (webView.getUrl().equals(URLConstants.PAGE_ERROR)) {//!BaseActivity.canLoad ||

                                myView.finishActivity();

                            } else if (webView.getUrl().equals(myView.getPresenter().getLoadURl(myView.getIntentURl()))) {

                                myView.finishActivity();

                            } else if (webView.canGoBack()) {

                                webView.goBack();

                            } else {

                                myView.finishActivity();

                            }
                            return true;
                        }
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return webView;
    }

}
