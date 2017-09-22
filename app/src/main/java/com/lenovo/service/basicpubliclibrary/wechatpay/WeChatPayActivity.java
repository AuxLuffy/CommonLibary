package com.lenovo.service.basicpubliclibrary.wechatpay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.utils.LogUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * 微信支付
 * Created by tangrenmei on 2017/9/21.
 */

public class WeChatPayActivity  extends Activity {
    private IWXAPI wxapi;
    private String appId;
    private String partnerId;
    private String prepayId;
    private String packageX;
    private String noncestr;
    private int timestamp;
    private String paysign;
    private String ordernum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechatpay);

        //通过WXAPIFactory工厂,获取IWXAPI实例
        wxapi = WXAPIFactory.createWXAPI(this, "wxb4ba3c02aa476ea1");
    }

    /**
     * 去支付按钮
     * @param view
     */
    public void toPay(View view) {
        Toast.makeText(WeChatPayActivity.this, "代码已集成微信支付,根据自己的需求具体实现,注释写的很清楚", Toast.LENGTH_SHORT).show();

        //请求后台获取订单的接口得到以下字段----->自己去实现
        //应用ID
        appId = "appId";
        // 商户号
        partnerId = "partnerId";
        // 预支付交易会话ID
        prepayId = "prepayId";
        // 拓展字段
        packageX = "packageX";
        // 随机字符串
        noncestr = "noncestr";
        // 时间戳
        timestamp = 11;
        //签名
        paysign = "paysign";
        //订单号
        ordernum = "ordernum";

        //调用微信支付
        genPayReq(appId, partnerId, prepayId, packageX, noncestr, timestamp, paysign, ordernum);
    }

    /**
     * 调用微信支付
     * @param appId
     * @param partnerId
     * @param prepayId
     * @param packageX
     * @param noncestr
     * @param timestamp
     * @param paysign
     * @param ordernum
     */
    private void genPayReq(String appId, String partnerId, String prepayId, String packageX, String noncestr, int timestamp, String paysign, String ordernum) {
        PayReq req = new PayReq();
        req.appId = appId;//应用ID
        req.partnerId = partnerId;// 商户号
        req.prepayId = prepayId;// 预支付交易会话ID
        req.packageValue = packageX;// 拓展字段
        req.nonceStr = noncestr;// 随机字符串
        req.timeStamp = String.valueOf(timestamp);// 时间戳
        req.sign = paysign;//签名
        sendPayReq(req);
    }

    private void sendPayReq(PayReq req) {
        boolean isWXAppInstalled = wxapi.isWXAppInstalled();
        //已经安装微信
        if (isWXAppInstalled) {
            LogUtil.e("已经安装微信");
            //将该app注册到微信
            wxapi.registerApp("APP_ID");// 自己注册得到的微信APP_ID
            wxapi.sendReq(req);
        }
        //未安装微信
        else {
            LogUtil.e("未安装微信");

        }

    }


}
