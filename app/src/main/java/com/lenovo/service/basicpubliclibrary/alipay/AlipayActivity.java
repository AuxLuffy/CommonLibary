package com.lenovo.service.basicpubliclibrary.alipay;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import com.lenovo.service.basicpubliclibrary.R;

import java.util.HashMap;
import java.util.Map;

public class AlipayActivity extends AppCompatActivity {

    private static final int SDK_PAY_FLAG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);
        findViewById(R.id.tvPay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info = "服务器生成的提交信息";
                payV2(info);
            }
        });
    }




    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    HashMap map = (HashMap<String, String>) msg.obj;
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    Toast.makeText(AlipayActivity.this,map.toString(),Toast.LENGTH_SHORT).show();

                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * 支付宝支付业务
     */
    public void payV2(final String info) {
        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(AlipayActivity.this);
                Map<String, String> result = alipay.payV2(info, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


}
