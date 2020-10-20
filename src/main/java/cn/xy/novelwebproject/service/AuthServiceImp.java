package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.dao.AuthorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authService")
public class AuthServiceImp implements AuthService {
		@Autowired
		private AuthorMapper authorMapper;

		private Logger logger = LoggerFactory.getLogger(ReaderServiceImp.class);

		public AuthServiceImp (AuthorMapper authorMapper) {
				this.authorMapper = authorMapper;
		}

		public List<Author> getAllAuth () {
				List<Author> authors = authorMapper.getAllAuth();
				logger.info("所有作者："+authors);
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
