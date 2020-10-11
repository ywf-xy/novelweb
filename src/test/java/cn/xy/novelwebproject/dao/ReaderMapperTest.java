package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.bean.Reader;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ReaderMapperTest extends BaseTest {
		@Autowired
		private ReaderMapper readerMapper;

		@Test
		public void findBookShelfByName() {
				try {
						Reader reader = readerMapper.findBookShelfByName("ywf");
						System.out.println(reader);
				} catch (Exception e) {
						e.printStackTrace();
				}
		}
}