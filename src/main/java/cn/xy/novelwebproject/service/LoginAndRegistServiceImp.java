package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.dao.LoginAndRegistMapper;
import cn.xy.novelwebproject.utils.JedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service("loginAndRegistService")
public class LoginAndRegistServiceImp implements LoginAndRegistService {
		@Autowired
		private LoginAndRegistMapper loginAndRegistMapper;

		@Override
		public boolean AuthLogin (Author author) {
				return loginAndRegistMapper.AuthLogin(author).size() > 0 ? true : false;
		}

		@Override
		public boolean ReaderLogin (Reader reader) {
				return loginAndRegistMapper.ReaderLogin(reader).size() > 0 ? true : false;
		}

		@Override
		public boolean AuthRegist (Author author) {
				boolean flag = false;
				try {
						flag = loginAndRegistMapper.AuthRegist(author) > 0 ? true : false;
				} catch (Exception e) {
						e.printStackTrace();
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
						e.printStackTrace();
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

								String json = new ObjectMapper().writeValueAsString(reader);
								jedis.setex(key, 60 * 60, json);

						}
				} catch (Exception e) {
						e.printStackTrace();
				} finally {
						JedisUtils.close(jedis);
				}
				return reader;
		}
}
