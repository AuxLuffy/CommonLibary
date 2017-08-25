package com.lenovo.service.basicpubliclibrary.questionnaire.entity;

import java.util.List;

/**
 * Created by wyj on 2016/12/2.
 * 问题选项
 */

public class QuestionOption {
    private String optionId;    //选项ID
    private String optionContent;   //选项内容

    public List<String> getRelationQuestionId() {
        return relationQuestionId;
    }

    public void setRelationQuestionId(List<String> relationQuestionId) {
        this.relationQuestionId = relationQuestionId;
    }

    //    private String relationQuestionId;    //关联问题ID
    private List<String> relationQuestionId;
    private boolean hasAddText = false; //是否有文字补充
    private String addText;    //文字补充
    public QuestionOption(String optionId, String optionContent, boolean hasAddText, List<String> relationQuestionId) {
        this.optionId = optionId;
        this.optionContent = optionContent;
        this.relationQuestionId = relationQuestionId;
        this.hasAddText = hasAddText;
    }

//    public QuestionOption(String optionId, String optionContent, boolean hasAddText, String relationQuestionId) {
//        this.optionId = optionId;
//        this.optionContent = optionContent;
//        this.relationQuestionId = relationQuestionId;
//        this.hasAddText = hasAddText;
//    }

    public String getAddText() {
        return addText;
    }

    public void setAddText(String addText) {
        this.addText = addText;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

//    public String getRelationQuestionId() {
//        return relationQuestionId;
//    }
//
//    public void setRelationQuestionId(String relationQuestionId) {
//        this.relationQuestionId = relationQuestionId;
//    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public boolean isHasAddText() {
        return hasAddText;
    }

    public void setHasAddText(boolean hasAddText) {
        this.hasAddText = hasAddText;
    }
}
