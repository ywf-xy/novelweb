package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.bean.NovelShelf;
import cn.xy.novelwebproject.bean.Reader;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderMapper {

		Reader selectByPrimaryKey(String nick_name);

		int SetUserHeadImg(String imgname, String nick_name);

		int updataReaderByName(Reader reader);

		List<NovelShelf> findBookShelfByName(String nick_name);

		int deletBookShelfById(int id);

		boolean addBookMark(String nick_name, String book_name, String catlogname);

		boolean updateBookMark(String nick_name, String book_name,String catlogname);

		Reader getAllMsg(String nick_name);
}