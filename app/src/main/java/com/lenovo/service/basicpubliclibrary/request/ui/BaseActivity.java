package com.lenovo.service.basicpubliclibrary.request.ui;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by cx on 2017/9/18.
 *
 * 用来演示网络框架的请求以及权限申请
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Map<Integer, Runnable> permissionRunnables = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(bindLayout());

        ButterKnife.bind(this);

        setWidget();
    }

    /**
     * to show your loading view
     */
    public void showLoading() {
    }

    /**
     * to close your loading view
     */
    public void dismissLoading() {
    }

    @TargetApi(23)
    protected void getPermission(int id, String permission, Runnable allowableRunnable) {

        permissionRunnables.put(id, allowableRunnable);

        if (Build.VERSION.SDK_INT >= 23) {

            int checkPermission = ContextCompat.checkSelfPermission(getApplicationContext(), permission);

            if (checkPermission != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{permission}, id);

            } else {

                allowableRunnable.run();

            }
        } else {

            allowableRunnable.run();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Runnable allowRun = permissionRunnables.get(requestCode);

            allowRun.run();

        } else {
            // 授权失败 do something what you want .For example, ToastUtil.showToast("请开启权限,否则无法使用此功能！");
        }
    }

    protected abstract int bindLayout();

    protected abstract void setWidget();

}
