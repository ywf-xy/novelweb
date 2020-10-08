package cn.xy.novelwebproject.controller;

import cn.xy.novelwebproject.bean.Author;
import cn.xy.novelwebproject.bean.Novel;
import cn.xy.novelwebproject.bean.Reader;
import cn.xy.novelwebproject.service.SearchService;
import cn.xy.novelwebproject.utils.JedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("search")
@Controller
public class SearchController {
		@Autowired
		private SearchService searchService;

		@RequestMapping("searchbookname")
		public String searchBookByName (String bookname, HttpServletRequest request, HttpServletResponse response) {
				List<Novel> novels = searchService.searchBookByName(bookname);
				Jedis jedis = JedisUtils.getConnect();
				String reader = request.getRemoteHost();
				String key = "search:"+reader;
				String temple = "<div style=\"width:100%; text-align:center;\">\n" +
						"  <div style=\"width:500px; margin-top:150px;\">\n" +
						"    <div class=\"block\" style=\"border: 1px solid #e9f1f8;\">\n" +
						"      <div class=\"blocktitle\" style=\"background: #e9f1f8;\">出现错误！</div>\n" +
						"      <div class=\"blockcontent\">\n" +
						"\t    <div style=\"padding:10px\"><br />错误原因：对不起，两次搜索的间隔时间不得少于 20 秒还剩"+jedis.ttl(key)+"秒！<br /><br />请 <a href=\"javascript:history.back(1)\">返 回</a> 并修正<br /><br /></div>\n" +
						"\t    <div style=\"width:100%; text-align: right; line-height:200%; padding-right:10px;\">[<a href=\"javascript:window.close()\">关闭本窗口</a>]</div>\n" +
						"\t  </div>\n" +
						"\t</div>\n" +
						"  </div>\n" +
						"</div>";
				String result = "result";
				try {
						if (jedis.exists(key)) {
								request.setAttribute("erro",true);
								request.setAttribute("searchresult", temple);
						}else {
								jedis.setex(key,20,reader);
								request.setAttribute("authnameresult", "");
								request.setAttribute("booknameresult", bookname);
								request.setAttribute("searchresult", novels);
						}
				}catch (Exception e){
						e.printStackTrace();
				}finally {
						JedisUtils.close(jedis);
				}
				return result;
		}

		@RequestMapping("searchauthname")
		public String searchAuthName (String authname, HttpServletRequest request, HttpServletResponse response) {
				List<Novel> novels = searchService.searchBookByAuth(authname);
				Jedis jedis = JedisUtils.getConnect();
				String auth = request.getRemoteHost();
				String key = "search:"+auth;
				String temple = "<div style=\"width:100%; text-align:center;\">\n" +
						"  <div style=\"width:500px; margin-top:150px;\">\n" +
						"    <div class=\"block\" style=\"border: 1px solid #e9f1f8;\">\n" +
						"      <div class=\"blocktitle\" style=\"background: #e9f1f8;\">出现错误！</div>\n" +
						"      <div class=\"blockcontent\">\n" +
						"\t    <div style=\"padding:10px\"><br />错误原因：对不起，两次搜索的间隔时间不得少于 20 秒还剩"+jedis.ttl(key)+"秒！<br /><br />请 <a href=\"javascript:history.back(1)\">返 回</a> 并修正<br /><br /></div>\n" +
						"\t    <div style=\"width:100%; text-align: right; line-height:200%; padding-right:10px;\">[<a href=\"javascript:window.close()\">关闭本窗口</a>]</div>\n" +
						"\t  </div>\n" +
						"\t</div>\n" +
						"  </div>\n" +
						"</div>";
				String result = "result";
				try {
						if (jedis.exists(key)) {
								request.setAttribute("erro",true);
								request.setAttribute("searchresult", temple);
						}else {
								jedis.setex(key,20,auth);
								request.setAttribute("booknameresult", "");
								request.setAttribute("authnameresult", authname);
								request.setAttribute("searchresult", novels);
						}
				}catch (Exception e){
						e.printStackTrace();
				}finally {
						JedisUtils.close(jedis);
				}
				return result;
		}
}
