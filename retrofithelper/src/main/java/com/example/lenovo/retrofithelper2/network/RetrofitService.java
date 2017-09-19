package com.example.lenovo.retrofithelper2.network;


import com.example.lenovo.retrofithelper2.appinfo.entity.AppInfo;
import com.example.lenovo.retrofithelper2.appinfo.entity.BaseBean;
import com.example.lenovo.retrofithelper2.appinfo.entity.PageBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 接口类
 * @Author 李巷阳
 * Created at 2017/9/18 17:32
 */

public interface RetrofitService {


    // 这种格式只使用Retrofit时调用
    //    @GET("book/search")
    //    Call<Book> getSearchBook(@Query("q") String name,
    //                             @Query("tag") String tag,
    //                             @Query("start") int start,
    //                             @Query("count") int count);

    // 全部路径
    // https://112.124.22.238:8081/course_api/cniaoplay/featured2?p={%27page%27:0}
    @GET("featured2")
    Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

}
