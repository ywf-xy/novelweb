package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.NovelShelf;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.dao.ReaderMapper;
import cn.xy.novelwebproject.utils.JedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

@Service
public class ReaderServiceImp implements ReaderService {
		@Autowired
		private ReaderMapper readerMapper;

		@Override
		public Reader getReaderByName(String nick_name) {
				//查看redis数据库是否存在改用户的信息
				//若存在则返回改用户，否则从数据库查询并保存到redis
				Jedis jedis = JedisUtils.getConnect();
				String key = "reader:" + nick_name;
				Reader reader = null;
				try {
						if (jedis.exists(key)) {
								String value = jedis.get(key);
								reader = new ObjectMapper().readValue(value, Reader.class);
						} else {
								reader = readerMapper.selectByPrimaryKey(nick_name);
								reader.setBirth(new SimpleDateFormat("yyyy-MM-dd").format(reader.getBirthday()));

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

		@Override
		public int SetUserHeadImg(String nick_name, String imgname) {
				int i = 0;
				String keu = "reader:" + nick_name;
				if ("".equals(nick_name) || "".equals(imgname) || nick_name == null || imgname == null) {
						i = -1;
				} else {
						i = readerMapper.SetUserHeadImg(imgname, nick_name);
						Jedis jedis = JedisUtils.getConnect();
						String value = jedis.get(keu);
						try {
								Reader reader = new ObjectMapper().readValue(value, Reader.class);
								reader.setHeadimage(imgname);
								int time = Math.toIntExact(jedis.ttl(keu));
								if (time == -1) {
										time = 60 * 60;
								}
								jedis.setex(keu, time, new ObjectMapper().writeValueAsString(reader));
						} catch (IOException e) {
								e.printStackTrace();
						} finally {
								JedisUtils.close(jedis);
						}
				}

				return i;
		}

		@Override
		public int UpdateReaderMsg(Reader reader) {
				Jedis jedis = JedisUtils.getConnect();
				String key = "reader:" + reader.getNick_name();
				int flag = 0;
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
										//修改变更字段
										result.setNick_name(reader.getNick_name());
										result.setSex(reader.getSex());
										result.setBirthday(reader.getBirthday());
										result.setAddress(reader.getAddress());
										result.setIntro(reader.getIntro());
								} else {
										result = readerMapper.selectByPrimaryKey(reader.getNick_name());
								}
								time = time == -1 ? 60 * 60 : time;
								jedis.setex(key, time, new ObjectMapper().writeValueAsString(result));
								return 1;
						}
				} catch (IOException e) {
						e.printStackTrace();
				} finally {
						JedisUtils.close(jedis);
				}
				return 0;
		}

		@Override
		public Reader findBookShelfByName(String nick_name) {
				Reader reader = null;
				Jedis jedis = JedisUtils.getConnect();
				String key = "reader:" + nick_name;

				try {
						if (jedis.exists(key)) {
								String value = jedis.get(key);
								reader = new ObjectMapper().readValue(value, Reader.class);
								if (reader.getMybookshelf() == null) {
										reader = readerMapper.findBookShelfByName(nick_name);
										//获取key的过期时间，已过期设置为3600秒，
										int time = Math.toIntExact(jedis.ttl(key)) == -2 ? 60 * 60 : Math.toIntExact(jedis.ttl(key));
										jedis.setex(key, time, new ObjectMapper().writeValueAsString(reader));
								}
						} else {
								reader = readerMapper.findBookShelfByName(nick_name);
								//更新redis的值
								if (reader != null) {
										//获取key的过期时间，已过期设置为3600秒，
										int time = Math.toIntExact(jedis.ttl(key)) == -2 ? 60 * 60 : Math.toIntExact(jedis.ttl(key));
										jedis.setex(key, time, new ObjectMapper().writeValueAsString(reader));
								}
						}

				} catch (Exception e) {
						e.printStackTrace();
				} finally {
						JedisUtils.close(jedis);
				}
				return reader;
		}

		@Override
		public int deletBookFromShelf(String nick_name, int id) {
				Jedis jedis = JedisUtils.getConnect();
				String key = "reader:" + nick_name;

				int result = 0;
				try {
						result = readerMapper.deletBookShelfById(id);
						if (result == 1) {
								String value = jedis.get(key);
								Reader reader = new ObjectMapper().readValue(value, Reader.class);
								List<NovelShelf> mybookshelf = reader.getMybookshelf();
								Iterator<NovelShelf> iterator = mybookshelf.iterator();
								while (iterator.hasNext()) {
										NovelShelf n = iterator.next();
										if (id == n.getId()) {
												iterator.remove();
										}
								}

								reader.setMybookshelf(mybookshelf);
								jedis.set(key, new ObjectMapper().writeValueAsString(reader));
						}
				} catch (Exception e) {
						e.printStackTrace();
				} finally {
						JedisUtils.close(jedis);
				}
				return result;
		}

		@Override
		public boolean addBookMark(String nick_name, String book_name, String catlogname) {
				Jedis jedis = JedisUtils.getConnect();
				String key = "reader:" + nick_name;
				Reader reader = null;
				int flag = 0;
				boolean result = false;
				try {

						if (jedis.exists(key)) {
								String value = jedis.get(key);
								reader = new ObjectMapper().readValue(value, Reader.class);
								reader = reader.getMybookshelf() == null ? readerMapper.findBookShelfByName(nick_name) : reader;
								List<NovelShelf> mybookshelf = reader.getMybookshelf();

								Iterator<NovelShelf> iterator = mybookshelf.iterator();

								while (iterator.hasNext()) {
										NovelShelf n = iterator.next();
										//如果书架有小说，就更新书签
										if (book_name.equals(n.getNovel_name().getBook_name())) {
												flag = 1;
										}
										if (book_name.equals(n.getNovel_name().getBook_name()) && !catlogname.equals(n.getBookmark())) {
												//如果书架有小说，就更新书签
												//同时更新数据库
												n.setBookmark(catlogname);
												result = readerMapper.updateBookMark(nick_name, book_name, catlogname);
										}
								}
								//如果没有找到，想数据库插入新的记录
								if (flag != 1) {
										result = readerMapper.addBookMark(nick_name, book_name, catlogname);
										reader = readerMapper.findBookShelfByName(nick_name);
								} else {
										reader.setMybookshelf(mybookshelf);
								}


								jedis.set(key, new ObjectMapper().writeValueAsString(reader));
						} else {

								reader = readerMapper.findBookShelfByName(nick_name);
								List<NovelShelf> mybookshelf = reader.getMybookshelf();
								for (NovelShelf n : mybookshelf) {
										if (catlogname.equals(n.getBookmark())) {
												flag = -1;
										}
								}
								if (flag != -1) {
										result = readerMapper.addBookMark(nick_name, book_name, catlogname);
								}
								jedis.setex(key, 60 * 60, new ObjectMapper().writeValueAsString(reader));
						}
				} catch (Exception e) {
						e.printStackTrace();
				} finally {
						JedisUtils.close(jedis);
				}
				return result;
		}
}
