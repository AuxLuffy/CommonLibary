package com.lenovo.service.basicpubliclibrary.questionnaire.data;

import java.util.List;

/**
 * Created by wyj on 2016/11/30.
 */

public class QuestionInfo {
    private Survey data;

    public Survey getData() {
        return data;
    }

    public void setData(Survey data) {
        this.data = data;
    }


    public class Survey {
        private String serviceName;
        private String orderId;
        private String name;
        private List<Question> content;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Question> getContent() {
            return content;
        }

        public void setContent(List<Question> content) {
            this.content = content;
        }

    }

    public class Question {
        private String id;
        private String name;
        private String instructions;
        private String is_fill;
        private String is_real;
        private String isRelation;
        private String order;
        private String questionnaire_id;
        private String problem_type;
        private String created_at;
        private String updated_at;
        private List<QuestionOption> option;

        public String getIsRelation() {
            return isRelation;
        }

        public void setIsRelation(String isRelation) {
            this.isRelation = isRelation;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public List<QuestionOption> getOption() {
            return option;
        }

        public void setOption(List<QuestionOption> option) {
            this.option = option;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInstructions() {
            return instructions;
        }

        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }

        public String getIs_fill() {
            return is_fill;
        }

        public void setIs_fill(String is_fill) {
            this.is_fill = is_fill;
        }

        public String getQuestionnaire_id() {
            return questionnaire_id;
        }

        public void setQuestionnaire_id(String questionnaire_id) {
            this.questionnaire_id = questionnaire_id;
        }

        public String getProblem_type() {
            return problem_type;
        }

        public void setProblem_type(String problem_type) {
            this.problem_type = problem_type;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getIs_real() {
            return is_real;
        }

        public void setIs_real(String is_real) {
            this.is_real = is_real;
        }
    }

    public class QuestionOption {

        private String id;
        private String name;
        private String is_add_text;
        private List<String> relation_problem_id;
        private String problem_id;
        private String created_at;
        private String updated_at;

        public List<String> getRelation_problem_id() {
            return relation_problem_id;
        }

        public void setRelation_problem_id(List<String> relation_problem_id) {
            this.relation_problem_id = relation_problem_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIs_add_text() {
            return is_add_text;
        }

        public void setIs_add_text(String is_add_text) {
            this.is_add_text = is_add_text;
        }

        public String getProblem_id() {
            return problem_id;
        }

        public void setProblem_id(String problem_id) {
            this.problem_id = problem_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

//        public Question getRelation() {
//            return relation;
//        }
//
//        public void setRelation(Question relation) {
//            this.relation = relation;
//        }
    }
}
