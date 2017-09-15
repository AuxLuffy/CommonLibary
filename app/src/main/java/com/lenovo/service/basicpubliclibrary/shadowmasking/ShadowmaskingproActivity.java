package com.lenovo.service.basicpubliclibrary.shadowmasking;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.shadowmasking.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/9/15.
 */

public class ShadowmaskingproActivity extends Activity implements View.OnClickListener {


    @BindView(R.id.ll_old)
    LinearLayout mLlOld;
    @BindView(R.id.ll_new)
    LinearLayout mLlNew;
    @BindView(R.id.iv_prompt)
    ImageView mIvPrompt;
    @BindView(R.id.rl_new_all)
    RelativeLayout mRlNewAll;
    @BindView(R.id.bt_close)
    Button mBtclose;
    private int[] location = new int[2];
    private Rect rect = new Rect();
    private float xLocation;
    private float yLocation;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadowmaskingpro);
        ButterKnife.bind(this);
        measureGuideLocation();
        init_listener();
    }



    private void init_listener() {
        mBtclose.setOnClickListener(this);
    }


    private void measureGuideLocation() {
        mLlOld.post(new Runnable() {
            @Override
            public void run() {
                mLlOld.getLocationOnScreen(location);
                rect.left = mLlOld.getLeft();
                rect.top = mLlOld.getTop();
                rect.right = mLlOld.getRight();
                rect.bottom = mLlOld.getBottom();
                xLocation = location[0];
                yLocation = location[1];
                System.out.println("hxy:" + "xLocation:" + xLocation + "yLocation:" + yLocation);
                System.out.println("hxy:" + "rect.left:" + rect.left + "rect.top:" + rect.top + "rect.right" + rect.right + "rect.bottom" + rect.bottom);
                //更新控件的位置，放在UI线程中做：
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setGuideLocation();
                    }
                });
            }
        });
    }


    private void setGuideLocation() {
        int statusBarHeight = ScreenUtils.getstatusBarHeight(getApplication());
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mRlNewAll.getLayoutParams();
        layoutParams.setMargins((int) xLocation - rect.top, (int) yLocation - statusBarHeight - rect.top, 0, 0);
        //layoutParams.setMargins((int)xLocation,(int)yLocation-statusBarHeight,0,0);
        mRlNewAll.setLayoutParams(layoutParams);
        mRlNewAll.setPadding(rect.top, rect.top, 0, rect.top);
        mRlNewAll.setVisibility(View.VISIBLE);
        mLlNew.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.iv_prompt)
    public void onViewClicked() {
        mRlNewAll.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt_close:
                ShadowmaskingproActivity.this.finish();
                break;
        }
    }




    //        //注意：此处需要开辟线程，延迟1s左右来做，以防测量不到坐标s
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                mLlOld.getLocationOnScreen(location);
//                rect.left = mLlOld.getLeft();
//                rect.top = mLlOld.getTop();
//                rect.right = mLlOld.getRight();
//                rect.bottom = mLlOld.getBottom();
//                xLocation = location[0];
//                yLocation = location[1];
//                System.out.println("hxy:" + "xLocation:" + xLocation + "yLocation:" + yLocation);
//                System.out.println("hxy:" + "rect.left:" + rect.left + "rect.top:" + rect.top + "rect.right" + rect.right + "rect.bottom" + rect.bottom);
//                //更新控件的位置，放在UI线程中做：
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        setGuideLocation();
//                    }
//                });
//            }
//        }).start();
}
