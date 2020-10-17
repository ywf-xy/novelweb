package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.dao.ReaderMapper;
import cn.xy.novelwebproject.utils.JedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReaderServiceImpTest extends BaseTest {
		@Autowired
		private ReaderMapper readerMapper;

		@Test
		public void getReaderByName() {
				String name = "zpj";
				Jedis jedis = JedisUtils.getConnect();
				//1、判断redis有没有数据
				String key = "reader:" + name;
				Reader reader = null;
				try {
						if (jedis.exists(key)) {
								//如果redis存在数据，直接从redis中取数据
								String value = jedis.get(key);
								//将数据转化为reader
								reader = new ObjectMapper().readValue(value, Reader.class);
						} else {
								//如果redis不存在数据，则从数据库中去数据
								reader = readerMapper.selectByPrimaryKey(name);
								//将数据转化为json格式存到redis中
								//设置键的生存时间为一小时
								jedis.setex(key, 60 * 60, new ObjectMapper().writeValueAsString(reader));
						}
				} catch (IOException e) {
						e.printStackTrace();
				} finally {
						JedisUtils.close(jedis);
				}
				System.out.println(reader);
		}

		@Test
		public void UpdateReaderMsg() {
				String sex = "男";
				String username = "ywf";
				Jedis jedis = JedisUtils.getConnect();
				String key = "reader:" + username;
				int flag = 0;
				Reader reader = new Reader();
				reader.setNick_name(username);
				reader.setSex(sex);
				try {
						//更新读者信息
						flag = readerMapper.updataReaderByName(reader);
						//如果数据库更新成功则更新redis
						if (flag == 1) {
								int time = Math.toIntExact(jedis.ttl(key)) == -2 ? 60 * 60 : Math.toIntExact(jedis.ttl(key));
								String value = "";
								Reader result = null;
								if (jedis.exists(key)) {
										value = jedis.get(key);
										result = new ObjectMapper().readValue(value, Reader.class);
										result.setSex(sex);
								} else {
										result = readerMapper.selectByPrimaryKey(username);
								}
								jedis.setex(key, time, new ObjectMapper().writeValueAsString(result));
								System.out.println(jedis.get(key));
						}
				} catch (IOException e) {
						e.printStackTrace();
				} finally {
						JedisUtils.close(jedis);
				}
		}

		@Test
		public void SetUserHeadImg() {
				String imgname = "sdadadaddad";
				String nick_name = "ywf";

				int i = readerMapper.SetUserHeadImg(imgname, nick_name);
				if (i == 1) {
						System.out.println("插入成功1");
				} else {
						System.out.println("插入失败！");
				}
		}
		@Test
		public void testTime(){
				String birthStr = "2000-01-01";
				String pattern = "\\d{4}-\\d{2}-\\d{2}";
				Date birthday = null;
				// 创建 Pattern 对象
				Pattern r = Pattern.compile(pattern);
				// 现在创建 matcher 对象
				Matcher m = r.matcher(birthStr);
				if (m.find( )) {
						birthday = new Date();
						System.out.println("======================"+birthday);
				}

		}

		@Test
		public void addBookMark() {
				String nick_name = "ywf";
				String book_name = "万族之劫";
				String catlogname = "第61章千钧修炼功法";
				boolean mark = readerMapper.addBookMark(nick_name, book_name, catlogname);
				System.out.println(mark);
		}
}