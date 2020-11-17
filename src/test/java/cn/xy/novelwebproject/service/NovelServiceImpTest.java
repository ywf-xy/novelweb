package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.dao.AuthorMapper;
import cn.xy.novelwebproject.dao.NovelMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class NovelServiceImpTest extends BaseTest {
		@Autowired
		private NovelMapper novelMapper;
		@Autowired
		private AuthorMapper authorMapper;
		private Logger logger = LoggerFactory.getLogger("NovelServiceImpTest.class");
		@Test
		public void getNovelCatlog() {
				String table;
				String txt = null;
				String novelname = "DOTA系统降临漫威";
				String catalogname = "第一百七十章尤里克";
				try {
						int dbNum = authorMapper.selectNovelDB(novelname);
						logger.info("addCatalogContent dbNum="+dbNum);
						if (dbNum==0){
								table = "";
						}else{
								table =  ""+dbNum;
						}
						txt = novelMapper.getNovelCatlog(novelname,catalogname,table).get(0);
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				}
				logger.info("text = "+txt);
		}
}