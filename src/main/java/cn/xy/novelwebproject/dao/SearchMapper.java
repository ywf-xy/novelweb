package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.bean.Novel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchMapper {
		public List<Novel> searchBookByName (String keywords);

		public List<Novel> searchBookByAuth (String keywords);
}
