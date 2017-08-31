package com.lenovo.camera.util;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by sunzh on 2017/8/31.
 */

public class CaptureImg extends View {

    private int imgSize;
    private Paint mPaint;


    public CaptureImg(Context context) {
        super(context);
    }

    public CaptureImg(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int layout_width;
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layout_width = outMetrics.widthPixels;
        } else {
            layout_width = outMetrics.widthPixels / 2;
        }
        imgSize = (int) (layout_width / 4.5f);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(imgSize, imgSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.FILL);
        //外圆（半透明灰色）
        mPaint.setColor(0xEECCCCCC);
        canvas.drawCircle(imgSize / 2, imgSize / 2, imgSize / 2, mPaint);

        //内圆（白色）
        mPaint.setColor(0xFFFFFFFF);
        canvas.drawCircle(imgSize / 2, imgSize / 2, 3 * imgSize / 8, mPaint);
        String text = "aaaaaa";
        Rect bounds = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), bounds);
        canvas.drawText("aaa", imgSize / 2 - bounds.width() / 2, imgSize / 2 + bounds.height() / 2, mPaint);
    }
}
