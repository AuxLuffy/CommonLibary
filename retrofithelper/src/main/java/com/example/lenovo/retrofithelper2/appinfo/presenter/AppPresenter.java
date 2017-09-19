package com.example.lenovo.retrofithelper2.appinfo.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.lenovo.retrofithelper2.appinfo.entity.AppInfo;
import com.example.lenovo.retrofithelper2.appinfo.entity.PageBean;
import com.example.lenovo.retrofithelper2.appinfo.view.AppView;
import com.example.lenovo.retrofithelper2.appinfo.view.BaseView;
import com.example.lenovo.retrofithelper2.manager.DataManager;
import com.example.lenovo.retrofithelper2.rx.RxErrorHandler;
import com.example.lenovo.retrofithelper2.rx.RxHttpReponseCompat;
import com.example.lenovo.retrofithelper2.rx.Subscriber.ProgressDialogSubcriber;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by lenovo on 2017/9/19.
 */

public class AppPresenter implements Presenter {


    private DataManager manager;// retrofit接口类的封装
    private CompositeSubscription mCompositeSubscription;// 用户包裹订阅事件,开启和关闭rxjava订阅。
    private Context mContext;
    private AppView mAppView;
    private RxErrorHandler mErrorHandler;

    public AppPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext);// 接口类的封装
        mCompositeSubscription = new CompositeSubscription();// 初始化订阅事件的管理者
        mErrorHandler=new RxErrorHandler(mContext);
    }


    public void getSearchApp(){
        mCompositeSubscription.add(manager.getApps().compose(RxHttpReponseCompat
                .<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressDialogSubcriber<PageBean<AppInfo>>((AppView) mContext, mErrorHandler) {
                    @Override
                    public void onNext(PageBean<AppInfo> response) {
                        if (response != null) {
                            mAppView.onSuccess(response.getDatas());
                        }
                    }
                }));
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){// 关闭订阅
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void attachView(BaseView view) {
        mAppView=(AppView)view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }
}
