package com.lenovo.service.basicpubliclibrary.verticaltextview;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.lenovo.service.basicpubliclibrary.R;


/**
 * Created by chongyangyang on 2017/9/14.
 */

public class VerticalTextViewActivity extends Activity {


    private EditText mEditText;
    private VerticalTextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_text_view);
        tv = findViewById(R.id.verticalTextView);
        mEditText = findViewById(R.id.verticalTextView_editText);
        initVerticalTextView();
    }

    private void initVerticalTextView() {
        final String defaultText = String.format("%s 实现对文字的垂直排版。并且对非 CJK (中文、日文、韩文)字符做90度旋转排版。可以在下方的输入框中输入文字，体验不同文字垂直排版的效果。",
                VerticalTextView.class.getSimpleName());
        tv.setText(defaultText);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv.setText(TextUtils.isEmpty(s) ? defaultText : s);
            }
        });
    }
}
