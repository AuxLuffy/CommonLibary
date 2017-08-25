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
 */

public class ViewQuestionMultiChoice extends ViewQuestion {
//    private List<CheckBox> checkBoxList = new ArrayList<>();
    private Map<Integer, Boolean> checkMap = new HashMap<>();
    private List<ImageView> optionImageList = new ArrayList<>();
    private List<EditText> optionTextList = new ArrayList<>();

    private ViewQuestionSingleChoice.onAnswerChangedListener listener;
    private Map<String, Boolean> relationQuestionMap = new HashMap<>();

    public ViewQuestionMultiChoice(Context context) {
        super(context);
    }

    public ViewQuestionMultiChoice(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void initOption(int style) {
        for (int i = 0; i < question.getOptionList().size(); i++) {
            QuestionOption option = question.getOptionList().get(i);
            List<String> relationQuestionList = option.getRelationQuestionId();
            if (relationQuestionList != null && relationQuestionList.size() != 0) {
                for (String relationQuestionId : relationQuestionList) {
                    relationQuestionMap.put(relationQuestionId, false);
                }
            }
            LinearLayout ll = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.option_multi_choice, null);
            LinearLayout mc_ll = (LinearLayout) ll.findViewById(R.id.mc_ll);
            mc_ll.setOnClickListener(new ViewQuestionMultiChoice.OptionOnClickListener(i));
            ImageView mc_iv = (ImageView) ll.findViewById(R.id.mc_iv);
            optionImageList.add(mc_iv);
            TextView mc_tv = (TextView) ll.findViewById(R.id.mc_tv);
            mc_tv.setText(option.getOptionContent());
            EditText mc_et = (EditText) ll.findViewById(R.id.mc_et);
            mc_et.setVisibility(View.GONE);
            optionTextList.add(mc_et);
            checkMap.put(i, false);
            mLlOption.addView(ll);
        }
    }

    @Override
    public void clearOption() {
        for (Map.Entry<Integer, Boolean> entry : checkMap.entrySet()) {
            entry.setValue(false);
        }
            updateOption();
    }

    private void updateOption() {
        for (int i = 0; i < question.getOptionList().size(); i++) {
            QuestionOption option = question.getOptionList().get(i);
//            String relationQuestionId = option.getRelationQuestionId();
            List<String> relationQuestionList = option.getRelationQuestionId();
            //与答案id匹配，设置成选中状态、且显示文本框
            if (checkMap.get(i)) {
                optionImageList.get(i).setImageResource(R.mipmap.option_multi_choice_selected);
                if (option.isHasAddText()) {
                    optionTextList.get(i).setVisibility(View.VISIBLE);
                }
                //显示相应的关联问题
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
                if (relationQuestionList != null && relationQuestionList.size() !=0) {
                    for (String relationQuestionId : relationQuestionList) {
                        relationQuestionMap.put(relationQuestionId, false);
                    }
                }
            }
        }
    }

    @Override
    public boolean generateAnswer() {
        int checkCnt = 0;
        for (Map.Entry<Integer, Boolean> entry : checkMap.entrySet()) {
            if (entry.getValue()) {
                checkCnt++;
            }
        }
        //必填
        if (question.isQuestionIsRequired() && checkCnt == 0) {
            return false;
        }
        List<QuestionAnswer> questionAnswerList = new ArrayList<>();
        for (Map.Entry<Integer, Boolean> entry : checkMap.entrySet()) {
            if (entry.getValue()) {
                QuestionOption questionOption = question.getOptionList().get(entry.getKey());
                QuestionAnswer questionAnswer = new QuestionAnswer(questionOption.getOptionId(), questionOption.getOptionContent(), optionTextList.get(entry.getKey()).getText().toString());
//                QuestionAnswer questionAnswer = new QuestionAnswer(questionOption.getOptionId(), optionTextList.get(i).getText().toString());
                questionAnswerList.add(questionAnswer);
            }
        }
        if (questionAnswerList.size() != 0 ) {
            question.setAnswerList(questionAnswerList);
        }
        return true;
    }

    public void setAnswerChangedListener(ViewQuestionSingleChoice.onAnswerChangedListener listener) {
        this.listener = listener;
    }

    public interface onAnswerChangedListener {
        void onAnswerChanged(Map<String, Boolean> relationQuestionMap);
    }


    class OptionOnClickListener implements OnClickListener {
        private int selected;

        public OptionOnClickListener(int selected) {
            this.selected = selected;
        }

        @Override
        public void onClick(View v) {
            if (checkMap.get(selected)) {
                checkMap.put(selected, false);
            } else {
                checkMap.put(selected, true);
            }
            //更新答案UI
            updateOption();
            if (listener != null) {
                listener.onAnswerChanged(relationQuestionMap);
            }
        }
    }
}
