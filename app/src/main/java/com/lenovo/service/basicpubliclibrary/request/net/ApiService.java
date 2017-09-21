package com.lenovo.service.basicpubliclibrary.request.net;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by cx on 2016/12/15.
 * 三种请求方式
 */

public interface ApiService {

    /**
     * test https example
     */
    @POST("otn/")
    @FormUrlEncoded
    Observable<ResponseBody> testHttps(@Field("param") String params);

    /**
     * noRxjava
     */
    @POST("ApiReport/statReportData")
    @FormUrlEncoded
    Call<JsonObject> getReportRecord(@Field("shop_number") String shop_id);

    /**
     *  rxjava
     */
    @POST("ApiReport/statReportData")
    @FormUrlEncoded
    Observable<JsonObject> getReportRecordwithRxjava(@Field("shop_number") String shop_id);

    @Multipart
    @POST("ApiActivity/reportupload")
    Call<ResponseBody> couponUploadPic(@Part("product_sn") RequestBody product_sn, @Part("shop_id") RequestBody shop_id, @Part MultipartBody.Part file);

}
