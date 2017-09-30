// AidlCallBack.aidl
package com.lenovo.service.basicpubliclibrary.aidl;
import com.lenovo.service.basicpubliclibrary.aidl.MessageX;
// Declare any non-default types here with import statements

interface AidlCallBack {
    void replyMessageX(inout MessageX msgx);
}
