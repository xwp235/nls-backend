package com.nlsapi.core.business.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MastSmsCodeEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MastSmsCodeEntityExample() {
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

        public Criteria andMscIdIsNull() {
            addCriterion("msc_id is null");
            return (Criteria) this;
        }

        public Criteria andMscIdIsNotNull() {
            addCriterion("msc_id is not null");
            return (Criteria) this;
        }

        public Criteria andMscIdEqualTo(Long value) {
            addCriterion("msc_id =", value, "mscId");
            return (Criteria) this;
        }

        public Criteria andMscIdNotEqualTo(Long value) {
            addCriterion("msc_id <>", value, "mscId");
            return (Criteria) this;
        }

        public Criteria andMscIdGreaterThan(Long value) {
            addCriterion("msc_id >", value, "mscId");
            return (Criteria) this;
        }

        public Criteria andMscIdGreaterThanOrEqualTo(Long value) {
            addCriterion("msc_id >=", value, "mscId");
            return (Criteria) this;
        }

        public Criteria andMscIdLessThan(Long value) {
            addCriterion("msc_id <", value, "mscId");
            return (Criteria) this;
        }

        public Criteria andMscIdLessThanOrEqualTo(Long value) {
            addCriterion("msc_id <=", value, "mscId");
            return (Criteria) this;
        }

        public Criteria andMscIdIn(List<Long> values) {
            addCriterion("msc_id in", values, "mscId");
            return (Criteria) this;
        }

        public Criteria andMscIdNotIn(List<Long> values) {
            addCriterion("msc_id not in", values, "mscId");
            return (Criteria) this;
        }

        public Criteria andMscIdBetween(Long value1, Long value2) {
            addCriterion("msc_id between", value1, value2, "mscId");
            return (Criteria) this;
        }

        public Criteria andMscIdNotBetween(Long value1, Long value2) {
            addCriterion("msc_id not between", value1, value2, "mscId");
            return (Criteria) this;
        }

        public Criteria andMscAccountIsNull() {
            addCriterion("msc_account is null");
            return (Criteria) this;
        }

        public Criteria andMscAccountIsNotNull() {
            addCriterion("msc_account is not null");
            return (Criteria) this;
        }

        public Criteria andMscAccountEqualTo(String value) {
            addCriterion("msc_account =", value, "mscAccount");
            return (Criteria) this;
        }

        public Criteria andMscAccountNotEqualTo(String value) {
            addCriterion("msc_account <>", value, "mscAccount");
            return (Criteria) this;
        }

        public Criteria andMscAccountGreaterThan(String value) {
            addCriterion("msc_account >", value, "mscAccount");
            return (Criteria) this;
        }

        public Criteria andMscAccountGreaterThanOrEqualTo(String value) {
            addCriterion("msc_account >=", value, "mscAccount");
            return (Criteria) this;
        }

        public Criteria andMscAccountLessThan(String value) {
            addCriterion("msc_account <", value, "mscAccount");
            return (Criteria) this;
        }

        public Criteria andMscAccountLessThanOrEqualTo(String value) {
            addCriterion("msc_account <=", value, "mscAccount");
            return (Criteria) this;
        }

        public Criteria andMscAccountLike(String value) {
            addCriterion("msc_account like", value, "mscAccount");
            return (Criteria) this;
        }

        public Criteria andMscAccountNotLike(String value) {
            addCriterion("msc_account not like", value, "mscAccount");
            return (Criteria) this;
        }

        public Criteria andMscAccountIn(List<String> values) {
            addCriterion("msc_account in", values, "mscAccount");
            return (Criteria) this;
        }

        public Criteria andMscAccountNotIn(List<String> values) {
            addCriterion("msc_account not in", values, "mscAccount");
            return (Criteria) this;
        }

        public Criteria andMscAccountBetween(String value1, String value2) {
            addCriterion("msc_account between", value1, value2, "mscAccount");
            return (Criteria) this;
        }

        public Criteria andMscAccountNotBetween(String value1, String value2) {
            addCriterion("msc_account not between", value1, value2, "mscAccount");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeIsNull() {
            addCriterion("msc_account_type is null");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeIsNotNull() {
            addCriterion("msc_account_type is not null");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeEqualTo(String value) {
            addCriterion("msc_account_type =", value, "mscAccountType");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeNotEqualTo(String value) {
            addCriterion("msc_account_type <>", value, "mscAccountType");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeGreaterThan(String value) {
            addCriterion("msc_account_type >", value, "mscAccountType");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeGreaterThanOrEqualTo(String value) {
            addCriterion("msc_account_type >=", value, "mscAccountType");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeLessThan(String value) {
            addCriterion("msc_account_type <", value, "mscAccountType");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeLessThanOrEqualTo(String value) {
            addCriterion("msc_account_type <=", value, "mscAccountType");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeLike(String value) {
            addCriterion("msc_account_type like", value, "mscAccountType");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeNotLike(String value) {
            addCriterion("msc_account_type not like", value, "mscAccountType");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeIn(List<String> values) {
            addCriterion("msc_account_type in", values, "mscAccountType");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeNotIn(List<String> values) {
            addCriterion("msc_account_type not in", values, "mscAccountType");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeBetween(String value1, String value2) {
            addCriterion("msc_account_type between", value1, value2, "mscAccountType");
            return (Criteria) this;
        }

        public Criteria andMscAccountTypeNotBetween(String value1, String value2) {
            addCriterion("msc_account_type not between", value1, value2, "mscAccountType");
            return (Criteria) this;
        }

        public Criteria andMscCodeIsNull() {
            addCriterion("msc_code is null");
            return (Criteria) this;
        }

        public Criteria andMscCodeIsNotNull() {
            addCriterion("msc_code is not null");
            return (Criteria) this;
        }

        public Criteria andMscCodeEqualTo(String value) {
            addCriterion("msc_code =", value, "mscCode");
            return (Criteria) this;
        }

        public Criteria andMscCodeNotEqualTo(String value) {
            addCriterion("msc_code <>", value, "mscCode");
            return (Criteria) this;
        }

        public Criteria andMscCodeGreaterThan(String value) {
            addCriterion("msc_code >", value, "mscCode");
            return (Criteria) this;
        }

        public Criteria andMscCodeGreaterThanOrEqualTo(String value) {
            addCriterion("msc_code >=", value, "mscCode");
            return (Criteria) this;
        }

        public Criteria andMscCodeLessThan(String value) {
            addCriterion("msc_code <", value, "mscCode");
            return (Criteria) this;
        }

        public Criteria andMscCodeLessThanOrEqualTo(String value) {
            addCriterion("msc_code <=", value, "mscCode");
            return (Criteria) this;
        }

        public Criteria andMscCodeLike(String value) {
            addCriterion("msc_code like", value, "mscCode");
            return (Criteria) this;
        }

        public Criteria andMscCodeNotLike(String value) {
            addCriterion("msc_code not like", value, "mscCode");
            return (Criteria) this;
        }

        public Criteria andMscCodeIn(List<String> values) {
            addCriterion("msc_code in", values, "mscCode");
            return (Criteria) this;
        }

        public Criteria andMscCodeNotIn(List<String> values) {
            addCriterion("msc_code not in", values, "mscCode");
            return (Criteria) this;
        }

        public Criteria andMscCodeBetween(String value1, String value2) {
            addCriterion("msc_code between", value1, value2, "mscCode");
            return (Criteria) this;
        }

        public Criteria andMscCodeNotBetween(String value1, String value2) {
            addCriterion("msc_code not between", value1, value2, "mscCode");
            return (Criteria) this;
        }

        public Criteria andMscUsageIsNull() {
            addCriterion("msc_usage is null");
            return (Criteria) this;
        }

        public Criteria andMscUsageIsNotNull() {
            addCriterion("msc_usage is not null");
            return (Criteria) this;
        }

        public Criteria andMscUsageEqualTo(String value) {
            addCriterion("msc_usage =", value, "mscUsage");
            return (Criteria) this;
        }

        public Criteria andMscUsageNotEqualTo(String value) {
            addCriterion("msc_usage <>", value, "mscUsage");
            return (Criteria) this;
        }

        public Criteria andMscUsageGreaterThan(String value) {
            addCriterion("msc_usage >", value, "mscUsage");
            return (Criteria) this;
        }

        public Criteria andMscUsageGreaterThanOrEqualTo(String value) {
            addCriterion("msc_usage >=", value, "mscUsage");
            return (Criteria) this;
        }

        public Criteria andMscUsageLessThan(String value) {
            addCriterion("msc_usage <", value, "mscUsage");
            return (Criteria) this;
        }

        public Criteria andMscUsageLessThanOrEqualTo(String value) {
            addCriterion("msc_usage <=", value, "mscUsage");
            return (Criteria) this;
        }

        public Criteria andMscUsageLike(String value) {
            addCriterion("msc_usage like", value, "mscUsage");
            return (Criteria) this;
        }

        public Criteria andMscUsageNotLike(String value) {
            addCriterion("msc_usage not like", value, "mscUsage");
            return (Criteria) this;
        }

        public Criteria andMscUsageIn(List<String> values) {
            addCriterion("msc_usage in", values, "mscUsage");
            return (Criteria) this;
        }

        public Criteria andMscUsageNotIn(List<String> values) {
            addCriterion("msc_usage not in", values, "mscUsage");
            return (Criteria) this;
        }

        public Criteria andMscUsageBetween(String value1, String value2) {
            addCriterion("msc_usage between", value1, value2, "mscUsage");
            return (Criteria) this;
        }

        public Criteria andMscUsageNotBetween(String value1, String value2) {
            addCriterion("msc_usage not between", value1, value2, "mscUsage");
            return (Criteria) this;
        }

        public Criteria andMscStatusIsNull() {
            addCriterion("msc_status is null");
            return (Criteria) this;
        }

        public Criteria andMscStatusIsNotNull() {
            addCriterion("msc_status is not null");
            return (Criteria) this;
        }

        public Criteria andMscStatusEqualTo(String value) {
            addCriterion("msc_status =", value, "mscStatus");
            return (Criteria) this;
        }

        public Criteria andMscStatusNotEqualTo(String value) {
            addCriterion("msc_status <>", value, "mscStatus");
            return (Criteria) this;
        }

        public Criteria andMscStatusGreaterThan(String value) {
            addCriterion("msc_status >", value, "mscStatus");
            return (Criteria) this;
        }

        public Criteria andMscStatusGreaterThanOrEqualTo(String value) {
            addCriterion("msc_status >=", value, "mscStatus");
            return (Criteria) this;
        }

        public Criteria andMscStatusLessThan(String value) {
            addCriterion("msc_status <", value, "mscStatus");
            return (Criteria) this;
        }

        public Criteria andMscStatusLessThanOrEqualTo(String value) {
            addCriterion("msc_status <=", value, "mscStatus");
            return (Criteria) this;
        }

        public Criteria andMscStatusLike(String value) {
            addCriterion("msc_status like", value, "mscStatus");
            return (Criteria) this;
        }

        public Criteria andMscStatusNotLike(String value) {
            addCriterion("msc_status not like", value, "mscStatus");
            return (Criteria) this;
        }

        public Criteria andMscStatusIn(List<String> values) {
            addCriterion("msc_status in", values, "mscStatus");
            return (Criteria) this;
        }

        public Criteria andMscStatusNotIn(List<String> values) {
            addCriterion("msc_status not in", values, "mscStatus");
            return (Criteria) this;
        }

        public Criteria andMscStatusBetween(String value1, String value2) {
            addCriterion("msc_status between", value1, value2, "mscStatus");
            return (Criteria) this;
        }

        public Criteria andMscStatusNotBetween(String value1, String value2) {
            addCriterion("msc_status not between", value1, value2, "mscStatus");
            return (Criteria) this;
        }

        public Criteria andMscCreatedAtIsNull() {
            addCriterion("msc_created_at is null");
            return (Criteria) this;
        }

        public Criteria andMscCreatedAtIsNotNull() {
            addCriterion("msc_created_at is not null");
            return (Criteria) this;
        }

        public Criteria andMscCreatedAtEqualTo(Date value) {
            addCriterion("msc_created_at =", value, "mscCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMscCreatedAtNotEqualTo(Date value) {
            addCriterion("msc_created_at <>", value, "mscCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMscCreatedAtGreaterThan(Date value) {
            addCriterion("msc_created_at >", value, "mscCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMscCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("msc_created_at >=", value, "mscCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMscCreatedAtLessThan(Date value) {
            addCriterion("msc_created_at <", value, "mscCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMscCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("msc_created_at <=", value, "mscCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMscCreatedAtIn(List<Date> values) {
            addCriterion("msc_created_at in", values, "mscCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMscCreatedAtNotIn(List<Date> values) {
            addCriterion("msc_created_at not in", values, "mscCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMscCreatedAtBetween(Date value1, Date value2) {
            addCriterion("msc_created_at between", value1, value2, "mscCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMscCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("msc_created_at not between", value1, value2, "mscCreatedAt");
            return (Criteria) this;
        }

        public Criteria andMscUpdatedAtIsNull() {
            addCriterion("msc_updated_at is null");
            return (Criteria) this;
        }

        public Criteria andMscUpdatedAtIsNotNull() {
            addCriterion("msc_updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andMscUpdatedAtEqualTo(Date value) {
            addCriterion("msc_updated_at =", value, "mscUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMscUpdatedAtNotEqualTo(Date value) {
            addCriterion("msc_updated_at <>", value, "mscUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMscUpdatedAtGreaterThan(Date value) {
            addCriterion("msc_updated_at >", value, "mscUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMscUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("msc_updated_at >=", value, "mscUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMscUpdatedAtLessThan(Date value) {
            addCriterion("msc_updated_at <", value, "mscUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMscUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("msc_updated_at <=", value, "mscUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMscUpdatedAtIn(List<Date> values) {
            addCriterion("msc_updated_at in", values, "mscUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMscUpdatedAtNotIn(List<Date> values) {
            addCriterion("msc_updated_at not in", values, "mscUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMscUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("msc_updated_at between", value1, value2, "mscUpdatedAt");
            return (Criteria) this;
        }

        public Criteria andMscUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("msc_updated_at not between", value1, value2, "mscUpdatedAt");
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