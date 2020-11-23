package cn.xy.novelwebproject.utils;

import cn.xy.novelwebproject.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.junit.Assert.*;

public class FileNameUtilTest extends BaseTest {
		private Logger l = LoggerFactory.getLogger(FileNameUtilTest.class);
		@Test
		public void gbEncoding() {
				String s = null;
				try {
						s = FileNameUtil.encoding("一念永恒");
				} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
				}
				l.info("[zh->unicode]="+s);
		}

		@Test
		public void decodeUnicode() {
				String code = "_u4E00_u5FF5_u6C38_u6052".replace("_","%");
				l.info("[code->replace]="+code);
				String decodeUnicode = null;
				try {
						//System.out.println(URLEncoder.encode("一念永恒"));
						decodeUnicode = FileNameUtil.getURLDecoderString(code);

				} catch (Exception e) {
						e.printStackTrace();
				}

				l.info("[unicode->zh]="+decodeUnicode);
		}
		@Test
		public void test01(){

		}
}