package cn.xy.novelwebproject.bean;

public class RootNovelType {
		private int id;
		private String typename;

		public RootNovelType(int id, String typename) {
				this.id = id;
				this.typename = typename;
		}

		public RootNovelType() {
		}

		public int getId() {
				return id;
		}

		public void setId(int id) {
				this.id = id;
		}

		public String getTypename() {
				return typename;
		}

		public void setTypename(String typename) {
				this.typename = typename;
		}

		@Override
		public String toString() {
				return "RootNovelType{" +
					"id=" + id +
					", typename='" + typename + '\'' +
					'}';
		}
}
