<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
		<title>搜索结果</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico"
		      type="image/x-icon"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/base.css"/>

		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/result.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/result.js"></script>
</head>
<body>
<div class="Header">
		<div>
				<a href="${pageContext.request.contextPath}/" class="log"><img src="${pageContext.request.contextPath}/static/picture/log.png"/></a>
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
								<dd >
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
				<a href="${pageContext.request.contextPath}/"class='now'><span>首页</span></a>
				<a href="${pageContext.request.contextPath}/xuanhuan"><span>玄幻</span></a>
				<a href="${pageContext.request.contextPath}/kehuan"><span>科幻</span></a>
				<a href="${pageContext.request.contextPath}/lishi"><span>历史</span></a>
				<a href="${pageContext.request.contextPath}/dushi"><span>都市</span></a>
				<a href="${pageContext.request.contextPath}/wuxia"><span>武侠</span></a>
				<a href="${pageContext.request.contextPath}/wangyou"><span>网游</span></a>
				<a href="${pageContext.request.contextPath}/rank"><span>排行</span></a>
				<a href="${pageContext.request.contextPath}/classification"><span>分类</span></a>
				<a href="${pageContext.request.contextPath}/auth/authorUI"><span>作家专区</span></a>
		</div>
</div>
<c:if test="${erro}">

		<div id="title">
						${searchresult}
		</div>
</c:if>
<c:if test="${!erro}">
		<div id="title">
				<span id="title_head">搜索 <strong>${booknameresult==null?"作者":"小说"}</strong> 关键字  > </span>
				<c:if test="${booknameresult!=null&&booknameresult!=''}"><span class="title_body">"${booknameresult}"</span></c:if>
				<c:if test="${authnameresult!=null&&authnameresult!=''}"><span class="title_body">"${authnameresult}"</span></c:if>
				<span id="count_result">共找到：<strong>${searchresult.size()}</strong>  本小说</span>
		</div>

		<div class="search-list">
				<c:if test="${searchresult.size()!=0}">
						<c:forEach var="book" items="${searchresult}">
								<div class="textlist">
										<input type="hidden" class="imgs" value="${pageContext.request.contextPath}/static/picture/${book.book_name}.jpg">
										<div class="textleft">
												<a class="img_p"></a>
										</div>
										<div class="textmiddle">
												<p>
														<span>书名：</span>
														<a href="${pageContext.request.contextPath}/novel/book/${book.book_name}">${book.book_name}</a>
												</p>
												<p><span>作者：</span>${book.book_author}</p>
												<p><span>状态：</span>${book.book_state}</p>
												<p><span>简介：</span>${fn:substring(book.book_intro,0,110)}</p>
										</div>
										<div class="textright">
												<a href="/wfRead/novel/book/${book.book_name}" class="read">在线阅读</a>
										</div>
								</div>

						</c:forEach>
				</c:if>
		</div>

</c:if>
<div class="Footer">
		<p>本站所收录的完整版电子书和小说及评论都属网友的个人上传行为，如侵犯您的权益，请将相关详情通知我们，我们将及时处理</p>
</div>
</body>
</html>
