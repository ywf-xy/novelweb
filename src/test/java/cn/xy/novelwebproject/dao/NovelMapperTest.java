package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.bean.Novel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class NovelMapperTest extends BaseTest {
		@Autowired
		private NovelMapper novelMapper;
		private final Logger l = LoggerFactory.getLogger(NovelMapperTest.class);
		@Test
		public void getRankList() {
				//update_time book_words monthly_tickets total_hits downloads
				String type = "downloads";
				List<Novel> rankList = novelMapper.getRankList(type);
				l.info("list="+rankList);
				rankList.forEach(n->l.info(" novel="+n));
		}
}