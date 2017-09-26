package com.lenovo.service.basicpubliclibrary.factorypattern;

import android.util.Log;

/**
 * Created by zyy on 2017/9/26.
 */

public class SamsungProcessor implements Processor {
    @Override
    public void processor() {
        Log.e("SamsungProcessor", "三星代工的处理器");
    }
}
