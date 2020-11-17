package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Novel;
import cn.xy.novelwebproject.bean.Noveltype;
import cn.xy.novelwebproject.bean.RootNovelType;

import java.util.Date;
import java.util.List;

public interface AuthService {
		List<Author> getAllAuth();

		List<Author> selectAuthor(String nickName);

		Author getAuthorWorks(String nickName);

		boolean updatePassword(String nickName, String oldPwd, String newPwd);

		int setUserHeadImg(String userName, String fileName);

		Object getAuthByName(String userName);

		Author getAuthorAllMsg(String nickName);

		boolean updateAuthWork(String bookName, String bookIntro, String bookStatus);

		boolean addNovel(Novel novel);

		boolean addType(List<Noveltype> types);

		void addAuhtorWork(String book_author, String book_name, Date date);

		boolean addCatalogContent(String bookName, String catalogName, String content);

		boolean addCatalog(String bookName, String catalogName, Date date);

		void updateWorkWords(Novel novel);

		List<RootNovelType> getAllTypes();

		boolean updateCatalog(String novelName, String catalogName);

		boolean updateCatalogContent(String novelName, String catalogName, String content);

		String getCatalogContent(String novelName, String novelCatalog);
}
