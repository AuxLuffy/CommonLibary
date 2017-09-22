package com.lenovo.service.basicpubliclibrary.marqueue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;

import java.util.ArrayList;
import java.util.List;

public class MarqueueActivity extends AppCompatActivity {

    private MarqueeView marqueeView;
    private MarqueeView marqueeView1;
    private MarqueeView marqueeView2;
    private MarqueeView marqueeView3;
    private MarqueeView marqueeView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marqueue);
        marqueeView = (MarqueeView) findViewById(R.id.marqueeView);
        marqueeView1 = (MarqueeView) findViewById(R.id.marqueeView1);
        marqueeView2 = (MarqueeView) findViewById(R.id.marqueeView2);
        marqueeView3 = (MarqueeView) findViewById(R.id.marqueeView3);
        marqueeView4 = (MarqueeView) findViewById(R.id.marqueeView4);

        List<CharSequence> list = new ArrayList<>();
        SpannableString ss1 = new SpannableString("1、OSD公共基础库");
//        ss1.setSpan(new ForegroundColorSpan(Color.RED), 2, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss1);
        SpannableString ss2 = new SpannableString("2、安卓研发");
//        ss2.setSpan(new ForegroundColorSpan(Color.GREEN), 9, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss2);
        SpannableString ss3 = new SpannableString("3、垂直跑马灯");
//        ss3.setSpan(new URLSpan("http://sunfusheng.com/"), 7, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss3);
        list.add("4、发布公告");

        marqueeView.startWithList(list);
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(MarqueueActivity.this, textView.getText() + "", Toast.LENGTH_SHORT).show();
            }
        });

        marqueeView1.startWithText(getString(R.string.marquee_texts), R.anim.anim_top_in, R.anim.anim_bottom_out);
        marqueeView1.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(MarqueueActivity.this, String.valueOf(marqueeView1.getPosition()) + ". " + textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        marqueeView2.startWithText(getString(R.string.marquee_text));

        marqueeView3.startWithText(getString(R.string.marquee_texts));

        marqueeView4.startWithText(getString(R.string.marquee_texts));
    }
}
