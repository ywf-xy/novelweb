package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Novel;
import cn.xy.novelwebproject.dao.SearchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("searchService")
public class SearchServiceImp implements SearchService {
		@Autowired
		private SearchMapper searchMapper;
		
		private Logger logger = LoggerFactory.getLogger(ReaderServiceImp.class);
		
		@Override
		public List<Novel> searchBookByName (String keywords) {
				List<Novel> novels = new ArrayList<Novel>();
				try {
						novels = searchMapper.searchBookByName(keywords);
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				}
				System.out.println(novels.size());
				return novels;
		}
		
		@Override
		public List<Novel> searchBookByAuth (String keywords) {
				List<Novel> novels = new ArrayList<Novel>();
				try {
						novels = searchMapper.searchBookByAuth(keywords);
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				}
				return novels;
		}
}
