package cn.xy.novelwebproject.controller;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Msg;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.service.ReaderService;
import cn.xy.novelwebproject.service.ReaderServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("reader")
public class ReaderController {
		@Autowired
		private ReaderService readerService;
		private Logger logger = LoggerFactory.getLogger(ReaderServiceImp.class);
		
		@RequestMapping("getusermsg")
		public String getReaderAllMsg(HttpServletRequest request) {
				//从session获取用户名
				HttpSession session = request.getSession();
				Reader read_user = (Reader) session.getAttribute("user_reader");
				if (read_user == null) {
						return "redirect:/";
				}
				String nick_name = read_user.getNick_name();

				//根据用户名从数据库中查用用户的所有信息
				Reader reader = readerService.getReaderByName(nick_name);
				logger.info("getReaderAllMsg:"+reader);
				request.setAttribute("readermsg", reader);
				return "reader";
		}

		public void emptyDir(String path){
				File file = new File(path);
				for(File f: file.listFiles()){
						logger.info("[delete: imgfile ]="+f.getName());
						f.delete();
				}

		}
		/*
		 * 图片上传
		 * */
		@ResponseBody
		@RequestMapping("imgupload")
		public Msg ImgUpLoad(HttpServletRequest request, @RequestParam(value = "file") MultipartFile multipartfile) {
				Msg msg = new Msg(true);
				MultipartFile multipartFile = multipartfile;
				Reader reader = (Reader) request.getSession().getAttribute("user_reader");
				List<String> urls = new ArrayList<>();

				if (!multipartFile.isEmpty()) {
						try {
								//1创建目录
								// 1.1 获取发布路径
								String username = reader.getNick_name();
								String dir = "static/user-photo/" + username;

								// 1.2 目标路径
								/*D:/NovelWebProject/src/main/webapp/static/user-photo/用户名*/
								String targetDir = request.getServletContext().getRealPath(dir);

								//1.3 创建path对象 File new File()-->Path  Paths.get() 新的对象
								Path targetDirFile = Paths.get(targetDir);

								//1.4 创建不存在的目录 如果上传的文件目录不存在就创建
								if (Files.notExists(targetDirFile)) {
										Files.createDirectories(targetDirFile);
								}
								emptyDir(targetDir);
								//2 获取上传文件的文件名
								String fileName = multipartFile.getOriginalFilename();

								// 3 保存到项目的服务器发布路径
								/*D:/NovelWebProject/src/main/webapp/static/user-photo/用户名/filename*/
								Path target = Paths.get(targetDir, fileName);//get(目标路径，文件名)

								//4 将数据写入文件
								multipartFile.transferTo(target.toFile());

								//5 将路径添加到urls中
								urls.add("wfRead/" + dir + File.separator + fileName);

								//设置用户头像
								//插入数据库
								int code = readerService.SetUserHeadImg(username, fileName);
								if (code == -1) {
										msg.setFlag(false);
										msg.setMessage("参数错误！请检查是否登录！");
								} else if (code == 0) {
										msg.setFlag(false);
										msg.setMessage("上传失败！请稍后重试！");
								} else {
										request.getSession().setAttribute("user_reader", readerService.getReaderByName(username));
								}
						} catch (Exception e) {
								logger.error("错误消息：{}",e.getMessage(),e);
								msg.setFlag(false);
								msg.setMessage(e.getMessage());
						}
						msg.setMessage("上传成功");
						msg.setData(urls);
				} else {
						msg.setFlag(false);
						msg.setMessage("上传文件为空！请检查后重试！");
				}
				logger.info("ImgUpLoad:"+msg);
				return msg;
		}

		/*
		 * 用户信息修改
		 *
		 * */
		@ResponseBody
		@RequestMapping("updatareader")
		public Msg updataReader(HttpServletRequest request) {
				Msg msg = new Msg(true);
				Reader reader = new Reader();
				try {
						String nick_name = request.getParameter("nick_name");
						String sex = request.getParameter("sex");
						String birthStr = request.getParameter("birthday");
						String pattern = "\\d{4}-\\d{1,2}-\\d{1,2}";
						Date birthday = null;
						String address = request.getParameter("address");
						String intro = request.getParameter("intro");

						int flag = 0;
						Map<String, String> erroField = new HashMap<String, String>();
						// 创建 Pattern 对象
						Pattern r = Pattern.compile(pattern);
						// 现在创建 matcher 对象
						Matcher m = r.matcher(birthStr);
						if (m.find()) {
								birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthStr);
						} else {
								erroField.put("birthday", "输入生日格式有误!应为yyyy-MM-dd格式！");
						}
						if (nick_name == null || "".equals(nick_name)) {
								erroField.put("nick_name", "昵称不能为空！");
						}
						if (intro.length() >= 100) {
								erroField.put("intro", "简介长度不能超过100");
						}
						if (address.length() >= 200) {
								erroField.put("address", "地址长度不能超过200");
						}
						if (erroField.isEmpty()) {
								reader.setNick_name(nick_name);
								reader.setSex(sex);
								reader.setBirthday(birthday);
								reader.setIntro(intro);
								reader.setAddress(address);
								flag = readerService.UpdateReaderMsg(reader);
						}
						if (flag == 1) {
								msg.setFlag(true);
						} else {
								msg.setFlag(false);
								msg.setFieldErrers(erroField);
								msg.setMessage("更新资料失败!请重试！");
						}

				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
						msg.setFlag(false);
						msg.setMessage(e.getMessage());
				}
				logger.info("updataReader:"+msg);
				return msg;
		}

		/*
		* 获取用户书架
		* */
		@ResponseBody
		@RequestMapping("getbookshelf")
		public Msg findBookShelfByName(HttpServletRequest request) {
				Msg msg = new Msg(true);
				Reader reader = null;
				try {
						String nick_name = request.getParameter("nick_name");
						if (nick_name == null || "".equals(nick_name)) {
								msg.setFlag(false);
								msg.setMessage("无法查询用户：" + nick_name + " 的书架信息，请刷新或重新登陆！");
						} else {
								reader = readerService.findBookShelfByName(nick_name);
								if (reader != null) {
										request.getSession().setAttribute("user_reader", reader);
										msg.setData(reader);
										msg.setFlag(true);
								} else {
										msg.setFlag(false);
										msg.setMessage("找不到用户" + nick_name + "的信息，请刷新或重新登陆！");
								}
						}
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
						msg.setFlag(false);
						msg.setMessage(e.getMessage());
				}
				logger.info("findBookShelfByName:"+msg);
				return msg;
		}

		/*
		* 跳转到用户书架页面
		* */
		@RequestMapping("personshelf")
		public String bookShelf(HttpServletRequest request, HttpServletResponse response) {
				String username = request.getParameter("nick_name");
				try {
						Reader reader = readerService.findBookShelfByName(username);
						logger.info("bookShelf："+reader);
						request.setAttribute("bookshelfs", reader.getMybookshelf());
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				}
				return "bookshelf";
		}
		/*
		* 通过id删除书架的小说
		* */
		@RequestMapping("deletbookfromshelf")
		@ResponseBody
		public Msg deletBookFromShelf(HttpServletRequest request){
				Msg msg = new Msg(true);
				String id = request.getParameter("id");
				Reader reader = (Reader)request.getSession().getAttribute("user_reader");
				int result = readerService.deletBookFromShelf(reader.getNick_name(),Integer.parseInt(id));
				if (result!=1){
						msg.setFlag(false);
						msg.setMessage("删除失败!请刷新后重试！");
				}
				logger.info("deletBookFromShelf:"+msg);
				return msg;
		}

		/*
		* 加入书签
		* */
		@ResponseBody
		@RequestMapping("addbookmark")
		public Msg addBookMark(HttpServletRequest request){
				Msg msg = new Msg(true);
				String catlogname = request.getParameter("catlogname");
				String nick_name = null;
				String book_name = request.getParameter("book_name");

				if (request.getSession().getAttribute("user_reader")!=null){
						Reader reader = (Reader) request.getSession().getAttribute("user_reader");
						nick_name = reader.getNick_name();
				}
				else if(request.getSession().getAttribute("user_auth")!=null){
						msg.setFlag(false);
						msg.setMessage("对不起，作者账号不能添加书签哦！请切换到读者账号");
				}else {
						msg.setFlag(false);
						msg.setMessage("对不起，需要登录后才能添加书签哦！");
				}

				try {
						boolean b = false;
						if (nick_name!=null){
								b= readerService.addBookMark(nick_name, book_name, catlogname);
								String action = b?"书签添加成功！":"添加失败！书签以存在";
								msg.setMessage(action);
						}

				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				}
				logger.info("addBookMark:"+msg);
				return msg;
		}
}
