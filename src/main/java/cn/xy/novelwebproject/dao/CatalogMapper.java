package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.bean.Catalog;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogMapper {
	void createtable (int id);

	String selectTableByName(String tablename);

	int findcatlognum();
}