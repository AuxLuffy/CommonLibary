package com.example.lenovo.fragmenttabhost.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.fragmenttabhost.R;



/**
 * Created by Administrator on 2017/6/7.
 */
public class AutoLoadToolbarLayout extends LinearLayout {
    private View backLayout;
    private View toolBar;
    private TextView tvLeftBack;
    private TextView tvCenterTitle;
    private ImageView ivRight;
    private TextView tvRightText;
    Context context;

    public AutoLoadToolbarLayout(Context context) {
        super(context);
        init(context);
    }

    public AutoLoadToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AutoLoadToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        this.context = context;
        View toolbarView = LayoutInflater.from(context).inflate(R.layout.common_toolbar, this, true);
        toolBar = toolbarView.findViewById(R.id.toolbar_content);
        backLayout = toolbarView.findViewById(R.id.backLayout);
        backLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
        tvLeftBack = (TextView) toolbarView.findViewById(R.id.tvLeftBack);
        tvCenterTitle = (TextView) toolbarView.findViewById(R.id.tvCenterTitle);
        ivRight = (ImageView) toolbarView.findViewById(R.id.ivRight);
        tvRightText = (TextView) toolbarView.findViewById(R.id.tvRightText);
    }


    public View setToolBarVisible(int status) {
        this.toolBar.setVisibility(status);
        return this.toolBar;
    }




    public TextView setTvLeftBack(String msg) {
        this.tvLeftBack.setVisibility(View.VISIBLE);
        backLayout.setVisibility(VISIBLE);
        this.tvLeftBack.setText(msg);
        return this.tvLeftBack;
    }

    public TextView setTvLeftBack(int msg) {
        return setTvLeftBack(context.getString(msg));
    }

    public TextView setTvCenterTitle(String msg) {
        this.tvCenterTitle.setText(msg);
        tvCenterTitle.setVisibility(VISIBLE);
        return this.tvCenterTitle;
    }

    public ImageView setIvRight(int resId) {
        this.ivRight.setImageResource(resId);
        ivRight.setVisibility(VISIBLE);
        return this.ivRight;
    }
    public ImageView getIvRight() {
        return this.ivRight;
    }
    public View setIvRightVisible(int status) {
        this.ivRight.setVisibility(status);
        return this.toolBar;
    }

    public TextView setTvRightText(String msg) {
        this.tvRightText.setText(msg);
        tvRightText.setVisibility(VISIBLE);
        return this.tvRightText;
    }

}
