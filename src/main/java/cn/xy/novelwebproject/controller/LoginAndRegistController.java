package cn.xy.novelwebproject.controller;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Msg;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.service.LoginAndRegistService;
import cn.xy.novelwebproject.service.ReaderServiceImp;
import cn.xy.novelwebproject.utils.JedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("loginAndRegist")
@Controller
public class LoginAndRegistController {
		@Autowired
		private LoginAndRegistService loginAndRegistService;
		private Logger logger = LoggerFactory.getLogger(ReaderServiceImp.class);
		private String referUrl = "/";

		@RequestMapping("login")
		public String Login(Model model, HttpSession session, HttpServletRequest request) {
				session.removeAttribute("user_reader");
				session.removeAttribute("user_auth");
				String urls[] = request.getHeader("Referer").split("wfRead");

				referUrl = urls[1].replaceFirst("/", "");
				logger.info(referUrl);
				return "login";
		}

		@RequestMapping("loginout")
		public String LoginOut(Model model, HttpSession session, HttpServletRequest request) {

				Jedis jedis = JedisUtils.getConnect();
				Reader reader = (Reader) session.getAttribute("user_reader");
				if (reader!=null) {
						jedis.del("reader:" + reader.getNick_name());
				}
				Author author = (Author) session.getAttribute("user_auth");
				if (author!=null){
						jedis.del("auth:" + author.getNick_name());
				}

				JedisUtils.close(jedis);

				session.removeAttribute("user_reader");
				session.removeAttribute("user_auth");

				String url = request.getHeader("Referer").replace("http://localhost:8080/wfRead/", "");
				return "redirect:/" + url;
		}

		@RequestMapping("backindexpage")
		public String BackIndexPage() {
				return "redirect:/index.jsp";
		}

		@RequestMapping("regist")
		public String Regist(Model model, HttpSession session) {
				model.addAttribute("user_auth", new Author());
				model.addAttribute("user_read", new Reader());
				return "regist";
		}

		@ResponseBody
		@RequestMapping("authlogin")
		public Msg AuthLogin(Author author, HttpServletRequest request, HttpSession session) {
				Msg msg = new Msg(true);
				logger.info(author.toString());
				Author authorLogin = loginAndRegistService.getAuthMsgByName(author.getNick_name());
				logger.info("AuthLogin auth="+authorLogin);
				if (!loginAndRegistService.AuthLogin(author)) {
						if(authorLogin==null){
								msg.setFlag(false);
								msg.setMessage("用户名错误！请检查后重试！");
						}
						else if (!authorLogin.getPassword().equals(author.getPassword())) {
								msg.setFlag(false);
								msg.setMessage("密码错误！请检查后重试！");
						}
				} else {
						msg.setFlag(true);
						msg.setMessage("登录成功！");
						session.setAttribute("user_auth", authorLogin);
						msg.setMessage(referUrl);
				}
				logger.info("authlogin:"+msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("readerlogin")
		public Msg Readerlogin(Reader reader, HttpServletRequest request, HttpSession session) {
				Msg msg = new Msg(true);
				logger.info("[login: reader]="+reader);
				Reader readerLogin = loginAndRegistService.getReaderMsgByName(reader.getNick_name());
				if (!loginAndRegistService.ReaderLogin(reader)) {
						if (readerLogin==null){
								msg.setFlag(false);
								msg.setMessage("用户名错误！请检查后重试！");
						}
						else if (!readerLogin.getPassword().equals(reader.getPassword())){
								msg.setFlag(false);
								msg.setMessage("密码错误！请检查后重试！");
						}
				} else {
						msg.setFlag(true);
						msg.setMessage("用户名错误！请检查后重试！");
						session.setAttribute("user_reader", readerLogin);
						msg.setMessage(referUrl);
				}
				logger.info("readerlogin:"+msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("authregist")
		public Msg authRegist(Author author, HttpSession session) {
				Msg msg = new Msg(true);
				if (loginAndRegistService.AuthRegist(author)) {
						msg.setFlag(true);
						msg.setMessage("注册成功！");
						session.removeAttribute("user_reader");
						session.setAttribute("user_auth", author);
				} else {
						msg.setFlag(false);
						msg.setMessage("注册失败！用户名以存在！");
				}
				logger.info("authregist:"+msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("readregist")
		public Msg readRegist(Reader reader, HttpSession session) {
				Msg msg = new Msg(true);
				if (loginAndRegistService.ReaderRegist(reader)) {
						msg.setFlag(true);
						msg.setMessage("注册成功！");
						session.removeAttribute("user_auth");
						session.setAttribute("user_reader", reader);
				} else {
						msg.setFlag(false);
						msg.setMessage("注册失败！用户名以存在！");
				}
				logger.info("readerregist:"+msg);
				return msg;
		}
}
