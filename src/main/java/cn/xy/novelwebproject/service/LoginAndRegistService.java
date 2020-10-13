package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Reader;
import org.springframework.stereotype.Service;

public interface LoginAndRegistService {
		public boolean AuthLogin (Author author);

		public boolean ReaderLogin (Reader reader);

		public boolean AuthRegist (Author author);

		public boolean ReaderRegist (Reader reader);

		Reader getReaderMsgByName(String nick_name);

		Author getAuthMsgByName(String nick_name);
}
