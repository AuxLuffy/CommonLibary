package com.lenovo.service.basicpubliclibrary.wowoviewpagerexample.gif;

import android.os.Bundle;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.wowoviewpagerexample.WoWoActivity;
import com.nightonke.wowoviewpager.Animation.WoWoInterfaceAnimation;
import com.nightonke.wowoviewpager.gif.WoWoGifView;

public class GIFActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_gif;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WoWoGifView gifView0 = (WoWoGifView) findViewById(R.id.gif_0);
        WoWoGifView gifView1 = (WoWoGifView) findViewById(R.id.gif_1);
        WoWoGifView gifView2 = (WoWoGifView) findViewById(R.id.gif_2);
        WoWoGifView gifView3 = (WoWoGifView) findViewById(R.id.gif_3);

        wowo.addAnimation(gifView0)
                .add(WoWoInterfaceAnimation.builder().page(0).implementedBy(gifView0).build());

        wowo.addAnimation(gifView1)
                .add(WoWoInterfaceAnimation.builder().page(1).implementedBy(gifView1).build());

        wowo.addAnimation(gifView2)
                .add(WoWoInterfaceAnimation.builder().page(2).implementedBy(gifView2).build());

        wowo.addAnimation(gifView3)
                .add(WoWoInterfaceAnimation.builder().page(3).implementedBy(gifView3).build());

        wowo.ready();
    }
}
