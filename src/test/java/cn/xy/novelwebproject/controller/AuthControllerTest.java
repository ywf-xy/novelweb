package cn.xy.novelwebproject.controller;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.bean.Novel;
import cn.xy.novelwebproject.bean.Noveltype;
import cn.xy.novelwebproject.dao.AuthorMapper;
import cn.xy.novelwebproject.dao.CatalogMapper;
import cn.xy.novelwebproject.service.AuthService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthControllerTest extends BaseTest {
		@Autowired
		private AuthService authService;
		@Autowired
		private AuthorMapper authorMapper;
		@Autowired
		private CatalogMapper catalogMapper;
		private final Logger logger = LoggerFactory.getLogger(AuthControllerTest.class);

		@Test
		public void addNovel() {
				Novel novel = new Novel();
				novel.setBook_name("1");
				novel.setBook_author("2");
				novel.setBook_type(null);
				novel.setBook_intro("3");
				novel.setBook_catalog(null);
				novel.setBook_state("4");
				novel.setBook_words(5);
				novel.setUpdate_time(new Date());
				logger.info("addNovel novel=" + novel);
				List<Noveltype> list = new ArrayList<>();
				list.add(new Noveltype(novel.getBook_name(), "玄幻"));
				list.add(new Noveltype(novel.getBook_name(), "历史"));
				//将新建小说信息插入数据库
				authService.addNovel(novel);

				//添加到作者的作品中
				authService.addAuhtorWork(novel.getBook_author(), novel.getBook_name(), new Date());

				//将小说类型插入数据库
				authService.addType(list);

		}

		@Test
		public void addCatalog() {
				String bookName = "漫威里的旅法师";
				String catalogName = "2";
				Date date = new Date();
				String content = "2";


//				boolean flag =authService.addCatalog(bookName,catalogName,date);
//				logger.info("addCatalog addcatalog:flag="+flag);
				//超过900本小说新建一个数据库
				boolean flag = authService.addCatalogContent(bookName,catalogName,content);
				logger.info("addCatalog addcatalogcontent:flag="+flag);
		}
		@Test
		public void test01(){
				List<String> list = authorMapper.findAllNovel();
				logger.info("test01 list="+list);
				for(String s:list){
						logger.info("bookName="+s);
						authorMapper.addNovelDB(s,"1");
				}
		}
		@Test
		public void test02(){
				String table = "catlog_content";
				logger.info("test02 num="+catalogMapper.findcatlognum());
				int dbNum = catalogMapper.findcatlognum();
				//小说数量每增加850，就新增一个catalog，catalog_content数据库
				dbNum = dbNum/850;
				logger.info("addCatalogContent dbNum="+dbNum);
				if (dbNum>0){
						table = catalogMapper.selectTableByName("catlog_content"+dbNum);

						if (table==null){
								authorMapper.addNovelDB("",dbNum+"");
								catalogMapper.createtable(dbNum);
						}
				}
				logger.info("addCatalogContent table="+table);
		}
		@Test
		public void test03(){
				String novelName = "马气大陆";
				String catalogName = "第一章马气大陆？1";
				String content = "当李旭从梦中醒来，他发现他穿越到了马气大陆....本书完！";
				boolean flag ;
				//flag= authService.updateCatalog(novelName,catalogName);

				flag = authService.updateCatalogContent(novelName, catalogName, content);
				logger.info("flag="+flag);
		}
		@Test
		public void test04(){
				String content = authService.getCatalogContent("马气大陆", "第一章马气大陆？");
				logger.info("content="+content);
		}
}