package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Author;

import java.util.List;

public interface AuthService {
		public List<Author> getAllAuth ();

		public List<Author> selectAuthor (String nickname);

		Author getAuthorWorks (String nick_name);

		boolean updatePassword(String nick_name, String old_pwd, String new_pwd);

		int SetUserHeadImg(String username, String fileName);

		Object getAuthByName(String username);
}
