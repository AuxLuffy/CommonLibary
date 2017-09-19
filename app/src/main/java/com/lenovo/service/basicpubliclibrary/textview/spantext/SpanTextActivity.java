package com.lenovo.service.basicpubliclibrary.textview.spantext;

import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.App;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.dialog.util.DisplayUtil;
import com.lenovo.service.basicpubliclibrary.textview.spantext.util.QMUIAlignMiddleImageSpan;
import com.lenovo.service.basicpubliclibrary.textview.spantext.util.QMUIBlockSpaceSpan;
import com.lenovo.service.basicpubliclibrary.textview.spantext.util.QMUICustomTypefaceSpan;
import com.lenovo.service.basicpubliclibrary.textview.spantext.util.QMUIDrawableHelper;
import com.lenovo.service.basicpubliclibrary.textview.spantext.util.QMUIMarginImageSpan;

import butterknife.BindView;


/**
 * Created by chongyangyang on 2017/9/14.
 */

public class SpanTextActivity extends AppCompatActivity {

    /**
     * 特殊字体 人民币符号
     */
    public static Typeface TYPEFACE_RMB;

    static {
        try {
            Typeface tmpRmb = Typeface.createFromAsset(App.getContext().getAssets(),
                    "fonts/iconfont.ttf");
            TYPEFACE_RMB = Typeface.create(tmpRmb, Typeface.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TextView mAlignMiddleTextView;
    private TextView mMarginImageTextView;
    private TextView mBlockSpaceTextView;
    private TextView mCustomTypefaceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spanhelper);
        mAlignMiddleTextView = (TextView) findViewById(R.id.alignMiddle);
        mMarginImageTextView = (TextView) findViewById(R.id.marginImage);
        mBlockSpaceTextView = (TextView) findViewById(R.id.blockSpace);
        mCustomTypefaceTextView = (TextView) findViewById(R.id.customTypeface);
        initContentView();
    }

    private void initContentView() {
        // 支持垂直居中的 ImageSpan
        int alignMiddleIconLength = DisplayUtil.dp2px(SpanTextActivity.this, 20);
        final float spanWidthCharacterCount = 2f;
        SpannableString spannable = new SpannableString("[icon]" + "这是一行示例文字，前面的 Span 设置了和文字垂直居中并占 " + spanWidthCharacterCount + " 个中文字的宽度");
        Drawable iconDrawable = QMUIDrawableHelper.createDrawableWithSize(getResources(), alignMiddleIconLength, alignMiddleIconLength, DisplayUtil.dp2px(SpanTextActivity.this, 4), ContextCompat.getColor(SpanTextActivity.this, R.color.app_color_theme_3));
        if (iconDrawable != null) {
            iconDrawable.setBounds(0, 0, iconDrawable.getIntrinsicWidth(), iconDrawable.getIntrinsicHeight());
        }
        ImageSpan alignMiddleImageSpan = new QMUIAlignMiddleImageSpan(iconDrawable, QMUIAlignMiddleImageSpan.ALIGN_MIDDLE, spanWidthCharacterCount);
        spannable.setSpan(alignMiddleImageSpan, 0, "[icon]".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mAlignMiddleTextView.setText(spannable);

        // 支持增加左右间距的 ImageSpan
        int marginImageLength = DisplayUtil.dp2px(SpanTextActivity.this, 20);
        Drawable marginIcon = QMUIDrawableHelper.createDrawableWithSize(getResources(), marginImageLength, marginImageLength, DisplayUtil.dp2px(SpanTextActivity.this, 4), ContextCompat.getColor(SpanTextActivity.this, R.color.app_color_theme_5));
        marginIcon.setBounds(0, 0, marginIcon.getIntrinsicWidth(), marginIcon.getIntrinsicHeight());
        CharSequence marginImageTextOne = "左侧内容";
        SpannableString marginImageText = new SpannableString(marginImageTextOne + "[margin]右侧内容");
        QMUIMarginImageSpan marginImageSpan = new QMUIMarginImageSpan(marginIcon, QMUIAlignMiddleImageSpan.ALIGN_MIDDLE, DisplayUtil.dp2px(SpanTextActivity.this, 10), DisplayUtil.dp2px(SpanTextActivity.this, 10));
        marginImageText.setSpan(marginImageSpan, marginImageTextOne.length(), marginImageTextOne.length() + "[margin]".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mMarginImageTextView.setText(marginImageText);

        // 整行的空白 Span，可用来用于制作段间距
        String paragraphFirst = "这是第一段比较长的段落，演示在段落之间插入段落间距。\n";
        String paragraphSecond = "这是第二段比较长的段落，演示在段落之间插入段落间距。";
        String spaceString = "[space]";
        SpannableString paragraphText = new SpannableString(paragraphFirst + spaceString + paragraphSecond);
        QMUIBlockSpaceSpan blockSpaceSpan = new QMUIBlockSpaceSpan(DisplayUtil.dp2px(SpanTextActivity.this, 6));
        paragraphText.setSpan(blockSpaceSpan, paragraphFirst.length(), paragraphFirst.length() + spaceString.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mBlockSpaceTextView.setText(paragraphText);

        // 自定义部分文字的字体
        SpannableString customTypefaceText = new SpannableString(getResources().getString(R.string.spanUtils_rmb) + "100， 前面的人民币符号使用自定义字体特殊处理，对比这个普通的人民币符号: " + getResources().getString(R.string.spanUtils_rmb));
        customTypefaceText.setSpan(new QMUICustomTypefaceSpan("", TYPEFACE_RMB), 0, getString(R.string.spanUtils_rmb).length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        mCustomTypefaceTextView.setText(customTypefaceText);
    }
}
