package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.*;

public class CatalogMapperTest extends BaseTest {
		@Autowired
		private CatalogMapper catalogMapper;
		@Test
		public void createtable() {
				catalogMapper.createtable(1);
		}

		@Test
		public void selectTableByName() {
				String catlog_content = catalogMapper.selectTableByName("catlog_content1");
				System.out.println(catlog_content);
		}

		@Test
		public void findcatlognum() {
				int i = catalogMapper.findcatlognum();
				System.out.println(i);
		}
}