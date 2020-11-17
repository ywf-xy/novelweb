<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<% pageContext.setAttribute("path", request.getContextPath()); %>
<html>
<head>
		<title>Title</title>
		<link href="${path}/static/css/auth/work_update.css" rel="stylesheet">
		<script type="text/javascript" src="${path}/static/js/jquery-3.4.1.min.js"></script>
		<script type="text/javascript" src="${path}/static/js/auth/work_update.js"></script>
</head>
<body>
<%--${author.works}--%>
<div class="full_box">
		<p><h3>${auth.nick_name} 的全部作品</h3></p>
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
		<c:forEach items="${auth.works}" var="auth" varStatus="vs">
				<tr>
						<td class="bookName">${auth.book_name}</td>
						<td class="bookType">
								<c:forEach items="${auth.book_type}" var="noveltype">
										${noveltype.type}
								</c:forEach>
						</td>
						<td class="bookStatus">${auth.book_state}</td>
						<td class="bookWords">${auth.book_words}</td>
						<td class="bookIntro">${auth.book_intro}</td>
						<td class="bookUpdate"><fmt:formatDate value="${auth.update_time}" pattern="yyyy年MM月dd日"/></td>
						<td class="bookHits">${auth.total_hits}</td>
						<td class="bookMonthTicket">${auth.monthly_tickets}</td>
						<td class="bookDownloads">${auth.downloads}</td>
						<td><a href="javascript:update(${vs.index})">修改</a></td>
				</tr>
		</c:forEach>
		</tbody>
</table>
<div id="mask">
		<div class="mask">
				<div class="tittle">
						编辑
						<span id="close_window">x</span>
				</div>
				<div class="cont">
						<input type="hidden" id="bookid">
						<label for="update_name">小说名： </label>
						<input type="text" name="book_name" id="update_name" value="" disabled/><br>
						<label for="update_type">小说类型： </label>
						<input type="text" name="noveltype" id="update_type" value="" disabled/><br>
						<span>小说状态：</span>连载<input type="radio" name="book_status" value="连载" class="update_status">完结<input
						type="radio" name="book_status" value="完结" class="update_status"><br>
						<label for="update_intro">小说简介：</label>
						<textarea id="update_intro" rows="7"></textarea><br>
						<button id="updata">更新</button>
						<button id="quit">取消</button>
				</div>
		</div>
</div>
</body>
</html>
