package com.lenovo.service.basicpubliclibrary.networkokhttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.MainActivity;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.networkokhttp.bean.Banner;
import com.lenovo.service.basicpubliclibrary.networkokhttp.http.OkHttpHelper;
import com.lenovo.service.basicpubliclibrary.networkokhttp.http.SpotsCallBack;
import com.squareup.okhttp.Response;

import java.util.List;

/**
 * Created by lenovo on 2017/9/15.
 */

public class NetokhttpActivity extends Activity implements View.OnClickListener {




    private OkHttpHelper httpHelper = OkHttpHelper.getInstance();// 初始化网络框架
    private Button mBt_access;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netokhttp);
        init_view();
        init_listener();

    }



    private void init_view() {
        mBt_access = (Button) findViewById(R.id.bt_access_networks);
    }


    private void init_listener() {
        mBt_access.setOnClickListener(NetokhttpActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_access_networks:
                String url ="http://112.124.22.238:8081/course_api/banner/query?type=1";

                httpHelper.get(url, new SpotsCallBack<List<Banner>>(NetokhttpActivity.this){
                    @Override
                    public void onSuccess(Response response, List<Banner> banners) {
                        StringBuilder sb=new StringBuilder();
                        for(Banner banner : banners){
                            sb.append(banner.toString());
                        }
                        Log.e("MainActivity",sb.toString());
                        Toast.makeText(NetokhttpActivity.this,sb.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Response response, int code, Exception e) {
                        Log.e("MainActivity",code+"");
                    }

                    @Override
                    public void onTokenError(Response response, int code) {
                        Log.e("MainActivity",code+"");

                    }
                });
                break;
        }
    }


}
