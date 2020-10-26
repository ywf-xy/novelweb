package cn.xy.novelwebproject.controller;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Msg;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.service.AuthService;
import cn.xy.novelwebproject.service.ReaderServiceImp;
import org.apache.ibatis.annotations.Param;
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
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("auth")
public class AuthController {
		@Autowired
		private AuthService authService;
		//日志打印
		private Logger logger = LoggerFactory.getLogger(ReaderServiceImp.class);

		/*
		* 获取所有作者
		* */
		@RequestMapping("getAllAuthor")
		public String getAllAuth (Model model) {
				System.out.println("getallauthor");
				List<Author> authors = authService.getAllAuth();
				String str = "";
				for (Author auth : authors) {
						logger.info("getAllAuth:authors:"+auth);
						str += auth.toString();
				}
				model.addAttribute("str", str);
				return "sucess";
		}

		/*
		* 获取作者信息
		* */
		@RequestMapping("getauth")
		public String selectAuthor (@Param("nickname") String nickname, Model model) throws UnsupportedEncodingException {
				model.addAttribute("str", nickname);
				List<Author> authorList = authService.selectAuthor(nickname);
				for (Author auth : authorList
				) {
						logger.info("selectAuthor:authlist"+auth);
				}
				model.addAttribute("auths", authorList);
				return "sucess";
		}

		/*
		* 获取作者全部作品
		* */
		@ResponseBody
		@RequestMapping("getauthorworks")
		public Msg getauthorworks (HttpServletRequest request) {
				Msg msg = new Msg(true);
				String nickname = request.getParameter("authname");
				try {
						Author authror = authService.getAuthorWorks(nickname);

						msg.setData(authror);
				} catch (Exception e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				}
				logger.info("getauthorworks:"+msg);
				return msg;
		}

		/*
		* 跳转作者中心页面
		* */
		@RequestMapping("authorUI")
		public String authorUI(Model model ,HttpServletRequest request){
				return "author";
		}

		/*
		* 跳转作品上传页面
		* */
		@RequestMapping("fileuploadUI")
		public String fileUpLoadUI(){
				return "workupload";
		}

		/*
		* 小说图片上传
		* */
		@RequestMapping("nimgupload")
		@ResponseBody
		public Msg imgUpLoad(HttpServletRequest request,@RequestParam(value = "file") MultipartFile multipartfile,String filename){
				Msg msg = new Msg();
				logger.info("[novelimg:img]="+multipartfile);
				logger.info("[novelimg:name]="+filename);
				try {
						if (!multipartfile.isEmpty()){
								//部署到服务器地址；String contexPath= request.getSession().getServletContext().getRealPath("/static/picture");
								String targetDir= "D:\\NovelWebProject\\src\\main\\webapp\\static\\picture";
								logger.info("[novelimg:path]="+targetDir);
								//1.3 创建path对象 File new File()-->Path  Paths.get() 新的对象
								Path targetDirFile = Paths.get(targetDir);

								//1.4 创建不存在的目录 如果上传的文件目录不存在就创建
								if (Files.notExists(targetDirFile)) {
										Files.createDirectories(targetDirFile);
								}
								//2 获取上传文件的文件名
								String fileName = multipartfile.getOriginalFilename();

								// 3 保存到项目的服务器发布路径
								/*D:/NovelWebProject/src/main/webapp/static/user-photo/用户名/filename*/
								Path target = Paths.get(targetDir, filename);//get(目标路径，文件名)

								//4 将数据写入文件
								multipartfile.transferTo(target.toFile());
								File file = new File(targetDir+ File.separator+filename);
								logger.info("[file]="+file.exists()+" "+file.getAbsolutePath());
								msg.setFlag(true);
								msg.setMessage("上传成功！上传文件为："+fileName);
						}else {
								msg.setFlag(false);
								msg.setMessage("上传文件为空！请检查后重试！");
						}
				} catch (IOException e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				}
				return msg;
		}

		/*
		* 小说上传
		* */
		@RequestMapping("novelupload")
		@ResponseBody
		public Msg novelUpLoad(HttpServletRequest request,@RequestParam(value = "file") MultipartFile multipartfile,String filename){
				Msg msg = new Msg();
				logger.info("[noveltxt:img]="+multipartfile);
				logger.info("[noveltxt:name]="+filename);
				try {
						if (!multipartfile.isEmpty()){
								//String contexPath= request.getSession().getServletContext().getRealPath("/static/picture");
								String targetDir= "D:\\NovelWebProject\\src\\main\\webapp\\static\\txt";
								logger.info("[noveltxt:path]="+targetDir);
								//1.3 创建path对象 File new File()-->Path  Paths.get() 新的对象
								Path targetDirFile = Paths.get(targetDir);

								//1.4 创建不存在的目录 如果上传的文件目录不存在就创建
								if (Files.notExists(targetDirFile)) {
										Files.createDirectories(targetDirFile);
								}
								//2 获取上传文件的文件名
								String fileName = multipartfile.getOriginalFilename();

								// 3 保存到项目的服务器发布路径
								/*D:/NovelWebProject/src/main/webapp/static/user-photo/用户名/filename*/
								Path target = Paths.get(targetDir, filename);//get(目标路径，文件名)

								//4 将数据写入文件
								multipartfile.transferTo(target.toFile());
								File file = new File(targetDir+ File.separator+filename);
								logger.info("[file]="+file.exists()+" "+file.getAbsolutePath());
								msg.setFlag(true);
								msg.setMessage("上传成功！上传文件为："+fileName);
						}else {
								msg.setFlag(false);
								msg.setMessage("上传文件为空！请检查后重试！");
						}
				} catch (IOException e) {
						logger.error("错误消息：{}",e.getMessage(),e);
				}
				return msg;
		}

		/*
		* 跳转用户中心页面
		* */
		@RequestMapping("authorcenterUI")
		public String authorCenter(HttpServletRequest request){
				Author author = (Author) request.getSession().getAttribute("user_auth");
				request.setAttribute("author",author);
				return "author_center";
		}

		/*
		* 更新作者密码
		* */
		@RequestMapping("updatepasswrod")
		@ResponseBody
		public Msg<Object> updataPassword(String old_pwd,String new_pwd,String cfm_pwd,HttpServletRequest request){
				Msg<Object> msg = new Msg<>(false);
				Author author = (Author) request.getSession().getAttribute("user_auth");
				if (author==null){
						msg.setFlag(false);
						msg.setMessage("对不起请登录！");
						return msg;
				}
				String nick_name = author.getNick_name();
				logger.info("oldPassword="+old_pwd);
				logger.info("newPassword="+new_pwd);
				logger.info("confirmPassword="+cfm_pwd);
				if ("".equals(old_pwd)||old_pwd==null){
						msg.setMessage("旧密码不能为空！");
						return msg;
				}else if(new_pwd==null||"".equals(new_pwd)){
						msg.setMessage("新密码不能为空！");
						return msg;
				}else if(!new_pwd.equals(cfm_pwd)){
						msg.setMessage("前后输入密码不一致，请重新输入！");
						return msg;
				}else if(new_pwd.length()>15){
						msg.setMessage("密码长度不能超过15位，请重新输入！");
						return msg;
				}
				boolean flag = authService.updatePassword(nick_name,old_pwd,new_pwd);
				msg.setFlag(flag);
				if (flag){
						msg.setMessage("密码修改成功！");
				}else {
						msg.setMessage("密码修改失败！请检查旧密码是否正确！");
				}
				return msg;
		}


		/*
		* 清空头像
		* */
		public void emptyDir(String path){
				File file = new File(path);
				for(File f: file.listFiles()){
						logger.info("[delete: imgfile ]="+f.getName());
						f.delete();
				}
		}
		/*
		* 头像上传
		* */
		@RequestMapping("authheadupload")
		@ResponseBody
		public Msg<Object> uploadHeadImg(HttpServletRequest request, @RequestParam(value = "file") MultipartFile multipartfile){
				Msg<Object> msg = new Msg<>(false);
				MultipartFile multipartFile = multipartfile;
				Author author = (Author) request.getSession().getAttribute("user_auth");

				List<String> urls = new ArrayList<>();
				logger.info("uploadimg auth="+author.toString());
				if (!multipartFile.isEmpty()) {
						try {
								//1创建目录
								// 1.1 获取发布路径
								String username = author.getNick_name();
								if (author==null||username==null){
										msg.setFlag(false);
										msg.setMessage("请登录");
										return msg;
								}
								String dir = "static/auth-photo/" + username;

								// 1.2 目标路径
								/*D:/NovelWebProject/src/main/webapp/static/auth-photo/用户名*/
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
								/*D:/NovelWebProject/src/main/webapp/static/auth-photo/用户名/filename*/
								Path target = Paths.get(targetDir, fileName);//get(目标路径，文件名)

								//4 将数据写入文件
								multipartFile.transferTo(target.toFile());

								//5 将路径添加到urls中
								urls.add("wfRead/" + dir + File.separator + fileName);
								logger.info("imgupload url="+urls);
								//设置用户头像
								//插入数据库
								int code = authService.SetUserHeadImg(username, fileName);
								if (code == -1) {
										msg.setFlag(false);
										msg.setMessage("参数错误！请检查是否登录！");
								} else if (code == 0) {
										msg.setFlag(false);
										msg.setMessage("上传失败！请稍后重试！");
								} else {
										msg.setFlag(true);
										request.getSession().setAttribute("user_auth", authService.getAuthByName(username));
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
}
