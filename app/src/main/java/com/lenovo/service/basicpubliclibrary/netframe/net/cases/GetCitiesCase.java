package com.lenovo.service.basicpubliclibrary.netframe.net.cases;


import com.lenovo.service.basicpubliclibrary.netframe.net.cases.base.UseCase;
import com.lenovo.service.basicpubliclibrary.netframe.net.models.City;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 界面描述：
 */

public class GetCitiesCase extends UseCase<GetCitiesCase.Api> {
    interface Api {
        @GET("api/china/")
        Observable<List<City>> getCitiesCase();
    }


    public Observable<List<City>> getCities() {
        return ApiClient().getCitiesCase()
                .compose(this.<List<City>>normalSchedulers());
    }

}
