package com.lenovo.service.basicpubliclibrary.textview.linktextview;

import android.view.View;



public interface TouchableSpan {
    void setPressed(boolean pressed);
    void onClick(View widget);
}
