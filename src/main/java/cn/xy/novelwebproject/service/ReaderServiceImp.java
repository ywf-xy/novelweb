package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.dao.ReaderMapper;
import cn.xy.novelwebproject.utils.JedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

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
		public int SetUserHeadImg(String nick_name,String imgname) {
				int i=0;
				if ("".equals(nick_name)||"".equals(imgname)||nick_name==null||imgname==null){
						i=-1;
				}
				i = readerMapper.SetUserHeadImg(imgname,nick_name);
				return i;
		}

}
