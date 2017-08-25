package com.lenovo.service.basicpubliclibrary.questionnaire.entity;

import java.util.List;

/**
 * Created by wyj on 2016/12/2.
 * 问题
 */

public class Question {
    public static final int Q_SINGLE_CHOICE = 1;    //单选
    public static final int Q_MULTI_CHOICE = 2; //多选
    public static final int Q_DROP_CHOICE = 3;  //下拉选择
    public static final int Q_BLANK = 4;    //填空

    private int questionType;   //问题种类
    private int questionOrder;   //问题顺序
    private String questionId;  //问题选项
    private String questionContent; //问题标题
    private boolean questionIsRequired; //是否必填
    private boolean isShow = false; //是否显示，被关联问题则默认不显示
    private List<QuestionOption> optionList;    //选项列表
    private List<QuestionAnswer> answerList;    //答案列表

    public Question(int questionType, String questionId, String questionContent, int questionOrder, boolean questionIsRequired, boolean isShow, List<QuestionOption> optionList) {
        this.questionType = questionType;
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.questionOrder = questionOrder;
        this.questionIsRequired = questionIsRequired;
        this.isShow = isShow;
        this.optionList = optionList;
    }

    public List<QuestionAnswer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<QuestionAnswer> answerList) {
        this.answerList = answerList;
    }

    public int getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(int questionOrder) {
        this.questionOrder = questionOrder;
    }

    public List<QuestionOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<QuestionOption> optionList) {
        this.optionList = optionList;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public boolean isQuestionIsRequired() {
        return questionIsRequired;
    }

    public void setQuestionIsRequired(boolean questionIsRequired) {
        this.questionIsRequired = questionIsRequired;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

}
