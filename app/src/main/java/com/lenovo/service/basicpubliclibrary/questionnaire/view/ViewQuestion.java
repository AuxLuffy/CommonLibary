package com.lenovo.service.basicpubliclibrary.questionnaire.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.questionnaire.entity.Question;


/**
 * Created by wyj on 2016/12/6.
 * 自定义View-问题基类
 */

public abstract class ViewQuestion extends LinearLayout {
    public static final int STYLE_UPORDER = 1;
    public static final int STYLE_SURVEY = 2;
    Question question;
    TextView mTvTitle;
    LinearLayout mLlOption;

    public ViewQuestion(Context context) {
        super(context);
    }

    public ViewQuestion(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
     * 界面控件初始化
     */
    private void initView(int style) {
        if (style == STYLE_UPORDER) {
            LayoutInflater.from(getContext()).inflate(R.layout.question_uporder, this);
        } else if (style == STYLE_SURVEY) {
            LayoutInflater.from(getContext()).inflate(R.layout.question_survey, this);
        }
        mTvTitle = (TextView) findViewById(R.id.question_tv_title);
        mLlOption = (LinearLayout) findViewById(R.id.question_ll_option);
    }

    public void updateNum(int num) {

        this.question = question;
        StringBuilder title = new StringBuilder();
        //初始化问题
        title.append(num + ". " + question.getQuestionContent());
        switch (question.getQuestionType()) {
            case Question.Q_BLANK:
                title.append("（填空）");
                break;
            case Question.Q_SINGLE_CHOICE:
                title.append("（单选）");
                break;
            case Question.Q_MULTI_CHOICE:
                title.append("（多选）");
                break;
            case Question.Q_DROP_CHOICE:
                title.append("（下拉选择）");
                break;
            default:
                break;
        }

        if (question.isQuestionIsRequired()) {
            mTvTitle.setText(Html.fromHtml(title.toString() + "<font color=\"#ff0000\">" + " *" + "</font>"));
        } else {
            mTvTitle.setText(title.toString());
        }
    }

    public void updateState(boolean isAnswered) {
        if (isAnswered) {
            mTvTitle.setTextColor(Color.parseColor("#666666"));
        } else {
            mTvTitle.setTextColor(Color.parseColor("#ff0000"));
        }
//        updateOptionState(isAnswered);
    }


    public void init(Question question, int style) {
        initView(style);
        this.question = question;
        StringBuilder title = new StringBuilder();
        if (style == STYLE_UPORDER) {
            title.append(question.getQuestionContent());
        } else if (style == STYLE_SURVEY) {
            //初始化问题
            title.append(question.getQuestionOrder() + ". " + question.getQuestionContent());
            switch (question.getQuestionType()) {
                case Question.Q_BLANK:
                    title.append("（填空）");
                    break;
                case Question.Q_SINGLE_CHOICE:
                    title.append("（单选）");
                    break;
                case Question.Q_MULTI_CHOICE:
                    title.append("（多选）");
                    break;
                case Question.Q_DROP_CHOICE:
                    title.append("（下拉选择）");
                    break;
                default:
                    break;
            }
        }
        if (question.isQuestionIsRequired()) {
            mTvTitle.setText(Html.fromHtml(title.toString() + "<font color=\"#ff0000\">" + " *" + "</font>"));
        } else {
            mTvTitle.setText(title.toString());
        }
        //初始化答案
        initOption(style);
        //是关联问题、则先隐藏
        if (!question.isShow()) {
            this.setVisibility(View.GONE);
        }
    }

    public abstract void initOption(int style);

    public abstract void clearOption();

//    public abstract void updateOptionState(boolean isAnswered);

    public abstract boolean generateAnswer();

}
