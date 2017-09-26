package com.lenovo.service.basicpubliclibrary.factorypattern;

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

public class FactoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);
        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setText("定义\n" +"\n" +"为创建一组相关或者是相互依赖的对象提供一个接口，而不需要指定它们的具体类。\n"+"\n"+ "使用场景:\n" + "\n" +
                "如果一个或多个对象有相同的约束时，可以使用抽象工厂模式。那什么是有相同约束的对象呢？\n" +
                "举个栗子，同样是iPhone的处理器，但是可能是由三星或者台积电分别代工生产的，如果用代码模拟这个场景就可以考虑用抽象工厂模式。再比如不同型号的iPhone处理器有着不同的工艺制程，我们也可以用抽象工厂模式来模拟不同型号处理器的生产过程。");

        //生产三星代工的处理器
        SamsungProcessorFactory samsungProcessorFactory = new SamsungProcessorFactory();
        samsungProcessorFactory.createProcessor().processor();

        //生产台积电代工的处理器
        TSMCProcessorFactory tsmcProcessorFactory = new TSMCProcessorFactory();
        tsmcProcessorFactory.createProcessor().processor();

    }
}
