<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
		<title>歪风阅读</title>
		<% pageContext.setAttribute("path", request.getContextPath()); %>
		<link rel="shortcut icon" href="${path}/static/picture/favicon.ico"/>
		<link href="${path}/static/css/bootstrap.min.css" rel="stylesheet">
		<link href="${path}/static/css/auth/auth-base.css" rel="stylesheet">
		<link href="${path}/static/css/auth/workupload.css" rel="stylesheet">
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="${path}/static/js/jquery-3.4.1.min.js" type="text/javascript"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="${path}/static/js/bootstrap.min.js"></script>
		<script src="${path}/static/js/auth/workupload.js"></script>
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

								<div class="row clearfix" style="margin-top: 100px">
										<div class="col-md-6 column">
												<h3 class="text-center text-info">
														<span class="glyphicon glyphicon-picture"></span> <strong>图片·上传</strong>
												</h3>
												<button type="button" class="btn btn-lg btn-success" id="up-img">上传小说封面</button>
										</div>
										<div class="col-md-6 column">
												<h3 class="text-info text-center">
														<span class="glyphicon glyphicon-file"></span> <strong>小说·上传</strong>
												</h3>
												<button type="button" class="btn btn-success btn-lg" id="up-novel">上传<strong>完本</strong>小说</button>
										</div>
								</div>
								<div class="row clearfix" id="imgDiv">
										<div class="col-md-4 column"></div>

										<div class="col-md-4 column">
												<form role="form">
														<div class="form-group">
																<label for="imgupload">图片·小说名</label>
																<input type="text" name="filename" class="filename" />
														</div>
														<div class="form-group">
																<label for="imgupload">上传图片</label>
																<input type="file" name="imgfile" class="imgfileup" />
														</div>
														<button type="button" class="btn btn-default" id="imgupload" ><span class="glyphicon glyphicon-arrow-up"></span> <strong>上传</strong></button>
												</form>
										</div>

										<div class="col-md-4 column"></div>
								</div>
								<div class="row clearfix" id="novelDiv">
										<div class="col-md-4 column"></div>

										<div class="col-md-4 column">
												<form role="form">
														<div class="form-group">
																<label for="novelupload">上传小说</label>
																<input type="file" name="novelfile" class="novelfileup" />
														</div>
														<button type="button" class="btn btn-default" id="novelupload"><span class="glyphicon glyphicon-arrow-up"></span> <strong>上传</strong></button>
												</form>
										</div>

										<div class="col-md-4 column"></div>
								</div>
				</div>
		</div>
</div>
</body>
</html>
