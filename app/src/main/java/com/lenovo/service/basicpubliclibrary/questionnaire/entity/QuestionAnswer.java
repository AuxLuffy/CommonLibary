package com.lenovo.service.basicpubliclibrary.questionnaire.entity;

/**
 * Created by wyj on 2016/12/13.
 * 问题答案
 */

public class QuestionAnswer {
    private String optionId;    //选项Id
    private String optionBackText;  //选项备注文本信息
    private String optionContext;

    public QuestionAnswer(String optionId, String optionContext, String optionBackText) {
        this.optionId = optionId;
        this.optionBackText = optionBackText;
        this.optionContext = optionContext;
    }

    public QuestionAnswer(String optionId, String optionBackText) {
        this.optionId = optionId;
        this.optionBackText = optionBackText;
    }

    public String getOptionContext() {
        return optionContext;
    }

    public void setOptionContext(String optionContext) {
        this.optionContext = optionContext;
    }

    @Override
    public String toString() {
        return optionId + "--" + optionContext + "--" + optionBackText + "   ";
//        return optionId + "--" + optionBackText + "   ";
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getOptionBackText() {
        return optionBackText;
    }

    public void setOptionBackText(String optionBackText) {
        this.optionBackText = optionBackText;
    }

}
