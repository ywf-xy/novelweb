package cn.xy.novelwebproject.controller;

import cn.xy.novelwebproject.BaseTest;
import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Msg;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.dao.LoginAndRegistMapper;
import cn.xy.novelwebproject.service.LoginAndRegistService;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class LoginAndRegistControllerTest extends BaseTest {
		@Autowired
		private LoginAndRegistService loginAndRegistService;
		@Test
		public void authLogin() {
				Msg msg = new Msg(true);
				Author author = new Author();
				author.setNick_name("严七官");
				author.setPassword("123456");
				if (loginAndRegistService.AuthLogin(author)) {
						Author authorLogin = loginAndRegistService.getAuthMsgByName(author.getNick_name());
						if(authorLogin.getPassword().equals(author.getPassword())){

						}else{
								msg.setFlag(false);
								msg.setMessage("密码错误！请检查后重试！");
						}
				} else {
						msg.setFlag(false);
						msg.setMessage("用户名错误！请检查后重试！");
				}
				System.out.println(msg);
		}

		@Test
		public void readerlogin() {
				Reader ywf = loginAndRegistService.getReaderMsgByName("ywf");
				System.out.println(ywf);
		}
}