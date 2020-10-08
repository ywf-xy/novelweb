package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Novel;
import cn.xy.novelwebproject.dao.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("searchService")
public class SearchServiceImp implements SearchService {
		@Autowired
		private SearchMapper searchMapper;

		@Transactional
		@Override
		public List<Novel> searchBookByName (String keywords) {
				List<Novel> novels = new ArrayList<Novel>();
				try {
						novels = searchMapper.searchBookByName(keywords);
				} catch (Exception e) {
						e.printStackTrace();
				}
				System.out.println(novels.size());
				return novels;
		}

		@Transactional
		@Override
		public List<Novel> searchBookByAuth (String keywords) {
				List<Novel> novels = new ArrayList<Novel>();
				try {
						novels = searchMapper.searchBookByAuth(keywords);
				} catch (Exception e) {
						e.printStackTrace();
				}
				return novels;
		}
}
