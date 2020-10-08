<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
		<meta charset="UTF-8">
		<title>登录</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico"
		      type="image/x-icon"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/regist.css"/>
		<script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js" type="text/javascript"
		        charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/static/js/regist.js" type="text/javascript"
		        charset="utf-8"></script>
		<script type="text/javascript">
				function readerregistModel() {
						$('#auth').css("display", "none");
						$('#reader').css("display", "block");
						$('.readerregist').css("background-color", "#00BFFF");
						$('.authorregist').css("background-color", "#FFFFFF");
				}

				function authorregistModel() {
						$('#reader').css("display", "none");
						$('#auth').css("display", "block");
						$('.authorregist').css("background-color", "#00BFFF");
						$('.readerregist').css("background-color", "#FFFFFF");
				}
		</script>
</head>
<body>
<div class="header">
		<div class="log"><a href="${pageContext.request.contextPath}/"><img
				src="${pageContext.request.contextPath}/static/picture/log2.png" alt="404"/></a></div>
		<div class="tittle"><h2>&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;用户注册</h2></div>
</div>
<div class="box-cneter">
		<div id="box_head">
				<span class="readerregist" onclick="readerregistModel()">读者注册</span>
				<span style="font-size: 28px;">|</span>
				<spna class="authorregist" onclick="authorregistModel()">作者注册</spna>
		</div>
		<div id="form">
				<div>
						<div id="auth" style="display: none;">
								<div style="width: 100%;height: 20px;"></div>
								<p><span style="color: red"></span></p>
								<form method="post" id="authregistForm">
										<span>用户名</span>&nbsp<input type="text" id="auth_nickname" name="nick_name"
										                            placeholder="请输入用户名"/>
										<div style="width: 100%;height: 40px;"></div>
										<span>密码</span>&nbsp&nbsp&nbsp&nbsp<input type="password" id="auth_password"
										                                          name="password" placeholder="请输入密码"/>
										<div style="width: 100%;height: 40px;"></div>
										<span>确认密码</span>&nbsp&nbsp<input type="password" id="auth_confirmpassword"
										                                  name="confirmpassword" placeholder="请输入密码"/>
								</form>
								<div style="width: 100%;height: 40px;"></div>
								<button id="authsubmitbtns" onclick="authRegist()">注册</button>
								&nbsp&nbsp
								<a href="${pageContext.request.contextPath}/">返回首页</a>
						</div>

						<div id="reader">
								<form id="readerregistForm" method="post">
										<div style="width: 100%;height: 20px;"></div>
										<p><span style="color: red"></span></p>
										<span>用户名</span>&nbsp<input type="text" id="reader_nickname" name="nick_name"
										                            placeholder="请输入用户名"/>
										<div style="width: 100%;height: 40px;"></div>
										<span>密码</span>&nbsp&nbsp&nbsp&nbsp<input type="password" id="reader_password"
										                                          name="password" placeholder="请输入密码"/>
										<div style="width: 100%;height: 40px;"></div>
										<span>确认密码</span>&nbsp&nbsp<input type="password" id="reader_confirmpassword"
										                                  name="confirmpassword" placeholder="请输入密码"/>
								</form>
								<div style="width: 100%;height: 40px;"></div>
								<button id="readersubmitbtns" onclick="readRegist()">注册</button>
								&nbsp&nbsp
								<a href="${pageContext.request.contextPath}/">返回首页</a>
						</div>
				</div>
		</div>
</div>
<div class="footer">

</div>
</body>
</html>

