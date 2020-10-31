package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.dao.AuthorMapper;
import cn.xy.novelwebproject.utils.JedisUtils;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service("authService")
public class AuthServiceImp implements AuthService {
		@Autowired
		private AuthorMapper authorMapper;

		private Logger logger = LoggerFactory.getLogger(ReaderServiceImp.class);

		public AuthServiceImp(AuthorMapper authorMapper) {
				this.authorMapper = authorMapper;
		}

		public List<Author> getAllAuth() {
				List<Author> authors = authorMapper.getAllAuth();
				logger.info("所有作者：" + authors);
				return authors;
		}

		public List<Author> selectAuthor(String nickname) {

				return authorMapper.selectAuthor(nickname);
		}

		@Override
		public Author getAuthorWorks(String nick_name) {
				return authorMapper.getAuthorWorks(nick_name);
		}

		@Override
		public boolean updatePassword(String nick_name, String old_pwd, String new_pwd) {
				boolean flag = false;
				String key = "author:" + nick_name;
				Jedis jedis = JedisUtils.getConnect();
				String value = "";
				try {
						//1、检查旧密码是否正确
						flag = authorMapper.findAuthByNamePwd(nick_name, old_pwd) == 1 ? true : false;
						//2、修改密码
						if (flag) {
								flag = authorMapper.updatePasswordByName(nick_name, new_pwd);
						}
						if (jedis.exists(key)) {
								value = jedis.get(key);
								//更新redis信息
								Author author = JSONObject.parseObject(value, Author.class);
								author.setPassword(new_pwd);

								//如果过期则重新设置过期时间
								int expireTime = jedis.ttl(key).intValue() == -2 ? 60 * 60 : jedis.ttl(key).intValue();
								value = JSONObject.toJSONString(author);
								jedis.setex(key, expireTime, value);
						} else {
								value = JSONObject.toJSONString(authorMapper.selectAuthor(nick_name));
								jedis.setex(key, 60 * 60, value);
						}
				} catch (Exception e) {
						logger.error("erro:" + e.getMessage());
				}finally {
						JedisUtils.close(jedis);
				}
				logger.info("是否成功=" + flag);
				return flag;
		}

		@Override
		public int SetUserHeadImg(String username, String fileName) {
				boolean flag = false;
				try {
						flag = authorMapper.SetUserHeadImg(username, fileName);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				return flag ? 1 : 0;
		}

		@Override
		public Object getAuthByName(String username) {
				Author author = null;
				String key = "author:" + username;
				Jedis jedis = JedisUtils.getConnect();
				try {
						author = authorMapper.findAuthorByName(username);
						if (author != null) {
								if (jedis.exists(key)) {
										int expireTime = jedis.ttl(key).intValue() <= 2 ? 60 * 60 : jedis.ttl(key).intValue();
										jedis.setex(key, expireTime, new JSONObject().toJSONString(author));
								} else {
										jedis.setex(key, 60 * 60, new JSONObject().toJSONString(author));
								}
						}
						logger.info("getauthbyname auth=" + author);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}finally {
						JedisUtils.close(jedis);
				}
				return author;
		}

		@Override
		public Author getAuthorAllMsg(String nick_name) {
				String key = "author:" + nick_name;
				Jedis jedis = JedisUtils.getConnect();
				logger.info("getallmsg nickname=" + nick_name);
				Author author = null;
				try {
						author = authorMapper.getAuthAllMsg(nick_name);
						if (author != null) {
								if (jedis.exists(key)) {
										int expireTime = jedis.ttl(key).intValue() <= 2 ? 60 * 60 : jedis.ttl(key).intValue();
										jedis.setex(key, expireTime, new JSONObject().toJSONString(author));
								} else {
										jedis.setex(key, 60 * 60, new JSONObject().toJSONString(author));
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				} finally {
						JedisUtils.close(jedis);
				}

				logger.info("getallmsg author=" + author);
				return author;
		}

		@Override
		public boolean updateAuthWork(String book_name, String book_intro, String book_status) {
				boolean result = false;
				try {
						result = authorMapper.updateAuthWork(book_name,book_intro,book_status);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				return result;
		}
}
