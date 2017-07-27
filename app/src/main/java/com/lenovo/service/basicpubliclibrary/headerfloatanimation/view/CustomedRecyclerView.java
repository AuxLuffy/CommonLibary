package com.lenovo.service.basicpubliclibrary.headerfloatanimation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by lenovo on 2016/5/9.
 */
public class CustomedRecyclerView extends RecyclerView {

    public CustomedRecyclerView(Context context) {
        super(context);

    }

    public CustomedRecyclerView(Context context, @Nullable AttributeSet attrs) {
       super(context,attrs);
    }

    public CustomedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
    }
}
