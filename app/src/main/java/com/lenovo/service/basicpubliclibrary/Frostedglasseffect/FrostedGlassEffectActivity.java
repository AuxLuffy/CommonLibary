package com.lenovo.service.basicpubliclibrary.Frostedglasseffect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.Frostedglasseffect.util.BlurBehind;
import com.lenovo.service.basicpubliclibrary.R;



/**
 * Created by chongyangyang on 2017/9/12.
 */

public class FrostedGlassEffectActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frosted_glass_effect);
//        requestCodeQrcodePermissions();
        BlurBehind.getInstance()//在你需要添加模糊或者透明的背景中只需要设置这几行简单的代码就可以了
                .withAlpha(50)
                .withFilterColor(Color.parseColor("#7F7F7F"))
                .setBackground(this);
    }
}
