package cn.xy.novelwebproject.bean;

public class PageQuery {
		private String type;
		private int curpage;
		private int pagesize;

		public String getType () {
				return type;
		}

		public void setType (String type) {
				this.type = type;
		}

		public int getCurpage () {
				return curpage;
		}

		public void setCurpage (int curpage) {
				this.curpage = curpage;
		}

		public int getPagesize () {
				return pagesize;
		}

		public void setPagesize (int pagesize) {
				this.pagesize = pagesize;
		}
}
