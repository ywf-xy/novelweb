package cn.xy.novelwebproject.controller;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Msg;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.service.LoginAndRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("loginAndRegist")
@Controller
public class LoginAndRegistController {
		@Autowired
		private LoginAndRegistService loginAndRegistService;
		private String referUrl = "/";

		@RequestMapping("login")
		public String Login (Model model, HttpSession session, HttpServletRequest request) {
				session.removeAttribute("user_reader");
				session.removeAttribute("user_auth");
				String urls[] = request.getHeader("Referer").split("wfRead");
				//System.out.println("UrL="+request.getHeader("Referer"));
				/*if ("/".equals(referUrl)) {
						referUrl = "/";
				} else {
						referUrl = urls[1];
				}*/
				referUrl = urls[1].replaceFirst("/", "");
				//System.out.println("REFER="+referUrl);
				return "login";
		}

		@RequestMapping("loginout")
		public String LoginOut (Model model, HttpSession session, HttpServletRequest request) {
				session.removeAttribute("user_reader");
				session.removeAttribute("user_auth");

				String url = request.getHeader("Referer").replace("http://localhost:8080/wfRead/", "");
				return "redirect:/" + url;
		}

		@RequestMapping("backindexpage")
		public String BackIndexPage () {
				return "redirect:/index.jsp";
		}

		@RequestMapping("regist")
		public String Regist (Model model, HttpSession session) {
				model.addAttribute("user_auth", new Author());
				model.addAttribute("user_read", new Reader());
				return "regist";
		}

		@ResponseBody
		@RequestMapping("authlogin")
		public Msg AuthLogin (Author author, HttpServletRequest request, HttpSession session) {
				Msg msg = new Msg(true);
				if (loginAndRegistService.AuthLogin(author)) {
						session.setAttribute("user_auth", author);
						msg.setMessage(referUrl);
				} else {
						msg.setFlag(false);
						msg.setMessage("用户名或密码错误！请检查后重试！");
				}
				return msg;
		}

		@ResponseBody
		@RequestMapping("readerlogin")
		public Msg Readerlogin (Reader reader, HttpServletRequest request, HttpSession session) {
				Msg msg = new Msg(true);
				if (loginAndRegistService.ReaderLogin(reader)) {
						session.setAttribute("user_reader", reader);
						//System.out.println(referUrl);
						msg.setMessage(referUrl);
				} else {
						msg.setFlag(false);
						msg.setMessage("用户名或密码错误！请检查后重试！");
				}
				return msg;
		}

		@ResponseBody
		@RequestMapping("authregist")
		public Msg authRegist (Author author, HttpSession session) {
				Msg msg = new Msg(true);
				if (loginAndRegistService.AuthRegist(author)) {
						msg.setFlag(true);
						msg.setMessage("注册成功！");
						session.removeAttribute("user_reader");
						session.setAttribute("user_auth", author);
				} else {
						msg.setFlag(false);
						msg.setMessage("注册失败！");
				}
				return msg;
		}

		@ResponseBody
		@RequestMapping("readregist")
		public Msg readRegist (Reader reader, HttpSession session) {
				Msg msg = new Msg(true);
				if (loginAndRegistService.ReaderRegist(reader)) {
						msg.setFlag(true);
						msg.setMessage("注册成功！");
						session.removeAttribute("user_auth");
						session.setAttribute("user_reader", reader);
				} else {
						msg.setFlag(false);
						msg.setMessage("注册失败！");
				}
				return msg;
		}
}
