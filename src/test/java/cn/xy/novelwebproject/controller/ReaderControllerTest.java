package cn.xy.novelwebproject.controller;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.bean.NovelShelf;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.service.ReaderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReaderControllerTest extends BaseTest {
		@Autowired
		private ReaderService readerService;

		@Test
		public void bookShelf() {
				Reader reader = readerService.findBookShelfByName("ywf");
				List<NovelShelf> novelShelf = reader.getMybookshelf();
				for (NovelShelf n : novelShelf) {
						System.out.println(n);
				}
		}
}