package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Reader;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginAndRegistMapper {
		public List<Author> AuthLogin (Author author);

		public List<Reader> ReaderLogin (Reader reader);

		public int AuthRegist (Author author);

		public int ReaderRegist (Reader reader);

		Reader selectByReaderKey(String nick_name);
}
