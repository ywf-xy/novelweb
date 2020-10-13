package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.bean.Author;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthorMapper {
		public List<Author> getAllAuth ();

		public List<Author> selectAuthor (String nickname);

		Author getAuthorWorks (String nick_name);
}