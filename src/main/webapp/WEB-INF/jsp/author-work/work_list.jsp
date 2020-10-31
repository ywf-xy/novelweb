<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
		<title>Title</title>
		<style>
				table>thead>tr>th, table>tbody>tr>th, table>tfoot>tr>th, table>thead>tr>td, table>tbody>tr>td,table>tfoot>tr>td {
						border-collapse: collapse;
						border-spacing: 0;
						border: 1px solid #ddd;
						text-align: center;
				}
				table{
						border-collapse: collapse;
						border-spacing: 0;
						width: 100%;
						border: 1px solid #ddd;
				}
				tbody>tr:nth-child(odd){
						background-color: #dff0d8;
				}
				body{
						background: #E7F4FE;
				}
				.full_box{
						width: 100%;

				}
		</style>
</head>
<body>
			<%--${author.works}--%>
			<div class="full_box">
					<p><h3>${author.nick_name} 的全部作品</h3></p>
			</div>
			<table>
					<thead>
					<tr>
							<th>小说名</th>
							<th>类型</th>
							<th>状态</th>
							<th>字数</th>
							<th>简介</th>
							<th>最后更新时间</th>
							<th>点击量</th>
							<th>月票数</th>
							<th>下载量</th>
							<th>操作</th>
					</tr>
					</thead>
					<tbody>
							<c:forEach items="${author.works}" var="auth">
									<tr>
											<td>${auth.book_name}</td>
											<td>
													<c:forEach items="${auth.book_type}" var="noveltype">
															${noveltype.type}
													</c:forEach>
											</td>
											<td>${auth.book_state}</td>
											<td>${auth.book_words}</td>
											<td>${fn:substring(auth.book_intro,0,20)}......</td>
											<td><fmt:formatDate value="${auth.update_time}" pattern="yyyy年MM月dd日"/></td>
											<td>${auth.total_hits}</td>
											<td>${auth.monthly_tickets}</td>
											<td>${auth.downloads}</td>
											<td>
													<button><a href="${auth.book_state=="完结"||auth.book_state=="完本"?'#':'写作页面'}">写作</a></button>
											</td>
									</tr>
							</c:forEach>
					</tbody>
			</table>
</body>
</html>
