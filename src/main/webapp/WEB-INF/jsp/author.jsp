<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
		<title>歪风阅读</title>
		<% pageContext.setAttribute("path", request.getContextPath()); %>
		<link rel="shortcut icon" href="${path}/static/picture/favicon.ico"/>
		<link href="${path}/static/css/bootstrap.min.css" rel="stylesheet">
		<link href="${path}/static/css/auth-base.css" rel="stylesheet">
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="${path}/static/js/jquery-3.4.1.min.js" type="text/javascript"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="${path}/static/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
		<div class="row clearfix">
				<div class="col-md-12 column">
						<div class="row clearfix">
								<div class="col-md-12 column">
										<nav class="navbar navbar-default navbar-static-top navbar-inverse"
											 role="navigation">
												<div class="navbar-header">
														<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
																<span class="sr-only">Toggle navigation</span>
																<span class="icon-bar"></span>
																<span class="icon-bar"></span>
																<span class="icon-bar"></span>
														</button>
														<a class="navbar-brand" href="/wfRead">歪风阅读</a>
												</div>

												<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
														<ul class="nav navbar-nav">
																<li class="active">
																		<a href="/wfRead/auth/authorUI">首页</a>
																</li>
																<li>
																		<a href="#">Link</a>
																</li>
														</ul>
														<form class="navbar-form navbar-left" style="margin-left: 200px" role="search">
																<div class="form-group">
																		<input type="text" class="form-control" placeholder="搜索你的作品"/>
																</div>
																<button type="search" class="btn btn-default">搜索
																</button>
														</form>
														<ul class="nav navbar-nav navbar-right" style="margin-right: 30px;">
																<c:if test="${user_auth.nick_name!=null}">
																		<li>
																				<c:if test="${user_auth.headimage==null}">
																						<img src="${pageContext.request.contextPath}/static/picture/default_user.png"
																								   alt="404" class="headimag">
																				</c:if>
																		<c:if test="${user_auth.headimage!=null}">
																				<img class="headimage" src="${pageContext.request.contextPath}/static/auth-photo/${user_auth.nick_name}/${user_auth.headimage}" alt="404">
																		</c:if>
																		</li>
																		<li><a href="">${user_auth.nick_name}</a></li>
																		<li>
																				<a href="${pageContext.request.contextPath}/loginAndRegist/loginout">注销</a>
																		</li>
																</c:if>
																<c:if test="${user_auth.nick_name==null}">
																		<li>
																				<a href="/wfRead/loginAndRegist/login">登录</a>
																		</li>
																		<li>
																				<a href="/wfRead/loginAndRegist/regist">成为作家</a>
																		</li>
																</c:if>
														</ul>
												</div>

										</nav>
								</div>
						</div>
						<div class="row clearfix" style="margin-top: 180px">
								<div class="col-md-4 column">
										<h3 class="text-center text-success">
												作品管理
										</h3>
										<button type="button" class="btn btn-success btn-lg btn-block" style="width: 280px;margin: 0 auto;">
												<c:if test="${user_auth.nick_name==null}">
														<a href="javascript:alert('您还未登录，请登录后使用！')" disabled="disabled" >由此进入</a>
												</c:if>
												<c:if test="${user_auth.nick_name!=null}">
														<a href="">管理入口</a>
												</c:if>
										</button>
								</div>
								<div class="col-md-4 column">
										<h3 class="text-center text-warning">
												作品上传
										</h3>
										<button type="button" class="btn btn-lg btn-block btn-warning" style="width: 280px;margin: 0 auto;">
												<c:if test="${user_auth.nick_name==null}">
														<a href="javascript:alert('您还未登录，请登录后使用！')" disabled="disabled" >由此进入</a>
												</c:if>
												<c:if test="${user_auth.nick_name!=null}">
														<a href="${pageContext.request.contextPath}/auth/fileuploadUI">由此进入</a>
												</c:if>
										</button>
								</div>
								<div class="col-md-4 column">
										<h3 class="text-center text-info">
												信息修改
										</h3>
										<button type="button" class="btn btn-lg btn-block btn-primary" style="width: 280px;margin: 0 auto;">
												<c:if test="${user_auth.nick_name==null}">
														<a href="javascript:alert('您还未登录，请登录后使用！')" disabled="disabled" >个人中心</a>
												</c:if>
												<c:if test="${user_auth.nick_name!=null}">
														<a href="${pageContext.request.contextPath}/auth/authorcenterUI">个人中心</a>
												</c:if>
										</button>
								</div>
						</div>
				</div>
		</div>
</div>
</body>
</html>
