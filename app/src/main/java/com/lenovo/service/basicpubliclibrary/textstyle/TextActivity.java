package com.lenovo.service.basicpubliclibrary.textstyle;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.Frostedglasseffect.util.BlurBehind;
import com.lenovo.service.basicpubliclibrary.R;


/**
 * Created by chongyangyang on 2017/9/14.
 */

public class TextActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_style);
        TextView tv = (TextView) findViewById(R.id.tv);
        String html = "<html><head><title>TextView使用HTML</title></head><body><p><strong>强调</strong></p><p><em>斜体</em></p>"
                + "<p><font color='#ff0000'><big>文字大小</big></font>变化</p><p><font color=\"#aabb00\">颜色1"
                + "</p><p><font color=\"#00bbaa\">颜色2</p><h1>标题1</h1><h3>标题2</h3><h6>标题3</h6><p>大于>小于<</p></body></html>";
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
        tv.setText(Html.fromHtml(html));
    }
}
