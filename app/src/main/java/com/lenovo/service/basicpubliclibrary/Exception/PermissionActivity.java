package com.lenovo.service.basicpubliclibrary.Exception;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.gesturelock.util.ToastUtil;
import com.lenovo.service.basicpubliclibrary.request.ui.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cx on 2017/9/30.
 * <p>
 * 两行代码来获取权限
 * 更新：添加多权限申请实例  2017.10.12
 */

public class PermissionActivity extends BaseActivity {

    @Override
    protected int bindLayout() {

        return R.layout.activity_permission;

    }

    @Override
    protected void setWidget() {

    }


    @OnClick({R.id.testbt1, R.id.testbt2})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.testbt1:

                getPermission(PermissionConstants.ONE, new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showMessage(PermissionActivity.this, "摄像权限已获取");
                    }
                }, Manifest.permission.CAMERA);

                break;

            case R.id.testbt2:

                getPermission(PermissionConstants.TWO, new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showMessage(PermissionActivity.this, "存储权限已获取");
                    }
                }, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                break;
        }
    }
}
