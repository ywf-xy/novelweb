package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Catalog;
import cn.xy.novelwebproject.bean.Novel;
import cn.xy.novelwebproject.bean.PageQuery;
import cn.xy.novelwebproject.dao.AuthorMapper;
import cn.xy.novelwebproject.dao.NovelMapper;
import cn.xy.novelwebproject.utils.JedisUtils;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("novelService")
public class NovelServiceImp implements NovelService {
		@Autowired
		private NovelMapper novelMapper;
		private Logger logger = LoggerFactory.getLogger(ReaderServiceImp.class);
		@Autowired
		private AuthorMapper authorMapper;
		private final ObjectMapper objectMapper = new ObjectMapper();

		@Override
		public Novel getNovelByColums (int colum, int i) {
				Jedis jedis = JedisUtils.getConnect();
				String key = "Colums" + colum;
				Novel novel = null;
				try {
						if (jedis.exists(key)) {
								String json = jedis.get(key);
								novel = objectMapper.readValue(json, Novel.class);
						} else {
								novel = novelMapper.getNovelByColums(colum);
								String value = objectMapper.writeValueAsString(novel);
								jedis.setnx(key, value);
								jedis.expire(key, 60 * 60 * 18);
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novel;
		}

		@Override
		public List<Novel> getNovelByColumindex (HashMap<String, Integer> map) {

				return novelMapper.getNovelByColumindex(map);
		}

		@Override
		public List<Novel> getWeekWecommend () {
				Jedis jedis = JedisUtils.getConnect();
				List<Novel> novelList = null;
				String key = "weekcommend";
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								novelList = novelMapper.getWeekWecommend();

								//将数据库查询出来的值存到redis中
								for (Novel n : novelList)
										jedis.lpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								novelList = new ArrayList<>();
								for (String s : result) {
										novelList.add(objectMapper.readValue(s, Novel.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novelList;
		}

		@Override
		public List<Novel> getHistoryList (int size) {
				Jedis jedis = JedisUtils.getConnect();
				List<Novel> novelList = null;
				String key = "historyList" + size;
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								novelList = novelMapper.getHistoryList(size);

								//将数据库查询出来的值存到redis中
								for (Novel n : novelList)
										jedis.lpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								novelList = new ArrayList<>();
								for (String s : result) {
										novelList.add(objectMapper.readValue(s, Novel.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novelList;
		}

		@Override
		public List<Novel> getOnlineGameList (int size) {
				Jedis jedis = JedisUtils.getConnect();
				List<Novel> novelList = null;
				String key = "onlineGameList" + size;
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								novelList = novelMapper.getOnlineGameList(size);

								//将数据库查询出来的值存到redis中
								for (Novel n : novelList)
										jedis.lpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								novelList = new ArrayList<>();
								for (String s : result) {
										novelList.add(objectMapper.readValue(s, Novel.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novelList;
		}

		@Override
		public List<Novel> getMartialArtsList (int size) {
				Jedis jedis = JedisUtils.getConnect();
				List<Novel> novelList = null;
				String key = "martialArtsList" + size;
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								novelList = novelMapper.getMartialArtsList(size);

								//将数据库查询出来的值存到redis中
								for (Novel n : novelList)
										jedis.lpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								novelList = new ArrayList<>();
								for (String s : result) {
										novelList.add(objectMapper.readValue(s, Novel.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novelList;
		}

		@Override
		public List<Novel> getCityList (int size) {
				Jedis jedis = JedisUtils.getConnect();
				List<Novel> novelList = null;
				String key = "cityList" + size;
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								novelList = novelMapper.getCityList(size);

								//将数据库查询出来的值存到redis中
								for (Novel n : novelList)
										jedis.lpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								novelList = new ArrayList<>();
								for (String s : result) {
										novelList.add(objectMapper.readValue(s, Novel.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novelList;
		}

		@Override
		public List<Novel> getImmortalKnightList (int size) {
				Jedis jedis = JedisUtils.getConnect();
				List<Novel> novelList = null;
				String key = "immortalKnightList" + size;
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								novelList = novelMapper.getImmortalKnightList(size);

								//将数据库查询出来的值存到redis中
								for (Novel n : novelList)
										jedis.lpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								novelList = new ArrayList<>();
								for (String s : result) {
										novelList.add(objectMapper.readValue(s, Novel.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novelList;
		}

		@Override
		public List<Novel> getScienceFictionList (int size) {
				Jedis jedis = JedisUtils.getConnect();
				List<Novel> novelList = null;
				String key = "scienceFictionList" + size;
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								novelList = novelMapper.getScienceFictionList(size);

								//将数据库查询出来的值存到redis中
								for (Novel n : novelList)
										jedis.lpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								novelList = new ArrayList<>();
								for (String s : result) {
										novelList.add(objectMapper.readValue(s, Novel.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novelList;
		}

		@Override
		public List<Novel> getFantasyList (int size) {
				Jedis jedis = JedisUtils.getConnect();
				List<Novel> novelList = null;
				String key = "fantasyList" + size;
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								novelList = novelMapper.getFantasyList(size);

								//将数据库查询出来的值存到redis中
								for (Novel n : novelList)
										jedis.lpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								novelList = new ArrayList<>();
								for (String s : result) {
										novelList.add(objectMapper.readValue(s, Novel.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novelList;
		}

		@Override
		public List<Novel> getMilitaryList (int size) {
				Jedis jedis = JedisUtils.getConnect();
				List<Novel> novelList = null;
				String key = "militaryList" + size;
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								novelList = novelMapper.getMilitaryList(size);

								//将数据库查询出来的值存到redis中
								for (Novel n : novelList)
										jedis.lpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								novelList = new ArrayList<>();
								for (String s : result) {
										novelList.add(objectMapper.readValue(s, Novel.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novelList;
		}

		@Override
		public Integer getAllPage (String type) {
				return novelMapper.getAllPage(type);
		}

		@Override
		public List<Novel> getPageByType (PageQuery query) {
				Jedis jedis = JedisUtils.getConnect();
				List<Novel> novelList = null;
				String key = "getPageBy" + query.getType() + query.getCurpage() + query.getPagesize();
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								novelList = novelMapper.getPageByType(query);

								//将数据库查询出来的值存到redis中
								for (Novel n : novelList)
										jedis.lpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								novelList = new ArrayList<>();
								for (String s : result) {
										novelList.add(objectMapper.readValue(s, Novel.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novelList;
		}

		@Override
		public List<Novel> getDownloadsList (PageQuery query) {
				Jedis jedis = JedisUtils.getConnect();
				List<Novel> novelList = null;
				String key = "getDownloadsListBy" + query.getType() + query.getCurpage() + query.getPagesize();
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								novelList = novelMapper.getDownloadsList(query);

								//将数据库查询出来的值存到redis中
								for (Novel n : novelList)
										jedis.lpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								novelList = new ArrayList<>();
								for (String s : result) {
										novelList.add(objectMapper.readValue(s, Novel.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novelList;
		}

		@Override
		public List<Novel> getMonthTicketList (PageQuery query) {
				Jedis jedis = JedisUtils.getConnect();
				List<Novel> novelList = null;
				String key = "getMonthTicketListBy" + query.getType() + query.getCurpage() + query.getPagesize();
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								novelList = novelMapper.getMonthTicketList(query);

								//将数据库查询出来的值存到redis中
								for (Novel n : novelList)
										jedis.lpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								novelList = new ArrayList<>();
								for (String s : result) {
										novelList.add(objectMapper.readValue(s, Novel.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novelList;
		}

		@Override
		public List<Novel> getCollectionList (PageQuery query) {
				Jedis jedis = JedisUtils.getConnect();
				List<Novel> novelList = null;
				String key = "getCollectionListBy" + query.getType() + query.getCurpage() + query.getPagesize();
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								novelList = novelMapper.getCollectionList(query);

								//将数据库查询出来的值存到redis中
								for (Novel n : novelList)
										jedis.lpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								novelList = new ArrayList<>();
								for (String s : result) {
										novelList.add(objectMapper.readValue(s, Novel.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return novelList;
		}

		@Override
		public int getClassiFicationPageSize (HashMap<String, Object> map) {
				return novelMapper.getClassiFicationPageSize(map);
		}

		@Override
		public List<Novel> getClassiFicationList (HashMap<String, Object> map) {
				// novelMapper.getClassiFicationList (HashMap<String, Object> map);
				/*Jedis jedis = JedisUtils.getConnect();
				List<Catalog> cataloglList = null;
				String key = "get" + "" + "Catalogs";*/
				return novelMapper.getClassiFicationList(map);

		}

		@Override
		public Novel getNovelMsg (String book_name) {
				return novelMapper.getNovelMsg(book_name);
		}

		@Override
		public List<Catalog> getBookCatalogs (String novelname) {
				// novelMapper.getBookCatalogs(novelname);
				Jedis jedis = JedisUtils.getConnect();
				List<Catalog> cataloglList = null;
				String key = "get" + novelname + "Catalogs";
				//如果redis没有数据，就从mysql查询
				try {
						if (jedis.llen(key) == 0) {
								cataloglList = novelMapper.getBookCatalogs(novelname);
								logger.info("getBookCatalogs catalogList="+cataloglList);
								if (cataloglList==null||cataloglList.size()==0){
										return new ArrayList<Catalog>();
								}
								//将查询出来的数据处理
								if (!cataloglList.get(0).getNovale_catalog().contains("第一章") && !cataloglList.get(0).getNovale_catalog().contains("第1章") &&
										!cataloglList.get(0).getNovale_catalog().contains("第0章") && !cataloglList.get(0).getNovale_catalog().contains("第01章") &&
										!cataloglList.get(0).getNovale_catalog().contains("第00章")) {
										List<Catalog> newlist = new ArrayList<>();
										for (int i = 0; i < cataloglList.size(); i++) {
												newlist.add(cataloglList.get(cataloglList.size() - 1 - i));
										}
										cataloglList = newlist;
								}
								//将数据库查询出来的值存到redis中
								for (Catalog n : cataloglList)
										jedis.rpush(key, objectMapper.writeValueAsString(n));

								//设置周排行榜生存时间为6天get一拳歼星Catalogs
								jedis.expire(key, 60 * 60 * 24 * 6);

						} else {

								List<String> result = jedis.lrange(key, 0, -1);
								cataloglList = new ArrayList<>();
								for (String s : result) {
										cataloglList.add(objectMapper.readValue(s, Catalog.class));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return cataloglList;
		}

		@Override
		public String getNovelCatlog (String novelname, String catlogname) {
				String table;
				String txt = null;
				try {
						int dbNum = authorMapper.selectNovelDB(novelname);
						logger.info("addCatalogContent dbNum="+dbNum);
						if (dbNum==0){
								table = "";
						}else{
								table =  ""+dbNum;
						}
						txt = novelMapper.getNovelCatlog(novelname,catlogname,table).get(0);
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				}
				return "".equals(txt) ? null : txt;
		}

		@Override
		public int voteTicket (String novelname, String username) {
				Jedis jedis = JedisUtils.getConnect();
				String key = "recommend:" + novelname;
				String countkey = "recommend:count:" + novelname;
				String flag = "recommend:flag:" + username + novelname;
				int restcode = 0;

				try {
						//如果用户给这本小说投过票返回，否则就将标记设置1天生存时间
						if (jedis.exists(flag)) {
								return restcode;
						} else {
								jedis.setex(flag, 24 * 60 * 60, username);
						}
						if (jedis.exists(key)) {
								//小说票加一
								jedis.incrBy(key, 1);
								//获取统计的值
								String countValue = jedis.get(countkey);
								//统计值加一
								int count = Integer.valueOf(countValue) + 1;
								//每50次做一次数据落地，保存到mysql中
								if (count == 50) {
										//更新mysql
										novelMapper.updateVoteTicket(countValue, novelname);
										//重置统计的值
										jedis.set(countkey, "0");
								} else {
										//统计更新
										jedis.set(countkey, String.valueOf(count));
								}
						} else {
								//从数据库中查询出小说推荐票的数量
								Integer value = novelMapper.getVoteTicket(novelname);
								int recommend = value == null ? 0 : value.intValue();
								//System.out.println(recommend);
								//将数量加一并存到redis中
								jedis.set(key, String.valueOf(recommend + 1));
								//开始统计
								jedis.set(countkey, "1");
						}

						restcode = 1;
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}

				return restcode;
		}

		@Override
		public Map<String, List<Novel>> getRankList() {
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
				try {
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
						}
						if (jedis.exists(downloadsKey)){
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
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				logger.info("map="+map);
				return map;
		}


}
