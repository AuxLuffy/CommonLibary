package com.lenovo.service.basicpubliclibrary.appupdate;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kcode.lib.UpdateWrapper;
import com.kcode.lib.bean.VersionModel;
import com.kcode.lib.log.L;
import com.kcode.lib.net.CheckUpdateTask;
import com.lenovo.service.basicpubliclibrary.R;

public class AppUpdateActivity extends AppCompatActivity {

    private static final String TAG = "AppUpdateActivity";
    private final static String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_update);

    }

    public void check(View view) {
        checkUpdate(0, null);
    }

    public void checkNow(View view) {
        checkUpdate(5 * 60 * 1000, null);
    }

    public void checkCustoms(View view) {
        checkUpdate(0, CustomsUpdateActivity.class);
    }

    private void checkUpdate(final long time, final Class<? extends FragmentActivity> cls) {

        UpdateWrapper.Builder builder = new UpdateWrapper.Builder(getApplicationContext())
                .setTime(time)
                .setNotificationIcon(R.mipmap.ic_launcher_round)
                .setUrl("http://45.78.52.169/app/update.json")
                .setIsShowToast(false)
                .setCallback(new CheckUpdateTask.Callback() {
                    @Override
                    public void callBack(VersionModel versionModel) {
                        L.d(TAG, "version info :" + versionModel.getVersionName());
                    }

                });

        if (cls != null) {
            builder.setCustomsActivity(cls);
        }

        builder.build().start();


    }
}
