package com.lenovo.service.basicpubliclibrary.textview.linktextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.textview.verticaltextview.VerticalTextView;


/**
 * Created by chongyangyang on 2017/9/22.
 */

public class LinkTextViewActivity extends AppCompatActivity {


    private EditText mEditText;
    private LinkTextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_texview_layout);
        tv = (LinkTextView) findViewById(R.id.link_text_view);
        tv.setOnLinkClickListener(mOnLinkClickListener);
    }


    private LinkTextView.OnLinkClickListener mOnLinkClickListener = new LinkTextView.OnLinkClickListener() {
        @Override
        public void onTelLinkClick(String phoneNumber) {
            Toast.makeText(LinkTextViewActivity.this, "识别到电话号码是：" + phoneNumber, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onMailLinkClick(String mailAddress) {
            Toast.makeText(LinkTextViewActivity.this, "识别到邮件地址是：" + mailAddress, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onWebUrlLinkClick(String url) {
            Toast.makeText(LinkTextViewActivity.this, "识别到网页链接是：" + url, Toast.LENGTH_SHORT).show();
        }
    };
}
