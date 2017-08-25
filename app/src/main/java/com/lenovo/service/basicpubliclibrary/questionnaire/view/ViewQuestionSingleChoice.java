package com.lenovo.service.basicpubliclibrary.questionnaire.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.questionnaire.entity.QuestionAnswer;
import com.lenovo.service.basicpubliclibrary.questionnaire.entity.QuestionOption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wyj on 2016/12/2.
 * 自定义View-单选问题
 */

public class ViewQuestionSingleChoice extends ViewQuestion {
    private int lastSelected = -1;
    //存每个选项图标的imageview
    private List<ImageView> optionImageList = new ArrayList<>();
    private List<EditText> optionTextList = new ArrayList<>();
    private onAnswerChangedListener listener;

    private Map<String, Boolean> relationQuestionMap = new HashMap<>();

    public ViewQuestionSingleChoice(Context context) {
        super(context);
    }

    public ViewQuestionSingleChoice(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void initOption(int style) {
        for (int i = 0; i < question.getOptionList().size(); i++) {
            QuestionOption option = question.getOptionList().get(i);
//            if (option.getRelationQuestionId() != null) {
//                relationQuestionMap.put(option.getRelationQuestionId(), false);
//            }
            List<String> relationQuestionList = option.getRelationQuestionId();
            if (relationQuestionList != null && relationQuestionList.size() !=0) {
                for (String relationQuestionId : relationQuestionList) {
                    relationQuestionMap.put(relationQuestionId, false);
                }
            }
            LinearLayout ll = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.option_single_choice_addtext, null);
            LinearLayout sc_ll = (LinearLayout) ll.findViewById(R.id.sc_ll);
            sc_ll.setOnClickListener(new OptionOnClickListener(i));
            ImageView sc_iv = (ImageView) ll.findViewById(R.id.sc_iv);
            optionImageList.add(sc_iv);
            TextView sc_tv = (TextView) ll.findViewById(R.id.sc_tv);
            sc_tv.setText(option.getOptionContent());
            EditText sc_et = (EditText) ll.findViewById(R.id.sc_et);
            sc_et.setVisibility(View.GONE);
            optionTextList.add(sc_et);
            mLlOption.addView(ll);
        }
    }

    @Override
    public void clearOption() {
        updateOption(-1);
    }

    @Override
    public boolean generateAnswer() {
        //必填
        if (question.isQuestionIsRequired() && lastSelected == -1) {
            return false;
        }
        if (lastSelected != -1) {
            List<QuestionAnswer> questionAnswerList = new ArrayList<>();
            QuestionAnswer questionAnswer = new QuestionAnswer(question.getOptionList().get(lastSelected).getOptionId(), question.getOptionList().get(lastSelected).getOptionContent(), optionTextList.get(lastSelected).getText().toString());
//            QuestionAnswer questionAnswer = new QuestionAnswer(question.getOptionList().get(lastSelected).getOptionId(), optionTextList.get(lastSelected).getText().toString());
            questionAnswerList.add(questionAnswer);
            question.setAnswerList(questionAnswerList);
        }
        return true;
    }

    private void updateOption(int selected) {
        for (int i = 0; i < question.getOptionList().size(); i++) {
            QuestionOption option = question.getOptionList().get(i);
//            String relationQuestionId = option.getRelationQuestionId();
            List<String> relationQuestionList = option.getRelationQuestionId();
            //与答案id匹配，设置成选中状态、且显示文本框
            if (selected == i) {
                //更新答案选项
                lastSelected = selected;
                optionImageList.get(i).setImageResource(R.mipmap.option_single_choice_selected);
                if (option.isHasAddText()) {
                    optionTextList.get(i).setVisibility(View.VISIBLE);
                }
                //显示相应的关联问题
//                if (relationQuestionId != null) {
//                    relationQuestionMap.put(relationQuestionId, true);
//                }
                if (relationQuestionList != null && relationQuestionList.size() !=0) {
                    for (String relationQuestionId : relationQuestionList) {
                        relationQuestionMap.put(relationQuestionId, true);
                    }
                }
                //设置成未选中状态、且隐藏文本框
            } else {
                optionImageList.get(i).setImageResource(R.mipmap.option_single_choice_normal);
                optionTextList.get(i).setText("");
                optionTextList.get(i).setVisibility(View.GONE);
                //隐藏相应的关联问题
//                if (relationQuestionId != null) {
//                    relationQuestionMap.put(relationQuestionId, false);
//                }
                if (relationQuestionList != null && relationQuestionList.size() !=0) {
                    for (String relationQuestionId : relationQuestionList) {
                        relationQuestionMap.put(relationQuestionId, false);
                    }
                }
            }
        }
    }

    public void setAnswerChangedListener(onAnswerChangedListener listener) {
        this.listener = listener;
    }

    public interface onAnswerChangedListener {
        void onAnswerChanged(Map<String, Boolean> relationQuestionMap);
    }

    class OptionOnClickListener implements View.OnClickListener {
        private int selected;

        public OptionOnClickListener(int selected) {
            this.selected = selected;
        }

        @Override
        public void onClick(View v) {
            //更新答案UI
            updateOption(selected);
            if (listener != null) {
                listener.onAnswerChanged(relationQuestionMap);
            }
        }
    }

}
