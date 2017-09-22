package com.lenovo.service.basicpubliclibrary.wowoviewpagerexample;

import android.os.Bundle;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.R;
import com.nightonke.wowoviewpager.Animation.ViewAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoTextViewColorAnimation;
import com.nightonke.wowoviewpager.Enum.Chameleon;

public class WoWoTextViewColorAnimationActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_wowo_textview_color_animation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addAnimations(findViewById(R.id.test1), Chameleon.RGB);
        addAnimations(findViewById(R.id.test2), Chameleon.HSV);
    }

    private void addAnimations(View view, Chameleon chameleon) {
        ViewAnimation viewAnimation = new ViewAnimation(view);
        viewAnimation.add(WoWoTextViewColorAnimation.builder().page(0)
                .from("#ff0000").to("#00ff00").chameleon(chameleon).build());
        viewAnimation.add(WoWoTextViewColorAnimation.builder().page(1)
                .from("#00ff00").to("#0000ff").chameleon(chameleon).build());
        viewAnimation.add(WoWoTextViewColorAnimation.builder().page(2)
                .from("#0000ff").to("#ff0000").chameleon(chameleon).build());
        viewAnimation.add(WoWoTextViewColorAnimation.builder().page(3).start(0).end(0.5)
                .from("#ff0000").to("#000000").chameleon(chameleon).build());
        viewAnimation.add(WoWoTextViewColorAnimation.builder().page(3).start(0.5).end(1)
                .from("#000000").to("#ff0000").chameleon(chameleon).build());

        wowo.addAnimation(viewAnimation);

        wowo.setEase(ease);
        wowo.setUseSameEaseBack(useSameEaseTypeBack);
        wowo.ready();
    }
}
