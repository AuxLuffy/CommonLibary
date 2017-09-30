package com.lenovo.service.basicpubliclibrary.netframe.net.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 界面描述：
 * <p>
 */

public interface RequestHandler {

    Request onBeforeRequest(Request request, Interceptor.Chain chain);

    Response onAfterRequest(Response response, Interceptor.Chain chain) throws IOException;

}
