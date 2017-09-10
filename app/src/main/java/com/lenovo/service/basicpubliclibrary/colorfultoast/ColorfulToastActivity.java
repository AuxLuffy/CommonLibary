package com.lenovo.service.basicpubliclibrary.colorfultoast;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.colorfultoast.tools.RxToast;
import com.lenovo.service.basicpubliclibrary.colorfultoast.tools.utils;
import com.lenovo.service.basicpubliclibrary.takeaway.widget.RxTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/9/10.
 */

public class ColorfulToastActivity extends Activity{

    @BindView(R.id.button_error_toast)
    Button mButtonErrorToast;
    @BindView(R.id.button_success_toast)
    Button mButtonSuccessToast;
    @BindView(R.id.button_info_toast)
    Button mButtonInfoToast;
    @BindView(R.id.button_warning_toast)
    Button mButtonWarningToast;
    @BindView(R.id.button_normal_toast_wo_icon)
    Button mButtonNormalToastWoIcon;
    @BindView(R.id.button_normal_toast_w_icon)
    Button mButtonNormalToastWIcon;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;
    @BindView(R.id.rx_title)
    RxTitle mRxTitle;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_toast);
        utils.init(this);
        ButterKnife.bind(this);
        mContext=this;
        initView();
    }



    @OnClick({R.id.button_error_toast, R.id.button_success_toast, R.id.button_info_toast, R.id.button_warning_toast, R.id.button_normal_toast_wo_icon, R.id.button_normal_toast_w_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_error_toast:
                RxToast.error(mContext, "这是一个提示错误的Toast！", Toast.LENGTH_SHORT, true).show();
                break;
            case R.id.button_success_toast:
                RxToast.success(mContext, "这是一个提示成功的Toast!", Toast.LENGTH_SHORT, true).show();
                break;
            case R.id.button_info_toast:
                RxToast.info(mContext, "这是一个提示信息的Toast.", Toast.LENGTH_SHORT, true).show();
                break;
            case R.id.button_warning_toast:
                RxToast.warning(mContext, "这是一个提示警告的Toast.", Toast.LENGTH_SHORT, true).show();
                break;
            case R.id.button_normal_toast_wo_icon:
                RxToast.normal(mContext, "这是一个普通的没有ICON的Toast").show();
                break;
            case R.id.button_normal_toast_w_icon:
                Drawable icon = getResources().getDrawable(R.drawable.set);
                RxToast.normal(mContext, "这是一个普通的包含ICON的Toast", icon).show();
                break;
        }
    }
    protected void initView() {
        mRxTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
