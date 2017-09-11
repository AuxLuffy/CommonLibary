package com.lenovo.service.basicpubliclibrary.SADL;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Created by sunzf
 */
public class Utils {

    public static Drawable getDrawable(Context context, int res) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(res, context.getTheme());
        } else {
            return context.getResources().getDrawable(res);
        }
    }
}
