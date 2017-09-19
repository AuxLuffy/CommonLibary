package com.lenovo.service.basicpubliclibrary.eventbus;

import android.app.Activity;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/9/19.
 */
public class BaseActivity extends Activity {

    protected void registEventBus() {
        //子类如果需要注册eventbus，则重写此方法
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    protected void unRegistEventBus() {
        //子类如果需要注销eventbus，则重写此方法
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
