package com.lenovo.service.basicpubliclibrary.smilepraiseview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.smilepraiseview.customview.SmileView;

/**
 * Created by tangrenmei on 2017/8/25.
 */

public class SmilePraiseActivity extends AppCompatActivity {


    LinearLayout backGround;
    ImageView smileFace;

    SmileView smileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smilepraise);

        backGround = (LinearLayout) findViewById(R.id.backGround);
        smileFace = (ImageView) findViewById(R.id.smileFace);
        smileView = (SmileView) findViewById(R.id.smileView);
        smileView.setNum(60,40);
    }


}
