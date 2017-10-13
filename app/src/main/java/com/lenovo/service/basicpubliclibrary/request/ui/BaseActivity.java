package com.lenovo.service.basicpubliclibrary.request.ui;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.service.basicpubliclibrary.gesturelock.util.ToastUtil;

import java.util.ArrayList;
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
    protected void getPermission(int id, Runnable allowableRunnable, String... permissions) {

        permissionRunnables.put(id, allowableRunnable);

        if (Build.VERSION.SDK_INT >= 23) {

            int checkPermission;

            ArrayList<String> needRequestPermissions = new ArrayList<String>();

            for(String permission : permissions){

                checkPermission = ContextCompat.checkSelfPermission(getApplicationContext(), permission);

                if (checkPermission != PackageManager.PERMISSION_GRANTED) {

                    needRequestPermissions.add(permission);

                }

            }
            if(needRequestPermissions.size() == 0){

                allowableRunnable.run();

            }else{

                String[] needPermissions = new String[needRequestPermissions.size()];

                for(int i = 0; i < needRequestPermissions.size();i ++){

                    needPermissions[i] = needRequestPermissions.get(i);

                }

                ActivityCompat.requestPermissions(this,needPermissions, id);

            }

        } else {
            allowableRunnable.run();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        int DeniedNum = 0;

        for (int grantResult : grantResults) {

            if(grantResult == PackageManager.PERMISSION_DENIED){
                DeniedNum  ++;
                break;
            }

        }

        if(DeniedNum == 0){

            Runnable allowRun = permissionRunnables.get(requestCode);

            allowRun.run();

        }else{

            ToastUtil.showMessage(this,"请开启权限,否则无法使用此功能！");

        }

    }

    protected abstract int bindLayout();

    protected abstract void setWidget();

}
