package com.lenovo.service.basicpubliclibrary.factorypattern;

import android.util.Log;

/**
 * Created by zyy on 2017/9/26.
 */

public class TSMCProcessor implements Processor {
    @Override
    public void processor() {
        Log.e("TSMCProcessor", "台积电代工的处理器");
    }
}
