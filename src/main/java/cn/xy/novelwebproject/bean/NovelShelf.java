package cn.xy.novelwebproject.bean;

public class NovelShelf {
		private int id;
		private String reader_name;
		private Novel novel_name;
		private String bookmark;

		public int getId() {
				return id;
		}

		public void setId(int id) {
				this.id = id;
		}

		public String getReader_name() {
				return reader_name;
		}

		public void setReader_name(String reader_name) {
				this.reader_name = reader_name;
		}

		public Novel getNovel_name() {
				return novel_name;
		}

		public void setNovel_name(Novel novel_name) {
				this.novel_name = novel_name;
		}

		public String getBookmark() {
				return bookmark;
		}

		public void setBookmark(String bookmark) {
				this.bookmark = bookmark;
		}

		@Override
		public String toString() {
				return "NovelShelf{" +
					"id=" + id +
					", reader_name='" + reader_name + '\'' +
					", novel_name=" + novel_name +
					", bookmark='" + bookmark + '\'' +
					'}';
		}
}
