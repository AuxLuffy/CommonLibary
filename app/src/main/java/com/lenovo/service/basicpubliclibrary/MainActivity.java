package com.lenovo.service.basicpubliclibrary;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    private View view1=null;
    private View view2=null;
    private View view3=null;
    private View view4=null;
    private View view5=null;
    private View view6=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        view1=findViewById(R.id.layout1);
        view1.setOnClickListener(new LayoutListener());
        view2=findViewById(R.id.layout2);
        view2.setOnClickListener(new LayoutListener());
        view3=findViewById(R.id.layout3);
        view3.setOnClickListener(new LayoutListener());
        view4=findViewById(R.id.layout4);
        view4.setOnClickListener(new LayoutListener());
        view5=findViewById(R.id.layout5);
        view5.setOnClickListener(new LayoutListener());
        view6=findViewById(R.id.layout6);
        view6.setOnClickListener(new LayoutListener());

    }

    public class LayoutListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent=new Intent();
            switch (view.getId()){

                case R.id.layout1:

                    intent.setClass(MainActivity.this,
                            WidgetActivity.class);
                    startActivity(intent);
                    break;
                case R.id.layout2:

                    intent.setClass(MainActivity.this,
                            AnimationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.layout3:
                    intent.setClass(MainActivity.this,
                            ComponentActivity.class);
                    startActivity(intent);
                    break;
                case R.id.layout4:
                    intent.setClass(MainActivity.this,
                            StorageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.layout5:
                    intent.setClass(MainActivity.this,
                            OptimizeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.layout6:
                    intent.setClass(MainActivity.this,
                            CompositeActivity.class);
                    startActivity(intent);
                    break;


                default:
                    break;
            }

        }
    }
}
