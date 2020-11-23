package cn.xy.novelwebproject.filter;

import cn.xy.novelwebproject.bean.Novel;
import cn.xy.novelwebproject.dao.NovelMapper;
import cn.xy.novelwebproject.utils.JedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;


public class NovelFilter implements Filter {
		private NovelMapper nm;
		private final Logger log = LoggerFactory.getLogger(NovelFilter.class);

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {

				ServletContext sc = filterConfig.getServletContext();
				WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(sc);

				if(cxt != null && cxt.getBean(NovelMapper.class) != null && nm == null){
						nm = cxt.getBean(NovelMapper.class);
				}
		}

		@Override
		public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
				HttpServletRequest request = (HttpServletRequest) servletRequest;
				//log.info("doFilter url="+ URLDecoder.decode(request.getRequestURI(),request.getCharacterEncoding()));
				//获取小说名
				String novelName = URLDecoder.decode(request.getRequestURI(),request.getCharacterEncoding()).replace("/wfRead/novel/book/","");
				log.info("点击的小说："+novelName);
				//更新小说点击量
				//1、存到redis中，每20次更新数据库
				addHit(novelName);
				//2、存到redis中，通过定时器更新数据库

				filterChain.doFilter(servletRequest,servletResponse);
		}

		public void addHit(String novelName){
				Jedis jedis = JedisUtils.getConnect();
				String key = "novel:"+novelName;
				String field = "totalHits";
				String value = "0";
				try {
						if (jedis.exists(key)){
								int i = Integer.parseInt(jedis.hget(key, field));
								log.info("redis "+novelName+" hits="+i);
								i++;
								if (i<20){
										value = String.valueOf(i);
										jedis.hset(key,field,value);
								}else {
										jedis.hset(key,field,value);
										//存到数据库
										int hits = nm.selectNovelHit(novelName);
										//一次增加20
										hits+=20;
										Novel novel = new Novel();
										novel.setBook_name(novelName);
										novel.setTotal_hits(hits);
										log.info("更新数据库 hit="+hits);
										nm.updateNovelHit(novel);
								}
						}else {
								value = String.valueOf(1);
								jedis.hset(key,field,value);
						}
				} catch (NumberFormatException e) {
						log.error("错误消息：{}",e.getMessage(),e);
				} finally {
						JedisUtils.close(jedis);
				}
		}
}
