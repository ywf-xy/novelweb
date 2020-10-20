package cn.xy.novelwebproject.controller;

import cn.xy.novelwebproject.bean.*;
import cn.xy.novelwebproject.service.NovelService;
import cn.xy.novelwebproject.service.ReaderServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("novel")
public class NovelController {
		@Autowired
		private NovelService novelService;
		private Logger logger = LoggerFactory.getLogger(ReaderServiceImp.class);

		@RequestMapping("getshownovel")
		@ResponseBody
		public Msg getShowNovel(HttpServletRequest request) {
				Msg msg = new Msg(true);
				int i = new Integer(request.getParameter("colum"));
				int index = new Integer(request.getParameter("index"));
				List<Novel> novelList = new ArrayList<Novel>();
				try {
						for (int j = 0; j < 4; j++) {
								Novel novel = novelService.getNovelByColums(i, 1);
								novelList.add(novel);
								i += index;
						}
						msg.setData(novelList);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setFlag(false);
				}
				logger.info(" getShowNovel:" + msg);
				return msg;
		}

		@RequestMapping("editorpush")
		@ResponseBody
		public Msg editorPush(HttpServletRequest request) {
				Msg msg = new Msg(true);
				int colum = new Integer(request.getParameter("colum"));
				int index = new Integer(request.getParameter("index"));
				HashMap<String, Integer> map = new HashMap<>();
				map.put("colum", colum);
				map.put("index", index);
				try {
						List<Novel> novels = novelService.getNovelByColumindex(map);
						msg.setData(novels);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setFlag(false);
				}
				logger.info(" editorPush:" + msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("weekrecommend")
		public Msg weekRecommend(HttpServletRequest request) {
				Msg msg = new Msg(true);
				try {
						List<Novel> novels = novelService.getWeekWecommend();
						msg.setData(novels);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setFlag(false);
				}
				logger.info("weekRecommend:" + msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("gethomepagelist")
		public Msg gethomepagelist(HttpServletRequest request) {
				Msg msg = new Msg(true);
				List<Novel> novelList = new ArrayList<Novel>();
				try {
						//都市榜
						List<Novel> nove1 = novelService.getCityList(10);

						for (Novel n : nove1) {
								novelList.add(n);
						}
						// 仙侠榜
						List<Novel> nove2 = novelService.getImmortalKnightList(10);

						for (Novel n : nove2) {
								novelList.add(n);
						}
						// 科幻榜
						List<Novel> nove3 = novelService.getScienceFictionList(10);

						for (Novel n : nove3) {
								novelList.add(n);
						}
						// 玄幻榜
						List<Novel> nove4 = novelService.getFantasyList(10);

						for (Novel n : nove4) {
								novelList.add(n);
						}
						// 军事榜
						List<Novel> nove5 = novelService.getMilitaryList(10);

						for (Novel n : nove5) {
								novelList.add(n);
						}
						// 历史榜
						List<Novel> nove6 = novelService.getHistoryList(10);

						for (Novel n : nove6) {
								novelList.add(n);
						}
						// 网游榜
						List<Novel> nove7 = novelService.getOnlineGameList(10);

						for (Novel n : nove7) {
								novelList.add(n);
						}
						// 武侠榜
						List<Novel> nove8 = novelService.getMartialArtsList(10);

						for (Novel n : nove8) {
								novelList.add(n);
						}

						msg.setData(novelList);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setFlag(false);
				}
				logger.info("gethomepagelist:" + msg);
				return msg;
		}

		/*
		 *limit分页公式：curPage是当前第几页；pageSize是一页多少条记录
		 * select * from student limit(curPage-1)*pageSize,pageSize;
		 * 总页数公式：totalRecord是总记录数；pageSize是一页分多少条记录
		 *int totalPageNum = (totalRecord +pageSize - 1) / pageSize;
		 * */
		@ResponseBody
		@RequestMapping("xuanhuan")
		public Msg getXuanHuanPageData(HttpServletRequest request) {
				Msg msg = new Msg(true);
				//获取参数
				Integer pagenum = new Integer(request.getParameter("page"));
				Integer pagesize = new Integer(request.getParameter("pagesize"));
				String load = request.getParameter("flag");
				//查询指定页面数据
				int allpage = (novelService.getAllPage("玄幻") + pagesize - 1) / pagesize;
				PageQuery query = new PageQuery();
				query.setCurpage((pagenum - 1) * pagesize);
				query.setPagesize(pagesize);
				query.setType("玄幻");
				try {
						if ("1".equals(load)) {
								List<Novel> list[] = new ArrayList[4];
								list[0] = novelService.getPageByType(query);
								//侧边栏数据
								query.setPagesize(10);
								query.setType("玄幻");
								list[1] = novelService.getDownloadsList(query);
								query.setPagesize(10);
								query.setType("玄幻");
								list[2] = novelService.getCollectionList(query);
								query.setPagesize(10);
								query.setType("玄幻");
								list[3] = novelService.getMonthTicketList(query);
								msg.setData(list);
						} else {
								List<Novel> list = novelService.getPageByType(query);
								msg.setData(list);
						}
						msg.setMessage(String.valueOf(allpage));
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setFlag(false);
						msg.setMessage("数据库出错了！请与管理员联系！");
				}
				logger.info("getXuanHuanPageData:" + msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("kehuan")
		public Msg getKeHuanPageData(HttpServletRequest request) {
				Msg msg = new Msg(true);
				//获取参数
				Integer pagenum = new Integer(request.getParameter("page"));
				Integer pagesize = new Integer(request.getParameter("pagesize"));
				String load = request.getParameter("flag");
				//查询指定页面数据
				int allpage = (novelService.getAllPage("科幻") + pagesize - 1) / pagesize;
				PageQuery query = new PageQuery();
				query.setCurpage((pagenum - 1) * pagesize);
				query.setPagesize(pagesize);
				query.setType("科幻");
				try {
						if ("1".equals(load)) {
								List<Novel> list[] = new ArrayList[4];
								list[0] = novelService.getPageByType(query);
								//侧边栏数据
								query.setPagesize(10);
								query.setType("科幻");
								list[1] = novelService.getDownloadsList(query);
								query.setPagesize(10);
								query.setType("科幻");
								list[2] = novelService.getCollectionList(query);
								query.setPagesize(10);
								query.setType("科幻");
								list[3] = novelService.getMonthTicketList(query);
								msg.setData(list);
						} else {
								List<Novel> list = novelService.getPageByType(query);
								msg.setData(list);
						}
						msg.setMessage(String.valueOf(allpage));
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setFlag(false);
						msg.setMessage("数据库出错了！请与管理员联系！");
				}
				logger.info("getKeHuanPageData:" + msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("lishi")
		public Msg getLiShiPageData(HttpServletRequest request) {
				Msg msg = new Msg(true);
				//获取参数
				Integer pagenum = new Integer(request.getParameter("page"));
				Integer pagesize = new Integer(request.getParameter("pagesize"));
				String load = request.getParameter("flag");
				//查询指定页面数据
				int allpage = (novelService.getAllPage("历史") + pagesize - 1) / pagesize;
				PageQuery query = new PageQuery();
				query.setCurpage((pagenum - 1) * pagesize);
				query.setPagesize(pagesize);
				query.setType("历史");
				try {
						if ("1".equals(load)) {
								List<Novel> list[] = new ArrayList[4];
								list[0] = novelService.getPageByType(query);
								//侧边栏数据
								query.setPagesize(10);
								query.setType("历史");
								list[1] = novelService.getDownloadsList(query);
								query.setPagesize(10);
								query.setType("历史");
								list[2] = novelService.getCollectionList(query);
								query.setPagesize(10);
								query.setType("历史");
								list[3] = novelService.getMonthTicketList(query);
								msg.setData(list);
						} else {
								List<Novel> list = novelService.getPageByType(query);
								msg.setData(list);
						}
						msg.setMessage(String.valueOf(allpage));
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setFlag(false);
						msg.setMessage("数据库出错了！请与管理员联系！");
				}
				logger.info("getLiShiPageData:" + msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("dushi")
		public Msg getDuShiPageData(HttpServletRequest request) {
				Msg msg = new Msg(true);
				//获取参数
				Integer pagenum = new Integer(request.getParameter("page"));
				Integer pagesize = new Integer(request.getParameter("pagesize"));
				String load = request.getParameter("flag");
				//查询指定页面数据
				int allpage = (novelService.getAllPage("都市") + pagesize - 1) / pagesize;
				PageQuery query = new PageQuery();
				query.setCurpage((pagenum - 1) * pagesize);
				query.setPagesize(pagesize);
				query.setType("都市");
				try {
						if ("1".equals(load)) {
								List<Novel> list[] = new ArrayList[4];
								list[0] = novelService.getPageByType(query);
								//侧边栏数据
								query.setPagesize(10);
								query.setType("都市");
								list[1] = novelService.getDownloadsList(query);
								query.setPagesize(10);
								query.setType("都市");
								list[2] = novelService.getCollectionList(query);
								query.setPagesize(10);
								query.setType("都市");
								list[3] = novelService.getMonthTicketList(query);
								msg.setData(list);
						} else {
								List<Novel> list = novelService.getPageByType(query);
								msg.setData(list);
						}
						msg.setMessage(String.valueOf(allpage));
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setFlag(false);
						msg.setMessage("数据库出错了！请与管理员联系！");
				}
				logger.info("getDuShiPageData:" + msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("wuxia")
		public Msg getWuXiaPageData(HttpServletRequest request) {
				Msg msg = new Msg(true);
				//获取参数
				Integer pagenum = new Integer(request.getParameter("page"));
				Integer pagesize = new Integer(request.getParameter("pagesize"));
				String load = request.getParameter("flag");
				//查询指定页面数据
				int allpage = (novelService.getAllPage("武侠") + pagesize - 1) / pagesize;
				PageQuery query = new PageQuery();
				query.setCurpage((pagenum - 1) * pagesize);
				query.setPagesize(pagesize);
				query.setType("武侠");
				try {
						if ("1".equals(load)) {
								List<Novel> list[] = new ArrayList[4];
								list[0] = novelService.getPageByType(query);
								//侧边栏数据
								query.setPagesize(10);
								query.setType("武侠");
								list[1] = novelService.getDownloadsList(query);
								query.setPagesize(10);
								query.setType("武侠");
								list[2] = novelService.getCollectionList(query);
								query.setPagesize(10);
								query.setType("武侠");
								list[3] = novelService.getMonthTicketList(query);
								msg.setData(list);
						} else {
								List<Novel> list = novelService.getPageByType(query);
								msg.setData(list);
						}
						msg.setMessage(String.valueOf(allpage));
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setFlag(false);
						msg.setMessage("数据库出错了！请与管理员联系！");
				}
				logger.info("getWuXiaPageData:" + msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("wangyou")
		public Msg getWangYouPageData(HttpServletRequest request) {
				Msg msg = new Msg(true);
				//获取参数
				Integer pagenum = new Integer(request.getParameter("page"));
				Integer pagesize = new Integer(request.getParameter("pagesize"));
				String load = request.getParameter("flag");
				//查询指定页面数据
				int allpage = (novelService.getAllPage("游戏") + pagesize - 1) / pagesize;
				PageQuery query = new PageQuery();
				query.setCurpage((pagenum - 1) * pagesize);
				query.setPagesize(pagesize);
				query.setType("游戏");
				try {
						if ("1".equals(load)) {
								List<Novel> list[] = new ArrayList[4];
								list[0] = novelService.getPageByType(query);
								//侧边栏数据
								query.setPagesize(10);
								query.setType("游戏");
								list[1] = novelService.getDownloadsList(query);
								query.setPagesize(10);
								query.setType("游戏");
								list[2] = novelService.getCollectionList(query);
								query.setPagesize(10);
								query.setType("游戏");
								list[3] = novelService.getMonthTicketList(query);
								msg.setData(list);
						} else {
								List<Novel> list = novelService.getPageByType(query);
								msg.setData(list);
						}
						msg.setMessage(String.valueOf(allpage));
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setFlag(false);
						msg.setMessage("数据库出错了！请与管理员联系！");
				}
				logger.info("getWangYouPageData:" + msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("rank")
		public Msg getRankListData(HttpServletRequest request) {
				Msg msg = new Msg(true);
				logger.info("getRankListData:" + msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("classification")
		public Msg getClassiFicationData(HttpServletRequest request) {
				Msg msg = new Msg(true);
				String book_type = request.getParameter("type");
				//System.out.println("type"+book_type);
				String book_state = request.getParameter("state");
				String book_words[] = request.getParameter("words").split("-");
				String sort = request.getParameter("sort");
				int pagenum = Integer.parseInt(request.getParameter("pagenum"));
				Integer gt = -1;
				Integer et = -1;
				if ("".equals(book_words[0]) || "NaN".equals(book_words[0])) {
						gt = 0;
				} else {
						gt = Integer.parseInt(book_words[0]);
				}
				if (book_words.length >= 2) {
						if (book_words[1] == "" || "NaN" == book_words[1]) {
								et = 999999990;
						} else {
								et = Integer.parseInt(book_words[1]);
						}
				}
				if (book_type == "全部") {
						book_type = "";
				}

				HashMap<String, Object> map = new HashMap<>();
				map.put("book_type", book_type);
				map.put("book_state", book_state);
				map.put("wordgt", gt);
				map.put("wordet", et);
				map.put("sort", sort);
				int allpage = (novelService.getClassiFicationPageSize(map) + 10 - 1) / 10;
				//System.out.println(allpage);
				int curpage = ((pagenum - 1) * 10);
				map.put("curpage", curpage);
				try {
						List<Novel> list = novelService.getClassiFicationList(map);
						int len = list.size();
						msg.setData(list);
						msg.setMessage(String.valueOf(allpage));
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setFlag(false);
						msg.setMessage("加载数据出错！请与管理员联系！");
				}
				logger.info("getClassiFicationData:" + msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("loadbookmsg")
		public Msg loadBookMsg(HttpServletRequest request) {
				Msg msg = new Msg(true);
				String bookname = request.getParameter("bookname");
				try {
						Novel novel = novelService.getNovelMsg(bookname);
						msg.setData(novel);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				logger.info("loadBookMsg:" + msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("loadbookcatlog")
		public Msg loadBookCatlog(HttpServletRequest request) {
				Msg msg = new Msg(true);
				String bookname = request.getParameter("bookname");
				try {
						List<Catalog> catalog = novelService.getBookCatalogs(bookname);
						logger.info("loadBookCatlog:" + catalog);
						msg.setData(catalog);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				}
				logger.info("loadBookCatlog:"+msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("downloadrank")
		public Msg getDownloadRank(HttpServletRequest request) {
				Msg msg = new Msg(true);
				String type = request.getParameter("type");
				try {
						PageQuery pageQuery = new PageQuery();
						pageQuery.setType(type);
						pageQuery.setPagesize(10);
						List<Novel> list = novelService.getDownloadsList(pageQuery);
						logger.info("getDownloadRank:"+list);
						msg.setData(list);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setFlag(false);
						msg.setMessage(e.getMessage());
				}
				logger.info("getDownloadRank:"+msg);
				return msg;
		}

		@ResponseBody
		@RequestMapping("readbycatlog")
		public Msg readCatlog(HttpServletRequest request) {
				Msg msg = new Msg(false);
				//1、获取参数
				String novelname = request.getParameter("novelname");
				String catlogname = request.getParameter("catlogname");

				try {
						//2、根据参数从数据库查询章节内容
						String data = novelService.getNovelCatlog(novelname, catlogname);
						msg.setData(data);
						msg.setFlag(true);
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setMessage("小说章节加载出错，请与管理员联系！");
				}
				logger.info("readCatlog:"+msg);
				return msg;
		}

		@RequestMapping("/download")
		public void downloadNovel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
				String encode = URLEncoder.encode(request.getParameter("novelname"), "UTF-8");
				String novelName = URLDecoder.decode(encode, "UTF-8");

				//1、加载小说
				//2、将文件写入页面
				//3、使用输入流读取服务器文件
				//4、输出流发送到客户端

				OutputStream os = null;
				InputStream fis = null;
				String fileName = "static\\txt" + File.separator + novelName;

				try {
						String mimeType = request.getServletContext().getMimeType(novelName);
						//response.setHeader("Content-Type", "application/x-msdownload");
						response.setHeader("Content-Type", mimeType);
						response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(novelName, "UTF-8"));

						//fis = new FileInputStream(fileName);
						fis = request.getServletContext().getResourceAsStream(fileName);
//          输出流
						os = response.getOutputStream();

						byte[] bytes = new byte[1024];
						int length = -1;
						while ((length = fis.read(bytes)) != -1) {

								os.write(bytes, 0, length);
								os.flush();
						}

				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
				} finally {
						//src\main\webapp\static\txt\一吨超人.txt
						if (os != null) {
								try {
										os.close();
								} catch (Exception e) {
										logger.error("错误消息：{}", e.getMessage(), e);
								}
						}
						if (fis != null) {
								try {
										fis.close();
								} catch (Exception e) {
										logger.error("错误消息：{}", e.getMessage(), e);
								}
						}

				}
		}

		@ResponseBody
		@RequestMapping("voteticket")
		public Msg voteTicket(HttpServletRequest request) {
				Msg msg = new Msg(true);
				String novelname = request.getParameter("bookname");
				HttpSession session = request.getSession();
				Reader reader = (Reader) session.getAttribute("user_reader");
				Author auth = (Author) session.getAttribute("user_auth");
				String username = null;
				if (reader != null) {
						username = reader.getNick_name();
				} else if (auth != null) {
						username = auth.getNick_name();
				} else {
						msg.setFlag(false);
						msg.setMessage("只有登录才能投票哦！清先登录！");
						return msg;
				}
				try {
						int code = novelService.voteTicket(novelname, username);
						//System.out.println(code);
						if (code == 1) {
								msg.setMessage("投票成功");
						} else {
								msg.setFlag(false);
								msg.setMessage("这本书你已经投过票了哦！一天只能投一次哦亲！");
						}
				} catch (Exception e) {
						logger.error("错误消息：{}", e.getMessage(), e);
						msg.setFlag(false);
						msg.setMessage("服务器出了点问题，投票失败！");
				}
				logger.info("voteTicket:"+msg);
				return msg;
		}
}
