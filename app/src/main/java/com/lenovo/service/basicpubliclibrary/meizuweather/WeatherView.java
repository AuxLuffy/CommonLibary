package com.lenovo.service.basicpubliclibrary.meizuweather;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.lenovo.service.basicpubliclibrary.R;

/**
 * Created by 焦禹铭
 */
@SuppressLint("NewApi")
public class WeatherView extends View {

    /**
     * 圆弧宽度
     */
    private float mBorderWidth = 3f;
    /**
     * 圆弧画笔
     */
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * 开始绘制圆弧的角度
     */
    private float mStartAngle = 180;
    /**
     * 终点对应的角度和起始点对应的角度的夹角
     */
    private float mAngleLength = 180;
    /**
     * 所要绘制的当前的夹角
     */
    private float mCurrentAngleLength = 0;
    /**
     * 时间文字大小
     */
    private float mTextSize = 28f;
    /**
     * 开始时间
     */
    private float mStartTime;
    /**
     * 结束时间
     */
    private float mEndTime;
    /**
     * 开始时间文字
     */
    private String mStartText = "07:00";
    /**
     * 结束时间文字
     */
    private String mEndText = "19:00";
    /**
     * 动画时长
     */
    private int mDuration = 3000;

    private Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_sun);

    private int mBitmapWidth;
    private int mBitmapHeight;

    private float mCirclePointX;

    private float mCirclePointY;

    private float mMovePointX;

    private float mMovePointY;

    private float mRadius;

    private RectF mRect;

    public WeatherView(Context context) {
        super(context);
    }

    public WeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTimeAndBitmap();
    }

    public WeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private boolean isFirstInit = true;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isFirstInit) {
            //半径，也是中心点的x坐标
            mRadius = (getWidth() - 50) / 2;
            //圆弧的外轮廓矩形
            mRect = new RectF(50, mBorderWidth, mRadius * 2, mRadius * 2);
            mMovePointY = mRect.centerY();
            mCirclePointX = mRect.centerX();
            mCirclePointY = mRect.centerY();
            isFirstInit = false;
        }

        //绘制背景半圆
        drawSumArc(canvas, mRect);
        //绘制半圆底部横线
        drawBottomLine(canvas, mRadius);
        //绘制开始时间和结束时间文字
        drawTimeText(canvas, mRadius);
        //绘制当前时间圆弧
        drawCurrentArc(canvas, mRect);

        drawCurrentArcBg(canvas);

        Log.d("zhe", "mMovePointY:" + mMovePointY);
        canvas.drawBitmap(mBitmap, mMovePointX - mBitmapWidth / 2 + mBorderWidth,
                mMovePointY - mBitmapHeight / 2 + mBorderWidth, null);
    }

    private void initTimeAndBitmap() {
        mStartTime = 5 * 60;
        mEndTime = 19 * 60;
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
        mMovePointX = 50;
    }

    private void drawSumArc(Canvas canvas, RectF rect) {
        mPaint.setColor(Color.parseColor("#FFCCCCCC"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorderWidth);
        canvas.drawArc(rect, mStartAngle, mAngleLength, false, mPaint);
    }

    private void drawBottomLine(Canvas canvas, float centerX) {
        mPaint.setColor(Color.parseColor("#FFCCCCCC"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorderWidth);
        canvas.drawLine(0, centerX, getWidth(), centerX, mPaint);
    }

    private void drawTimeText(Canvas canvas, float centerX) {
        Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);
        mPaint.setTypeface(font);//字体风格
        mPaint.setColor(getResources().getColor(R.color.gray));
        mPaint.setTextSize(mTextSize);
        mPaint.setStrokeWidth(2);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(mStartText, mBorderWidth + 50, centerX + mBorderWidth + 30, mPaint);
        canvas.drawText(mEndText, centerX * 2 - mBorderWidth, centerX + mBorderWidth + 30, mPaint);
    }

    private void drawCurrentArc(Canvas canvas, RectF rect) {
        mPaint.setColor(getResources().getColor(R.color.red_EA0000));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorderWidth);
        canvas.drawArc(rect, mStartAngle, mCurrentAngleLength, false, mPaint);
    }

    private void drawCurrentArcBg(Canvas canvas) {
        mPaint.setColor(getResources().getColor(R.color.red_standerd));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mBorderWidth);
        canvas.save();
        canvas.clipRect(50, mBorderWidth, mMovePointX, mRadius * 2 - mBorderWidth, Region.Op.INTERSECT);
        canvas.drawArc(mRect, mStartAngle, mAngleLength, true, mPaint);
        canvas.restore();
    }


    public void setCurrentTime(float currentTime) {
        //当时间超出结束时间，设置为结束时间
        currentTime = currentTime > mEndTime ? mEndTime : currentTime;
        //当时间小于开始时间，设置为开始时间
        currentTime = currentTime < mStartTime ? mStartTime : currentTime;
        //计算当前时间占比
        float scale = currentTime / mEndTime;
        //计算当前时间弧长
        float currentAngleLength = scale * mAngleLength;
        //执行动画
        setAnimation(0, currentAngleLength, mDuration);
    }

    private void setAnimation(float start, float current, int length) {
        final ValueAnimator progressAnimator = ValueAnimator.ofFloat(start, current);
        progressAnimator.setDuration(length);
        progressAnimator.setTarget(mCurrentAngleLength);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取过渡值，更新进度
                mCurrentAngleLength = (float) animation.getAnimatedValue();
                mMovePointX = (float) (mCirclePointX - mRadius * Math.cos(mCurrentAngleLength * Math.PI / 180));
                mMovePointY = (float) (mCirclePointY - mRadius * Math.sin(mCurrentAngleLength * Math.PI / 180));
                Log.d("zhe", "mMovePointX：" + mMovePointX + ",y:" + mMovePointY);
                invalidate();
            }
        });
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
    }

    /**
     * dp转px
     */
    public int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }


}
