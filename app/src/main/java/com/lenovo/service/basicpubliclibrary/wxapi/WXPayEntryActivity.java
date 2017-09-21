package com.lenovo.service.basicpubliclibrary.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lenovo.service.basicpubliclibrary.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 支付结果的回调类
 * 注意:此类必须放在项目下的wxapi包中
 * Created by tangrenmei on 2017/9/21.
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    private IWXAPI api;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);//这个类中的布局是可以自定义的，如果你不需要展示什么布局，而是要跳转页面，把这段代码删除即可
        api = WXAPIFactory.createWXAPI(this, "APP_ID");//自己注册的微信APP_ID
        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    /**
     * 支付结果的回调,其根据自己具体需求实现
     * @param resp
     */
    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {//支付成功

                //去后台查询订单,支付成功的结果以后台返回的结果为标准,具体逻辑根据实际需求自己实现

            } else if (resp.errCode == -1) {
                //支付错误的逻辑处理

            } else if (resp.errCode == -2) {
                //用户取消支付的逻辑处理

            } else {
                //其他异常的逻辑处理
            }
        }
    }





}
