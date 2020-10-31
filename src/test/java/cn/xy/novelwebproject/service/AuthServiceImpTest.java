package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.dao.AuthorMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class AuthServiceImpTest extends BaseTest {
		private Logger logger = LoggerFactory.getLogger("AuthServiceImpTest.class");
		@Autowired
		private AuthorMapper authorMapper;
		@Test
		public void getAllAuth() {
		}

		@Test
		public void selectAuthor() {
		}

		@Test
		public void getAuthorWorks() {
		}

		@Test
		public void updatePassword(){
				String old_pwd = "1";
				String nick_name = "8难";
				String new_pwd = "123456";
				boolean flag = false;
				try {
						//1、检查旧密码是否正确
						flag = authorMapper.findAuthByNamePwd(nick_name,old_pwd)==1?true:false;
						//2、修改密码
						flag = authorMapper.updatePasswordByName(nick_name,new_pwd);
				} catch (Exception e) {
						logger.error("erro:"+e.getMessage());
				}
				logger.info("是否成功="+flag);
		}

		@Test
		public void getAuthByName() {
				Author author = authorMapper.findAuthorByName("8难");
				logger.info("findauthorbyname auth="+author);
		}

		@Test
		public void updateAuthWork() {
				boolean b = authorMapper.updateAuthWork("从当爷爷开始", "我是爷爷，你们都是孙子", "完结");
				logger.info("updateAuthWork result="+b);
		}
}