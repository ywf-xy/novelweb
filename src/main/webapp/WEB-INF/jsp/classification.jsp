
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
		<meta charset="utf-8"/>
		<title>歪风小说</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico"
		      type="image/x-icon"/>
		<link rel="stylesheet" type="text/css" href="static/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="static/css/classification.css"/>
		<script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js" type="text/javascript"
		        charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/static/js/base.js" type="text/javascript"
		        charset="UTF-8"></script>
		<script src="${pageContext.request.contextPath}/static/js/classification.js" type="text/javascript"
		        charset="utf-8"></script>
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
												<span><img src="${pageContext.request.contextPath}/static/user-photo/${user_reader.nick_name}/${user_reader.headimage}" alt="404" id="headimage"></span>
												<a href="${pageContext.request.contextPath}/reader/getusermsg">欢迎！${user_reader.nick_name}</a>
												<a href="${pageContext.request.contextPath}/loginAndRegist/loginout">注销</a>
										</div>
								</dd>
						</c:if>

						<dd>
								<div>
										<span class="booklib_log"></span>
									<c:if test="${user_reader.nick_name!=null}">
										<a href="/wfRead/reader/personshelf?nick_name=${user_reader.nick_name}" >书架</a>
									</c:if>
									<c:if test="${user_reader.nick_name==nulls&&user_auth.nick_name==null}">
										<a href="">书架</a>
									</c:if>
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
				<a href="${pageContext.request.contextPath}/lishi"><span>历史</span></a>
				<a href="${pageContext.request.contextPath}/dushi"><span>都市</span></a>
				<a href="${pageContext.request.contextPath}/wuxia"><span>武侠</span></a>
				<a href="${pageContext.request.contextPath}/wangyou"><span>网游</span></a>
				<a href="${pageContext.request.contextPath}/rank"><span>排行</span></a>
				<a href="" class='now'><span>分类</span></a>
				<a href="${pageContext.request.contextPath}/auth/authorUI"><span>作家专区</span></a>
		</div>
</div>
<!--主体-->
<div class="Main_1">
		<div class="fllist">
				<div class="fllist-item">
						<dl id="type">
								<dt>作品类别：</dt>
								<dd><span class="fb">全部</span></dd>
								<dd><span>玄幻</span></dd>
								<dd><span>奇幻</span></dd>
								<dd><span>武侠</span></dd>
								<dd><span>仙侠</span></dd>
								<dd><span>都市</span></dd>
								<dd><span>现实</span></dd>
								<dd><span>军事</span></dd>
								<dd><span>历史</span></dd>
								<dd><span>游戏</span></dd>
								<dd><span>体育</span></dd>
								<dd><span>科幻</span></dd>
								<dd><span>悬疑</span></dd>
								<dd><span>轻小说</span></dd>
								<dd><span>短篇</span></dd>
						</dl>
				</div>
				<div class="fllist-item">
						<dl id="state">
								<dt>作品状态：</dt>
								<dd><span class="fb">全部</span></dd>
								<dd><span>连载</span></dd>
								<dd><span>完本</span></dd>
						</dl>
				</div>
				<div class="fllist-item">
						<dl id="words">
								<dt>作品字数：</dt>
								<dd><span class="fb">全部</span></dd>
								<dd><span>30万以下</span></dd>
								<dd><span>30万-50万</span></dd>
								<dd><span>50万-100万</span></dd>
								<dd><span>100万-200万</span></dd>
								<dd><span>200万以上</span></dd>
						</dl>
				</div>
				<div class="fllist-item">
						<dl id="sorttype">
								<dt>排序方式：</dt>
								<dd><span class="fb">默认</span></dd>
								<dd><span>总字数</span></dd>
								<dd><span>周点击</span></dd>
								<dd><span>月点击</span></dd>
								<dd><span>总点击</span></dd>
								<dd><span>周收藏</span></dd>
								<dd><span>月收藏</span></dd>
								<dd><span>总收藏</span></dd>
						</dl>
				</div>
				<div class="fliter_result">
						<span>搜索条件：&nbsp;</span><span id="results">全部</span>
						<span id="cleanfilter" style="color: orangered;font-weight: bold;margin-left: 10px">x</span>
				</div>
		</div>

		<div class="searchlist">
				<div class="alltable">
						<table>
								<thead>
								<tr>
										<td class="td1">序号</td>
										<td class="td2">类别</td>
										<td class="td3">书名</td>
										<td class="td5">字数</td>
										<td class="td6">作者</td>
										<td class="td7">更新时间</td>
										<td class="td8">状态</td>
								</tr>
								</thead>
								<tbody>

								</tbody>
						</table>
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
<div class="Footer">
		<p>本站所收录的完整版电子书和小说及评论都属网友的个人上传行为，如侵犯您的权益，请将相关详情通知我们，我们将及时处理</p>
</div>
</body>
</html>

