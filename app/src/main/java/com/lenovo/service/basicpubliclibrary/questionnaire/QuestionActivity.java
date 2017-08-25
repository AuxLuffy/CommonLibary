package com.lenovo.service.basicpubliclibrary.questionnaire;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.questionnaire.data.QuestionAnswerInfo;
import com.lenovo.service.basicpubliclibrary.questionnaire.data.QuestionInfo;
import com.lenovo.service.basicpubliclibrary.questionnaire.entity.Question;
import com.lenovo.service.basicpubliclibrary.questionnaire.entity.QuestionAnswer;
import com.lenovo.service.basicpubliclibrary.questionnaire.entity.QuestionOption;
import com.lenovo.service.basicpubliclibrary.questionnaire.view.ViewQuestion;
import com.lenovo.service.basicpubliclibrary.questionnaire.view.ViewQuestionBlank;
import com.lenovo.service.basicpubliclibrary.questionnaire.view.ViewQuestionMultiChoice;
import com.lenovo.service.basicpubliclibrary.questionnaire.view.ViewQuestionSingleChoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wyj on 2016/12/2.
 * 问卷界面
 */

public class QuestionActivity extends AppCompatActivity {
    //view
    private LinearLayout mLlDetail;
    private Button mBtnCommit;
    private TextView mTvAnswer;
    //动态添加布局，问题View列表
    private List<ViewQuestion> viewQuestionList = new ArrayList<>();
    //data
    private List<Question> localQuestionList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        mLlDetail = (LinearLayout) findViewById(R.id.survey_ll_detail);
        mTvAnswer = (TextView) findViewById(R.id.survey_tv_answer);
        mBtnCommit = (Button) findViewById(R.id.survey_btn_commit);
        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateQuestionResult();
            }
        });
        initQuestionData();
        initQuestionView();
    }

    /**
     * 初始化问题列表
     */
    private void initQuestionData() {
        String str = " {\"status_code\":\"200\",\"message\":\"seccess\",\"data\":{\"serviceName\":\"MA标准\",\"orderId\":\"201609182100319008\",\"name\":\"问卷test2\",\"content\":[{\"id\":1,\"name\":\"问题1\",\"instructions\":\"我是问题1-单选题测试啊问题1-测试\",\"is_fill\":1,\"questionnaire_id\":2,\"problem_type\":0,\"created_at\":null,\"updated_at\":null,\"is_real\":\"0\",\"order\":\"1\",\"isRelation\":0,\"option\":[{\"id\":1,\"name\":\"你好-有文本-关联问题2-4-5\",\"is_add_text\":1,\"relation_problem_id\":[\t\t\t\t\t\t\t\"2\",\t\t\t\t\t\t\t\"4\",\t\t\t\t\t\t\t\"5\"\t\t\t\t\t\t],\"problem_id\":2,\"created_at\":null,\"updated_at\":null,\"questionnaire_id\":2},{\"id\":2,\"name\":\"再见-关联问题4\",\"is_add_text\":0,\"relation_problem_id\":[\"4\"],\"problem_id\":2,\"created_at\":null,\"updated_at\":null,\"questionnaire_id\":2},{\"id\":3,\"name\":\"对不起-有文本-关联问题5\",\"is_add_text\":1,\"relation_problem_id\":[\"5\"],\"problem_id\":2,\"created_at\":null,\"updated_at\":null,\"questionnaire_id\":2}]},{\"id\":2,\"name\":\"问题2\",\"instructions\":\"我是问题2我是问题2我是问题1我是问题2我是问题2-被关联\",\"is_fill\":0,\"questionnaire_id\":2,\"problem_type\":0,\"created_at\":null,\"updated_at\":null,\"is_real\":\"0\",\"order\":\"2\",\"isRelation\":1,\"option\":[{\"id\":2,\"name\":\"我是选项一（单选）-关联问题4\",\"is_add_text\":0,\"relation_problem_id\":[\"4\"],\"problem_id\":2,\"created_at\":null,\"updated_at\":null,\"questionnaire_id\":2},{\"id\":3,\"name\":\"我是选项二（单选）-有文本-关联问题5\",\"is_add_text\":1,\"relation_problem_id\":[\"5\"],\"problem_id\":2,\"created_at\":null,\"updated_at\":null,\"questionnaire_id\":2}]},{\"id\":3,\"name\":\"问题111\",\"instructions\":\"我是填空啊啊问题111\",\"is_fill\":0,\"questionnaire_id\":2,\"problem_type\":2,\"created_at\":null,\"updated_at\":null,\"is_real\":\"0\",\"order\":\"3\",\"isRelation\":0,\"option\":[]},{\"id\":4,\"name\":\"问题4\",\"instructions\":\"我是问题4-多选题啊啊啊-测试测试-被关联\",\"is_fill\":0,\"questionnaire_id\":2,\"problem_type\":1,\"created_at\":null,\"updated_at\":null,\"is_real\":\"0\",\"order\":\"4\",\"isRelation\":1,\"option\":[{\"id\":4,\"name\":\"我是选项一（多选）\",\"is_add_text\":0,\"relation_problem_id\":null,\"problem_id\":3,\"created_at\":null,\"updated_at\":null,\"questionnaire_id\":2},{\"id\":5,\"name\":\"我是选项二（多选）\",\"is_add_text\":0,\"relation_problem_id\":null,\"problem_id\":3,\"created_at\":null,\"updated_at\":null,\"questionnaire_id\":2},{\"id\":6,\"name\":\"我是选项三（多选）\",\"is_add_text\":0,\"relation_problem_id\":null,\"problem_id\":3,\"created_at\":null,\"updated_at\":null,\"questionnaire_id\":2}]},{\"id\":5,\"name\":\"问题5\",\"instructions\":\"我是问题5-问题5-问题5-问题5-问题5-多选题啊啊啊-测试测试-被关联\",\"is_fill\":1,\"questionnaire_id\":2,\"problem_type\":1,\"created_at\":null,\"updated_at\":null,\"is_real\":\"0\",\"order\":\"5\",\"isRelation\":1,\"option\":[{\"id\":4,\"name\":\"张三\",\"is_add_text\":0,\"relation_problem_id\":null,\"problem_id\":3,\"created_at\":null,\"updated_at\":null,\"questionnaire_id\":2},{\"id\":5,\"name\":\"李四\",\"is_add_text\":0,\"relation_problem_id\":null,\"problem_id\":3,\"created_at\":null,\"updated_at\":null,\"questionnaire_id\":2},{\"id\":6,\"name\":\"王五\",\"is_add_text\":0,\"relation_problem_id\":null,\"problem_id\":3,\"created_at\":null,\"updated_at\":null,\"questionnaire_id\":2},{\"id\":6,\"name\":\"未知111\",\"is_add_text\":0,\"relation_problem_id\":null,\"problem_id\":3,\"created_at\":null,\"updated_at\":null,\"questionnaire_id\":2},{\"id\":6,\"name\":\"未知222\",\"is_add_text\":0,\"relation_problem_id\":null,\"problem_id\":3,\"created_at\":null,\"updated_at\":null,\"questionnaire_id\":2}]}]}}";
        QuestionInfo surveyInfo = new Gson().fromJson(str, QuestionInfo.class);
        List<QuestionInfo.Question> questionList = surveyInfo.getData().getContent();
        for (QuestionInfo.Question question : questionList) {
            List<QuestionInfo.QuestionOption> optionList = question.getOption();
            List<QuestionOption> localOptionList = new ArrayList<>();
            for (QuestionInfo.QuestionOption questionOption : optionList) {
                boolean isAddText = "0".equals(questionOption.getIs_add_text()) ? false : true;
                QuestionOption localOption = null;
                localOption = new QuestionOption(questionOption.getId(), questionOption.getName(), isAddText, questionOption.getRelation_problem_id());
                localOptionList.add(localOption);
            }
            boolean isRequired = "0".equals(question.getIs_fill()) ? false : true;
            boolean isShow = "1".equals(question.getIsRelation()) ? false : true;
            Question localQuestion = null;
            String typeStr = question.getProblem_type();
            int type = -1;
            //单选
            int order = 0;
            if ("0".equals(typeStr)) {
                type = Question.Q_SINGLE_CHOICE;
                try {
                    order = Integer.parseInt(question.getOrder());
                } catch (Exception e) {
                    order = 0;
                }
                localQuestion = new Question(type, question.getId(), question.getName(), order, isRequired, isShow, localOptionList);
                //多选
            } else if ("1".equals(typeStr)) {
                type = Question.Q_MULTI_CHOICE;
                try {
                    order = Integer.parseInt(question.getOrder());
                } catch (Exception e) {
                    order = 0;
                }
                localQuestion = new Question(type, question.getId(), question.getName(), order, isRequired, isShow, localOptionList);
                //填空
            } else if ("2".equals(typeStr)) {
                type = Question.Q_BLANK;
                List<QuestionOption> blankLocalOptionList = new ArrayList<>();
                QuestionOption blankLocalOption = new QuestionOption("", "", false, null);
                blankLocalOptionList.add(blankLocalOption);
                try {
                    order = Integer.parseInt(question.getOrder());
                } catch (Exception e) {
                    order = 0;
                }
                localQuestion = new Question(type, question.getId(), question.getName(), order, isRequired, isShow, blankLocalOptionList);
            }
            localQuestionList.add(localQuestion);
        }
    }

    /**
     * 生成问卷界面
     */
    private void initQuestionView() {
        for (Question question : localQuestionList) {
            switch (question.getQuestionType()) {
                case Question.Q_BLANK:
                    ViewQuestionBlank questionBlank = new ViewQuestionBlank(this);
                    questionBlank.init(question, ViewQuestion.STYLE_SURVEY);
                    viewQuestionList.add(questionBlank);
                    break;
                case Question.Q_SINGLE_CHOICE:
                    ViewQuestionSingleChoice questionSingleChoice = new ViewQuestionSingleChoice(this);
                    questionSingleChoice.init(question, ViewQuestion.STYLE_SURVEY);
                    questionSingleChoice.setAnswerChangedListener(new ViewQuestionSingleChoice.onAnswerChangedListener() {
                        @Override
                        public void onAnswerChanged(Map<String, Boolean> relationQuestionMap) {
                            for (Map.Entry<String, Boolean> entry : relationQuestionMap.entrySet()) {
                                //更新QuestionList状态
                                String questionId = entry.getKey();
                                boolean isShow = entry.getValue();
                                for (Question localQuestion : localQuestionList) {
                                    if (questionId.equals(localQuestion.getQuestionId())) {
                                        localQuestion.setShow(isShow);
                                    }
                                }
                            }
                            updateQuestionView();
                        }
                    });
                    viewQuestionList.add(questionSingleChoice);
                    break;
                case Question.Q_MULTI_CHOICE:
                    ViewQuestionMultiChoice questionMultiChoice = new ViewQuestionMultiChoice(this);
                    questionMultiChoice.init(question, ViewQuestion.STYLE_SURVEY);
                    questionMultiChoice.setAnswerChangedListener(new ViewQuestionSingleChoice.onAnswerChangedListener() {
                        @Override
                        public void onAnswerChanged(Map<String, Boolean> relationQuestionMap) {
                            for (Map.Entry<String, Boolean> entry : relationQuestionMap.entrySet()) {
                                //更新QuestionList状态
                                String questionId = entry.getKey();
                                boolean isShow = entry.getValue();
                                for (Question localQuestion : localQuestionList) {
                                    if (questionId.equals(localQuestion.getQuestionId())) {
                                        localQuestion.setShow(isShow);
                                    }
                                }
                            }
                            updateQuestionView();
                        }
                    });
                    viewQuestionList.add(questionMultiChoice);
                    break;
                case Question.Q_DROP_CHOICE:
                    break;
                default:
                    break;
            }
        }
        for (ViewQuestion viewQuestion : viewQuestionList) {
            mLlDetail.addView(viewQuestion);
            if (!viewQuestion.getQuestion().isShow()) {
                viewQuestion.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 更新界面
     */
    private void updateQuestionView() {
        int questionNum = 1;
        for (int i = 0; i < localQuestionList.size(); i++) {
            Question question = localQuestionList.get(i);
            if (question.isShow()) {
                viewQuestionList.get(i).updateNum(questionNum);
                questionNum++;
                viewQuestionList.get(i).setVisibility(View.VISIBLE);
            } else {
                viewQuestionList.get(i).clearOption();
                viewQuestionList.get(i).setVisibility(View.GONE);
            }
        }
    }

    /**
     * 生成答案
     */
    private void generateQuestionResult() {
        for (ViewQuestion viewQuestion : viewQuestionList) {
            if (!viewQuestion.generateAnswer()) {
                if (viewQuestion.isShown()) {
                    Toast.makeText(QuestionActivity.this, "请完善所有必填信息", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
        //获取答案
        List<QuestionAnswerInfo> answerRequestList = new ArrayList<>();
        for (Question question : localQuestionList) {
            List<QuestionAnswer> questionAnswerList = question.getAnswerList();
            if (questionAnswerList != null) {
                for (QuestionAnswer answer : questionAnswerList) {
                    answerRequestList.add(new QuestionAnswerInfo(question.getQuestionContent(), answer.getOptionContext(), answer.getOptionBackText()));
                }
            }
        }
        //提交问卷
        String questionResult = new Gson().toJson(answerRequestList);
        mTvAnswer.setText(questionResult);
    }


}
