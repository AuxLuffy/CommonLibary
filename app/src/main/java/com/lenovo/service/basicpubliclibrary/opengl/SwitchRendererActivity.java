package com.lenovo.service.basicpubliclibrary.opengl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lenovo.service.basicpubliclibrary.R;

public class SwitchRendererActivity extends AppCompatActivity implements View.OnClickListener{

    private Button tv_renderer1;
    private Button tv_renderer2;
    private Button tv_renderer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_renderer);

        tv_renderer1 = (Button) findViewById(R.id.tv_renderer1);
        tv_renderer2 = (Button) findViewById(R.id.tv_renderer2);
        tv_renderer1.setOnClickListener(this);
        tv_renderer2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_renderer1:
                Intent intent = new Intent(this,OpenGLActivity.class);
                intent.putExtra("flag",1);
                startActivity(intent);
                break;
            case R.id.tv_renderer2:
                Intent intent2 = new Intent(this,OpenGLActivity.class);
                intent2.putExtra("flag",2);
                startActivity(intent2);
                break;
        }
    }
}
