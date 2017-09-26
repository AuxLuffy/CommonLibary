package com.lenovo.service.basicpubliclibrary.builderpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.StrategyPattern.DiskCache;
import com.lenovo.service.basicpubliclibrary.StrategyPattern.ImageLoader;


/**
 * 调用策略模式的activity
 * Created by chongyangyang on 2017/9/14.
 */

public class BuilderPatternActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);
        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setText("定义\n" +"\n" + "将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。\n" +
                "\n" +
                "只要包括以下角色：\n" +
                "\n" +
                "Product 产品类 : 产品的抽象类。\n" +
                "Builder : 抽象类， 规范产品的组建，一般是由子类实现具体的组件过程。\n" +
                "ConcreteBuilder : 具体的构建器.\n" +
                "Director : 统一组装过程(可省略)。\n"+"\n"+"使用场景:\n" + "\n" +
                "主要用于创建一些复杂的对象，将其内部的构建细节隐藏起来，使用者只需要按照约定好的规则做相应的配置来完成构建，然后将配置好的对象表示出来即可，构建和展示的过程相对独立。同时不同的配置会影响具体的构建过程，最终影响目标对象。");

        new DstObject.Builder().setA("A").setB("B").setC('C').create().show();
    }
}
