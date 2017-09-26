package com.lenovo.service.basicpubliclibrary.StrategyPattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;


/**
 * 调用策略模式的activity
 * Created by chongyangyang on 2017/9/14.
 */

public class StrategyPatternActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);
        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setText("定义\n" +"\n" + "策略模式定义了一系列算法，并将每一个算法封装起来，而且使它们可以相互替换。策略模式让算法独立于使用它的客户端独立变化。\n"+"\n"+"使用场景:\n" + "\n" +
                "当我们处理问题时，如果需要根据不同的条件来选择不同的解决方案，最简单的方式就是用if-else，但每增加一种方案，就要修改原来的代码，而且容易出现错误，最终导致代码臃肿、耦合性高、难维护等，同时也违背了开放封闭原则，所以就需要使用策略模式来解决这样的问题。");
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.setCache(new DiskCache());
    }
}
