package net.wicp.tams.app.duckula.controller.bean.models;

import java.util.ArrayList;
import java.util.List;

public class CommonDeployExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	public CommonDeployExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table common_deploy
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

		public Criteria andDeployIsNull() {
			addCriterion("deploy is null");
			return (Criteria) this;
		}

		public Criteria andDeployIsNotNull() {
			addCriterion("deploy is not null");
			return (Criteria) this;
		}

		public Criteria andDeployEqualTo(String value) {
			addCriterion("deploy =", value, "deploy");
			return (Criteria) this;
		}

		public Criteria andDeployNotEqualTo(String value) {
			addCriterion("deploy <>", value, "deploy");
			return (Criteria) this;
		}

		public Criteria andDeployGreaterThan(String value) {
			addCriterion("deploy >", value, "deploy");
			return (Criteria) this;
		}

		public Criteria andDeployGreaterThanOrEqualTo(String value) {
			addCriterion("deploy >=", value, "deploy");
			return (Criteria) this;
		}

		public Criteria andDeployLessThan(String value) {
			addCriterion("deploy <", value, "deploy");
			return (Criteria) this;
		}

		public Criteria andDeployLessThanOrEqualTo(String value) {
			addCriterion("deploy <=", value, "deploy");
			return (Criteria) this;
		}

		public Criteria andDeployLike(String value) {
			addCriterion("deploy like", value, "deploy");
			return (Criteria) this;
		}

		public Criteria andDeployNotLike(String value) {
			addCriterion("deploy not like", value, "deploy");
			return (Criteria) this;
		}

		public Criteria andDeployIn(List<String> values) {
			addCriterion("deploy in", values, "deploy");
			return (Criteria) this;
		}

		public Criteria andDeployNotIn(List<String> values) {
			addCriterion("deploy not in", values, "deploy");
			return (Criteria) this;
		}

		public Criteria andDeployBetween(String value1, String value2) {
			addCriterion("deploy between", value1, value2, "deploy");
			return (Criteria) this;
		}

		public Criteria andDeployNotBetween(String value1, String value2) {
			addCriterion("deploy not between", value1, value2, "deploy");
			return (Criteria) this;
		}

		public Criteria andEnvIsNull() {
			addCriterion("env is null");
			return (Criteria) this;
		}

		public Criteria andEnvIsNotNull() {
			addCriterion("env is not null");
			return (Criteria) this;
		}

		public Criteria andEnvEqualTo(String value) {
			addCriterion("env =", value, "env");
			return (Criteria) this;
		}

		public Criteria andEnvNotEqualTo(String value) {
			addCriterion("env <>", value, "env");
			return (Criteria) this;
		}

		public Criteria andEnvGreaterThan(String value) {
			addCriterion("env >", value, "env");
			return (Criteria) this;
		}

		public Criteria andEnvGreaterThanOrEqualTo(String value) {
			addCriterion("env >=", value, "env");
			return (Criteria) this;
		}

		public Criteria andEnvLessThan(String value) {
			addCriterion("env <", value, "env");
			return (Criteria) this;
		}

		public Criteria andEnvLessThanOrEqualTo(String value) {
			addCriterion("env <=", value, "env");
			return (Criteria) this;
		}

		public Criteria andEnvLike(String value) {
			addCriterion("env like", value, "env");
			return (Criteria) this;
		}

		public Criteria andEnvNotLike(String value) {
			addCriterion("env not like", value, "env");
			return (Criteria) this;
		}

		public Criteria andEnvIn(List<String> values) {
			addCriterion("env in", values, "env");
			return (Criteria) this;
		}

		public Criteria andEnvNotIn(List<String> values) {
			addCriterion("env not in", values, "env");
			return (Criteria) this;
		}

		public Criteria andEnvBetween(String value1, String value2) {
			addCriterion("env between", value1, value2, "env");
			return (Criteria) this;
		}

		public Criteria andEnvNotBetween(String value1, String value2) {
			addCriterion("env not between", value1, value2, "env");
			return (Criteria) this;
		}

		public Criteria andUrlIsNull() {
			addCriterion("url is null");
			return (Criteria) this;
		}

		public Criteria andUrlIsNotNull() {
			addCriterion("url is not null");
			return (Criteria) this;
		}

		public Criteria andUrlEqualTo(String value) {
			addCriterion("url =", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotEqualTo(String value) {
			addCriterion("url <>", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlGreaterThan(String value) {
			addCriterion("url >", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlGreaterThanOrEqualTo(String value) {
			addCriterion("url >=", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLessThan(String value) {
			addCriterion("url <", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLessThanOrEqualTo(String value) {
			addCriterion("url <=", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLike(String value) {
			addCriterion("url like", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotLike(String value) {
			addCriterion("url not like", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlIn(List<String> values) {
			addCriterion("url in", values, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotIn(List<String> values) {
			addCriterion("url not in", values, "url");
			return (Criteria) this;
		}

		public Criteria andUrlBetween(String value1, String value2) {
			addCriterion("url between", value1, value2, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotBetween(String value1, String value2) {
			addCriterion("url not between", value1, value2, "url");
			return (Criteria) this;
		}

		public Criteria andTokenIsNull() {
			addCriterion("token is null");
			return (Criteria) this;
		}

		public Criteria andTokenIsNotNull() {
			addCriterion("token is not null");
			return (Criteria) this;
		}

		public Criteria andTokenEqualTo(String value) {
			addCriterion("token =", value, "token");
			return (Criteria) this;
		}

		public Criteria andTokenNotEqualTo(String value) {
			addCriterion("token <>", value, "token");
			return (Criteria) this;
		}

		public Criteria andTokenGreaterThan(String value) {
			addCriterion("token >", value, "token");
			return (Criteria) this;
		}

		public Criteria andTokenGreaterThanOrEqualTo(String value) {
			addCriterion("token >=", value, "token");
			return (Criteria) this;
		}

		public Criteria andTokenLessThan(String value) {
			addCriterion("token <", value, "token");
			return (Criteria) this;
		}

		public Criteria andTokenLessThanOrEqualTo(String value) {
			addCriterion("token <=", value, "token");
			return (Criteria) this;
		}

		public Criteria andTokenLike(String value) {
			addCriterion("token like", value, "token");
			return (Criteria) this;
		}

		public Criteria andTokenNotLike(String value) {
			addCriterion("token not like", value, "token");
			return (Criteria) this;
		}

		public Criteria andTokenIn(List<String> values) {
			addCriterion("token in", values, "token");
			return (Criteria) this;
		}

		public Criteria andTokenNotIn(List<String> values) {
			addCriterion("token not in", values, "token");
			return (Criteria) this;
		}

		public Criteria andTokenBetween(String value1, String value2) {
			addCriterion("token between", value1, value2, "token");
			return (Criteria) this;
		}

		public Criteria andTokenNotBetween(String value1, String value2) {
			addCriterion("token not between", value1, value2, "token");
			return (Criteria) this;
		}

		public Criteria andConfigIsNull() {
			addCriterion("config is null");
			return (Criteria) this;
		}

		public Criteria andConfigIsNotNull() {
			addCriterion("config is not null");
			return (Criteria) this;
		}

		public Criteria andConfigEqualTo(String value) {
			addCriterion("config =", value, "config");
			return (Criteria) this;
		}

		public Criteria andConfigNotEqualTo(String value) {
			addCriterion("config <>", value, "config");
			return (Criteria) this;
		}

		public Criteria andConfigGreaterThan(String value) {
			addCriterion("config >", value, "config");
			return (Criteria) this;
		}

		public Criteria andConfigGreaterThanOrEqualTo(String value) {
			addCriterion("config >=", value, "config");
			return (Criteria) this;
		}

		public Criteria andConfigLessThan(String value) {
			addCriterion("config <", value, "config");
			return (Criteria) this;
		}

		public Criteria andConfigLessThanOrEqualTo(String value) {
			addCriterion("config <=", value, "config");
			return (Criteria) this;
		}

		public Criteria andConfigLike(String value) {
			addCriterion("config like", value, "config");
			return (Criteria) this;
		}

		public Criteria andConfigNotLike(String value) {
			addCriterion("config not like", value, "config");
			return (Criteria) this;
		}

		public Criteria andConfigIn(List<String> values) {
			addCriterion("config in", values, "config");
			return (Criteria) this;
		}

		public Criteria andConfigNotIn(List<String> values) {
			addCriterion("config not in", values, "config");
			return (Criteria) this;
		}

		public Criteria andConfigBetween(String value1, String value2) {
			addCriterion("config between", value1, value2, "config");
			return (Criteria) this;
		}

		public Criteria andConfigNotBetween(String value1, String value2) {
			addCriterion("config not between", value1, value2, "config");
			return (Criteria) this;
		}

		public Criteria andHostIsNull() {
			addCriterion("host is null");
			return (Criteria) this;
		}

		public Criteria andHostIsNotNull() {
			addCriterion("host is not null");
			return (Criteria) this;
		}

		public Criteria andHostEqualTo(String value) {
			addCriterion("host =", value, "host");
			return (Criteria) this;
		}

		public Criteria andHostNotEqualTo(String value) {
			addCriterion("host <>", value, "host");
			return (Criteria) this;
		}

		public Criteria andHostGreaterThan(String value) {
			addCriterion("host >", value, "host");
			return (Criteria) this;
		}

		public Criteria andHostGreaterThanOrEqualTo(String value) {
			addCriterion("host >=", value, "host");
			return (Criteria) this;
		}

		public Criteria andHostLessThan(String value) {
			addCriterion("host <", value, "host");
			return (Criteria) this;
		}

		public Criteria andHostLessThanOrEqualTo(String value) {
			addCriterion("host <=", value, "host");
			return (Criteria) this;
		}

		public Criteria andHostLike(String value) {
			addCriterion("host like", value, "host");
			return (Criteria) this;
		}

		public Criteria andHostNotLike(String value) {
			addCriterion("host not like", value, "host");
			return (Criteria) this;
		}

		public Criteria andHostIn(List<String> values) {
			addCriterion("host in", values, "host");
			return (Criteria) this;
		}

		public Criteria andHostNotIn(List<String> values) {
			addCriterion("host not in", values, "host");
			return (Criteria) this;
		}

		public Criteria andHostBetween(String value1, String value2) {
			addCriterion("host between", value1, value2, "host");
			return (Criteria) this;
		}

		public Criteria andHostNotBetween(String value1, String value2) {
			addCriterion("host not between", value1, value2, "host");
			return (Criteria) this;
		}

		public Criteria andPwdIsNull() {
			addCriterion("pwd is null");
			return (Criteria) this;
		}

		public Criteria andPwdIsNotNull() {
			addCriterion("pwd is not null");
			return (Criteria) this;
		}

		public Criteria andPwdEqualTo(String value) {
			addCriterion("pwd =", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdNotEqualTo(String value) {
			addCriterion("pwd <>", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdGreaterThan(String value) {
			addCriterion("pwd >", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdGreaterThanOrEqualTo(String value) {
			addCriterion("pwd >=", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdLessThan(String value) {
			addCriterion("pwd <", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdLessThanOrEqualTo(String value) {
			addCriterion("pwd <=", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdLike(String value) {
			addCriterion("pwd like", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdNotLike(String value) {
			addCriterion("pwd not like", value, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdIn(List<String> values) {
			addCriterion("pwd in", values, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdNotIn(List<String> values) {
			addCriterion("pwd not in", values, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdBetween(String value1, String value2) {
			addCriterion("pwd between", value1, value2, "pwd");
			return (Criteria) this;
		}

		public Criteria andPwdNotBetween(String value1, String value2) {
			addCriterion("pwd not between", value1, value2, "pwd");
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
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table common_deploy
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
     * This class corresponds to the database table common_deploy
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}