package com.lenovo.service.basicpubliclibrary.jsBridge.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016/9/26.
 */

public class NetWorkUtil {
    public static boolean IsNetWorkAvailable(Context context){
        try{
            ConnectivityManager connectivityManager =  (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager != null){
                NetworkInfo networkinfo = connectivityManager.getActiveNetworkInfo();
                if(networkinfo != null && networkinfo.isConnected()){
                    if(networkinfo.getState() == NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
