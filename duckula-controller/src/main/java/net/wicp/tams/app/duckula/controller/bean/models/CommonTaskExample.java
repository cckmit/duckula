package net.wicp.tams.app.duckula.controller.bean.models;

import java.util.ArrayList;
import java.util.List;

public class CommonTaskExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table common_task
	 * @mbg.generated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table common_task
	 * @mbg.generated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table common_task
	 * @mbg.generated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	public CommonTaskExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table common_task
	 * @mbg.generated
	 */
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

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Long> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andVersionIsNull() {
			addCriterion("version is null");
			return (Criteria) this;
		}

		public Criteria andVersionIsNotNull() {
			addCriterion("version is not null");
			return (Criteria) this;
		}

		public Criteria andVersionEqualTo(String value) {
			addCriterion("version =", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotEqualTo(String value) {
			addCriterion("version <>", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionGreaterThan(String value) {
			addCriterion("version >", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionGreaterThanOrEqualTo(String value) {
			addCriterion("version >=", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionLessThan(String value) {
			addCriterion("version <", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionLessThanOrEqualTo(String value) {
			addCriterion("version <=", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionLike(String value) {
			addCriterion("version like", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotLike(String value) {
			addCriterion("version not like", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionIn(List<String> values) {
			addCriterion("version in", values, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotIn(List<String> values) {
			addCriterion("version not in", values, "version");
			return (Criteria) this;
		}

		public Criteria andVersionBetween(String value1, String value2) {
			addCriterion("version between", value1, value2, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotBetween(String value1, String value2) {
			addCriterion("version not between", value1, value2, "version");
			return (Criteria) this;
		}

		public Criteria andBufferTypeIsNull() {
			addCriterion("buffer_type is null");
			return (Criteria) this;
		}

		public Criteria andBufferTypeIsNotNull() {
			addCriterion("buffer_type is not null");
			return (Criteria) this;
		}

		public Criteria andBufferTypeEqualTo(String value) {
			addCriterion("buffer_type =", value, "bufferType");
			return (Criteria) this;
		}

		public Criteria andBufferTypeNotEqualTo(String value) {
			addCriterion("buffer_type <>", value, "bufferType");
			return (Criteria) this;
		}

		public Criteria andBufferTypeGreaterThan(String value) {
			addCriterion("buffer_type >", value, "bufferType");
			return (Criteria) this;
		}

		public Criteria andBufferTypeGreaterThanOrEqualTo(String value) {
			addCriterion("buffer_type >=", value, "bufferType");
			return (Criteria) this;
		}

		public Criteria andBufferTypeLessThan(String value) {
			addCriterion("buffer_type <", value, "bufferType");
			return (Criteria) this;
		}

		public Criteria andBufferTypeLessThanOrEqualTo(String value) {
			addCriterion("buffer_type <=", value, "bufferType");
			return (Criteria) this;
		}

		public Criteria andBufferTypeLike(String value) {
			addCriterion("buffer_type like", value, "bufferType");
			return (Criteria) this;
		}

		public Criteria andBufferTypeNotLike(String value) {
			addCriterion("buffer_type not like", value, "bufferType");
			return (Criteria) this;
		}

		public Criteria andBufferTypeIn(List<String> values) {
			addCriterion("buffer_type in", values, "bufferType");
			return (Criteria) this;
		}

		public Criteria andBufferTypeNotIn(List<String> values) {
			addCriterion("buffer_type not in", values, "bufferType");
			return (Criteria) this;
		}

		public Criteria andBufferTypeBetween(String value1, String value2) {
			addCriterion("buffer_type between", value1, value2, "bufferType");
			return (Criteria) this;
		}

		public Criteria andBufferTypeNotBetween(String value1, String value2) {
			addCriterion("buffer_type not between", value1, value2, "bufferType");
			return (Criteria) this;
		}

		public Criteria andSendnumIsNull() {
			addCriterion("sendNum is null");
			return (Criteria) this;
		}

		public Criteria andSendnumIsNotNull() {
			addCriterion("sendNum is not null");
			return (Criteria) this;
		}

		public Criteria andSendnumEqualTo(Integer value) {
			addCriterion("sendNum =", value, "sendnum");
			return (Criteria) this;
		}

		public Criteria andSendnumNotEqualTo(Integer value) {
			addCriterion("sendNum <>", value, "sendnum");
			return (Criteria) this;
		}

		public Criteria andSendnumGreaterThan(Integer value) {
			addCriterion("sendNum >", value, "sendnum");
			return (Criteria) this;
		}

		public Criteria andSendnumGreaterThanOrEqualTo(Integer value) {
			addCriterion("sendNum >=", value, "sendnum");
			return (Criteria) this;
		}

		public Criteria andSendnumLessThan(Integer value) {
			addCriterion("sendNum <", value, "sendnum");
			return (Criteria) this;
		}

		public Criteria andSendnumLessThanOrEqualTo(Integer value) {
			addCriterion("sendNum <=", value, "sendnum");
			return (Criteria) this;
		}

		public Criteria andSendnumIn(List<Integer> values) {
			addCriterion("sendNum in", values, "sendnum");
			return (Criteria) this;
		}

		public Criteria andSendnumNotIn(List<Integer> values) {
			addCriterion("sendNum not in", values, "sendnum");
			return (Criteria) this;
		}

		public Criteria andSendnumBetween(Integer value1, Integer value2) {
			addCriterion("sendNum between", value1, value2, "sendnum");
			return (Criteria) this;
		}

		public Criteria andSendnumNotBetween(Integer value1, Integer value2) {
			addCriterion("sendNum not between", value1, value2, "sendnum");
			return (Criteria) this;
		}

		public Criteria andRuleIsNull() {
			addCriterion("rule is null");
			return (Criteria) this;
		}

		public Criteria andRuleIsNotNull() {
			addCriterion("rule is not null");
			return (Criteria) this;
		}

		public Criteria andRuleEqualTo(String value) {
			addCriterion("rule =", value, "rule");
			return (Criteria) this;
		}

		public Criteria andRuleNotEqualTo(String value) {
			addCriterion("rule <>", value, "rule");
			return (Criteria) this;
		}

		public Criteria andRuleGreaterThan(String value) {
			addCriterion("rule >", value, "rule");
			return (Criteria) this;
		}

		public Criteria andRuleGreaterThanOrEqualTo(String value) {
			addCriterion("rule >=", value, "rule");
			return (Criteria) this;
		}

		public Criteria andRuleLessThan(String value) {
			addCriterion("rule <", value, "rule");
			return (Criteria) this;
		}

		public Criteria andRuleLessThanOrEqualTo(String value) {
			addCriterion("rule <=", value, "rule");
			return (Criteria) this;
		}

		public Criteria andRuleLike(String value) {
			addCriterion("rule like", value, "rule");
			return (Criteria) this;
		}

		public Criteria andRuleNotLike(String value) {
			addCriterion("rule not like", value, "rule");
			return (Criteria) this;
		}

		public Criteria andRuleIn(List<String> values) {
			addCriterion("rule in", values, "rule");
			return (Criteria) this;
		}

		public Criteria andRuleNotIn(List<String> values) {
			addCriterion("rule not in", values, "rule");
			return (Criteria) this;
		}

		public Criteria andRuleBetween(String value1, String value2) {
			addCriterion("rule between", value1, value2, "rule");
			return (Criteria) this;
		}

		public Criteria andRuleNotBetween(String value1, String value2) {
			addCriterion("rule not between", value1, value2, "rule");
			return (Criteria) this;
		}

		public Criteria andDeployIdIsNull() {
			addCriterion("deploy_id is null");
			return (Criteria) this;
		}

		public Criteria andDeployIdIsNotNull() {
			addCriterion("deploy_id is not null");
			return (Criteria) this;
		}

		public Criteria andDeployIdEqualTo(Long value) {
			addCriterion("deploy_id =", value, "deployId");
			return (Criteria) this;
		}

		public Criteria andDeployIdNotEqualTo(Long value) {
			addCriterion("deploy_id <>", value, "deployId");
			return (Criteria) this;
		}

		public Criteria andDeployIdGreaterThan(Long value) {
			addCriterion("deploy_id >", value, "deployId");
			return (Criteria) this;
		}

		public Criteria andDeployIdGreaterThanOrEqualTo(Long value) {
			addCriterion("deploy_id >=", value, "deployId");
			return (Criteria) this;
		}

		public Criteria andDeployIdLessThan(Long value) {
			addCriterion("deploy_id <", value, "deployId");
			return (Criteria) this;
		}

		public Criteria andDeployIdLessThanOrEqualTo(Long value) {
			addCriterion("deploy_id <=", value, "deployId");
			return (Criteria) this;
		}

		public Criteria andDeployIdIn(List<Long> values) {
			addCriterion("deploy_id in", values, "deployId");
			return (Criteria) this;
		}

		public Criteria andDeployIdNotIn(List<Long> values) {
			addCriterion("deploy_id not in", values, "deployId");
			return (Criteria) this;
		}

		public Criteria andDeployIdBetween(Long value1, Long value2) {
			addCriterion("deploy_id between", value1, value2, "deployId");
			return (Criteria) this;
		}

		public Criteria andDeployIdNotBetween(Long value1, Long value2) {
			addCriterion("deploy_id not between", value1, value2, "deployId");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNull() {
			addCriterion("user_id is null");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("user_id is not null");
			return (Criteria) this;
		}

		public Criteria andUserIdEqualTo(Long value) {
			addCriterion("user_id =", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotEqualTo(Long value) {
			addCriterion("user_id <>", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThan(Long value) {
			addCriterion("user_id >", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
			addCriterion("user_id >=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThan(Long value) {
			addCriterion("user_id <", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThanOrEqualTo(Long value) {
			addCriterion("user_id <=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdIn(List<Long> values) {
			addCriterion("user_id in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotIn(List<Long> values) {
			addCriterion("user_id not in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdBetween(Long value1, Long value2) {
			addCriterion("user_id between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotBetween(Long value1, Long value2) {
			addCriterion("user_id not between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andMiddlewareIdIsNull() {
			addCriterion("middleware_id is null");
			return (Criteria) this;
		}

		public Criteria andMiddlewareIdIsNotNull() {
			addCriterion("middleware_id is not null");
			return (Criteria) this;
		}

		public Criteria andMiddlewareIdEqualTo(Long value) {
			addCriterion("middleware_id =", value, "middlewareId");
			return (Criteria) this;
		}

		public Criteria andMiddlewareIdNotEqualTo(Long value) {
			addCriterion("middleware_id <>", value, "middlewareId");
			return (Criteria) this;
		}

		public Criteria andMiddlewareIdGreaterThan(Long value) {
			addCriterion("middleware_id >", value, "middlewareId");
			return (Criteria) this;
		}

		public Criteria andMiddlewareIdGreaterThanOrEqualTo(Long value) {
			addCriterion("middleware_id >=", value, "middlewareId");
			return (Criteria) this;
		}

		public Criteria andMiddlewareIdLessThan(Long value) {
			addCriterion("middleware_id <", value, "middlewareId");
			return (Criteria) this;
		}

		public Criteria andMiddlewareIdLessThanOrEqualTo(Long value) {
			addCriterion("middleware_id <=", value, "middlewareId");
			return (Criteria) this;
		}

		public Criteria andMiddlewareIdIn(List<Long> values) {
			addCriterion("middleware_id in", values, "middlewareId");
			return (Criteria) this;
		}

		public Criteria andMiddlewareIdNotIn(List<Long> values) {
			addCriterion("middleware_id not in", values, "middlewareId");
			return (Criteria) this;
		}

		public Criteria andMiddlewareIdBetween(Long value1, Long value2) {
			addCriterion("middleware_id between", value1, value2, "middlewareId");
			return (Criteria) this;
		}

		public Criteria andMiddlewareIdNotBetween(Long value1, Long value2) {
			addCriterion("middleware_id not between", value1, value2, "middlewareId");
			return (Criteria) this;
		}

		public Criteria andInstanceIdIsNull() {
			addCriterion("instance_id is null");
			return (Criteria) this;
		}

		public Criteria andInstanceIdIsNotNull() {
			addCriterion("instance_id is not null");
			return (Criteria) this;
		}

		public Criteria andInstanceIdEqualTo(Long value) {
			addCriterion("instance_id =", value, "instanceId");
			return (Criteria) this;
		}

		public Criteria andInstanceIdNotEqualTo(Long value) {
			addCriterion("instance_id <>", value, "instanceId");
			return (Criteria) this;
		}

		public Criteria andInstanceIdGreaterThan(Long value) {
			addCriterion("instance_id >", value, "instanceId");
			return (Criteria) this;
		}

		public Criteria andInstanceIdGreaterThanOrEqualTo(Long value) {
			addCriterion("instance_id >=", value, "instanceId");
			return (Criteria) this;
		}

		public Criteria andInstanceIdLessThan(Long value) {
			addCriterion("instance_id <", value, "instanceId");
			return (Criteria) this;
		}

		public Criteria andInstanceIdLessThanOrEqualTo(Long value) {
			addCriterion("instance_id <=", value, "instanceId");
			return (Criteria) this;
		}

		public Criteria andInstanceIdIn(List<Long> values) {
			addCriterion("instance_id in", values, "instanceId");
			return (Criteria) this;
		}

		public Criteria andInstanceIdNotIn(List<Long> values) {
			addCriterion("instance_id not in", values, "instanceId");
			return (Criteria) this;
		}

		public Criteria andInstanceIdBetween(Long value1, Long value2) {
			addCriterion("instance_id between", value1, value2, "instanceId");
			return (Criteria) this;
		}

		public Criteria andInstanceIdNotBetween(Long value1, Long value2) {
			addCriterion("instance_id not between", value1, value2, "instanceId");
			return (Criteria) this;
		}

		public Criteria andGroupIdIsNull() {
			addCriterion("group_id is null");
			return (Criteria) this;
		}

		public Criteria andGroupIdIsNotNull() {
			addCriterion("group_id is not null");
			return (Criteria) this;
		}

		public Criteria andGroupIdEqualTo(Integer value) {
			addCriterion("group_id =", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdNotEqualTo(Integer value) {
			addCriterion("group_id <>", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdGreaterThan(Integer value) {
			addCriterion("group_id >", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("group_id >=", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdLessThan(Integer value) {
			addCriterion("group_id <", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
			addCriterion("group_id <=", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdIn(List<Integer> values) {
			addCriterion("group_id in", values, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdNotIn(List<Integer> values) {
			addCriterion("group_id not in", values, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdBetween(Integer value1, Integer value2) {
			addCriterion("group_id between", value1, value2, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
			addCriterion("group_id not between", value1, value2, "groupId");
			return (Criteria) this;
		}

		public Criteria andCheckpointIsNull() {
			addCriterion("checkpoint is null");
			return (Criteria) this;
		}

		public Criteria andCheckpointIsNotNull() {
			addCriterion("checkpoint is not null");
			return (Criteria) this;
		}

		public Criteria andCheckpointEqualTo(String value) {
			addCriterion("checkpoint =", value, "checkpoint");
			return (Criteria) this;
		}

		public Criteria andCheckpointNotEqualTo(String value) {
			addCriterion("checkpoint <>", value, "checkpoint");
			return (Criteria) this;
		}

		public Criteria andCheckpointGreaterThan(String value) {
			addCriterion("checkpoint >", value, "checkpoint");
			return (Criteria) this;
		}

		public Criteria andCheckpointGreaterThanOrEqualTo(String value) {
			addCriterion("checkpoint >=", value, "checkpoint");
			return (Criteria) this;
		}

		public Criteria andCheckpointLessThan(String value) {
			addCriterion("checkpoint <", value, "checkpoint");
			return (Criteria) this;
		}

		public Criteria andCheckpointLessThanOrEqualTo(String value) {
			addCriterion("checkpoint <=", value, "checkpoint");
			return (Criteria) this;
		}

		public Criteria andCheckpointLike(String value) {
			addCriterion("checkpoint like", value, "checkpoint");
			return (Criteria) this;
		}

		public Criteria andCheckpointNotLike(String value) {
			addCriterion("checkpoint not like", value, "checkpoint");
			return (Criteria) this;
		}

		public Criteria andCheckpointIn(List<String> values) {
			addCriterion("checkpoint in", values, "checkpoint");
			return (Criteria) this;
		}

		public Criteria andCheckpointNotIn(List<String> values) {
			addCriterion("checkpoint not in", values, "checkpoint");
			return (Criteria) this;
		}

		public Criteria andCheckpointBetween(String value1, String value2) {
			addCriterion("checkpoint between", value1, value2, "checkpoint");
			return (Criteria) this;
		}

		public Criteria andCheckpointNotBetween(String value1, String value2) {
			addCriterion("checkpoint not between", value1, value2, "checkpoint");
			return (Criteria) this;
		}

		public Criteria andClientIdIsNull() {
			addCriterion("client_id is null");
			return (Criteria) this;
		}

		public Criteria andClientIdIsNotNull() {
			addCriterion("client_id is not null");
			return (Criteria) this;
		}

		public Criteria andClientIdEqualTo(Integer value) {
			addCriterion("client_id =", value, "clientId");
			return (Criteria) this;
		}

		public Criteria andClientIdNotEqualTo(Integer value) {
			addCriterion("client_id <>", value, "clientId");
			return (Criteria) this;
		}

		public Criteria andClientIdGreaterThan(Integer value) {
			addCriterion("client_id >", value, "clientId");
			return (Criteria) this;
		}

		public Criteria andClientIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("client_id >=", value, "clientId");
			return (Criteria) this;
		}

		public Criteria andClientIdLessThan(Integer value) {
			addCriterion("client_id <", value, "clientId");
			return (Criteria) this;
		}

		public Criteria andClientIdLessThanOrEqualTo(Integer value) {
			addCriterion("client_id <=", value, "clientId");
			return (Criteria) this;
		}

		public Criteria andClientIdIn(List<Integer> values) {
			addCriterion("client_id in", values, "clientId");
			return (Criteria) this;
		}

		public Criteria andClientIdNotIn(List<Integer> values) {
			addCriterion("client_id not in", values, "clientId");
			return (Criteria) this;
		}

		public Criteria andClientIdBetween(Integer value1, Integer value2) {
			addCriterion("client_id between", value1, value2, "clientId");
			return (Criteria) this;
		}

		public Criteria andClientIdNotBetween(Integer value1, Integer value2) {
			addCriterion("client_id not between", value1, value2, "clientId");
			return (Criteria) this;
		}

		public Criteria andHaTypeIsNull() {
			addCriterion("ha_type is null");
			return (Criteria) this;
		}

		public Criteria andHaTypeIsNotNull() {
			addCriterion("ha_type is not null");
			return (Criteria) this;
		}

		public Criteria andHaTypeEqualTo(String value) {
			addCriterion("ha_type =", value, "haType");
			return (Criteria) this;
		}

		public Criteria andHaTypeNotEqualTo(String value) {
			addCriterion("ha_type <>", value, "haType");
			return (Criteria) this;
		}

		public Criteria andHaTypeGreaterThan(String value) {
			addCriterion("ha_type >", value, "haType");
			return (Criteria) this;
		}

		public Criteria andHaTypeGreaterThanOrEqualTo(String value) {
			addCriterion("ha_type >=", value, "haType");
			return (Criteria) this;
		}

		public Criteria andHaTypeLessThan(String value) {
			addCriterion("ha_type <", value, "haType");
			return (Criteria) this;
		}

		public Criteria andHaTypeLessThanOrEqualTo(String value) {
			addCriterion("ha_type <=", value, "haType");
			return (Criteria) this;
		}

		public Criteria andHaTypeLike(String value) {
			addCriterion("ha_type like", value, "haType");
			return (Criteria) this;
		}

		public Criteria andHaTypeNotLike(String value) {
			addCriterion("ha_type not like", value, "haType");
			return (Criteria) this;
		}

		public Criteria andHaTypeIn(List<String> values) {
			addCriterion("ha_type in", values, "haType");
			return (Criteria) this;
		}

		public Criteria andHaTypeNotIn(List<String> values) {
			addCriterion("ha_type not in", values, "haType");
			return (Criteria) this;
		}

		public Criteria andHaTypeBetween(String value1, String value2) {
			addCriterion("ha_type between", value1, value2, "haType");
			return (Criteria) this;
		}

		public Criteria andHaTypeNotBetween(String value1, String value2) {
			addCriterion("ha_type not between", value1, value2, "haType");
			return (Criteria) this;
		}

		public Criteria andGtidsIsNull() {
			addCriterion("gtids is null");
			return (Criteria) this;
		}

		public Criteria andGtidsIsNotNull() {
			addCriterion("gtids is not null");
			return (Criteria) this;
		}

		public Criteria andGtidsEqualTo(String value) {
			addCriterion("gtids =", value, "gtids");
			return (Criteria) this;
		}

		public Criteria andGtidsNotEqualTo(String value) {
			addCriterion("gtids <>", value, "gtids");
			return (Criteria) this;
		}

		public Criteria andGtidsGreaterThan(String value) {
			addCriterion("gtids >", value, "gtids");
			return (Criteria) this;
		}

		public Criteria andGtidsGreaterThanOrEqualTo(String value) {
			addCriterion("gtids >=", value, "gtids");
			return (Criteria) this;
		}

		public Criteria andGtidsLessThan(String value) {
			addCriterion("gtids <", value, "gtids");
			return (Criteria) this;
		}

		public Criteria andGtidsLessThanOrEqualTo(String value) {
			addCriterion("gtids <=", value, "gtids");
			return (Criteria) this;
		}

		public Criteria andGtidsLike(String value) {
			addCriterion("gtids like", value, "gtids");
			return (Criteria) this;
		}

		public Criteria andGtidsNotLike(String value) {
			addCriterion("gtids not like", value, "gtids");
			return (Criteria) this;
		}

		public Criteria andGtidsIn(List<String> values) {
			addCriterion("gtids in", values, "gtids");
			return (Criteria) this;
		}

		public Criteria andGtidsNotIn(List<String> values) {
			addCriterion("gtids not in", values, "gtids");
			return (Criteria) this;
		}

		public Criteria andGtidsBetween(String value1, String value2) {
			addCriterion("gtids between", value1, value2, "gtids");
			return (Criteria) this;
		}

		public Criteria andGtidsNotBetween(String value1, String value2) {
			addCriterion("gtids not between", value1, value2, "gtids");
			return (Criteria) this;
		}

		public Criteria andAttrConfigIsNull() {
			addCriterion("attr_config is null");
			return (Criteria) this;
		}

		public Criteria andAttrConfigIsNotNull() {
			addCriterion("attr_config is not null");
			return (Criteria) this;
		}

		public Criteria andAttrConfigEqualTo(String value) {
			addCriterion("attr_config =", value, "attrConfig");
			return (Criteria) this;
		}

		public Criteria andAttrConfigNotEqualTo(String value) {
			addCriterion("attr_config <>", value, "attrConfig");
			return (Criteria) this;
		}

		public Criteria andAttrConfigGreaterThan(String value) {
			addCriterion("attr_config >", value, "attrConfig");
			return (Criteria) this;
		}

		public Criteria andAttrConfigGreaterThanOrEqualTo(String value) {
			addCriterion("attr_config >=", value, "attrConfig");
			return (Criteria) this;
		}

		public Criteria andAttrConfigLessThan(String value) {
			addCriterion("attr_config <", value, "attrConfig");
			return (Criteria) this;
		}

		public Criteria andAttrConfigLessThanOrEqualTo(String value) {
			addCriterion("attr_config <=", value, "attrConfig");
			return (Criteria) this;
		}

		public Criteria andAttrConfigLike(String value) {
			addCriterion("attr_config like", value, "attrConfig");
			return (Criteria) this;
		}

		public Criteria andAttrConfigNotLike(String value) {
			addCriterion("attr_config not like", value, "attrConfig");
			return (Criteria) this;
		}

		public Criteria andAttrConfigIn(List<String> values) {
			addCriterion("attr_config in", values, "attrConfig");
			return (Criteria) this;
		}

		public Criteria andAttrConfigNotIn(List<String> values) {
			addCriterion("attr_config not in", values, "attrConfig");
			return (Criteria) this;
		}

		public Criteria andAttrConfigBetween(String value1, String value2) {
			addCriterion("attr_config between", value1, value2, "attrConfig");
			return (Criteria) this;
		}

		public Criteria andAttrConfigNotBetween(String value1, String value2) {
			addCriterion("attr_config not between", value1, value2, "attrConfig");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table common_task
	 * @mbg.generated
	 */
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table common_task
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}