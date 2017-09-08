package com.lenovo.service.basicpubliclibrary.gesturelock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.gesturelock.util.Contants;
import com.lenovo.service.basicpubliclibrary.gesturelock.util.PasswordUtil;
import com.leo.gesturelibray.enums.LockMode;
import com.leo.gesturelibray.util.StringUtils;

public class GestureLockActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_setting;
    private Button bt_verify;
    private Button bt_edit;
    private Button bt_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_lock);

        bt_setting = (Button) findViewById(R.id.bt_setting);
        bt_verify = (Button) findViewById(R.id.bt_verify);
        bt_edit = (Button) findViewById(R.id.bt_edit);
        bt_clear = (Button) findViewById(R.id.bt_clear);

        bt_setting.setOnClickListener(this);
        bt_verify.setOnClickListener(this);
        bt_edit.setOnClickListener(this);
        bt_clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_clear:
                actionSecondActivity(LockMode.CLEAR_PASSWORD);
                break;
            case R.id.bt_edit:
                actionSecondActivity(LockMode.EDIT_PASSWORD);
                break;
            case R.id.bt_setting:
                actionSecondActivity(LockMode.SETTING_PASSWORD);
                break;
            case R.id.bt_verify:
                actionSecondActivity(LockMode.VERIFY_PASSWORD);
                break;
        }
    }

    /**
     * 跳转到密码处理界面
     */
    private void actionSecondActivity(LockMode mode) {
        if (mode != LockMode.SETTING_PASSWORD) {
            if (StringUtils.isEmpty(PasswordUtil.getPin(this))) {
                Toast.makeText(getBaseContext(), "请先设置密码", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Intent intent = new Intent(this, DrawPasswordActivity.class);
        intent.putExtra(Contants.INTENT_SECONDACTIVITY_KEY, mode);
        startActivity(intent);
    }
}
