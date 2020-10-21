package cn.xy.novelwebproject.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class FileNameUtil {
		/*
		 * 中文转unicode编码
		 */
		public static String gbEncoding(final String gbString) {
				char[] utfBytes = gbString.toCharArray();
				String unicodeBytes = "";
				for (int i = 0; i < utfBytes.length; i++) {
						String hexB = Integer.toHexString(utfBytes[i]);
						if (hexB.length() <= 2) {
								hexB = "00" + hexB;
						}
						unicodeBytes = unicodeBytes + "\\u" + hexB;
				}
				return unicodeBytes;
		}
		/*
		 * unicode编码转中文
		 */
		public static String decodeUnicode(final String dataStr) {
				int start = 0;
				int end = 0;
				final StringBuffer buffer = new StringBuffer();
				while (start > -1) {
						end = dataStr.indexOf("\\u", start + 2);
						String charStr = "";
						if (end == -1) {
								charStr = dataStr.substring(start + 2, dataStr.length());
						} else {
								charStr = dataStr.substring(start + 2, end);
						}
						char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
						buffer.append(new Character(letter).toString());
						start = end;
				}
				return buffer.toString();
		}
		/*
		* url-->String
		* */
		public static String decoding(String encoderUrl) throws UnsupportedEncodingException {
				return URLDecoder.decode(encoderUrl, "UTF-8");
		}
		/*
		* String -->url
		* */
		public static String encoding(String wd) throws UnsupportedEncodingException {
				return URLEncoder.encode(wd, "UTF-8");
		}
		/**
		 * URL 解码
		 *
		 * @return String
		 * @author lifq
		 * @date 2015-3-17 下午04:09:51
		 */
		public static String getURLDecoderString(String str) {
				String result = "";
				if (null == str) {
						return "";
				}
				try {
						result = java.net.URLDecoder.decode(str, "UTF-8");
				} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
				}
				return result;
		}
		/**
		 * URL 转码
		 *
		 * @return String
		 * @author lifq
		 * @date 2015-3-17 下午04:10:28
		 */
		public static String getURLEncoderString(String str) {
				String result = "";
				if (null == str) {
						return "";
				}
				try {
						result = java.net.URLEncoder.encode(str, "UTF-8");
				} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
				}
				return result;
		}
}
