package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.RootNovelType;
import cn.xy.novelwebproject.dao.AuthorMapper;
import cn.xy.novelwebproject.dao.CatalogMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class AuthServiceImplTest extends BaseTest {
		@Autowired
		private AuthorMapper authorMapper;
		@Autowired
		private CatalogMapper catalogMapper;

		private final Logger logger = LoggerFactory.getLogger(AuthServiceImplTest.class);

		@Test
		public void getAllTypes() {
				List<RootNovelType> novelTypes = authorMapper.selectAllType();
				for(RootNovelType root:novelTypes){
						logger.info("getAllTypes root="+root);
				}
		}
}