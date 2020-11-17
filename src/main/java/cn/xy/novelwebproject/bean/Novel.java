package cn.xy.novelwebproject.bean;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Novel {
		private String book_name;
		private List<String> book_type;
		private String book_author;
		private String book_state;
		private Integer book_words;
		private String book_intro;
		private List<Catalog> book_catalog;
		@DateTimeFormat(pattern = "yyyy-MM-dd")//页面写入数据库时格式化
		@JSONField(format = "yyyy-MM-dd")//数据库导出页面时json格式化
		private Date update_time;
		private Integer monthly_tickets;
		private Integer total_hits;
		private Integer monthly_hits;
		private Integer weekly_hits;
		private Integer daily_hits;
		private Integer total_collections;
		private Integer monthly_collections;
		private Integer weekly_collections;
		private Integer daily_collections;
		private Integer downloads;
		private List<Comment> comments;

		public Integer getMonthly_hits () {
				return monthly_hits;
		}

		public void setMonthly_hits (Integer monthly_hits) {
				this.monthly_hits = monthly_hits;
		}

		public Integer getWeekly_hits () {
				return weekly_hits;
		}

		public void setWeekly_hits (Integer weekly_hits) {
				this.weekly_hits = weekly_hits;
		}

		public Integer getDaily_hits () {
				return daily_hits;
		}

		public void setDaily_hits (Integer daily_hits) {
				this.daily_hits = daily_hits;
		}

		public Integer getDaily_collections () {
				return daily_collections;
		}

		public void setDaily_collections (Integer daily_collections) {
				this.daily_collections = daily_collections;
		}

		public Integer getDownloads () {
				return downloads;
		}

		public void setDownloads (Integer downloads) {
				this.downloads = downloads;
		}

		public List<Comment> getComments () {
				return comments;
		}

		public void setComments (List<Comment> comments) {
				this.comments = comments;
		}

		public String getBook_name () {
				return book_name;
		}

		public void setBook_name (String book_name) {
				this.book_name = book_name;
		}

		public List<String> getBook_type () {
				return book_type;
		}

		public void setBook_type (List<String> book_type) {
				this.book_type = book_type;
		}

		public String getBook_author () {
				return book_author;
		}

		public void setBook_author (String book_author) {
				this.book_author = book_author;
		}

		public String getBook_state () {
				return book_state;
		}

		public void setBook_state (String book_state) {
				this.book_state = book_state;
		}

		public Integer getBook_words () {
				return book_words;
		}

		public void setBook_words (Integer book_words) {
				this.book_words = book_words;
		}

		public String getBook_intro () {
				return book_intro;
		}

		public void setBook_intro (String book_intro) {
				this.book_intro = book_intro;
		}

		public List<Catalog> getBook_catalog () {
				return book_catalog;
		}

		public void setBook_catalog (List<Catalog> book_catalog) {
				this.book_catalog = book_catalog;
		}

		public Date getUpdate_time () {
				return update_time;
		}

		public void setUpdate_time (Date update_time) {
				this.update_time = update_time;
		}

		public Integer getMonthly_tickets () {
				return monthly_tickets;
		}

		public void setMonthly_tickets (Integer monthly_tickets) {
				this.monthly_tickets = monthly_tickets;
		}

		public Integer getTotal_hits () {
				return total_hits;
		}

		public void setTotal_hits (Integer total_hits) {
				this.total_hits = total_hits;
		}

		public Integer getTotal_collections () {
				return total_collections;
		}

		public void setTotal_collections (Integer total_collections) {
				this.total_collections = total_collections;
		}

		public Integer getMonthly_collections () {
				return monthly_collections;
		}

		public void setMonthly_collections (Integer monthly_collections) {
				this.monthly_collections = monthly_collections;
		}

		public Integer getWeekly_collections () {
				return weekly_collections;
		}

		public void setWeekly_collections (Integer weekly_collections) {
				this.weekly_collections = weekly_collections;
		}

		@Override
		public String toString() {
				return "Novel{" +
					"book_name='" + book_name + '\'' +
					", book_author='" + book_author + '\'' +
					", book_state='" + book_state + '\'' +
					", book_words=" + book_words +
					", book_intro='" + book_intro + '\'' +
					", book_catalog=" + book_catalog +
					", update_time=" + update_time +
					", monthly_tickets=" + monthly_tickets +
					", total_hits=" + total_hits +
					", monthly_hits=" + monthly_hits +
					", weekly_hits=" + weekly_hits +
					", daily_hits=" + daily_hits +
					", total_collections=" + total_collections +
					", monthly_collections=" + monthly_collections +
					", weekly_collections=" + weekly_collections +
					", daily_collections=" + daily_collections +
					", downloads=" + downloads +
					", comments=" + comments +
					'}';
		}
}