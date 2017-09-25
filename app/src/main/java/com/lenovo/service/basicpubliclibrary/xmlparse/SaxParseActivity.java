package com.lenovo.service.basicpubliclibrary.xmlparse;

import android.util.Log;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.request.ui.BaseActivity;

import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cx on 2017/9/25.
 */

public class SaxParseActivity extends BaseActivity {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.texttitle)
    TextView texttitle;

    @Override
    protected int bindLayout() {
        return R.layout.activity_xml_parse;
    }

    @Override
    protected void setWidget() {
        texttitle.setText("SAX解析演示");
    }

    @OnClick(R.id.parse)
    public void onClick() {
        try {
            InputStream is = getAssets().open("books.xml");
            SaxBookParser parser = new SaxBookParser();
            List<Book> books = parser.parse(is);
            for (Book book : books) {
                text.setText(text.getText().toString() + book.toString());
                Log.i("book", book.toString());
            }
        } catch (Exception e) {
            Log.e("book", e.getMessage());
        }
    }
}
