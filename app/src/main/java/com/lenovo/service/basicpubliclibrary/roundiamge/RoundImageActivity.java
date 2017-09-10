package com.lenovo.service.basicpubliclibrary.roundiamge;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.lenovo.service.basicpubliclibrary.R;
import com.wx.ovalimageview.RoundImageView;

public class RoundImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_image);

        // imageview1 用xml实现
        RoundImageView imageView1 = (RoundImageView) findViewById(R.id.round_imageview1);
//        imageView1.setImageResource(R.mipmap.ic_launcher, Color.BLACK, 5, true);

        RoundImageView imageView2 = (RoundImageView) findViewById(R.id.round_imageview2);
        imageView2.setImageResource(R.drawable.image, Color.RED, 10, true);

        RoundImageView imageView3 = (RoundImageView) findViewById(R.id.round_imageview3);
        imageView3.setImageResource(R.drawable.image, Color.parseColor("#ff2a99ff"), 20, false);

        RoundImageView imageView4 = (RoundImageView) findViewById(R.id.round_imageview4);
        imageView4.setImageResource(R.drawable.image, Color.parseColor("#ff2a99ff"), 20, true);

    }
}
