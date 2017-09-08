package com.lenovo.service.basicpubliclibrary.UserGuideView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lenovo.service.basicpubliclibrary.R;


public class UserGuideActivity extends AppCompatActivity {

    private UserGuideView guideView;
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide);
        guideView = (UserGuideView) findViewById(R.id.guideView);
//        icon = (ImageView) findViewById(R.id.icon);
//        guideView.setHighLightView(icon);
        icon = (ImageView) findViewById(R.id.icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guideView.setHighLightView(icon);
            }
        });
    }


}
