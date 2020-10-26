
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
		<link rel="stylesheet" type="text/css" href="static/css/rank.css"/>
		<script src="${pageContext.request.contextPath}/static/js/rank.js" type="text/javascript"
		        charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js" type="text/javascript"
		        charset="utf-8"></script>
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
								<dd >
										<div>
												<span><img class="headimage" src="${pageContext.request.contextPath}/static/auth-photo/${user_auth.nick_name}/${user_auth.headimage}" alt="404"></span>
												<a href="" >欢迎！${user_auth.nick_name}</a>
												<a href="${pageContext.request.contextPath}/loginAndRegist/loginout">注销</a>
										</div>
								</dd>
						</c:if>
						<c:if test="${user_reader.nick_name!=null}">
								<dd >
										<div>
												<span><img src="${pageContext.request.contextPath}/static/user-photo/${user_reader.nick_name}/${user_reader.headimage}" alt="404" class="headimage"></span>
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
										<c:if test="${user_reader.nick_name==null}">
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
				<a href="" class='now'><span>排行</span></a>
				<a href="${pageContext.request.contextPath}/classification"><span>分类</span></a>
				<a href="${pageContext.request.contextPath}/auth/authorUI"><span>作家专区</span></a>
		</div>
</div>
<!--主体-->
<div class="Main_1">
		<div class="topten">
				<div class="rank_box">
						<div class="rank_tittle">
								总点击榜
								<span class="more"><a href="">更多</a></span>
						</div>
						<div class="topten_box">
								<ul>
										<li><b>1</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>2</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>3</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>4</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>5</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>6</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>7</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>8</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>9</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>10</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
								</ul>
						</div>
				</div>
		</div>
		<div class="topten">
				<div class="rank_box">
						<div class="rank_tittle">
								月点击榜
								<span class="more"><a href="">更多</a></span>
						</div>
						<div class="topten_box">
								<ul>
										<li><b>1</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>2</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>3</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>4</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>5</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>6</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>7</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>8</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>9</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>10</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
								</ul>
						</div>
				</div>
		</div>

		<div class="topten">
				<div class="rank_box">
						<div class="rank_tittle">
								周点击榜
								<span class="more"><a href="">更多</a></span>
						</div>
						<div class="topten_box">
								<ul>
										<li><b>1</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>2</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>3</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>4</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>5</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>6</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>7</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>8</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>9</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>10</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
								</ul>
						</div>
				</div>
		</div>

		<div class="topten">
				<div class="rank_box">
						<div class="rank_tittle">
								日点击榜
								<span class="more"><a href="">更多</a></span>
						</div>
						<div class="topten_box">
								<ul>
										<li><b>1</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>2</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>3</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>4</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>5</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>6</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>7</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>8</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>9</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>10</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
								</ul>
						</div>
				</div>
		</div>
		<div class="topten">
				<div class="rank_box">
						<div class="rank_tittle">
								总推荐榜
								<span class="more"><a href="">更多</a></span>
						</div>
						<div class="topten_box">
								<ul>
										<li><b>1</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>2</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>3</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>4</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>5</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>6</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>7</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>8</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>9</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>10</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
								</ul>
						</div>
				</div>
		</div>

		<div class="topten">
				<div class="rank_box">
						<div class="rank_tittle">
								月推荐榜
								<span class="more"><a href="">更多</a></span>
						</div>
						<div class="topten_box">
								<ul>
										<li><b>1</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>2</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>3</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>4</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>5</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>6</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>7</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>8</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>9</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>10</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
								</ul>
						</div>
				</div>
		</div>

		<div class="topten">
				<div class="rank_box">
						<div class="rank_tittle">
								周推荐榜
								<span class="more"><a href="">更多</a></span>
						</div>
						<div class="topten_box">
								<ul>
										<li><b>1</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>2</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>3</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>4</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>5</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>6</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>7</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>8</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>9</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>10</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
								</ul>
						</div>
				</div>
		</div>
		<div class="topten">
				<div class="rank_box">
						<div class="rank_tittle">
								日推荐榜
								<span class="more"><a href="">更多</a></span>
						</div>
						<div class="topten_box">
								<ul>
										<li><b>1</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>2</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>3</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>4</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>5</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>6</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>7</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>8</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>9</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>10</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
								</ul>
						</div>
				</div>
		</div>

		<div class="topten">
				<div class="rank_box">
						<div class="rank_tittle">
								总收藏榜
								<span class="more"><a href="">更多</a></span>
						</div>
						<div class="topten_box">
								<ul>
										<li><b>1</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>2</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>3</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>4</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>5</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>6</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>7</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>8</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>9</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>10</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
								</ul>
						</div>
				</div>
		</div>

		<div class="topten">
				<div class="rank_box">
						<div class="rank_tittle">
								总字数榜
								<span class="more"><a href="">更多</a></span>
						</div>
						<div class="topten_box">
								<ul>
										<li><b>1</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>2</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>3</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>4</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>5</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>6</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>7</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>8</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>9</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>10</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
								</ul>
						</div>
				</div>
		</div>
		<div class="topten">
				<div class="rank_box">
						<div class="rank_tittle">
								最近更新
								<span class="more"><a href="">更多</a></span>
						</div>
						<div class="topten_box">
								<ul>
										<li><b>1</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>2</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>3</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>4</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>5</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>6</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>7</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>8</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>9</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>10</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
								</ul>
						</div>
				</div>
		</div>

		<div class="topten">
				<div class="rank_box">
						<div class="rank_tittle">
								总下载榜
								<span class="more"><a href="">更多</a></span>
						</div>
						<div class="topten_box">
								<ul>
										<li><b>1</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>2</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>3</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>4</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>5</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>6</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>7</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>8</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>9</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
										<li><b>10</b>.&nbsp;<a href="">斗破苍穹</a><span>(天蚕土豆)</span></li>
								</ul>
						</div>
				</div>
		</div>
</div>

<div class="Footer">
		<p>本站所收录的完整版电子书和小说及评论都属网友的个人上传行为，如侵犯您的权益，请将相关详情通知我们，我们将及时处理</p>
</div>
</body>
</html>
