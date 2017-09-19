package com.example.lenovo.retrofithelper2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.retrofithelper2.appinfo.entity.AppInfo;
import com.example.lenovo.retrofithelper2.appinfo.presenter.AppPresenter;
import com.example.lenovo.retrofithelper2.appinfo.view.AppView;

import java.util.List;

public class RetrofitHelperActivity extends Activity implements AppView, View.OnClickListener {


    private AppPresenter mAppPreseenter;
    private Context mContext;
    private Button mBt_networkbook;
    private TextView mTv_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofithelper_activity);
        init_presenter();
        init_view();
        init_listener();
    }


    private void init_presenter() {
        mContext=this;
        mAppPreseenter=new AppPresenter(this);
        mAppPreseenter.onCreate();
        mAppPreseenter.attachView(this);
    }

    private void init_view() {
        mBt_networkbook = (Button) findViewById(R.id.bt_networkbook);
        mTv_data = (TextView) findViewById(R.id.tv_data);

    }

    private void init_listener() {
        mBt_networkbook.setOnClickListener(this);
    }



    @Override
    public void onSuccess(List<AppInfo> listappinfo) {
        StringBuffer sb=new StringBuffer();
        for(AppInfo info : listappinfo)
        {
            sb.append(info.toString());
        }
        mTv_data.setText(sb);
    }

    @Override
    public void onError(String result) {
        mTv_data.setText(result);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.bt_networkbook) {
            getDataAppInfo();

        }
    }

    private void getDataAppInfo() {
        mAppPreseenter.getSearchApp();
    }


}
