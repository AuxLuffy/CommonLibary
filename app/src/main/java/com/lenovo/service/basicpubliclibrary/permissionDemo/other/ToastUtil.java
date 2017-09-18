package com.lenovo.service.basicpubliclibrary.permissionDemo.other;

import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.App;

/**
 * Created by joker on 2017/7/27.
 */

public class ToastUtil {
    private static Toast toast;

    public static void show(String content) {
        if (toast == null) {
            toast = Toast.makeText(App.getInstance(), content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
