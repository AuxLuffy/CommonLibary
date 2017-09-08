package com.lenovo.service.basicpubliclibrary.gesturelock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.gesturelock.util.Contants;
import com.lenovo.service.basicpubliclibrary.gesturelock.util.PasswordUtil;
import com.leo.gesturelibray.enums.LockMode;
import com.leo.gesturelibray.view.CustomLockView;

import static com.leo.gesturelibray.enums.LockMode.CLEAR_PASSWORD;
import static com.leo.gesturelibray.enums.LockMode.SETTING_PASSWORD;

public class DrawPasswordActivity extends AppCompatActivity {

    private CustomLockView lv_lock;
    private TextView tv_hint;
    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_password);
        lv_lock = (CustomLockView) findViewById(R.id.lv_lock);
        tv_hint = (TextView) findViewById(R.id.tv_hint);
        tv_text = (TextView) findViewById(R.id.tv_text);

        //显示绘制方向
        lv_lock.setShow(true);
        //允许最大输入次数
        lv_lock.setErrorNumber(3);
        //密码最少位数
        lv_lock.setPasswordMinLength(4);
        //编辑密码或设置密码时，是否将密码保存到本地，配合setSaveLockKey使用
        lv_lock.setSavePin(true);
        //保存密码Key
        lv_lock.setSaveLockKey(Contants.PASS_KEY);

        //设置模式
        LockMode lockMode = (LockMode) getIntent().getSerializableExtra(Contants.INTENT_SECONDACTIVITY_KEY);
        setLockMode(lockMode);

        lv_lock.setOnCompleteListener(onCompleteListener);
    }

    /**
     * 密码输入监听
     */
    CustomLockView.OnCompleteListener onCompleteListener = new CustomLockView.OnCompleteListener() {
        @Override
        public void onComplete(String password, int[] indexs) {
            tv_hint.setText(getPassWordHint());
            finish();
        }

        @Override
        public void onError(String errorTimes) {
            tv_hint.setText("密码错误，还可以输入" + errorTimes + "次");
        }

        @Override
        public void onPasswordIsShort(int passwordMinLength) {
            tv_hint.setText("密码不能少于" + passwordMinLength + "个点");
        }

        @Override
        public void onAginInputPassword(LockMode mode, String password, int[] indexs) {
            tv_hint.setText("请再次输入密码");
        }


        @Override
        public void onInputNewPassword() {
            tv_hint.setText("请输入新密码");
        }

        @Override
        public void onEnteredPasswordsDiffer() {
            tv_hint.setText("两次输入的密码不一致");
        }

        @Override
        public void onErrorNumberMany() {
            tv_hint.setText("密码错误次数超过限制，不能再输入");
        }

    };

    /**
     * 密码相关操作完成回调提示
     */
    private String getPassWordHint() {
        String str = null;
        switch (lv_lock.getMode()) {
            case SETTING_PASSWORD:
                str = "密码设置成功";
                break;
            case EDIT_PASSWORD:
                str = "密码修改成功";
                break;
            case VERIFY_PASSWORD:
                str = "密码正确";
                break;
            case CLEAR_PASSWORD:
                str = "密码已经清除";
                break;
        }
        return str;
    }

    /**
     * 设置解锁模式
     */
    private void setLockMode(LockMode mode) {
        String str = "";
        switch (mode) {
            case CLEAR_PASSWORD:
                str = "清除密码";
                setLockMode(CLEAR_PASSWORD, PasswordUtil.getPin(this), str);
                break;
            case EDIT_PASSWORD:
                str = "修改密码";
                setLockMode(LockMode.EDIT_PASSWORD, PasswordUtil.getPin(this), str);
                break;
            case SETTING_PASSWORD:
                str = "设置密码";
                setLockMode(SETTING_PASSWORD, null, str);
                break;
            case VERIFY_PASSWORD:
                str = "验证密码";
                setLockMode(LockMode.VERIFY_PASSWORD, PasswordUtil.getPin(this), str);
                break;
        }
        tv_text.setText(str);
    }

    /**
     * 密码输入模式
     */
    private void setLockMode(LockMode mode, String password, String msg) {
        lv_lock.setMode(mode);
        lv_lock.setErrorNumber(3);
        if (mode != SETTING_PASSWORD) {
            tv_hint.setText("请输入已经设置过的密码");
            lv_lock.setOldPassword(password);
        } else {
            tv_hint.setText("请输入要设置的密码");
        }
        tv_text.setText(msg);
    }
}
