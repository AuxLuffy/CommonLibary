package com.lenovo.service.basicpubliclibrary.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;

public class OpenGLActivity extends AppCompatActivity {

    private GLSurfaceView glview;
    private PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glview = new GLSurfaceView(this);

        int flag = getIntent().getIntExtra("flag", 1);
        switch(flag){
            case 1:
                glview.setRenderer(new CubeRenderer());
                break;
            case 2:
                glview.setRenderer(new OpenGL3DRenderer());
                break;
        }
        setContentView(glview);


    }

    @Override
    protected void onPause() {
        super.onPause();
        glview.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        glview.onResume();

    }
}
