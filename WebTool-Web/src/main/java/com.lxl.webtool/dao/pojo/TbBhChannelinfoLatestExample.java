package com.lxl.webtool.dao.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbBhChannelinfoLatestExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbBhChannelinfoLatestExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andChannelkeyIsNull() {
            addCriterion("channelkey is null");
            return (Criteria) this;
        }

        public Criteria andChannelkeyIsNotNull() {
            addCriterion("channelkey is not null");
            return (Criteria) this;
        }

        public Criteria andChannelkeyEqualTo(String value) {
            addCriterion("channelkey =", value, "channelkey");
            return (Criteria) this;
        }

        public Criteria andChannelkeyNotEqualTo(String value) {
            addCriterion("channelkey <>", value, "channelkey");
            return (Criteria) this;
        }

        public Criteria andChannelkeyGreaterThan(String value) {
            addCriterion("channelkey >", value, "channelkey");
            return (Criteria) this;
        }

        public Criteria andChannelkeyGreaterThanOrEqualTo(String value) {
            addCriterion("channelkey >=", value, "channelkey");
            return (Criteria) this;
        }

        public Criteria andChannelkeyLessThan(String value) {
            addCriterion("channelkey <", value, "channelkey");
            return (Criteria) this;
        }

        public Criteria andChannelkeyLessThanOrEqualTo(String value) {
            addCriterion("channelkey <=", value, "channelkey");
            return (Criteria) this;
        }

        public Criteria andChannelkeyLike(String value) {
            addCriterion("channelkey like", value, "channelkey");
            return (Criteria) this;
        }

        public Criteria andChannelkeyNotLike(String value) {
            addCriterion("channelkey not like", value, "channelkey");
            return (Criteria) this;
        }

        public Criteria andChannelkeyIn(List<String> values) {
            addCriterion("channelkey in", values, "channelkey");
            return (Criteria) this;
        }

        public Criteria andChannelkeyNotIn(List<String> values) {
            addCriterion("channelkey not in", values, "channelkey");
            return (Criteria) this;
        }

        public Criteria andChannelkeyBetween(String value1, String value2) {
            addCriterion("channelkey between", value1, value2, "channelkey");
            return (Criteria) this;
        }

        public Criteria andChannelkeyNotBetween(String value1, String value2) {
            addCriterion("channelkey not between", value1, value2, "channelkey");
            return (Criteria) this;
        }

        public Criteria andLogininfoIsNull() {
            addCriterion("logininfo is null");
            return (Criteria) this;
        }

        public Criteria andLogininfoIsNotNull() {
            addCriterion("logininfo is not null");
            return (Criteria) this;
        }

        public Criteria andLogininfoEqualTo(String value) {
            addCriterion("logininfo =", value, "logininfo");
            return (Criteria) this;
        }

        public Criteria andLogininfoNotEqualTo(String value) {
            addCriterion("logininfo <>", value, "logininfo");
            return (Criteria) this;
        }

        public Criteria andLogininfoGreaterThan(String value) {
            addCriterion("logininfo >", value, "logininfo");
            return (Criteria) this;
        }

        public Criteria andLogininfoGreaterThanOrEqualTo(String value) {
            addCriterion("logininfo >=", value, "logininfo");
            return (Criteria) this;
        }

        public Criteria andLogininfoLessThan(String value) {
            addCriterion("logininfo <", value, "logininfo");
            return (Criteria) this;
        }

        public Criteria andLogininfoLessThanOrEqualTo(String value) {
            addCriterion("logininfo <=", value, "logininfo");
            return (Criteria) this;
        }

        public Criteria andLogininfoLike(String value) {
            addCriterion("logininfo like", value, "logininfo");
            return (Criteria) this;
        }

        public Criteria andLogininfoNotLike(String value) {
            addCriterion("logininfo not like", value, "logininfo");
            return (Criteria) this;
        }

        public Criteria andLogininfoIn(List<String> values) {
            addCriterion("logininfo in", values, "logininfo");
            return (Criteria) this;
        }

        public Criteria andLogininfoNotIn(List<String> values) {
            addCriterion("logininfo not in", values, "logininfo");
            return (Criteria) this;
        }

        public Criteria andLogininfoBetween(String value1, String value2) {
            addCriterion("logininfo between", value1, value2, "logininfo");
            return (Criteria) this;
        }

        public Criteria andLogininfoNotBetween(String value1, String value2) {
            addCriterion("logininfo not between", value1, value2, "logininfo");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("createdate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("createdate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Long value) {
            addCriterion("createdate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Long value) {
            addCriterion("createdate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Long value) {
            addCriterion("createdate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Long value) {
            addCriterion("createdate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Long value) {
            addCriterion("createdate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Long value) {
            addCriterion("createdate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Long> values) {
            addCriterion("createdate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Long> values) {
            addCriterion("createdate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Long value1, Long value2) {
            addCriterion("createdate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Long value1, Long value2) {
            addCriterion("createdate not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIsNull() {
            addCriterion("updatedate is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIsNotNull() {
            addCriterion("updatedate is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateEqualTo(Long value) {
            addCriterion("updatedate =", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotEqualTo(Long value) {
            addCriterion("updatedate <>", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThan(Long value) {
            addCriterion("updatedate >", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThanOrEqualTo(Long value) {
            addCriterion("updatedate >=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThan(Long value) {
            addCriterion("updatedate <", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThanOrEqualTo(Long value) {
            addCriterion("updatedate <=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIn(List<Long> values) {
            addCriterion("updatedate in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotIn(List<Long> values) {
            addCriterion("updatedate not in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateBetween(Long value1, Long value2) {
            addCriterion("updatedate between", value1, value2, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotBetween(Long value1, Long value2) {
            addCriterion("updatedate not between", value1, value2, "updatedate");
            return (Criteria) this;
        }

        public Criteria andProxyurlIsNull() {
            addCriterion("proxyUrl is null");
            return (Criteria) this;
        }

        public Criteria andProxyurlIsNotNull() {
            addCriterion("proxyUrl is not null");
            return (Criteria) this;
        }

        public Criteria andProxyurlEqualTo(String value) {
            addCriterion("proxyUrl =", value, "proxyUrl");
            return (Criteria) this;
        }

        public Criteria andProxyurlNotEqualTo(String value) {
            addCriterion("proxyUrl <>", value, "proxyUrl");
            return (Criteria) this;
        }

        public Criteria andProxyurlGreaterThan(String value) {
            addCriterion("proxyUrl >", value, "proxyUrl");
            return (Criteria) this;
        }

        public Criteria andProxyurlGreaterThanOrEqualTo(String value) {
            addCriterion("proxyUrl >=", value, "proxyUrl");
            return (Criteria) this;
        }

        public Criteria andProxyurlLessThan(String value) {
            addCriterion("proxyUrl <", value, "proxyUrl");
            return (Criteria) this;
        }

        public Criteria andProxyurlLessThanOrEqualTo(String value) {
            addCriterion("proxyUrl <=", value, "proxyUrl");
            return (Criteria) this;
        }

        public Criteria andProxyurlLike(String value) {
            addCriterion("proxyUrl like", value, "proxyUrl");
            return (Criteria) this;
        }

        public Criteria andProxyurlNotLike(String value) {
            addCriterion("proxyUrl not like", value, "proxyUrl");
            return (Criteria) this;
        }

        public Criteria andProxyurlIn(List<String> values) {
            addCriterion("proxyUrl in", values, "proxyUrl");
            return (Criteria) this;
        }

        public Criteria andProxyurlNotIn(List<String> values) {
            addCriterion("proxyUrl not in", values, "proxyUrl");
            return (Criteria) this;
        }

        public Criteria andProxyurlBetween(String value1, String value2) {
            addCriterion("proxyUrl between", value1, value2, "proxyUrl");
            return (Criteria) this;
        }

        public Criteria andProxyurlNotBetween(String value1, String value2) {
            addCriterion("proxyUrl not between", value1, value2, "proxyUrl");
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