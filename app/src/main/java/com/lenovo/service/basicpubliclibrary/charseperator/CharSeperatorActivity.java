package com.lenovo.service.basicpubliclibrary.charseperator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.aip.nlp.AipNlp;
import com.lenovo.service.basicpubliclibrary.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CharSeperatorActivity extends AppCompatActivity {
    private EditText mEditOri;
    private TextView mTxtResul;
    private Button mBtnSeperate;
    private Button mBtnClear;
    public static final String APP_ID = "10215571";
    public static final String API_KEY = "GoKMeNItn5NiQGm8EL5jiHfr";
    public static final String SECRET_KEY = "wfPHXhUF5zqhXIvO6YMhFG84w5r6zhUe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_seperator);
        mEditOri = (EditText) findViewById(R.id.editOrignalText);
        mTxtResul = (TextView) findViewById(R.id.textResult);
        mBtnClear = (Button) findViewById(R.id.btnClear);
        mBtnSeperate = (Button) findViewById(R.id.btnSeperate);
        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditOri.setText("");
                mTxtResul.setText("");
            }
        });
        mBtnSeperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String source = mEditOri.getText().toString();
                if (!TextUtils.isEmpty(source)) {
                    getSeperate(source, myListener);
                } else {
                    Toast.makeText(CharSeperatorActivity.this, "内容为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getSeperate(final String sourceStr, final AILinstener linstener) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
                client.setConnectionTimeoutInMillis(2000);
                client.setSocketTimeoutInMillis(60000);
                JSONObject object = client.lexer(sourceStr);
                linstener.onResult(object);
            }
        };
        thread.start();
    }

    private interface AILinstener {
        void onResult(JSONObject object);
    }

    private AILinstener myListener = new AILinstener() {
        @Override
        public void onResult(JSONObject object) {
            try {
                String rp = "";
                JSONArray array = object.getJSONArray("items");
                for (int i = 0; i < array.length(); i++) {
                   JSONObject jo = array.getJSONObject(i);
                    rp += "  "+jo.getString("item");
                }
                final String result = rp;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTxtResul.setText(result);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
