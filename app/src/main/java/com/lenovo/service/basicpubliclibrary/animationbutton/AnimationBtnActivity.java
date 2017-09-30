package com.lenovo.service.basicpubliclibrary.animationbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;

public class AnimationBtnActivity extends AppCompatActivity {

    private AnimationButton animationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_btn);
        animationButton = (AnimationButton) findViewById(R.id.animation_btn);
        animationButton.setAnimationButtonListener(new AnimationButton.AnimationButtonListener() {
            @Override
            public void onClickListener() {
                animationButton.start();
            }

            @Override
            public void animationFinish() {
                Toast.makeText(AnimationBtnActivity.this,"动画执行完毕", Toast.LENGTH_SHORT).show();
//                finish();
            }
        });
    }
}
