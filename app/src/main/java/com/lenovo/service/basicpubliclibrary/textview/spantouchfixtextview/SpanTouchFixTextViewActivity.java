package com.lenovo.service.basicpubliclibrary.textview.spantouchfixtextview;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;


/**
 * Created by chongyangyang on 2017/9/22.
 */

public class SpanTouchFixTextViewActivity extends AppCompatActivity {


    private TextView mSystemTv1;
    private TextView mSystemTv2;
    private SpanTouchFixTextView mSpanTouchFixTextView1;
    private SpanTouchFixTextView mSpanTouchFixTextView2;


    private int highlightTextNormalColor;
    private int highlightTextPressedColor;
    private int highlightBgNormalColor;
    private int highlightBgPressedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        highlightTextNormalColor = ContextCompat.getColor(this, R.color.color_13);
        highlightTextPressedColor = ContextCompat.getColor(this, R.color.colorPrimary);
        highlightBgNormalColor = ResHelper.getAttrColor(this, R.attr.qmui_config_color_gray_2);
        highlightBgPressedColor = ResHelper.getAttrColor(this, R.attr.qmui_config_color_gray_1);

        setContentView(R.layout.activity_touch_span_fix_layout);
        mSystemTv1 = (TextView) findViewById(R.id.sysytem_tv_1);
        mSystemTv2 = (TextView) findViewById(R.id.sysytem_tv_2);
        mSpanTouchFixTextView1 = (SpanTouchFixTextView) findViewById(R.id.touch_fix_tv_1);
        mSpanTouchFixTextView2 = (SpanTouchFixTextView) findViewById(R.id.touch_fix_tv_2);


        // 场景一
        mSystemTv1.setMovementMethod(LinkMovementMethod.getInstance());
        mSystemTv1.setText(generateSp(getResources().getString(R.string.system_behavior_1)));

        mSpanTouchFixTextView1.setMovementMethodDefault();
        mSpanTouchFixTextView1.setText(generateSp(getResources().getString(R.string.span_touch_fix_1)));

        // 场景二
        mSystemTv2.setMovementMethod(LinkMovementMethod.getInstance());
        mSystemTv2.setText(generateSp(getResources().getString(R.string.system_behavior_2)));

        mSpanTouchFixTextView2.setMovementMethodDefault();
        mSpanTouchFixTextView2.setNeedForceEventToParent(true);
        mSpanTouchFixTextView2.setText(generateSp(getResources().getString(R.string.span_touch_fix_2)));
    }

    private SpannableString generateSp(String text) {
        String highlight1 = "@qmui";
        String highlight2 = "#qmui#";
        SpannableString sp = new SpannableString(text);
        int start = 0, end;
        int index;
        while ((index = text.indexOf(highlight1, start)) > -1) {
            end = index + highlight1.length();
            sp.setSpan(new TouchableSpan(highlightTextNormalColor, highlightTextPressedColor,
                    highlightBgNormalColor, highlightBgPressedColor) {
                @Override
                public void onSpanClick(View widget) {
                    Toast.makeText(SpanTouchFixTextViewActivity.this, "click @qmui", Toast.LENGTH_SHORT).show();
                }
            }, index, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            start = end;
        }

        start = 0;
        while ((index = text.indexOf(highlight2, start)) > -1) {
            end = index + highlight2.length();
            sp.setSpan(new TouchableSpan(highlightTextNormalColor, highlightTextPressedColor,
                    highlightBgNormalColor, highlightBgPressedColor) {
                @Override
                public void onSpanClick(View widget) {
                    Toast.makeText(SpanTouchFixTextViewActivity.this, "click #qmui#", Toast.LENGTH_SHORT).show();
                }
            }, index, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            start = end;
        }
        return sp;
    }


}
