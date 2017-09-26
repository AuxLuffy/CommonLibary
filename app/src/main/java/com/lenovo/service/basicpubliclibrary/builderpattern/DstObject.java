package com.lenovo.service.basicpubliclibrary.builderpattern;

import android.util.Log;

/**
 * Created by zyy on 2017/9/26.
 */

public class DstObject {
    String a;
    String b;
    char c;

    public void show(){
        Log.e("DstObject", "show-->" + a + b + c);
    }

    public static class Builder{
        String a;
        String b;
        char c;
        public Builder setA(String a){
            this.a = a;
            return this;
        }

        public Builder setB(String b){
            this.b = b;
            return this;
        }

        public Builder setC(char c){
            this.c = c;
            return this;
        }

        public DstObject create(){
            DstObject dstObject = new DstObject();
            dstObject.a = a;
            dstObject.b = b;
            dstObject.c = c;
            return dstObject;
        }
    }
}
