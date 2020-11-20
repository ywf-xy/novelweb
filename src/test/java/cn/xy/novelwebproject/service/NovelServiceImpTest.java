package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.bean.Novel;
import cn.xy.novelwebproject.dao.AuthorMapper;
import cn.xy.novelwebproject.dao.NovelMapper;
import cn.xy.novelwebproject.utils.JedisUtils;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.*;

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
		@Test
		public void testRank(){
				Map<String, List<Novel>> map = new HashMap<>();
				Jedis jedis = JedisUtils.getConnect();
				String wordsKey = "bookWordsRank";
				String updateTimeKey = "updateTimeRank";
				String monthlyTicketsKey = "monthlyTicketsRank";
				String totalHitsKey = "totalHitsRank";
				String downloadsKey = "downloadsRank";

//				jedis.del(wordsKey);
//				jedis.del(updateTimeKey);
//				jedis.del(monthlyTicketsKey);
//				jedis.del(totalHitsKey);
//				jedis.del(downloadsKey);
				//字数榜单
				if (jedis.exists(wordsKey)){
						Set<String> wordsSet = jedis.zrevrange(wordsKey, 0, 10);
						List<Novel> list =new ArrayList<>();
						wordsSet.forEach(n->list.add(JSON.parseObject(n,Novel.class)));
						map.put("bookwords",list);
				}else {
						List<Novel> bookWords = novelMapper.getRankList("book_words");
						logger.info("字数list="+bookWords);
						if (bookWords!=null){
								//将字数排行榜放入rediszset
								bookWords.forEach(n->{jedis.zadd(wordsKey, n.getBook_words(), JSON.toJSONString(n));});
								jedis.expire(wordsKey,7*24*60*60);

								Set<String> wordsSet = jedis.zrevrange(wordsKey, 0, 10);
								List<Novel> list =new ArrayList<>();
								wordsSet.forEach(n->list.add(JSON.parseObject(n,Novel.class)));
								map.put("bookwords",list);
						}
				}
				//更新时间榜单
				if (jedis.exists(updateTimeKey)){
						Set<String> updateTime = jedis.zrevrange(updateTimeKey, 0, 10);
						List<Novel> list = new ArrayList<>();
						updateTime.forEach(n->list.add(JSON.parseObject(n,Novel.class)));
						map.put("updatetime",list);
				}else {
						List<Novel> updateTime = novelMapper.getRankList("update_time");
						logger.info("更新时间list="+updateTime);
						if (updateTime!=null){
								updateTime.forEach(n->{
										SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
										String format = simpleDateFormat.format(n.getUpdate_time());
										jedis.zadd(updateTimeKey,Integer.parseInt(format),JSON.toJSONString(n));
								});
								jedis.expire(updateTimeKey,7*24*60*60);

								Set<String> updateTimeSet = jedis.zrevrange(updateTimeKey, 0, 10);
								List<Novel> list = new ArrayList<>();
								updateTimeSet.forEach(n->list.add(JSON.parseObject(n,Novel.class)));
								map.put("updatetime",list);
						}
				}
				//总推荐榜
				if (jedis.exists(monthlyTicketsKey)){
						Set<String> monthTicket = jedis.zrevrange(monthlyTicketsKey, 0, 10);
						List<Novel> list = new ArrayList<>();
						monthTicket.forEach(n->list.add(JSON.parseObject(n,Novel.class)));
						map.put("monthlytickets",list);
				}else {
						List<Novel> monthlyTickets = novelMapper.getRankList("monthly_tickets");
						logger.info("总推荐list="+monthlyTickets);
						if (monthlyTickets!=null){
								monthlyTickets.forEach(n -> jedis.zadd(monthlyTicketsKey, n.getMonthly_tickets(), JSON.toJSONString(n)));
								jedis.expire(monthlyTicketsKey,7*24*60*60);

								Set<String> monthTicket = jedis.zrevrange(monthlyTicketsKey, 0, 10);
								List<Novel> list = new ArrayList<>();
								monthTicket.forEach(n -> list.add(JSON.parseObject(n, Novel.class)));
								map.put("monthlytickets", list);
						}
				}
				//总点击榜
				if (jedis.exists(totalHitsKey)){
						Set<String> totalHits = jedis.zrevrange(totalHitsKey, 0, 10);
						List<Novel> list = new ArrayList<>();
						totalHits.forEach(n->list.add(JSON.parseObject(n,Novel.class)));
						map.put("totalhits",list);
				}else {
						List<Novel> totalHit = novelMapper.getRankList("total_hits");
						logger.info("总点击list="+totalHit);
						if (totalHit!=null){
								totalHit.forEach(n -> jedis.zadd(totalHitsKey, n.getTotal_hits(), JSON.toJSONString(n)));
								jedis.expire(totalHitsKey,7*24*60*60);

								Set<String> totalHits = jedis.zrevrange(totalHitsKey, 0, 10);
								List<Novel> list = new ArrayList<>();
								totalHits.forEach(n -> list.add(JSON.parseObject(n, Novel.class)));
								map.put("totalhits", list);
						}
				}if (jedis.exists(downloadsKey)){
						Set<String> downloads = jedis.zrevrange(downloadsKey, 0, 10);
						List<Novel> list = new ArrayList<>();
						downloads.forEach(n->list.add(JSON.parseObject(n,Novel.class)));
						map.put("downloads",list);
				}else {
						List<Novel> download = novelMapper.getRankList("downloads");
						logger.info("下载list="+download);
						if (download!=null){
								download.forEach(n -> jedis.zadd(downloadsKey, n.getDownloads(), JSON.toJSONString(n)));
								jedis.expire(downloadsKey,7*24*60*60);

								Set<String> downloads = jedis.zrevrange(downloadsKey, 0, 10);
								List<Novel> list = new ArrayList<>();
								downloads.forEach(n -> list.add(JSON.parseObject(n, Novel.class)));
								map.put("downloads", list);
						}
				}
				logger.info("map="+map);
		}
}