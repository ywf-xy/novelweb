package cn.xy.novelwebproject.controller;

import cn.xy.novelwebproject.bean.Msg;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("reader")
public class ReaderController {
		@Autowired
		private ReaderService readerService;

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
				request.setAttribute("readermsg", reader);
				return "reader";
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
								int code = readerService.SetUserHeadImg(username,fileName);
								if (code==-1){
										msg.setFlag(false);
										msg.setMessage("参数错误！请检查是否登录！");
								}
						} catch (Exception e) {
								e.printStackTrace();
						}
						msg.setMessage("上传成功");
						msg.setData(urls);
				} else {
						msg.setFlag(false);
						msg.setMessage("上传文件为空！请检查后重试！");
				}
				return msg;
		}
}
