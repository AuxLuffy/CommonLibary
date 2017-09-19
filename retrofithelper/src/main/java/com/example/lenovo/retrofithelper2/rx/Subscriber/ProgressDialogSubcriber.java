package com.example.lenovo.retrofithelper2.rx.Subscriber;

import android.app.Activity;
import android.app.ProgressDialog;

import com.example.lenovo.retrofithelper2.appinfo.view.AppView;
import com.example.lenovo.retrofithelper2.rx.RxErrorHandler;


/**
 * Created by lenovo on 2017/5/10.
 */

public abstract class ProgressDialogSubcriber<T> extends ErrorHandlerSubscriber<T> {

    private AppView mBaseview;
    private ProgressDialog mProgressdialog;


    public ProgressDialogSubcriber(AppView baseview, RxErrorHandler mRxErrorHandler) {
        super(mRxErrorHandler);
        mBaseview = baseview;
        mProgressdialog=new ProgressDialog((Activity)baseview);
    }

    // 如果子类,继承此方法,并且返回为false。则不再弹框。
    protected boolean isShowProgressDialog() {
        return true;
    }

    @Override
    public void onStart() {
        if (isShowProgressDialog()) {
            mProgressdialog.show();
        }
    }

    @Override
    public void onCompleted() {
        if (isShowProgressDialog()) {
            mProgressdialog.dismiss();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (isShowProgressDialog()) {
            mProgressdialog.dismiss();
        }
    }



}
