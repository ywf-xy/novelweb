package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Author;

import java.util.List;

public interface AuthService {
		public List<Author> getAllAuth ();

		public List<Author> selectAuthor (String nickname);

		Author getAuthorWorks (String nick_name);
}
