package com.lenovo.service.basicpubliclibrary.questionnaire.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.questionnaire.entity.QuestionAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyj on 2016/12/2.
 */

public class ViewQuestionBlank extends ViewQuestion {

    private EditText et;

    public ViewQuestionBlank(Context context) {
        super(context);
    }

    public ViewQuestionBlank(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void initOption(int style) {
        LinearLayout ll = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.option_blank, null);
        et = (EditText) ll.findViewById(R.id.blank_et);
        mLlOption.addView(ll);
    }

    @Override
    public void clearOption() {
        et.setText(null);
    }

    @Override
    public boolean generateAnswer() {
        String answer = et.getText().toString();
        //必填
        if (question.isQuestionIsRequired() && "".equals(answer)) {
            return false;
        }
        if (!"".equals(answer)) {
            List<QuestionAnswer> questionAnswerList = new ArrayList<>();
            QuestionAnswer questionAnswer = new QuestionAnswer(question.getOptionList().get(0).getOptionId(), question.getOptionList().get(0).getOptionContent(), answer);
//            QuestionAnswer questionAnswer = new QuestionAnswer(question.getOptionList().get(0).getOptionId(), answer);
            questionAnswerList.add(questionAnswer);
            question.setAnswerList(questionAnswerList);
        }
        return true;
    }
}
