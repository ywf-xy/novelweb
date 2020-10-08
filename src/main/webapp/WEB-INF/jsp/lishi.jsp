<%--
  Created by IntelliJ IDEA.
  User: 33077
  Date: 2020/7/25 0025
  Time: 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
		<meta charset="utf-8"/>
		<title>歪风小说</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico"
		      type="image/x-icon"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/lishi.css"/>
		<script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js" type="text/javascript"
		        charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/static/js/lishi.js" type="text/javascript"
		        charset="UTF-8"></script>
		<script src="${pageContext.request.contextPath}/static/js/base.js" type="text/javascript"
		        charset="UTF-8"></script>
</head>
<body>
<div class="Header">
		<div>
				<a href="${pageContext.request.contextPath}/" class="log"><img
						src="${pageContext.request.contextPath}/static/picture/log.png"/></a>
				<div class="so">
						<select id="selectmodel">
								<option label="novel">小说</option>
								<option label="auth">作者</option>
						</select>
						<input type="text" name="searchbox" id="searchbbox" required="required"/>
						<button class="sub" onclick="searchbtn()">搜索</button>
				</div>
				<dl class="nav">
						<c:if test="${user_auth.nick_name==null && user_reader.nick_name==null}">
								<dd>
										<div>
												<span class="login_log"></span>
												<a href="${pageContext.request.contextPath}/loginAndRegist/login">登录</a>
										</div>
								</dd>
								<dd>
										<div>
												<span class="login_log"></span>
												<a href="${pageContext.request.contextPath}/loginAndRegist/regist">注册</a>
										</div>
								</dd>
						</c:if>
						<c:if test="${user_auth.nick_name!=null}">
								<dd>
										<div>
												<span><img src="" alt="404"></span>
												<a href="">${user_auth.nick_name}</a>
												<a href="${pageContext.request.contextPath}/loginAndRegist/loginout">注销</a>
										</div>
								</dd>
						</c:if>
						<c:if test="${user_reader.nick_name!=null}">
								<dd>
										<div>
												<span><img src="" alt="404"></span>
												<a href="">${user_reader.nick_name}</a>
												<a href="${pageContext.request.contextPath}/loginAndRegist/loginout">注销</a>
										</div>
								</dd>
						</c:if>

						<dd>
								<div>
										<span class="booklib_log"></span>
										<a href="">书架</a>
								</div>
						</dd>
				</dl>
		</div>
</div>
<div class="NavBar">
		<div>
				<a href="${pageContext.request.contextPath}/"><span>首页</span></a>
				<a href="${pageContext.request.contextPath}/xuanhuan"><span>玄幻</span></a>
				<a href="${pageContext.request.contextPath}/kehuan"><span>科幻</span></a>
				<a href="" class='now'><span>历史</span></a>
				<a href="${pageContext.request.contextPath}/dushi"><span>都市</span></a>
				<a href="${pageContext.request.contextPath}/wuxia"><span>武侠</span></a>
				<a href="${pageContext.request.contextPath}/wangyou"><span>网游</span></a>
				<a href="${pageContext.request.contextPath}/rank"><span>排行</span></a>
				<a href="${pageContext.request.contextPath}/classification"><span>分类</span></a>
		</div>
</div>
<div style="text-align: center;margin: 0px auto;padding: 5px 10px;width: 100%;background: #f7f7f7;height: 25px;ine-height: 25px;float: left;">
		<p style="display: none;">asdlas</p>
</div>
<!--主体-->
<div class="Main">
		<div class="Mina_left">
				<div id="Mian_left_head"><h2>历史</h2></div>
				<div id="Mian_left_body">
						<!--小说列表-->
						<div class="book_list">

						</div>
						<div id="fy">

						</div>
						<!--分页-->
						<div class="page_nav">
								<div class="page_nav_content">
										<ul class="page_nav_list">
												<li><a id="firstpage">首页</a></li>
												<li><a id="prepage">上一页</a></li>
												<li><a class="active_page" id="1th_page">1</a></li>
												<li><a class="stay_page" id="2th_page">2</a></li>
												<li><a class="stay_page" id="3th_page">3</a></li>
												<li><a class="stay_page" id="4th_page">4</a></li>
												<li><a class="stay_page" id="5th_page">5</a></li>
												<li><a id="nextpage">下一页</a></li>
												<li><a id="lastpage">尾页</a></li>
										</ul>

										<div class="page_opt">
												<input type="text" class="current_page" value="1"/> /<span
												class="all_page">999</span>&nbsp;页
												<a id="gotopage">GO</a>
										</div>
								</div>
						</div>
				</div>
		</div>
		<div class="Mian_right">
				<!--下载榜-->
				<div class="rank_tittle">下载榜</div>
				<div class="rank_list">
						<ul class="rank_list_ul">
								<li><span class="seq1"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq2"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq3"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq4"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq5"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq6"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq7"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq8"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq9"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq10"></span><a href="">《美食大帝》全集</a></li>
						</ul>
				</div>
				<!--月票榜-->
				<div class="rank_tittle">月票榜</div>
				<div class="rank_list">
						<ul class="rank_list_ul">
								<li><span class="seq1"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq2"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq3"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq4"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq5"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq6"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq7"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq8"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq9"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq10"></span><a href="">《美食大帝》全集</a></li>
						</ul>
				</div>
				<!--收藏榜-->
				<div class="rank_tittle">收藏榜</div>
				<div class="rank_list">
						<ul class="rank_list_ul">
								<li><span class="seq1"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq2"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq3"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq4"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq5"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq6"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq7"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq8"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq9"></span><a href="">《美食大帝》全集</a></li>
								<li><span class="seq10"></span><a href="">《美食大帝》全集</a></li>
						</ul>
				</div>
		</div>
</div>
<div class="Footer">
		<p>本站所收录的完整版电子书和小说及评论都属网友的个人上传行为，如侵犯您的权益，请将相关详情通知我们，我们将及时处理</p>
</div>
</body>
</html>

