package com.lenovo.service.basicpubliclibrary.psdinput;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;

public class PayPsdViewActivity extends AppCompatActivity {
    private PayPsdInputView passwordInputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_psd_view);

        passwordInputView = (PayPsdInputView) findViewById(R.id.password);

        passwordInputView.setComparePassword("123456", new PayPsdInputView.onPasswordListener() {
            @Override
            public void onDifference() {
                // TODO: 2017/5/7   和上次输入的密码不一致  做相应的业务逻辑处理
                Toast.makeText(PayPsdViewActivity.this, "两次密码输入不同", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEqual(String psd) {
                // TODO: 2017/5/7 两次输入密码相同，那就去进行支付楼
                Toast.makeText(PayPsdViewActivity.this, "密码相同" + psd, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
