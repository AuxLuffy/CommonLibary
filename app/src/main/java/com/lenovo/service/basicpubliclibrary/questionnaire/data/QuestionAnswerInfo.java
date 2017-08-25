package com.lenovo.service.basicpubliclibrary.questionnaire.data;

/**
 * Created by wyj
 */

public class QuestionAnswerInfo {

    private String Question;
    private String Answer;
    private String ExtraMessage;

    public QuestionAnswerInfo(String question, String answer, String extraMessage) {
        Question = question;
        Answer = answer;
        ExtraMessage = extraMessage;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getExtraMessage() {
        return ExtraMessage;
    }

    public void setExtraMessage(String extraMessage) {
        ExtraMessage = extraMessage;
    }


}
