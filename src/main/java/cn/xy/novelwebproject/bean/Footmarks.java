package cn.xy.novelwebproject.bean;

import java.util.Date;

public class Footmarks {
		/**
		 * This field was generated by MyBatis Generator.
		 * This field corresponds to the database column footmarks.user_name
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		private String user_name;

		/**
		 * This field was generated by MyBatis Generator.
		 * This field corresponds to the database column footmarks.novle_name
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		private String novle_name;

		/**
		 * This field was generated by MyBatis Generator.
		 * This field corresponds to the database column footmarks.visit_time
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		private Date visit_time;

		/**
		 * This method was generated by MyBatis Generator.
		 * This method returns the value of the database column footmarks.user_name
		 *
		 * @return the value of footmarks.user_name
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public String getUser_name () {
				return user_name;
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method sets the value of the database column footmarks.user_name
		 *
		 * @param user_name the value for footmarks.user_name
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public void setUser_name (String user_name) {
				this.user_name = user_name == null ? null : user_name.trim();
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method returns the value of the database column footmarks.novle_name
		 *
		 * @return the value of footmarks.novle_name
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public String getNovle_name () {
				return novle_name;
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method sets the value of the database column footmarks.novle_name
		 *
		 * @param novle_name the value for footmarks.novle_name
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public void setNovle_name (String novle_name) {
				this.novle_name = novle_name == null ? null : novle_name.trim();
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method returns the value of the database column footmarks.visit_time
		 *
		 * @return the value of footmarks.visit_time
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public Date getVisit_time () {
				return visit_time;
		}

		/**
		 * This method was generated by MyBatis Generator.
		 * This method sets the value of the database column footmarks.visit_time
		 *
		 * @param visit_time the value for footmarks.visit_time
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		public void setVisit_time (Date visit_time) {
				this.visit_time = visit_time;
		}

		@Override
		public String toString () {
				return "Footmarks{" +
						"user_name='" + user_name + '\'' +
						", novle_name='" + novle_name + '\'' +
						", visit_time=" + visit_time +
						'}';
		}
}