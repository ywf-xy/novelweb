package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Novel;

import java.util.List;

public interface SearchService {
		public List<Novel> searchBookByName (String keywords);

		public List<Novel> searchBookByAuth (String keywords);
}
