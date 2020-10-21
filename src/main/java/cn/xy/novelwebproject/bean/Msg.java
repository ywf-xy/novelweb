package cn.xy.novelwebproject.bean;

import java.util.Map;

public class Msg<T> {
		private T data;
		//  字段错误信息
		private Map<String, String> fieldErrers;

		//  错误信息
		private String message;

		//  表示是否有错误
		private boolean flag;

		public Msg () {
		}

		public Msg (boolean flag) {
				this.flag = flag;
		}

		public Msg (Map<String, String> fieldErrers, String message, boolean flag) {
				this.fieldErrers = fieldErrers;
				this.message = message;
				this.flag = flag;
		}

		public Map<String, String> getFieldErrers () {
				return fieldErrers;
		}

		public void setFieldErrers (Map<String, String> fieldErrers) {
				this.fieldErrers = fieldErrers;
		}

		public String getMessage () {
				return message;
		}

		public void setMessage (String message) {
				this.message = message;
		}

		public boolean isFlag () {
				return flag;
		}

		public void setFlag (boolean flag) {
				this.flag = flag;
		}

		public T getData () {
				return data;
		}

		public void setData (T data) {
				this.data = data;
		}

		@Override
		public String toString() {
				return "Msg{" +
					"data=" + data +
					", fieldErrers=" + fieldErrers +
					", message='" + message + '\'' +
					", flag=" + flag +
					'}';
		}
}
