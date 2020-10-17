package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.bean.Reader;

import org.springframework.stereotype.Repository;

@Repository
public interface ReaderMapper {

		Reader selectByPrimaryKey(String nick_name);

		int SetUserHeadImg(String imgname, String nick_name);

		int updataReaderByName(Reader reader);

		Reader findBookShelfByName(String nick_name);

		int deletBookShelfById(int id);

		boolean addBookMark(String nick_name, String book_name, String catlogname);

		boolean updateBookMark(String nick_name, String book_name,String catlogname);
}