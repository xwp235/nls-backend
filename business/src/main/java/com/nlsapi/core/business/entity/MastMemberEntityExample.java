package com.nlsapi.core.business.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MastMemberEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MastMemberEntityExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMmIdIsNull() {
            addCriterion("mm_id is null");
            return (Criteria) this;
        }

        public Criteria andMmIdIsNotNull() {
            addCriterion("mm_id is not null");
            return (Criteria) this;
        }

        public Criteria andMmIdEqualTo(Long value) {
            addCriterion("mm_id =", value, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdNotEqualTo(Long value) {
            addCriterion("mm_id <>", value, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdGreaterThan(Long value) {
            addCriterion("mm_id >", value, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdGreaterThanOrEqualTo(Long value) {
            addCriterion("mm_id >=", value, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdLessThan(Long value) {
            addCriterion("mm_id <", value, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdLessThanOrEqualTo(Long value) {
            addCriterion("mm_id <=", value, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdIn(List<Long> values) {
            addCriterion("mm_id in", values, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdNotIn(List<Long> values) {
            addCriterion("mm_id not in", values, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdBetween(Long value1, Long value2) {
            addCriterion("mm_id between", value1, value2, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdNotBetween(Long value1, Long value2) {
            addCriterion("mm_id not between", value1, value2, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmAccountIsNull() {
            addCriterion("mm_account is null");
            return (Criteria) this;
        }

        public Criteria andMmAccountIsNotNull() {
            addCriterion("mm_account is not null");
            return (Criteria) this;
        }

        public Criteria andMmAccountEqualTo(String value) {
            addCriterion("mm_account =", value, "mmAccount");
            return (Criteria) this;
        }

        public Criteria andMmAccountNotEqualTo(String value) {
            addCriterion("mm_account <>", value, "mmAccount");
            return (Criteria) this;
        }

        public Criteria andMmAccountGreaterThan(String value) {
            addCriterion("mm_account >", value, "mmAccount");
            return (Criteria) this;
        }

        public Criteria andMmAccountGreaterThanOrEqualTo(String value) {
            addCriterion("mm_account >=", value, "mmAccount");
            return (Criteria) this;
        }

        public Criteria andMmAccountLessThan(String value) {
            addCriterion("mm_account <", value, "mmAccount");
            return (Criteria) this;
        }

        public Criteria andMmAccountLessThanOrEqualTo(String value) {
            addCriterion("mm_account <=", value, "mmAccount");
            return (Criteria) this;
        }

        public Criteria andMmAccountLike(String value) {
            addCriterion("mm_account like", value, "mmAccount");
            return (Criteria) this;
        }

        public Criteria andMmAccountNotLike(String value) {
            addCriterion("mm_account not like", value, "mmAccount");
            return (Criteria) this;
        }

        public Criteria andMmAccountIn(List<String> values) {
            addCriterion("mm_account in", values, "mmAccount");
            return (Criteria) this;
        }

        public Criteria andMmAccountNotIn(List<String> values) {
            addCriterion("mm_account not in", values, "mmAccount");
            return (Criteria) this;
        }

        public Criteria andMmAccountBetween(String value1, String value2) {
            addCriterion("mm_account between", value1, value2, "mmAccount");
            return (Criteria) this;
        }

        public Criteria andMmAccountNotBetween(String value1, String value2) {
            addCriterion("mm_account not between", value1, value2, "mmAccount");
            return (Criteria) this;
        }

        public Criteria andMmPasswordIsNull() {
            addCriterion("mm_password is null");
            return (Criteria) this;
        }

        public Criteria andMmPasswordIsNotNull() {
            addCriterion("mm_password is not null");
            return (Criteria) this;
        }

        public Criteria andMmPasswordEqualTo(String value) {
            addCriterion("mm_password =", value, "mmPassword");
            return (Criteria) this;
        }

        public Criteria andMmPasswordNotEqualTo(String value) {
            addCriterion("mm_password <>", value, "mmPassword");
            return (Criteria) this;
        }

        public Criteria andMmPasswordGreaterThan(String value) {
            addCriterion("mm_password >", value, "mmPassword");
            return (Criteria) this;
        }

        public Criteria andMmPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("mm_password >=", value, "mmPassword");
            return (Criteria) this;
        }

        public Criteria andMmPasswordLessThan(String value) {
            addCriterion("mm_password <", value, "mmPassword");
            return (Criteria) this;
        }

        public Criteria andMmPasswordLessThanOrEqualTo(String value) {
            addCriterion("mm_password <=", value, "mmPassword");
            return (Criteria) this;
        }

        public Criteria andMmPasswordLike(String value) {
            addCriterion("mm_password like", value, "mmPassword");
            return (Criteria) this;
        }

        public Criteria andMmPasswordNotLike(String value) {
            addCriterion("mm_password not like", value, "mmPassword");
            return (Criteria) this;
        }

        public Criteria andMmPasswordIn(List<String> values) {
            addCriterion("mm_password in", values, "mmPassword");
            return (Criteria) this;
        }

        public Criteria andMmPasswordNotIn(List<String> values) {
            addCriterion("mm_password not in", values, "mmPassword");
            return (Criteria) this;
        }

        public Criteria andMmPasswordBetween(String value1, String value2) {
            addCriterion("mm_password between", value1, value2, "mmPassword");
            return (Criteria) this;
        }

        public Criteria andMmPasswordNotBetween(String value1, String value2) {
            addCriterion("mm_password not between", value1, value2, "mmPassword");
            return (Criteria) this;
        }

        public Criteria andMmNicknameIsNull() {
            addCriterion("mm_nickname is null");
            return (Criteria) this;
        }

        public Criteria andMmNicknameIsNotNull() {
            addCriterion("mm_nickname is not null");
            return (Criteria) this;
        }

        public Criteria andMmNicknameEqualTo(String value) {
            addCriterion("mm_nickname =", value, "mmNickname");
            return (Criteria) this;
        }

        public Criteria andMmNicknameNotEqualTo(String value) {
            addCriterion("mm_nickname <>", value, "mmNickname");
            return (Criteria) this;
        }

        public Criteria andMmNicknameGreaterThan(String value) {
            addCriterion("mm_nickname >", value, "mmNickname");
            return (Criteria) this;
        }

        public Criteria andMmNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("mm_nickname >=", value, "mmNickname");
            return (Criteria) this;
        }

        public Criteria andMmNicknameLessThan(String value) {
            addCriterion("mm_nickname <", value, "mmNickname");
            return (Criteria) this;
        }

        public Criteria andMmNicknameLessThanOrEqualTo(String value) {
            addCriterion("mm_nickname <=", value, "mmNickname");
            return (Criteria) this;
        }

        public Criteria andMmNicknameLike(String value) {
            addCriterion("mm_nickname like", value, "mmNickname");
            return (Criteria) this;
        }

        public Criteria andMmNicknameNotLike(String value) {
            addCriterion("mm_nickname not like", value, "mmNickname");
            return (Criteria) this;
        }

        public Criteria andMmNicknameIn(List<String> values) {
            addCriterion("mm_nickname in", values, "mmNickname");
            return (Criteria) this;
        }

        public Criteria andMmNicknameNotIn(List<String> values) {
            addCriterion("mm_nickname not in", values, "mmNickname");
            return (Criteria) this;
        }

        public Criteria andMmNicknameBetween(String value1, String value2) {
            addCriterion("mm_nickname between", value1, value2, "mmNickname");
            return (Criteria) this;
        }

        public Criteria andMmNicknameNotBetween(String value1, String value2) {
            addCriterion("mm_nickname not between", value1, value2, "mmNickname");
            return (Criteria) this;
        }

        public Criteria andMmCreatedAtIsNull() {
            addCriterion("mm_created_at is null");
            return (Criteria) this;
        }

        public Criteria andMmCreatedAtIsNotNull() {
            addCriterion("mm_created_at is not null");
            return (Criteria) this;
        }

        public Criteria andMmCreatedAtEqualTo(Date value) {
            addCriterion("mm_created_at =", value, "mmCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMmCreatedAtNotEqualTo(Date value) {
            addCriterion("mm_created_at <>", value, "mmCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMmCreatedAtGreaterThan(Date value) {
            addCriterion("mm_created_at >", value, "mmCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMmCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("mm_created_at >=", value, "mmCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMmCreatedAtLessThan(Date value) {
            addCriterion("mm_created_at <", value, "mmCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMmCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("mm_created_at <=", value, "mmCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMmCreatedAtIn(List<Date> values) {
            addCriterion("mm_created_at in", values, "mmCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMmCreatedAtNotIn(List<Date> values) {
            addCriterion("mm_created_at not in", values, "mmCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMmCreatedAtBetween(Date value1, Date value2) {
            addCriterion("mm_created_at between", value1, value2, "mmCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMmCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("mm_created_at not between", value1, value2, "mmCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMmUpdatedAtIsNull() {
            addCriterion("mm_updated_at is null");
            return (Criteria) this;
        }

        public Criteria andMmUpdatedAtIsNotNull() {
            addCriterion("mm_updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andMmUpdatedAtEqualTo(Date value) {
            addCriterion("mm_updated_at =", value, "mmUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMmUpdatedAtNotEqualTo(Date value) {
            addCriterion("mm_updated_at <>", value, "mmUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMmUpdatedAtGreaterThan(Date value) {
            addCriterion("mm_updated_at >", value, "mmUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMmUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("mm_updated_at >=", value, "mmUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMmUpdatedAtLessThan(Date value) {
            addCriterion("mm_updated_at <", value, "mmUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMmUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("mm_updated_at <=", value, "mmUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMmUpdatedAtIn(List<Date> values) {
            addCriterion("mm_updated_at in", values, "mmUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMmUpdatedAtNotIn(List<Date> values) {
            addCriterion("mm_updated_at not in", values, "mmUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMmUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("mm_updated_at between", value1, value2, "mmUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMmUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("mm_updated_at not between", value1, value2, "mmUpdatedAt");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}