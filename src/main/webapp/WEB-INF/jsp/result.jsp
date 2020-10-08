<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
		<title>搜索结果</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/picture/favicon.ico"
		      type="image/x-icon"/>
</head>
<body>
<c:if test="${erro}">
		${searchresult}
</c:if>
<c:if test="${!erro}">
		<p>${booknameresult}</p>
		<p>${authnameresult}</p>
		<c:if test="${searchresult.size()!=0}">
				<c:forEach var="book" items="${searchresult}">
						<p><img src="${pageContext.request.contextPath}/static/picture/${book.book_name}.jpg" alt="404">
						</p>
						<p>
								<sapn>书名：</sapn>
								<a href="${pageContext.request.contextPath}/novel/book/${book.book_name}">${book.book_name}</a>
						</p>
						<p><span>作者：</span>${book.book_author}</p>
						<p><span>状态：</span>${book.book_state}</p>
						<p><span>简介：</span>${book.book_intro}</p>
				</c:forEach>
		</c:if>
</c:if>
</body>
</html>
