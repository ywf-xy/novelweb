package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.bean.Author;

import java.util.List;

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
}