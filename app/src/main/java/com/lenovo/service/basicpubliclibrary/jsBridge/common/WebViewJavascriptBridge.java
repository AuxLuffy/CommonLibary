package com.lenovo.service.basicpubliclibrary.jsBridge.common;


public interface WebViewJavascriptBridge {

        public void send(String data);

        public void send(String data, CallBackFunction responseCallback);


}
