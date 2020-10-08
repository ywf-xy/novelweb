package cn.xy.novelwebproject.controller;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Msg;
import cn.xy.novelwebproject.service.AuthService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@RequestMapping("auth")
public class AuthController {
		@Autowired
		private AuthService authService;

		@RequestMapping("getAllAuthor")
		public String getAllAuth (Model model) {
				System.out.println("getallauthor");
				List<Author> authors = authService.getAllAuth();
				String str = "";
				for (Author auth : authors) {
						System.out.println(auth);
						str += auth.toString();
				}
				model.addAttribute("str", str);
				return "sucess";
		}

		@RequestMapping("getauth")
		public String selectAuthor (@Param("nickname") String nickname, Model model) throws UnsupportedEncodingException {
				model.addAttribute("str", nickname);
				List<Author> authorList = authService.selectAuthor(nickname);
				for (Author auth : authorList
				) {
						System.out.println(auth);
				}
				model.addAttribute("auths", authorList);
				return "sucess";
		}

		@ResponseBody
		@RequestMapping("getauthorworks")
		public Msg getauthorworks (HttpServletRequest request) {
				Msg msg = new Msg(true);
				String nickname = request.getParameter("authname");
				try {
						Author authror = authService.getAuthorWorks(nickname);

						msg.setData(authror);
				} catch (Exception e) {
						e.printStackTrace();
				}
				return msg;
		}
}