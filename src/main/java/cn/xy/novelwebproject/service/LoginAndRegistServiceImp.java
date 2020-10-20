package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.dao.LoginAndRegistMapper;
import cn.xy.novelwebproject.utils.JedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.List;

@Service("loginAndRegistService")
public class LoginAndRegistServiceImp implements LoginAndRegistService {
		@Autowired
		private LoginAndRegistMapper loginAndRegistMapper;

		private Logger logger = LoggerFactory.getLogger(ReaderServiceImp.class);

		@Override
		public boolean AuthLogin (Author author) {
				return loginAndRegistMapper.AuthLogin(author).size() > 0 ? true : false;
		}

		@Override
		public boolean ReaderLogin (Reader reader) {
				logger.info("[readerloginService:reader]"+reader);
				List<Reader> readerList = loginAndRegistMapper.ReaderLogin(reader);
				logger.info("[readerloginService:resultReader]"+reader);
				return readerList.size() > 0 ? true : false;
		}

		@Override
		public boolean AuthRegist (Author author) {
				boolean flag = false;
				try {
						flag = loginAndRegistMapper.AuthRegist(author) > 0 ? true : false;
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				}
				System.out.println(flag);
				return flag;
		}

		@Override
		public boolean ReaderRegist (Reader reader) {
				boolean flag = false;
				try {
						flag = loginAndRegistMapper.ReaderRegist(reader) > 0 ? true : false;
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				}
				System.out.println(flag);
				return flag;
		}

		@Override
		public Reader getReaderMsgByName(String nick_name) {
				Jedis jedis = JedisUtils.getConnect();
				String key = "reader:"+nick_name;
				Reader reader = null;
				try {
						if (jedis.exists(key)) {
								String value = jedis.get(key);
								reader = new ObjectMapper().readValue(value, Reader.class);
						} else {
								reader = loginAndRegistMapper.selectByReaderKey(nick_name);
								reader.setBirth(new SimpleDateFormat("yyyy-MM-dd").format(reader.getBirthday()));
								String json = new ObjectMapper().writeValueAsString(reader);
								jedis.setex(key, 60 * 60, json);

						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return reader;
		}

		@Override
		public Author getAuthMsgByName(String nick_name) {
				Jedis jedis = JedisUtils.getConnect();
				String key = "author:"+nick_name;
				Author author = null;
				try {
						if (jedis.exists(key)) {
								String value = jedis.get(key);
								author = new ObjectMapper().readValue(value, Author.class);
						} else {
								author = loginAndRegistMapper.selectByAuthorKey(nick_name);
								//author.setBirth(new SimpleDateFormat("yyyy-MM-dd").format(reader.getBirthday()));
								String json = new ObjectMapper().writeValueAsString(author);
								jedis.setex(key, 60 * 60, json);

						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
				return author;
		}
}
