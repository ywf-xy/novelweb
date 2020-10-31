package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Novel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class AuthorMapperTest extends BaseTest {
		@Autowired
		private AuthorMapper authorMapper;
		@Test
		public void getAuthAllMsg() {
				Author author = authorMapper.getAuthAllMsg("8难");
				for (Novel n : author.getWorks()){
						n.setBook_catalog(null);
						System.out.println(n);

				}
		}
}