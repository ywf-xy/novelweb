<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
		<title>Title</title>
		<% pageContext.setAttribute("path", request.getContextPath()); %>
		<link rel="shortcut icon" href="${path}/static/picture/favicon.ico"/>
		<link href="${path}/static/css/bootstrap.min.css" rel="stylesheet">
		<link href="${path}/static/css/auth/work_add.css" rel="stylesheet">
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="${path}/static/js/jquery-3.4.1.min.js" type="text/javascript"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="${path}/static/js/bootstrap.min.js"></script>
		<script src="${path}/static/js/auth/work_add.js"></script>
</head>
<body>
<div class="container-fluid">
		<div class="row-fluid">
				<div class="span12">
						<div class="tabbable" id="tabs-535382">
								<ul class="nav nav-tabs">
										<li >
												<a href="#newnovel" data-toggle="tab">新建小说</a>
										</li>
										<li class="active">
												<a href="#write" data-toggle="tab">写作</a>
										</li>
								</ul>
								<div class="tab-content">
										<div class="tab-pane" id="newnovel">
												<form class="form-horizontal">
														<div class="control-group">
																<label class="control-label" for="novelName">小说名</label>
																<div class="controls">
																		<input id="novelName" name="book_name"
																			   type="text"/>
																</div>
														</div>
														<div class="control-group">
																<label class="control-label"
																	   for="novelAuthor">作者</label>
																<div class="controls">
																		<input id="novelAuthor" name="book_author"
																			   type="text" disabled
																			   value="${user_auth.nick_name}"/>
																</div>
														</div>
														<div class="control-group">
																<label class="control-label"
																	   for="novelStatus">状态</label>
																<div class="controls">
																		<input id="novelStatus" name="book_state"
																			   type="text" disabled value="连载"/>
																</div>
														</div>
														<div class="control-group">
																<label class="control-label" for="novelType">类型</label>
																<div class="controls">

																		<div id="novelType"
																			 style="margin-left: 20px;font-size: 19px">
																				<c:forEach items="${types}" var="type">
																						${type.typename}: <input
																						type="checkbox" name="types"
																						value="${type.typename}"
																						style="margin-right: 15px">
																				</c:forEach>
																		</div>
																</div>
														</div>
														<div class="control-group">
																<label class="control-label" for="novelIntro">简介</label>
																<div class="controls">
																		<input id="novelIntro" name="book_intro"
																			   type="text"/>
																</div>
														</div>
														<div class="control-group">
																<label class="control-label" for="addBtn"></label>
																<div class="controls">
																		<button type="button"
																				class="btn btn-success btn-lg"
																				id="addBtn">新建
																		</button>
																</div>
														</div>
												</form>
										</div>
										<div class="tab-pane active" id="write">

												<div id="work_list">
														<table>
																<thead>
																<tr>
																		<th>书名</th>
																		<th>更新时间</th>
																		<th>最新章节</th>
																		<th>操作</th>
																</tr>
																</thead>
																<tbody>
																<c:forEach items="${work}" var="book" varStatus="VS">

																		<tr>
																				<td>${book.book_name}</td>
																				<td><fmt:formatDate
																						value="${book.update_time}"
																						pattern="yyyy年MM月dd日"/></td>
																				<c:forEach varStatus="vs"
																						   items="${book.book_catalog}"
																						   var="catalog"
																						   begin="${book.book_catalog.size()-1}">
																						<c:if test="${catalog.novale_catalog!=null}">
																								<td>${catalog.novale_catalog}</td>
																						</c:if>
																						<c:if test="${catalog.novale_catalog==null}">
																								<td>暂无章节</td>
																						</c:if>
																				</c:forEach>
																				<td>
																						<a id="add_catalog"
																					   href="${path}/auth/toedit?catalogname=${book.book_catalog[book.book_catalog.size()-1]==null?"0":book.book_catalog[book.book_catalog.size()-1].novale_catalog}&novelname=${book.book_name}"
																					   target="_blank">开新章</a>
																						<a href='javascript:showCatalog("${VS.index}","${book.book_name}")'>查看所有章节</a>
																				</td>
																		</tr>

																</c:forEach>
																</tbody>
														</table>
												</div>
												<div style="display: none" id="catalog_list">
														<div class="catalog_list_top"><span id="back_list" style="color:red;">返回列表</span><h4 style="color: #2aabd2"></h4></div>
														<div class="catalog_list_content">
																<table>
																		<tbody>

																		</tbody>
																</table>
														</div>
												</div>

										</div>
								</div>
						</div>

				</div>
		</div>
</div>
</body>
</html>
