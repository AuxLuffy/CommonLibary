package com.lenovo.service.basicpubliclibrary.xmlparse;

import android.util.Log;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.request.ui.BaseActivity;

import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class XmlParseActivity extends BaseActivity {

    @BindView(R.id.text)
    TextView text;

    @Override
    protected int bindLayout() {
        return R.layout.activity_xml_parse;
    }

    @Override
    protected void setWidget() {
//        Button readBtn = (Button) findViewById(R.id.readBtn);
//        Button writeBtn = (Button) findViewById(R.id.writeBtn);
//
//        readBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    InputStream is = getAssets().open("books.xml");
//                    parser = new SaxBookParser();  //创建SaxBookParser实例
//                    books = parser.parse(is);  //解析输入流
//                    for (Book book : books) {
//                        Log.i(TAG, book.toString());
//                    }
//                } catch (Exception e) {
//                    Log.e(TAG, e.getMessage());
//                }
//            }
//        });
//        writeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    String xml = parser.serialize(books);  //序列化
//                    FileOutputStream fos = openFileOutput("books.xml", Context.MODE_PRIVATE);
//                    fos.write(xml.getBytes("UTF-8"));
//                } catch (Exception e) {
//                    Log.e(TAG, e.getMessage());
//                }
//            }
//        });
    }

    @OnClick(R.id.parse)
    public void onClick() {
        try {
            InputStream is = getAssets().open("books.xml");
//            SaxBookParser parser = new SaxBookParser();
//            DomParseService parser = new DomParseService();
            PullParser parser = new PullParser();
            List<Book> books = parser.parse(is);
            for (Book book : books) {
                text.setText(text.getText().toString()+book.toString());
                Log.i("book", book.toString());
            }
        } catch (Exception e) {
            Log.e("book", e.getMessage());
        }
    }
}
