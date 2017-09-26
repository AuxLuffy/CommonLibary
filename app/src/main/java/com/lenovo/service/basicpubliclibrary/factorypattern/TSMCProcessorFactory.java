package com.lenovo.service.basicpubliclibrary.factorypattern;

/**
 * Created by zyy on 2017/9/26.
 */

public class TSMCProcessorFactory extends ProcessorFactory {
    @Override
    public Processor createProcessor() {
        return new TSMCProcessor();
    }
}
