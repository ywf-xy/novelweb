package cn.xy.novelwebproject.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FootmarksExample {
		/**
		 * This field was generated by MyBatis Generator.
		 * This field corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		protected String orderByClause;

		/**
		 * This field was generated by MyBatis Generator.
		 * This field corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		protected boolean distinct;

		/**
		 * This field was generated by MyBatis Generator.
		 * This field corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		protected List<Criteria> oredCriteria;

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public FootmarksExample () {
				oredCriteria = new ArrayList<Criteria>();
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public void setOrderByClause (String orderByClause) {
				this.orderByClause = orderByClause;
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public String getOrderByClause () {
				return orderByClause;
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public void setDistinct (boolean distinct) {
				this.distinct = distinct;
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public boolean isDistinct () {
				return distinct;
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public List<Criteria> getOredCriteria () {
				return oredCriteria;
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public void or (Criteria criteria) {
				oredCriteria.add(criteria);
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public Criteria or () {
				Criteria criteria = createCriteriaInternal();
				oredCriteria.add(criteria);
				return criteria;
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public Criteria createCriteria () {
				Criteria criteria = createCriteriaInternal();
				if (oredCriteria.size() == 0) {
						oredCriteria.add(criteria);
				}
				return criteria;
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		protected Criteria createCriteriaInternal () {
				Criteria criteria = new Criteria();
				return criteria;
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public void clear () {
				oredCriteria.clear();
				orderByClause = null;
				distinct = false;
		}

		/**
		 * This class was generated by MyBatis Generator.
		 * This class corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		protected abstract static class GeneratedCriteria {
				protected List<Criterion> criteria;

				protected GeneratedCriteria () {
						super();
						criteria = new ArrayList<Criterion>();
				}

				public boolean isValid () {
						return criteria.size() > 0;
				}

				public List<Criterion> getAllCriteria () {
						return criteria;
				}

				public List<Criterion> getCriteria () {
						return criteria;
				}

				protected void addCriterion (String condition) {
						if (condition == null) {
								throw new RuntimeException("Value for condition cannot be null");
						}
						criteria.add(new Criterion(condition));
				}

				protected void addCriterion (String condition, Object value, String property) {
						if (value == null) {
								throw new RuntimeException("Value for " + property + " cannot be null");
						}
						criteria.add(new Criterion(condition, value));
				}

				protected void addCriterion (String condition, Object value1, Object value2, String property) {
						if (value1 == null || value2 == null) {
								throw new RuntimeException("Between values for " + property + " cannot be null");
						}
						criteria.add(new Criterion(condition, value1, value2));
				}

				protected void addCriterionForJDBCDate (String condition, Date value, String property) {
						if (value == null) {
								throw new RuntimeException("Value for " + property + " cannot be null");
						}
						addCriterion(condition, new java.sql.Date(value.getTime()), property);
				}

				protected void addCriterionForJDBCDate (String condition, List<Date> values, String property) {
						if (values == null || values.size() == 0) {
								throw new RuntimeException("Value list for " + property + " cannot be null or empty");
						}
						List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
						Iterator<Date> iter = values.iterator();
						while (iter.hasNext()) {
								dateList.add(new java.sql.Date(iter.next().getTime()));
						}
						addCriterion(condition, dateList, property);
				}

				protected void addCriterionForJDBCDate (String condition, Date value1, Date value2, String property) {
						if (value1 == null || value2 == null) {
								throw new RuntimeException("Between values for " + property + " cannot be null");
						}
						addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
				}

				public Criteria andUser_nameIsNull () {
						addCriterion("user_name is null");
						return (Criteria) this;
				}

				public Criteria andUser_nameIsNotNull () {
						addCriterion("user_name is not null");
						return (Criteria) this;
				}

				public Criteria andUser_nameEqualTo (String value) {
						addCriterion("user_name =", value, "user_name");
						return (Criteria) this;
				}

				public Criteria andUser_nameNotEqualTo (String value) {
						addCriterion("user_name <>", value, "user_name");
						return (Criteria) this;
				}

				public Criteria andUser_nameGreaterThan (String value) {
						addCriterion("user_name >", value, "user_name");
						return (Criteria) this;
				}

				public Criteria andUser_nameGreaterThanOrEqualTo (String value) {
						addCriterion("user_name >=", value, "user_name");
						return (Criteria) this;
				}

				public Criteria andUser_nameLessThan (String value) {
						addCriterion("user_name <", value, "user_name");
						return (Criteria) this;
				}

				public Criteria andUser_nameLessThanOrEqualTo (String value) {
						addCriterion("user_name <=", value, "user_name");
						return (Criteria) this;
				}

				public Criteria andUser_nameLike (String value) {
						addCriterion("user_name like", value, "user_name");
						return (Criteria) this;
				}

				public Criteria andUser_nameNotLike (String value) {
						addCriterion("user_name not like", value, "user_name");
						return (Criteria) this;
				}

				public Criteria andUser_nameIn (List<String> values) {
						addCriterion("user_name in", values, "user_name");
						return (Criteria) this;
				}

				public Criteria andUser_nameNotIn (List<String> values) {
						addCriterion("user_name not in", values, "user_name");
						return (Criteria) this;
				}

				public Criteria andUser_nameBetween (String value1, String value2) {
						addCriterion("user_name between", value1, value2, "user_name");
						return (Criteria) this;
				}

				public Criteria andUser_nameNotBetween (String value1, String value2) {
						addCriterion("user_name not between", value1, value2, "user_name");
						return (Criteria) this;
				}

				public Criteria andNovle_nameIsNull () {
						addCriterion("novle_name is null");
						return (Criteria) this;
				}

				public Criteria andNovle_nameIsNotNull () {
						addCriterion("novle_name is not null");
						return (Criteria) this;
				}

				public Criteria andNovle_nameEqualTo (String value) {
						addCriterion("novle_name =", value, "novle_name");
						return (Criteria) this;
				}

				public Criteria andNovle_nameNotEqualTo (String value) {
						addCriterion("novle_name <>", value, "novle_name");
						return (Criteria) this;
				}

				public Criteria andNovle_nameGreaterThan (String value) {
						addCriterion("novle_name >", value, "novle_name");
						return (Criteria) this;
				}

				public Criteria andNovle_nameGreaterThanOrEqualTo (String value) {
						addCriterion("novle_name >=", value, "novle_name");
						return (Criteria) this;
				}

				public Criteria andNovle_nameLessThan (String value) {
						addCriterion("novle_name <", value, "novle_name");
						return (Criteria) this;
				}

				public Criteria andNovle_nameLessThanOrEqualTo (String value) {
						addCriterion("novle_name <=", value, "novle_name");
						return (Criteria) this;
				}

				public Criteria andNovle_nameLike (String value) {
						addCriterion("novle_name like", value, "novle_name");
						return (Criteria) this;
				}

				public Criteria andNovle_nameNotLike (String value) {
						addCriterion("novle_name not like", value, "novle_name");
						return (Criteria) this;
				}

				public Criteria andNovle_nameIn (List<String> values) {
						addCriterion("novle_name in", values, "novle_name");
						return (Criteria) this;
				}

				public Criteria andNovle_nameNotIn (List<String> values) {
						addCriterion("novle_name not in", values, "novle_name");
						return (Criteria) this;
				}

				public Criteria andNovle_nameBetween (String value1, String value2) {
						addCriterion("novle_name between", value1, value2, "novle_name");
						return (Criteria) this;
				}

				public Criteria andNovle_nameNotBetween (String value1, String value2) {
						addCriterion("novle_name not between", value1, value2, "novle_name");
						return (Criteria) this;
				}

				public Criteria andVisit_timeIsNull () {
						addCriterion("visit_time is null");
						return (Criteria) this;
				}

				public Criteria andVisit_timeIsNotNull () {
						addCriterion("visit_time is not null");
						return (Criteria) this;
				}

				public Criteria andVisit_timeEqualTo (Date value) {
						addCriterionForJDBCDate("visit_time =", value, "visit_time");
						return (Criteria) this;
				}

				public Criteria andVisit_timeNotEqualTo (Date value) {
						addCriterionForJDBCDate("visit_time <>", value, "visit_time");
						return (Criteria) this;
				}

				public Criteria andVisit_timeGreaterThan (Date value) {
						addCriterionForJDBCDate("visit_time >", value, "visit_time");
						return (Criteria) this;
				}

				public Criteria andVisit_timeGreaterThanOrEqualTo (Date value) {
						addCriterionForJDBCDate("visit_time >=", value, "visit_time");
						return (Criteria) this;
				}

				public Criteria andVisit_timeLessThan (Date value) {
						addCriterionForJDBCDate("visit_time <", value, "visit_time");
						return (Criteria) this;
				}

				public Criteria andVisit_timeLessThanOrEqualTo (Date value) {
						addCriterionForJDBCDate("visit_time <=", value, "visit_time");
						return (Criteria) this;
				}

				public Criteria andVisit_timeIn (List<Date> values) {
						addCriterionForJDBCDate("visit_time in", values, "visit_time");
						return (Criteria) this;
				}

				public Criteria andVisit_timeNotIn (List<Date> values) {
						addCriterionForJDBCDate("visit_time not in", values, "visit_time");
						return (Criteria) this;
				}

				public Criteria andVisit_timeBetween (Date value1, Date value2) {
						addCriterionForJDBCDate("visit_time between", value1, value2, "visit_time");
						return (Criteria) this;
				}

				public Criteria andVisit_timeNotBetween (Date value1, Date value2) {
						addCriterionForJDBCDate("visit_time not between", value1, value2, "visit_time");
						return (Criteria) this;
				}
		}

		/**
		 * This class was generated by MyBatis Generator.
		 * This class corresponds to the database table footmarks
		 *
		 * @mbg.generated do_not_delete_during_merge Sat Jun 06 19:29:15 CST 2020
		 */
		public static class Criteria extends GeneratedCriteria {

				protected Criteria () {
						super();
				}
		}

		/**
		 * This class was generated by MyBatis Generator.
		 * This class corresponds to the database table footmarks
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
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

				public String getCondition () {
						return condition;
				}

				public Object getValue () {
						return value;
				}

				public Object getSecondValue () {
						return secondValue;
				}

				public boolean isNoValue () {
						return noValue;
				}

				public boolean isSingleValue () {
						return singleValue;
				}

				public boolean isBetweenValue () {
						return betweenValue;
				}

				public boolean isListValue () {
						return listValue;
				}

				public String getTypeHandler () {
						return typeHandler;
				}

				protected Criterion (String condition) {
						super();
						this.condition = condition;
						this.typeHandler = null;
						this.noValue = true;
				}

				protected Criterion (String condition, Object value, String typeHandler) {
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

				protected Criterion (String condition, Object value) {
						this(condition, value, null);
				}

				protected Criterion (String condition, Object value, Object secondValue, String typeHandler) {
						super();
						this.condition = condition;
						this.value = value;
						this.secondValue = secondValue;
						this.typeHandler = typeHandler;
						this.betweenValue = true;
				}

				protected Criterion (String condition, Object value, Object secondValue) {
						this(condition, value, secondValue, null);
				}
		}
}