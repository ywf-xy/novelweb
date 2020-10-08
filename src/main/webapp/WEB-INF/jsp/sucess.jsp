<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
		<title>success</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico"
		      type="image/x-icon"/>
</head>
<body>
<h3>用户：${user_auth.nick_name}</h3>

<c:forEach items="${auths}" var="auth">
		<p>${auth.nick_name}</p>
		<p>${auth.sex}</p>
</c:forEach>
<c:set var="salary" scope="session" value="${2000*2}"/>
<c:if test="${salary > 2000}">
<p>我的工资为: <c:out value="${salary}"/><p>
		</c:if>
		<c:if test="${user_auth.nick_name!=null}">
<dd>
		<div>
				<span><img src="" alt="404"></span>
				<a href="">fuck!${user_auth.nick_name}</a>
		</div>
</dd>
</c:if>
</body>
</html>
