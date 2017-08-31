package com.lenovo.service.basicpubliclibrary.AppStartGuide;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.lenovo.service.basicpubliclibrary.AppStartGuide.utils.HandlerUtil;
import com.lenovo.service.basicpubliclibrary.AppStartGuide.widget.SplashScreen;
import com.lenovo.service.basicpubliclibrary.R;

/**
 * 引导页图片，停留若干秒，然后自动消失。
 * @Author 李巷阳
 * Created at 2017/7/28 17:59
 */
public class AppStartGuide extends Activity implements View.OnClickListener {

    private SplashScreen splashScreen;

    private Button mBt_splash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appstartguide);
        init_view();
        init_listener();
    }


    private void init_view() {
        mBt_splash = (Button) findViewById(R.id.bt_splash);
    }

    private void init_listener() {
        mBt_splash.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.bt_splash:
                splashScreen = new SplashScreen(this);
                splashScreen.show(R.drawable.art_login_bg, SplashScreen.SLIDE_UP);
                HandlerUtil.getInstance(this).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        splashScreen.removeSplashScreen();
                    }
                }, 2000);

                break;
        }
    }
}
