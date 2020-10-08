package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.dao.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authService")
public class AuthServiceImp implements AuthService {
		@Autowired
		private AuthorMapper authorMapper;

		public AuthServiceImp (AuthorMapper authorMapper) {
				this.authorMapper = authorMapper;
		}

		public List<Author> getAllAuth () {
				List<Author> authors = authorMapper.getAllAuth();
				return authors;
		}

		public List<Author> selectAuthor (String nickname) {

				return authorMapper.selectAuthor(nickname);
		}

		@Override
		public Author getAuthorWorks (String nick_name) {
				return authorMapper.getAuthorWorks(nick_name);
		}
}
