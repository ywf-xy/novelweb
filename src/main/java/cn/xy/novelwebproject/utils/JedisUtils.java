package cn.xy.novelwebproject.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisUtils {
		private static JedisPool pool;

		static {
				ResourceBundle bundle = ResourceBundle.getBundle("redis");

				JedisPoolConfig config = new JedisPoolConfig();

				//获取参数
				String maxTotal = bundle.getString("maxTotal");
				String maxIdle = bundle.getString("maxIdle");
				String url = bundle.getString("host");
				String port = bundle.getString("port");
				String maxActive = bundle.getString("maxActive");
				String maxWait = bundle.getString("maxWait");
				String timeout = bundle.getString("timeout");

				//设置参数
				config.setMaxIdle(Integer.parseInt(maxIdle));
				config.setMaxTotal(Integer.parseInt(maxTotal));
				config.setMaxWaitMillis(Long.parseLong(maxWait));

				//pool = new JedisPool(config,url,Integer.parseInt(port),Integer.parseInt(maxActive),timeout);
				pool = new JedisPool(config, url, Integer.parseInt(port));
		}

		/*
		 * 获取jedis连接
		 * */
		public static Jedis getConnect () {
				return pool.getResource();
		}

		/*
		 * 关闭连接
		 * */
		public static void close (Jedis jedis) {
				if (jedis != null) {
						jedis.close();
				}
		}
}
