package cn.xy.novelwebproject.controller;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Msg;
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
import java.util.List;


@Controller
@RequestMapping("auth")
public class AuthController {
		@Autowired
		private AuthService authService;

		private Logger logger = LoggerFactory.getLogger(ReaderServiceImp.class);
		
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
		@RequestMapping("authorUI")
		public String authorUI(Model model ,HttpServletRequest request){

				return "author";
		}
		@RequestMapping("fileuploadUI")
		public String fileUpLoadUI(){
				return "workupload";
		}
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
}
