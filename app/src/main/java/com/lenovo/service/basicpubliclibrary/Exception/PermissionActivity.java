package com.lenovo.service.basicpubliclibrary.Exception;

import android.Manifest;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.gesturelock.util.ToastUtil;
import com.lenovo.service.basicpubliclibrary.request.ui.BaseActivity;

/**
 * Created by cx on 2017/9/30.
 *
 * 两行代码来获取权限
 *
 */

public class PermissionActivity extends BaseActivity{

    @Override
    protected int bindLayout() {
        return R.layout.activity_base_jsbridge;
    }

    @Override
    protected void setWidget() {

        getPermission(1, Manifest.permission.CAMERA, new Runnable() {
            @Override
            public void run() {
                ToastUtil.showMessage(PermissionActivity.this,"摄像权限已获取");
            }
        });
    }
}
