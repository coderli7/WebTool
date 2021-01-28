package com.lxl.webtool.dao.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbBhImgquestionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbBhImgquestionExample() {
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

        public Criteria andGuidIsNull() {
            addCriterion("guid is null");
            return (Criteria) this;
        }

        public Criteria andGuidIsNotNull() {
            addCriterion("guid is not null");
            return (Criteria) this;
        }

        public Criteria andGuidEqualTo(String value) {
            addCriterion("guid =", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotEqualTo(String value) {
            addCriterion("guid <>", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidGreaterThan(String value) {
            addCriterion("guid >", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidGreaterThanOrEqualTo(String value) {
            addCriterion("guid >=", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLessThan(String value) {
            addCriterion("guid <", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLessThanOrEqualTo(String value) {
            addCriterion("guid <=", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLike(String value) {
            addCriterion("guid like", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotLike(String value) {
            addCriterion("guid not like", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidIn(List<String> values) {
            addCriterion("guid in", values, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotIn(List<String> values) {
            addCriterion("guid not in", values, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidBetween(String value1, String value2) {
            addCriterion("guid between", value1, value2, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotBetween(String value1, String value2) {
            addCriterion("guid not between", value1, value2, "guid");
            return (Criteria) this;
        }

        public Criteria andImgdataIsNull() {
            addCriterion("imgdata is null");
            return (Criteria) this;
        }

        public Criteria andImgdataIsNotNull() {
            addCriterion("imgdata is not null");
            return (Criteria) this;
        }

        public Criteria andImgdataEqualTo(String value) {
            addCriterion("imgdata =", value, "imgdata");
            return (Criteria) this;
        }

        public Criteria andImgdataNotEqualTo(String value) {
            addCriterion("imgdata <>", value, "imgdata");
            return (Criteria) this;
        }

        public Criteria andImgdataGreaterThan(String value) {
            addCriterion("imgdata >", value, "imgdata");
            return (Criteria) this;
        }

        public Criteria andImgdataGreaterThanOrEqualTo(String value) {
            addCriterion("imgdata >=", value, "imgdata");
            return (Criteria) this;
        }

        public Criteria andImgdataLessThan(String value) {
            addCriterion("imgdata <", value, "imgdata");
            return (Criteria) this;
        }

        public Criteria andImgdataLessThanOrEqualTo(String value) {
            addCriterion("imgdata <=", value, "imgdata");
            return (Criteria) this;
        }

        public Criteria andImgdataLike(String value) {
            addCriterion("imgdata like", value, "imgdata");
            return (Criteria) this;
        }

        public Criteria andImgdataNotLike(String value) {
            addCriterion("imgdata not like", value, "imgdata");
            return (Criteria) this;
        }

        public Criteria andImgdataIn(List<String> values) {
            addCriterion("imgdata in", values, "imgdata");
            return (Criteria) this;
        }

        public Criteria andImgdataNotIn(List<String> values) {
            addCriterion("imgdata not in", values, "imgdata");
            return (Criteria) this;
        }

        public Criteria andImgdataBetween(String value1, String value2) {
            addCriterion("imgdata between", value1, value2, "imgdata");
            return (Criteria) this;
        }

        public Criteria andImgdataNotBetween(String value1, String value2) {
            addCriterion("imgdata not between", value1, value2, "imgdata");
            return (Criteria) this;
        }

        public Criteria andImgurlIsNull() {
            addCriterion("imgurl is null");
            return (Criteria) this;
        }

        public Criteria andImgurlIsNotNull() {
            addCriterion("imgurl is not null");
            return (Criteria) this;
        }

        public Criteria andImgurlEqualTo(String value) {
            addCriterion("imgurl =", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotEqualTo(String value) {
            addCriterion("imgurl <>", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlGreaterThan(String value) {
            addCriterion("imgurl >", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlGreaterThanOrEqualTo(String value) {
            addCriterion("imgurl >=", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLessThan(String value) {
            addCriterion("imgurl <", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLessThanOrEqualTo(String value) {
            addCriterion("imgurl <=", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLike(String value) {
            addCriterion("imgurl like", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotLike(String value) {
            addCriterion("imgurl not like", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlIn(List<String> values) {
            addCriterion("imgurl in", values, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotIn(List<String> values) {
            addCriterion("imgurl not in", values, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlBetween(String value1, String value2) {
            addCriterion("imgurl between", value1, value2, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotBetween(String value1, String value2) {
            addCriterion("imgurl not between", value1, value2, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImganswerIsNull() {
            addCriterion("imganswer is null");
            return (Criteria) this;
        }

        public Criteria andImganswerIsNotNull() {
            addCriterion("imganswer is not null");
            return (Criteria) this;
        }

        public Criteria andImganswerEqualTo(String value) {
            addCriterion("imganswer =", value, "imganswer");
            return (Criteria) this;
        }

        public Criteria andImganswerNotEqualTo(String value) {
            addCriterion("imganswer <>", value, "imganswer");
            return (Criteria) this;
        }

        public Criteria andImganswerGreaterThan(String value) {
            addCriterion("imganswer >", value, "imganswer");
            return (Criteria) this;
        }

        public Criteria andImganswerGreaterThanOrEqualTo(String value) {
            addCriterion("imganswer >=", value, "imganswer");
            return (Criteria) this;
        }

        public Criteria andImganswerLessThan(String value) {
            addCriterion("imganswer <", value, "imganswer");
            return (Criteria) this;
        }

        public Criteria andImganswerLessThanOrEqualTo(String value) {
            addCriterion("imganswer <=", value, "imganswer");
            return (Criteria) this;
        }

        public Criteria andImganswerLike(String value) {
            addCriterion("imganswer like", value, "imganswer");
            return (Criteria) this;
        }

        public Criteria andImganswerNotLike(String value) {
            addCriterion("imganswer not like", value, "imganswer");
            return (Criteria) this;
        }

        public Criteria andImganswerIn(List<String> values) {
            addCriterion("imganswer in", values, "imganswer");
            return (Criteria) this;
        }

        public Criteria andImganswerNotIn(List<String> values) {
            addCriterion("imganswer not in", values, "imganswer");
            return (Criteria) this;
        }

        public Criteria andImganswerBetween(String value1, String value2) {
            addCriterion("imganswer between", value1, value2, "imganswer");
            return (Criteria) this;
        }

        public Criteria andImganswerNotBetween(String value1, String value2) {
            addCriterion("imganswer not between", value1, value2, "imganswer");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusIsNull() {
            addCriterion("imganswerstatus is null");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusIsNotNull() {
            addCriterion("imganswerstatus is not null");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusEqualTo(String value) {
            addCriterion("imganswerstatus =", value, "imganswerstatus");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusNotEqualTo(String value) {
            addCriterion("imganswerstatus <>", value, "imganswerstatus");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusGreaterThan(String value) {
            addCriterion("imganswerstatus >", value, "imganswerstatus");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusGreaterThanOrEqualTo(String value) {
            addCriterion("imganswerstatus >=", value, "imganswerstatus");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusLessThan(String value) {
            addCriterion("imganswerstatus <", value, "imganswerstatus");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusLessThanOrEqualTo(String value) {
            addCriterion("imganswerstatus <=", value, "imganswerstatus");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusLike(String value) {
            addCriterion("imganswerstatus like", value, "imganswerstatus");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusNotLike(String value) {
            addCriterion("imganswerstatus not like", value, "imganswerstatus");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusIn(List<String> values) {
            addCriterion("imganswerstatus in", values, "imganswerstatus");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusNotIn(List<String> values) {
            addCriterion("imganswerstatus not in", values, "imganswerstatus");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusBetween(String value1, String value2) {
            addCriterion("imganswerstatus between", value1, value2, "imganswerstatus");
            return (Criteria) this;
        }

        public Criteria andImganswerstatusNotBetween(String value1, String value2) {
            addCriterion("imganswerstatus not between", value1, value2, "imganswerstatus");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusIsNull() {
            addCriterion("imgoperatestatus is null");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusIsNotNull() {
            addCriterion("imgoperatestatus is not null");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusEqualTo(String value) {
            addCriterion("imgoperatestatus =", value, "imgoperatestatus");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusNotEqualTo(String value) {
            addCriterion("imgoperatestatus <>", value, "imgoperatestatus");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusGreaterThan(String value) {
            addCriterion("imgoperatestatus >", value, "imgoperatestatus");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusGreaterThanOrEqualTo(String value) {
            addCriterion("imgoperatestatus >=", value, "imgoperatestatus");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusLessThan(String value) {
            addCriterion("imgoperatestatus <", value, "imgoperatestatus");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusLessThanOrEqualTo(String value) {
            addCriterion("imgoperatestatus <=", value, "imgoperatestatus");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusLike(String value) {
            addCriterion("imgoperatestatus like", value, "imgoperatestatus");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusNotLike(String value) {
            addCriterion("imgoperatestatus not like", value, "imgoperatestatus");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusIn(List<String> values) {
            addCriterion("imgoperatestatus in", values, "imgoperatestatus");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusNotIn(List<String> values) {
            addCriterion("imgoperatestatus not in", values, "imgoperatestatus");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusBetween(String value1, String value2) {
            addCriterion("imgoperatestatus between", value1, value2, "imgoperatestatus");
            return (Criteria) this;
        }

        public Criteria andImgoperatestatusNotBetween(String value1, String value2) {
            addCriterion("imgoperatestatus not between", value1, value2, "imgoperatestatus");
            return (Criteria) this;
        }

        public Criteria andImgcreatedateIsNull() {
            addCriterion("imgcreatedate is null");
            return (Criteria) this;
        }

        public Criteria andImgcreatedateIsNotNull() {
            addCriterion("imgcreatedate is not null");
            return (Criteria) this;
        }

        public Criteria andImgcreatedateEqualTo(Long value) {
            addCriterion("imgcreatedate =", value, "imgcreatedate");
            return (Criteria) this;
        }

        public Criteria andImgcreatedateNotEqualTo(Long value) {
            addCriterion("imgcreatedate <>", value, "imgcreatedate");
            return (Criteria) this;
        }

        public Criteria andImgcreatedateGreaterThan(Long value) {
            addCriterion("imgcreatedate >", value, "imgcreatedate");
            return (Criteria) this;
        }

        public Criteria andImgcreatedateGreaterThanOrEqualTo(Long value) {
            addCriterion("imgcreatedate >=", value, "imgcreatedate");
            return (Criteria) this;
        }

        public Criteria andImgcreatedateLessThan(Long value) {
            addCriterion("imgcreatedate <", value, "imgcreatedate");
            return (Criteria) this;
        }

        public Criteria andImgcreatedateLessThanOrEqualTo(Long value) {
            addCriterion("imgcreatedate <=", value, "imgcreatedate");
            return (Criteria) this;
        }

        public Criteria andImgcreatedateIn(List<Long> values) {
            addCriterion("imgcreatedate in", values, "imgcreatedate");
            return (Criteria) this;
        }

        public Criteria andImgcreatedateNotIn(List<Long> values) {
            addCriterion("imgcreatedate not in", values, "imgcreatedate");
            return (Criteria) this;
        }

        public Criteria andImgcreatedateBetween(Long value1, Long value2) {
            addCriterion("imgcreatedate between", value1, value2, "imgcreatedate");
            return (Criteria) this;
        }

        public Criteria andImgcreatedateNotBetween(Long value1, Long value2) {
            addCriterion("imgcreatedate not between", value1, value2, "imgcreatedate");
            return (Criteria) this;
        }

        public Criteria andImgupdatedateIsNull() {
            addCriterion("imgupdatedate is null");
            return (Criteria) this;
        }

        public Criteria andImgupdatedateIsNotNull() {
            addCriterion("imgupdatedate is not null");
            return (Criteria) this;
        }

        public Criteria andImgupdatedateEqualTo(Long value) {
            addCriterion("imgupdatedate =", value, "imgupdatedate");
            return (Criteria) this;
        }

        public Criteria andImgupdatedateNotEqualTo(Long value) {
            addCriterion("imgupdatedate <>", value, "imgupdatedate");
            return (Criteria) this;
        }

        public Criteria andImgupdatedateGreaterThan(Long value) {
            addCriterion("imgupdatedate >", value, "imgupdatedate");
            return (Criteria) this;
        }

        public Criteria andImgupdatedateGreaterThanOrEqualTo(Long value) {
            addCriterion("imgupdatedate >=", value, "imgupdatedate");
            return (Criteria) this;
        }

        public Criteria andImgupdatedateLessThan(Long value) {
            addCriterion("imgupdatedate <", value, "imgupdatedate");
            return (Criteria) this;
        }

        public Criteria andImgupdatedateLessThanOrEqualTo(Long value) {
            addCriterion("imgupdatedate <=", value, "imgupdatedate");
            return (Criteria) this;
        }

        public Criteria andImgupdatedateIn(List<Long> values) {
            addCriterion("imgupdatedate in", values, "imgupdatedate");
            return (Criteria) this;
        }

        public Criteria andImgupdatedateNotIn(List<Long> values) {
            addCriterion("imgupdatedate not in", values, "imgupdatedate");
            return (Criteria) this;
        }

        public Criteria andImgupdatedateBetween(Long value1, Long value2) {
            addCriterion("imgupdatedate between", value1, value2, "imgupdatedate");
            return (Criteria) this;
        }

        public Criteria andImgupdatedateNotBetween(Long value1, Long value2) {
            addCriterion("imgupdatedate not between", value1, value2, "imgupdatedate");
            return (Criteria) this;
        }

        public Criteria andRemark1IsNull() {
            addCriterion("remark1 is null");
            return (Criteria) this;
        }

        public Criteria andRemark1IsNotNull() {
            addCriterion("remark1 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark1EqualTo(String value) {
            addCriterion("remark1 =", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotEqualTo(String value) {
            addCriterion("remark1 <>", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1GreaterThan(String value) {
            addCriterion("remark1 >", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1GreaterThanOrEqualTo(String value) {
            addCriterion("remark1 >=", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1LessThan(String value) {
            addCriterion("remark1 <", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1LessThanOrEqualTo(String value) {
            addCriterion("remark1 <=", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1Like(String value) {
            addCriterion("remark1 like", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotLike(String value) {
            addCriterion("remark1 not like", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1In(List<String> values) {
            addCriterion("remark1 in", values, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotIn(List<String> values) {
            addCriterion("remark1 not in", values, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1Between(String value1, String value2) {
            addCriterion("remark1 between", value1, value2, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotBetween(String value1, String value2) {
            addCriterion("remark1 not between", value1, value2, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark2IsNull() {
            addCriterion("remark2 is null");
            return (Criteria) this;
        }

        public Criteria andRemark2IsNotNull() {
            addCriterion("remark2 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark2EqualTo(String value) {
            addCriterion("remark2 =", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotEqualTo(String value) {
            addCriterion("remark2 <>", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2GreaterThan(String value) {
            addCriterion("remark2 >", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2GreaterThanOrEqualTo(String value) {
            addCriterion("remark2 >=", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2LessThan(String value) {
            addCriterion("remark2 <", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2LessThanOrEqualTo(String value) {
            addCriterion("remark2 <=", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2Like(String value) {
            addCriterion("remark2 like", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotLike(String value) {
            addCriterion("remark2 not like", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2In(List<String> values) {
            addCriterion("remark2 in", values, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotIn(List<String> values) {
            addCriterion("remark2 not in", values, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2Between(String value1, String value2) {
            addCriterion("remark2 between", value1, value2, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotBetween(String value1, String value2) {
            addCriterion("remark2 not between", value1, value2, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark3IsNull() {
            addCriterion("remark3 is null");
            return (Criteria) this;
        }

        public Criteria andRemark3IsNotNull() {
            addCriterion("remark3 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark3EqualTo(String value) {
            addCriterion("remark3 =", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3NotEqualTo(String value) {
            addCriterion("remark3 <>", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3GreaterThan(String value) {
            addCriterion("remark3 >", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3GreaterThanOrEqualTo(String value) {
            addCriterion("remark3 >=", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3LessThan(String value) {
            addCriterion("remark3 <", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3LessThanOrEqualTo(String value) {
            addCriterion("remark3 <=", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3Like(String value) {
            addCriterion("remark3 like", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3NotLike(String value) {
            addCriterion("remark3 not like", value, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3In(List<String> values) {
            addCriterion("remark3 in", values, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3NotIn(List<String> values) {
            addCriterion("remark3 not in", values, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3Between(String value1, String value2) {
            addCriterion("remark3 between", value1, value2, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark3NotBetween(String value1, String value2) {
            addCriterion("remark3 not between", value1, value2, "remark3");
            return (Criteria) this;
        }

        public Criteria andRemark4IsNull() {
            addCriterion("remark4 is null");
            return (Criteria) this;
        }

        public Criteria andRemark4IsNotNull() {
            addCriterion("remark4 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark4EqualTo(String value) {
            addCriterion("remark4 =", value, "remark4");
            return (Criteria) this;
        }

        public Criteria andRemark4NotEqualTo(String value) {
            addCriterion("remark4 <>", value, "remark4");
            return (Criteria) this;
        }

        public Criteria andRemark4GreaterThan(String value) {
            addCriterion("remark4 >", value, "remark4");
            return (Criteria) this;
        }

        public Criteria andRemark4GreaterThanOrEqualTo(String value) {
            addCriterion("remark4 >=", value, "remark4");
            return (Criteria) this;
        }

        public Criteria andRemark4LessThan(String value) {
            addCriterion("remark4 <", value, "remark4");
            return (Criteria) this;
        }

        public Criteria andRemark4LessThanOrEqualTo(String value) {
            addCriterion("remark4 <=", value, "remark4");
            return (Criteria) this;
        }

        public Criteria andRemark4Like(String value) {
            addCriterion("remark4 like", value, "remark4");
            return (Criteria) this;
        }

        public Criteria andRemark4NotLike(String value) {
            addCriterion("remark4 not like", value, "remark4");
            return (Criteria) this;
        }

        public Criteria andRemark4In(List<String> values) {
            addCriterion("remark4 in", values, "remark4");
            return (Criteria) this;
        }

        public Criteria andRemark4NotIn(List<String> values) {
            addCriterion("remark4 not in", values, "remark4");
            return (Criteria) this;
        }

        public Criteria andRemark4Between(String value1, String value2) {
            addCriterion("remark4 between", value1, value2, "remark4");
            return (Criteria) this;
        }

        public Criteria andRemark4NotBetween(String value1, String value2) {
            addCriterion("remark4 not between", value1, value2, "remark4");
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