package com.lenovo.service.basicpubliclibrary.danmu;

import android.view.View;


public abstract class DanmuConverter<M> {
    public abstract int getSingleLineHeight();

    public abstract View convert(M model);
}
