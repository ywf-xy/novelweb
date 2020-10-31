package cn.xy.novelwebproject.bean;

public class Noveltype {
		private String novel_name;
		private String type;

		public Noveltype() {
		}

		public Noveltype(String novel_name, String type) {
				this.novel_name = novel_name;
				this.type = type;
		}

		public String getNovel_name() {
				return novel_name;
		}

		public void setNovel_name(String novel_name) {
				this.novel_name = novel_name;
		}

		public String getType() {
				return type;
		}

		public void setType(String type) {
				this.type = type;
		}

		@Override
		public String toString() {
				return "Noveltype{" +
					"novel_name='" + novel_name + '\'' +
					", type='" + type + '\'' +
					'}';
		}
}