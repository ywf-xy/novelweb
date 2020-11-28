package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Novel;
import cn.xy.novelwebproject.bean.Noveltype;
import cn.xy.novelwebproject.bean.RootNovelType;
import cn.xy.novelwebproject.dao.AuthorMapper;
import cn.xy.novelwebproject.dao.CatalogMapper;
import cn.xy.novelwebproject.utils.JedisUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 33077
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {
		@Autowired
		private AuthorMapper authorMapper;
		@Autowired
		private CatalogMapper catalogMapper;
		private Author author;
		private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

		@Override
		public List<Author> getAllAuth() {
				List<Author> authors = null;
				try {
						authors = authorMapper.getAllAuth();
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				logger.info("所有作者：" + authors);
				return authors;
		}

		@Override
		public List<Author> selectAuthor(String nickname) {
				List<Author> authorList = null;
				try {
						authorList = authorMapper.selectAuthor(nickname);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				return authorList;
		}

		@Override
		public Author getAuthorWorks(String nickName) {
				try {
						author = authorMapper.getAuthorWorks(nickName);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				return author;
		}

		@Transactional
		@Override
		public boolean updatePassword(String nickName, String oldNwd, String newNwd) {
				boolean flag = false;
				String key = "author:" + nickName;
				Jedis jedis = JedisUtils.getConnect();
				String value;
				try {
						//1、检查旧密码是否正确
						flag = authorMapper.findAuthByNamePwd(nickName, oldNwd) == 1;
						//2、修改密码
						if (flag) {
								flag = authorMapper.updatePasswordByName(nickName, newNwd);
						}
						if (jedis.exists(key)) {
								value = jedis.get(key);
								//更新redis信息
								author = JSONObject.parseObject(value, Author.class);
								author.setPassword(newNwd);

								//如果过期则重新设置过期时间
								int expireTime = jedis.ttl(key).intValue() == -2 ? 60 * 60 : jedis.ttl(key).intValue();
								value = JSONObject.toJSONString(author);
								jedis.setex(key, expireTime, value);
						} else {
								value = JSONObject.toJSONString(authorMapper.selectAuthor(nickName));
								jedis.setex(key, 60 * 60, value);
						}
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				} finally {
						JedisUtils.close(jedis);
				}
				logger.info("是否成功=" + flag);
				return flag;
		}

		@Transactional
		@Override
		public int setUserHeadImg(String username, String fileName) {
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
				String key = "author:" + username;
				Jedis jedis = JedisUtils.getConnect();
				try {
						author = authorMapper.findAuthorByName(username);
						if (author != null) {
								if (jedis.exists(key)) {
										int expireTime = jedis.ttl(key).intValue() <= 2 ? 60 * 60 : jedis.ttl(key).intValue();
										jedis.setex(key, expireTime, JSONObject.toJSONString(author));
								} else {
										jedis.setex(key, 60 * 60, JSONObject.toJSONString(author));
								}
						}
						logger.info("getauthbyname auth=" + author);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				} finally {
						JedisUtils.close(jedis);
				}
				return author;
		}

		@Override
		public Author getAuthorAllMsg(String nickName) {
				String key = "author:" + nickName;
				Jedis jedis = JedisUtils.getConnect();
				logger.info("getallmsg nickname=" + nickName);
				try {
						author = authorMapper.getAuthAllMsg(nickName);
						if (author != null) {
								if (jedis.exists(key)) {
										int expireTime = jedis.ttl(key).intValue() <= 2 ? 60 * 60 : jedis.ttl(key).intValue();
										jedis.setex(key, expireTime, JSONObject.toJSONString(author));
								} else {
										jedis.setex(key, 60 * 60, JSONObject.toJSONString(author));
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

		@Transactional
		@Override
		public boolean updateAuthWork(String bookName, String bookIntro, String bookStatus) {
				boolean result = false;
				try {
						result = authorMapper.updateAuthWork(bookName, bookIntro, bookStatus);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				return result;
		}

		@Transactional
		@Override
		public boolean addNovel(Novel novel) {
				boolean flag;
				try {
						int dbNum = catalogMapper.findcatlognum();
						//小说数量每增加850，就新增一个catalog，catalog_content数据库
						dbNum = dbNum / 850;
						logger.info("addCatalogContent dbNum=" + dbNum);
						if (dbNum > 0) {
								String table = catalogMapper.selectTableByName("catlog_content" + dbNum);

								if (table == null) {
										authorMapper.addNovelDB(novel.getBook_name(), dbNum + "");
										catalogMapper.createtable(dbNum);
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						return false;
				}

				try {
						novel.setBook_state("连载");
						novel.setBook_words(0);
						novel.setUpdate_time(new Date());
						flag = authorMapper.addNovel(novel);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						flag = false;
				}
				return flag;
		}

		@Transactional
		@Override
		public boolean addType(List<Noveltype> types) {
				boolean flag = false;
				try {
						flag = authorMapper.addType(types);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				return flag;
		}

		@Transactional
		@Override
		public void addAuhtorWork(String book_author, String book_name, Date date) {
				try {
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String time = simpleDateFormat.format(date);
						authorMapper.addWork(book_author, book_name, time);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
		}

		@Transactional
		@Override
		public boolean addCatalogContent(String bookName, String catalogName, String content) {
				boolean flag = false;
				try {
						String table;
						//获取小说数据库id
						int dbNum = authorMapper.selectNovelDB(bookName)==null?-1:authorMapper.selectNovelDB(bookName);
						if(dbNum==-1){
								dbNum = catalogMapper.findcatlognum();
								//小说数量每增加850，就新增一个catalog，catalog_content数据库
								dbNum = dbNum / 850;
								authorMapper.addNovelDB(bookName,dbNum+"");
						}
						logger.info("addCatalogContent dbNum=" + dbNum);
						if (dbNum == 0) {
								//0就是默认数据库catlog_content
								table = "";
						}else{
								table = "" + dbNum;
						}

						//插入数据库
						flag = authorMapper.addCatalogContent(bookName, catalogName, content, table);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				return flag;
		}

		@Transactional
		@Override
		public boolean addCatalog(String bookName, String catalogName, Date date) {
				boolean flag = false;
				Jedis jedis = JedisUtils.getConnect();
				String key = "get" + bookName + "Catalogs";
				try {
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String time = simpleDateFormat.format(date);
						flag = authorMapper.addCatalog(bookName, catalogName, time);
						if(jedis.exists(key)){
								jedis.del(key);
						}
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}finally {
						JedisUtils.close(jedis);
				}
				return flag;
		}

		@Transactional
		@Override
		public void updateWorkWords(Novel novel) {
				//1、获取小说字数
				int words = authorMapper.selectWorkWords(novel.getBook_name());
				logger.info("updateWorkWords  before：words=" + words);
				//2、更新小说字数并插入数据库
				words += novel.getBook_words();
				logger.info("updateWorkWords  after：words=" + words);

				try {
						String updateTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						authorMapper.updateWorkWords(words, updateTime, novel.getBook_name());
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
		}

		@Override
		public List<RootNovelType> getAllTypes() {
				List<RootNovelType> list=null;
				try {
						list = authorMapper.selectAllType();
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				return list;
		}

		@Transactional
		@Override
		public boolean updateCatalog(String novelName, String catalogName) {
				boolean flag = false;
				try {
						flag = authorMapper.updateCatalog(novelName,catalogName);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				return flag;
		}

		@Transactional
		@Override
		public boolean updateCatalogContent(String novelName, String catalogName, String content) {
				boolean flag = false;
				try {
						flag = authorMapper.updateCatalogContent(novelName,catalogName,content);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				return flag;
		}

		@Override
		public String getCatalogContent(String novelName, String novelCatalog) {
				String content = "";
				try {
						content = authorMapper.getCatalogContent(novelName,novelCatalog);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				return content;
		}
}
