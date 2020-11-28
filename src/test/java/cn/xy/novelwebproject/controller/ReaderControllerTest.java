package cn.xy.novelwebproject.controller;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.bean.Novel;
import cn.xy.novelwebproject.bean.NovelShelf;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.service.ReaderService;
import cn.xy.novelwebproject.utils.JedisUtils;
import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReaderControllerTest extends BaseTest {
		@Autowired
		private ReaderService readerService;
		private final Logger logger = LoggerFactory.getLogger(ReaderControllerTest.class);

		@Test
		public void bookShelf() {
				Reader reader = readerService.findBookShelfByName("ywf");
				List<NovelShelf> novelShelf = reader.getMybookshelf();
				for (NovelShelf n : novelShelf) {
						System.out.println(n);
				}
		}

		@Test
		public void addBookMark() {
				String nick_name = "ywf";
				String book_name = "万族之劫";
				String catlogname = "第62章万族入境";
				boolean mark = readerService.addBookMark(nick_name, book_name, catlogname);
				System.out.println(mark);
		}
		@Test
		public void test03(){
				Jedis jedis = JedisUtils.getConnect();
				String novelname = "马气大陆1";
				String catlogname = "第一章马气大陆？";
				String reader = "ww";
				String key = "history:"+reader;
				String url = "http://localhost:8080/wfRead/read/"+novelname+"/"+catlogname;
				String readTime=new SimpleDateFormat("yyyy-MM-dd HH").format(new Date());
				String record = "{" +
					"\"novelname\": \""+novelname+"\"," +
					"\"url\": \""+url+"\"," +
					"\"catalogname\": \""+catlogname+"\"," +
					"\"readtime\": \""+readTime+"\"" +
					"}";

				logger.info("record:"+record);
				jedis.hset(key,novelname,record);
				jedis.hset(key,"user",reader);
				Novel novel = new Novel();
				novel.setBook_name("yy");
				novel.setBook_author("xx");
				jedis.hset(key,"obj", JSON.toJSONString(novel));
				jedis.expire(key,240);
				Map<String, String> map = jedis.hgetAll(key);
				logger.info("map="+map);
				logger.info("redis keys="+jedis.hkeys(key));
				JedisUtils.close(jedis);
		}
}