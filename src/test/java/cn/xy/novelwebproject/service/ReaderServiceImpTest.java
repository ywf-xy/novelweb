package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.dao.ReaderMapper;
import cn.xy.novelwebproject.utils.JedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.io.IOException;

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
						if (jedis.exists(key)){
								//如果redis存在数据，直接从redis中取数据
								String value = jedis.get(key);
								//将数据转化为reader
								reader = new ObjectMapper().readValue(value,Reader.class);
						}
						else{
								//如果redis不存在数据，则从数据库中去数据
								reader = readerMapper.selectByPrimaryKey(name);
								//将数据转化为json格式存到redis中
								//设置键的生存时间为一小时
								jedis.setex(key,60*60,new ObjectMapper().writeValueAsString(reader));
						}
				} catch (IOException e) {
						e.printStackTrace();
				} finally {
						JedisUtils.close(jedis);
				}
				System.out.println(reader);
		}
		@Test
		public void FingUserHeadImg(){
				String imgname = "sdadadaddad";
				String username = "ywf";
				Jedis jedis = JedisUtils.getConnect();
				String key = "reader:"+username;
				boolean flag =true;
				//1 检查redis有没有用户数据
				if(jedis.exists(key)){
						//如果有值，查看用户头像o
						//将redis用户信息转换为对象

						//判断是否有头像信息，有返回，没有从数据库查


				}
				if (flag){
						//从数据库查询用户头像信息

				}
		}
		@Test
		public void SetUserHeadImg(){
				String imgname = "sdadadaddad";
				String nick_name = "ywf";

				int i = readerMapper.SetUserHeadImg(imgname,nick_name);
				if (i==1){
						System.out.println("插入成功1");
				}else {
						System.out.println("插入失败！");
				}
		}
}