package com.lenovo.service.basicpubliclibrary.zbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.yanzhenjie.zbar.camera.CameraPreview;
import com.yanzhenjie.zbar.camera.ScanCallback;

/**
 * 版权所有 ,lenovo保留所有版权。<br>
 * 项目描述：Zbar二维码扫描速度非常快 基本上看到就扫描出来了
 * 作者：Jiao
 * 日期：2017/9/25
 **/
public class ZbarActivity extends Activity {
    private CameraPreview mPreview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zbar);
        mPreview = (CameraPreview) findViewById(R.id.capture_preview);
        mPreview.setScanCallback(callback);
    }
    /**
     * 打开相机并开始扫描。
     */
    private void startScan() {
        mPreview.start();
    }

    /**
     * 停止扫描并关闭相机。
     */
    private void stopScan() {
        mPreview.stop();
    }

    /**
     * 监听结果。
     */
    private ScanCallback callback = new ScanCallback() {
        @Override
        public void onScanResult(String content) {
            Toast.makeText(ZbarActivity.this,content,Toast.LENGTH_SHORT).show();
            mPreview.start();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        startScan();
    }

    @Override
    protected void onPause() {
        // 必须在这里停止扫描释放相机。
        stopScan();
        super.onPause();
    }
}
