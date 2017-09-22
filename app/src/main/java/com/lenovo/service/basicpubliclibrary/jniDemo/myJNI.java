package com.lenovo.service.basicpubliclibrary.jniDemo;

import android.util.Log;

/**
 * Created by cx on 2017/9/14.
 */

public class myJNI {

    static {
        System.loadLibrary("JniTest");
    }

    public static void main(String a[]){
        myJNI myJNI = new myJNI();
        System.out.println(myJNI.sayHello());
        myJNI.set("hello jni");
    }

    public static native String sayHello();

    public static native void set(String welcome);

}
