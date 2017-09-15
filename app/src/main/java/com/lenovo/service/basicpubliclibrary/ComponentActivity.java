package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.lenovo.service.basicpubliclibrary.Frostedglasseffect.FrostedGlassEffectActivity;
import com.lenovo.service.basicpubliclibrary.Frostedglasseffect.util.BlurBehind;
import com.lenovo.service.basicpubliclibrary.Frostedglasseffect.util.OnBlurCompleteListener;
import com.lenovo.service.basicpubliclibrary.colortheme.ColorThemeActivity;
import com.lenovo.service.basicpubliclibrary.fragmentation.demo_flow.FlowMainActivity;
import com.lenovo.service.basicpubliclibrary.maillistananimation.MaillistActivity;
import com.lenovo.service.basicpubliclibrary.multitype.bilibili.BilibiliActivity;
import com.lenovo.service.basicpubliclibrary.obtainlocalphoto.LocalPhotoActivity;
import com.lenovo.service.basicpubliclibrary.picture_cut.SampleActivity;
import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.PullTorefreshActivity;
import com.lenovo.service.basicpubliclibrary.shadowmasking.ShadowmaskingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import lenovo.com.zxing.MipcaActivityCapture;

public class ComponentActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE = 1;
    private static final int PERMISSION_CAMARA_REQUEST = 2;
    @BindView(R.id.tv_maillist)
    TextView mtv_maillist;
    @BindView(R.id.tv_multitype)
    TextView mTvMultitype;
    @BindView(R.id.tv_localphoto)
    TextView mtv_localphoto;
    @BindView(R.id.tv_pulltorefresh)
    TextView mtv_pulltorefresh;
    @BindView(R.id.tv_fragmentation)
    TextView mTvFragmentation;

    @BindView(R.id.tv_h5_activity)
    TextView mTvH5Activity;
    @BindView(R.id.tv_picut)
    TextView mTvPicut;
    @BindView(R.id.tvNightMode)
    TextView tvNightNode;
    @BindView(R.id.tv_frosted_galss_effect)
    TextView mTvFrosted;
    @BindView(R.id.tvShadowmasking)
    TextView mTvShadowmasking;


    @BindView(R.id.tvQRCode)
    TextView tvQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);
        ButterKnife.bind(this);
        init_listener();
    }

    private void init_listener() {
        mtv_maillist.setOnClickListener(this);
        mtv_localphoto.setOnClickListener(this);
        mTvMultitype.setOnClickListener(this);
        mTvH5Activity.setOnClickListener(this);
        mtv_pulltorefresh.setOnClickListener(this);
        mTvFragmentation.setOnClickListener(this);
        mTvPicut.setOnClickListener(this);
        mTvFrosted.setOnClickListener(this);
        tvNightNode.setOnClickListener(this);
        tvQRCode.setOnClickListener(this);
        mTvShadowmasking.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_maillist:
                intent.setClass(ComponentActivity.this,
                        MaillistActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_localphoto:
                intent.setClass(ComponentActivity.this,
                        LocalPhotoActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_multitype:
                startActivity(new Intent(ComponentActivity.this, BilibiliActivity.class));
                break;
            case R.id.tv_h5_activity:
                Intent intent1 = new Intent(ComponentActivity.this, H5Activity.class);
                intent1.putExtra(H5Activity.H5_URL, "http://www.jianshu.com/");
                startActivity(intent1);
                break;
            case R.id.tv_pulltorefresh:
                startActivity(new Intent(ComponentActivity.this, PullTorefreshActivity.class));
                break;
            case R.id.tv_fragmentation:
                startActivity(new Intent(ComponentActivity.this, FlowMainActivity.class));
                break;
            case R.id.tv_picut:
                startActivity(new Intent(ComponentActivity.this, SampleActivity.class));
                break;
            case R.id.tvNightMode:
                startActivity(new Intent(ComponentActivity.this, ColorThemeActivity.class));
                break;
            case R.id.tv_frosted_galss_effect://毛玻璃效果的activity
                BlurBehind.getInstance().execute(ComponentActivity.this, new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {
                        Intent intent = new Intent(ComponentActivity.this, FrostedGlassEffectActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }
                });
                break;
            case R.id.tvShadowmasking://指引遮盖蒙版
                startActivity(new Intent(ComponentActivity.this, ShadowmaskingActivity.class));
                break;
            case R.id.tvQRCode:
                // 扫描二维码
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, PERMISSION_CAMARA_REQUEST);
                } else {
                    MipcaActivityCapture.openMipcapActivity(this, REQUEST_CODE);
                }

                break;
        }
    }


    //  重写方法    当权限申请后     执行 接收结果的作用
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // 先判断请求码 与 请求时的  是否一致
        if (requestCode == PERMISSION_CAMARA_REQUEST) {
            //  判断请求结果长度     且  结果 为  允许访问  则 进行使用
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 可以使用
                MipcaActivityCapture.openMipcapActivity(this, REQUEST_CODE);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                if (data != null) {
                    String msg = data.getStringExtra(MipcaActivityCapture.RESULT_MSG);
                    tvQRCode.setText(tvQRCode.getText() + "\n" + msg);
                }
            }
        }
    }
}
