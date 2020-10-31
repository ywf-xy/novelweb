<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
		<title>未登录</title>
</head>
<body>
		<% pageContext.setAttribute("path", request.getContextPath()); %>
		<h3>对不起你还未登录</h3>
		<h4><a href="${path}/loginAndRegist/login">登录</a>  || <a href="${url}">返回</a></h4>
</body>
</html>
