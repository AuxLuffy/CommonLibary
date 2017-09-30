// PersonManager.aidl
package com.lenovo.service.basicpubliclibrary.aidl;
import com.lenovo.service.basicpubliclibrary.aidl.MessageX;
import com.lenovo.service.basicpubliclibrary.aidl.AidlCallBack;
// Declare any non-default types here with import statements

interface MessageXManager {
    void sendMessageX(inout MessageX msg);

    void registerCallback(AidlCallBack cb);
    /**
             * Remove a previously registered callback interface.
             */
    void unregisterCallback(AidlCallBack cb);

}
