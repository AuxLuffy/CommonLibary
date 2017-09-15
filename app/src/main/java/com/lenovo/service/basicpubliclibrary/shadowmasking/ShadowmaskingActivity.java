package com.lenovo.service.basicpubliclibrary.shadowmasking;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lenovo.service.basicpubliclibrary.R;

/**
 * Created by lenovo on 2017/9/15.
 */

public class ShadowmaskingActivity extends Activity implements View.OnClickListener {

    private RelativeLayout rl_guide;//整个指引布局
    private LinearLayout ll_guide;//指引图片父布局
    private ImageView iv_imageGuide;//指引图片
    private ImageView iv_sendMs;//参考位置

    private int statusBarHeight;
    private int[] location = new int[2];
    private Rect rect = new Rect();
    private float xLocation;
    private float yLocation;
    private Button mOpen;
    private Activity mActivity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadowmasking);
        initView();
        getstatusBarHeight();
        measureGuideLocation();
        initListener();
    }

    private void initView() {
        mActivity=this;
        iv_sendMs = (ImageView) findViewById(R.id.iv_sendMs);
        rl_guide = (RelativeLayout) findViewById(R.id.rl_guide);
        rl_guide.setVisibility(View.GONE);
        ll_guide = (LinearLayout) findViewById(R.id.ll_guide);
        iv_imageGuide = (ImageView) findViewById(R.id.iv_imageGuide);
        mOpen = (Button) findViewById(R.id.bt_open);
        mOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mActivity,ShadowmaskingproActivity.class);
                startActivity(intent);
            }
        });
    }

    /**测量通知栏高度*/
    private void getstatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            System.out.println("hxy:"+"statusBarHeight"+statusBarHeight);
        }
    }

    private void measureGuideLocation() {
        //注意：此处需要开辟线程，延迟1s左右来做，以防测量不到坐标
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                iv_sendMs.getLocationOnScreen(location);
                rect.left = iv_sendMs.getLeft();
                rect.top = iv_sendMs.getTop();
                rect.right = iv_sendMs.getRight();
                rect.bottom = iv_sendMs.getBottom();
                xLocation = location[0];
                yLocation = location[1];
                System.out.println("hxy:"+"xLocation:"+xLocation+"yLocation:"+yLocation);
                System.out.println("hxy:"+"rect.left:"+rect.left+"rect.top:"+rect.top+"rect.right"+rect.right+"rect.bottom"+rect.bottom);
                //更新控件的位置，放在UI线程中做：
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setGuideLocation();
                    }
                });
            }
        }).start();
    }

    private void initListener() {
        iv_imageGuide.setOnClickListener(this);
        rl_guide.setOnClickListener(this);
    }


    private void setGuideLocation() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ll_guide.getLayoutParams();
        layoutParams.setMargins((int)xLocation-rect.top,(int)yLocation-statusBarHeight-rect.top,0,0);
        //layoutParams.setMargins((int)xLocation,(int)yLocation-statusBarHeight,0,0);
        ll_guide.setLayoutParams(layoutParams);
        ll_guide.setPadding(rect.top,rect.top,rect.top,rect.top);
        rl_guide.setVisibility(View.VISIBLE);
        ll_guide.setVisibility(View.VISIBLE);
        iv_imageGuide.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_imageGuide:
            case R.id.rl_guide:
                rl_guide.setVisibility(View.GONE);
                break;
        }
    }


}
