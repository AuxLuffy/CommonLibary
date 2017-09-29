package com.lenovo.service.basicpubliclibrary.aidls;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.aidl.AidlCallBack;
import com.lenovo.service.basicpubliclibrary.aidl.MessageX;
import com.lenovo.service.basicpubliclibrary.aidl.MessageXManager;


public class AidlActivity extends AppCompatActivity implements View.OnClickListener{
    private MessageXManager mMessageXManager = null;
    private boolean mBound = false;
    private Button btn_bind, btn_unbind;
    private TextView tv_info;
    private SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        btn_bind = (Button) findViewById(R.id.btn_bind);
        btn_unbind = (Button) findViewById(R.id.btn_unbind);
        btn_send = (Button) findViewById(R.id.btn_send);
        tv_info = (TextView) findViewById(R.id.tv_info);

        btn_bind.setOnClickListener(this);
        btn_unbind.setOnClickListener(this);
        btn_send.setOnClickListener(this);
    }

    private AidlCallBack mCallBack = new AidlCallBack.Stub() {

        @Override
        public void replyMessageX(MessageX msgx) throws RemoteException {
            showMessage("\n服务器端："+msgx.getText());
        }
    };

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessageXManager = MessageXManager.Stub.asInterface(service);
            mBound = true;
            showMessage("\n客户端：链接服务器成功");
            if(mMessageXManager != null){
                try {
                    if (mCallBack == null){
                        Log.e("AidlActivity", "mCallBack空");
                        return;
                    }
                    mMessageXManager.registerCallback(mCallBack);
                    showMessage("\n看客户端：发送一条消息");
                    mMessageXManager.sendMessageX(new MessageX("测试"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bind:
                //绑定服务并注册观察者
                if (!mBound) {
                    Intent intent = new Intent();
                    intent.setAction("com.lenovo.service.basicpubliclibrary.aidlservice.aidl.service");
                    intent.setPackage("com.lenovo.service.basicpubliclibrary.aidlservice");
                    bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
                } else {
                    showMessage("\n客户端：已经连接上啦！不用重复连接~");
                }
                break;
            case R.id.btn_unbind:
                //解除绑定并解注册观察者
                if (mBound) {
                    if (mMessageXManager != null) {
                        //解除注册
                        try {
                            mMessageXManager.unregisterCallback(mCallBack);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    if (mServiceConnection != null) {
                        unbindService(mServiceConnection);
                    }
                    showMessage("\n客户端：解除绑定成功...");
                    mBound = false;
                } else {
                    showMessage("\n客户端：尚未绑定...");
                }
                break;
            case R.id.btn_send:
                if (mBound) {
                    if(mMessageXManager != null){
                        try {
                            showMessage("\n客户端：发送一条消息");
                            mMessageXManager.sendMessageX(new MessageX("测试"));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    showMessage("\n客户端：尚未绑定...");
                }
                break;
        }
    }

    /**
     * 显示文字
     *
     * @param info 提示信息
     */
    private void showMessage(String info) {
        showMessage(info, R.color.black_deep);
    }

    /**
     * 显示文字
     *
     * @param info 提示信息
     */
    private void showMessage(String info, int color) {
        int startPos = stringBuilder.length();
        stringBuilder.append(info);
        tv_info.setText(DisplayUtil.changeTextColor(this, stringBuilder, color, startPos));
    }
}
