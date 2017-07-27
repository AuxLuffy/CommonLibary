package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.headerfloatanimation.HeaderFloatActivity;

public class AnimationActivity extends AppCompatActivity {
    TextView text1=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        text1=(TextView)findViewById(R.id.text1);
        text1.setOnClickListener(new TextViewClickListener() );
    }
    public class TextViewClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent=new Intent();
            switch (view.getId()){

                case R.id.text1:

                    intent.setClass(AnimationActivity.this,
                            HeaderFloatActivity.class);
                    startActivity(intent);
                    break;



                default:
                    break;
            }

        }
    }
}
