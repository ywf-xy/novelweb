package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Reader;

public interface ReaderService {

		 Reader getReaderByName(String nick_name) ;

		int SetUserHeadImg(String nick_name,String imgname);
}
