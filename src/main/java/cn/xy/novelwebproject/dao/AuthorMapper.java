package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.bean.Author;

import java.util.Date;
import java.util.List;

import cn.xy.novelwebproject.bean.Novel;
import cn.xy.novelwebproject.bean.Noveltype;
import cn.xy.novelwebproject.bean.RootNovelType;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorMapper {
		public List<Author> getAllAuth ();

		public List<Author> selectAuthor (String nickname);

		Author getAuthorWorks (String nick_name);

		int findAuthByNamePwd(String nick_name, String old_pwd);

		boolean updatePasswordByName(String nick_name, String new_pwd);

		boolean SetUserHeadImg(String username, String fileName);

		Author findAuthorByName(String username);

		Author getAuthAllMsg(String nick_name);

		boolean updateAuthWork(String book_name, String book_intro, String book_status);

		boolean addNovel(Novel novel);

		boolean addType(List<Noveltype> types);

		void addWork(String book_author, String book_name, String date);

		boolean addCatalog(String bookName, String catalogName, String time);

		boolean addCatalogContent(String bookName, String catalogName, String content,String table);

		void addNovelDB(String bookName,String dbId);

		List<String> findAllNovel();

		Integer selectNovelDB(String bookName);

		int selectWorkWords(String bookName);

		void updateWorkWords(int words,String updateTime,String bookName);

		List<RootNovelType> selectAllType();

		boolean updateCatalog(String novelName, String catalogName);

		boolean updateCatalogContent(String novelName, String catalogName, String content);

		String getCatalogContent(String novelName, String novelCatalog);
}