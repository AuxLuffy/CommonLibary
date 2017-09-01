package com.lenovo.service.basicpubliclibrary.loaddata;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;


public class Sample2Activity extends AppCompatActivity {

    private SwipeLoadDataLayout swipeLoadDataLayout;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sample2);

        swipeLoadDataLayout = (SwipeLoadDataLayout) findViewById(R.id.swipeLoadDataLayout);
        swipeLoadDataLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeLoadDataLayout.setOnReloadListener(new SwipeLoadDataLayout.OnReloadListener() {
            @Override
            public void onReload(View v, int status) {
                Toast.makeText(Sample2Activity.this,R.string.reload_text, Toast.LENGTH_SHORT).show();
            }
        });
        swipeLoadDataLayout.setStatus(SwipeLoadDataLayout.LOADING);
        initHandler();
    }

    private void initHandler() {
        if(mHandler==null){
            mHandler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0:
                            swipeLoadDataLayout.setStatus(SwipeLoadDataLayout.EMPTY);
                            mHandler.sendEmptyMessageDelayed(1,2000);
                            break;
                        case 1:
                            swipeLoadDataLayout.setStatus(SwipeLoadDataLayout.ERROR);
                            mHandler.sendEmptyMessageDelayed(2,2000);
                            break;
                        case 2:
                            swipeLoadDataLayout.setStatus(SwipeLoadDataLayout.NO_NETWORK);
                            mHandler.sendEmptyMessageDelayed(3,2000);
                            break;
                        case 3:
                            swipeLoadDataLayout.setStatus(SwipeLoadDataLayout.SUCCESS);
                            break;
                    }
                }
            };

            mHandler.sendEmptyMessageDelayed(0,2000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
            mHandler=null;
        }
    }
}
