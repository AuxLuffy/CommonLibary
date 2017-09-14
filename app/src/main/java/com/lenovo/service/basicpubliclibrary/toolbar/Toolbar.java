package com.lenovo.service.basicpubliclibrary.toolbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;


/**
 * Created by cx on 2017/1/20.
 */
public class Toolbar {
    private RelativeLayout relativeLayout;
    private ImageView ivRight;//ivLeft
    private LinearLayout ivLeft;
    private TextView tvTitle,tvTitleFront,tvTitleEnd,textRight;
    private android.support.v7.widget.Toolbar toolbar;

    private Activity context;
    private View view;


    public Toolbar(AppCompatActivity activity) {

        this.context = activity;
        initToolbar(context);
        activity.setSupportActionBar(toolbar);

    }

    private void initToolbar(final Activity context) {
        toolbar = (android.support.v7.widget.Toolbar) context.findViewById(R.id.id_toolbar);
//        ivLeft = (ImageView) context.findViewById(R.id.toolbar_imgv_nav);
        ivLeft = (LinearLayout) context.findViewById(R.id.toolbar_imgv_nav);
        ivRight = (ImageView) context.findViewById(R.id.toolbar_img_right);
        tvTitle = (TextView) context.findViewById(R.id.toolbar_tv_title);
        tvTitleFront = (TextView) context.findViewById(R.id.toolbar_tv_titlefront);
        tvTitleEnd = (TextView) context.findViewById(R.id.toolbar_tv_titleend);
        textRight = (TextView) context.findViewById(R.id.toolbar_text_right);
        relativeLayout = (RelativeLayout) context.findViewById(R.id.toolbar_content);


        ivRight.setVisibility(View.INVISIBLE);
    }

    public void setBackgroundColor(@DrawableRes int ResId) {
        relativeLayout.setBackgroundResource(ResId);
    }
    public void setTitle(String title,@DrawableRes int titleColor){
        if(title != null){
            tvTitle.setText(title);
            if(titleColor > 0){
                tvTitle.setTextColor(Util.getColor(titleColor));
            }
        }
    }
    public void setNoTitle(){
        tvTitle.setVisibility(View.GONE);
    }
    public void setTitleTwo(String title,@DrawableRes int colorFirstId,String titleTwo,@DrawableRes int colorTwoId){
        tvTitle.setVisibility(View.GONE);
        tvTitleEnd.setVisibility(View.VISIBLE);
        tvTitleFront.setVisibility(View.VISIBLE);
        if(title != null){
            tvTitleFront.setText(title);
            if(colorFirstId > 0){
                tvTitleFront.setTextColor(Util.getColor(colorFirstId));
            }
        }
        tvTitleEnd.setText(titleTwo);
        if(colorTwoId > 0){
            tvTitleEnd.setTextColor(Util.getColor(colorTwoId));
        }
    }

    public void setRightImg(int resId,View.OnClickListener rightClick){
        ivRight.setImageResource(resId);
        ivRight.setOnClickListener(rightClick);
    }
    public void setRightTv(String text,int textColor,View.OnClickListener rightClick){
        ivRight.setVisibility(View.GONE);
        textRight.setVisibility(View.VISIBLE);
        textRight.setText(text);
        textRight.setTextColor(textColor);
        textRight.setOnClickListener(rightClick);
    }
    //    public ImageView getIvLeft() {
//        return ivLeft;
//    }
    public ImageView getIvRight() {
        return ivRight;
    }
    public TextView getTitle() {
        return tvTitle;
    }
    public TextView getFirstTitle() {
        return tvTitleFront;
    }
    public TextView getSecondTitle() {
        return tvTitleEnd;
    }
    public TextView getTextRight() {
        return textRight;
    }

}
