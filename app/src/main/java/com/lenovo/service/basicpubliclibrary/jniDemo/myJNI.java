package com.lenovo.service.basicpubliclibrary.jniDemo;

/**
 * Created by cx on 2017/9/14.
 */

public class myJNI {

    static {
        System.loadLibrary("JniTest");
    }

    public static native String sayHello();

}
