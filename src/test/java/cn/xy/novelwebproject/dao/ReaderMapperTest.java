package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.bean.NovelShelf;
import cn.xy.novelwebproject.bean.Reader;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ReaderMapperTest extends BaseTest {
		@Autowired
		private ReaderMapper readerMapper;

		@Test
		public void findBookShelfByName() {
				try {
						List<NovelShelf> shelf = readerMapper.findBookShelfByName("ywf");
						System.out.println(shelf);
				} catch (Exception e) {
						e.printStackTrace();
				}
		}
}