<%@ taglib prefix="form" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
		<meta charset="UTF-8">
		<title>登录</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico"
		      type="image/x-icon"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css"/>
		<script src="${pageContext.request.contextPath}/static/js/login.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js" type="text/javascript"
		        charset="utf-8"></script>
		<script type="text/javascript">
				function readerlogin() {
						$('#auth').css("display", "none");
						$('#reader').css("display", "block");
						$('.readerlogin').css("background-color", "#00BFFF");
						$('.authorlogin').css("background-color", "#FFFFFF");
				}

				function authorlogin() {
						$('#reader').css("display", "none");
						$('#auth').css("display", "block");
						$('.authorlogin').css("background-color", "#00BFFF");
						$('.readerlogin').css("background-color", "#FFFFFF");
				}
		</script>
</head>
<body>
<div class="header">
		<div class="log"><a href="${pageContext.request.contextPath}/"><img
				src="${pageContext.request.contextPath}/static/picture/log2.png" alt="404"/></a></div>
		<div class="tittle"><h2>用户登录</h2></div>
</div>
<div class="box-cneter">
		<div id="box_head">
				<span class="readerlogin" onclick="readerlogin()">读者登录</span>
				<span style="font-size: 28px;">|</span>
				<spna class="authorlogin" onclick="authorlogin()">作者登录</spna>
		</div>
		<div id="form">
				<div>
						<div id="auth" style="display: none;">
								<p><span style="color: red"></span></p>
								<form method="post" id="authLoginForm">
										<span>用户名</span>&nbsp<input type="text" id="auth_nickname" name="nick_name"
										                            placeholder="请输入用户名"/>
										<br/><br/>
										<span>密码</span>&nbsp&nbsp&nbsp&nbsp<input type="password" id="auth_password"
										                                          name="password" placeholder="请输入密码"
										                                          autocomplete="on"/>
								</form>
								<button id="authsubmitbtns" onclick="authLogining()">登录</button>
								<button><a href="${pageContext.request.contextPath}/loginAndRegist/regist">注册</a>
								</button>
						</div>

						<div id="reader">
								<form id="readerLoginForm" method="post">
										<p><span style="color: red"></span></p>
										<span>用户名</span>&nbsp<input type="text" id="reader_nickname" name="nick_name"
										                            placeholder="请输入用户名"/>
										<br/><br/>
										<span>密码</span>&nbsp&nbsp&nbsp&nbsp<input type="password" id="reader_password"
										                                          name="password" placeholder="请输入密码"
										                                          autocomplete="on"/>
								</form>
								<button id="readersubmitbtns" onclick="readerLogining()">登录</button>
								<button><a href="${pageContext.request.contextPath}/loginAndRegist/regist">注册</a>
								</button>
						</div>

				</div>
		</div>
</div>
<div class="footer">

</div>
</body>
</html>
