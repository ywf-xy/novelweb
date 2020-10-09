package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.bean.ReaderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderMapper {

		Reader selectByPrimaryKey(String nick_name);

		int SetUserHeadImg(String imgname, String nick_name);

		int updataReaderByName(Reader reader);
}