package com.lenovo.service.basicpubliclibrary.scoringstrip;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lenovo.service.basicpubliclibrary.R;

import java.util.ArrayList;
import java.util.List;


/**
 * function:自定义RatingBar
 * <p>
 * Created by 何云超 on 2016/10/18
 */

public class CustomRatingbar extends LinearLayout {

    private int starNumber = 5;// 星星的个数
    private static final int PADDING_LEFT_RIGHT = 10;// 星星的距离
    private List<ImageView> stars = new ArrayList<>();
    private int rating;

    public interface OnRatingBarChangeListener {
        void onRatingChanged(int rating);
    }

    public CustomRatingbar(Context context) {
        this(context, null);
    }

    public CustomRatingbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRatingbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        LayoutParams layoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);

        stars.add(new ImageView(getContext()));// 占位
        for (int x = 1; x <= starNumber; x++) {
            ImageView star = new ImageView(getContext());
            star.setTag(x);
            star.setImageResource(R.drawable.star_selected);
            star.setPadding(PADDING_LEFT_RIGHT, 0, PADDING_LEFT_RIGHT, 0);
            star.setLayoutParams(layoutParams);
            stars.add(star);
            addView(star);
        }
        setOnTouchListener(mOnTouchListener);
    }

    private OnRatingBarChangeListener mOnRatingBarChangeListener;

    public void setOnRatingBarChangeListener(OnRatingBarChangeListener onRatingBarChangeListener) {
        this.mOnRatingBarChangeListener = onRatingBarChangeListener;
    }

    public void setClickOnly(boolean clickOnly) {
        if (clickOnly) {
            setOnTouchListener(null);
            for (ImageView iv : stars) {
                iv.setOnClickListener(onClickListener);
            }
        }
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        refreshRating(rating);
    }

    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            float num = getMeasuredWidth() * 1.0f / starNumber;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    float offsetDown = event.getX() / num;
                    refreshRating(offsetDown >= starNumber - 1 ? starNumber : (int) offsetDown + 1);
                    break;
                case MotionEvent.ACTION_MOVE:
                    float offsetMove = event.getX() / num;
                    refreshRating(offsetMove < 0.3 ? 0 : (int) offsetMove + 1);
                    break;
            }
            return true;
        }
    };

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            refreshRating((Integer) v.getTag());
        }
    };

    private void refreshRating(int index) {
        for (int x = 1; x <= starNumber; ++x) {
            stars.get(x).setImageResource(x <= index ? R.drawable.star_selected : R.drawable.star_unselected);
        }
        index = index < 0 ? 0 : index > starNumber ? starNumber : index;
        rating = index;
        if (mOnRatingBarChangeListener != null) {
            mOnRatingBarChangeListener.onRatingChanged(index);
        }
    }


}
