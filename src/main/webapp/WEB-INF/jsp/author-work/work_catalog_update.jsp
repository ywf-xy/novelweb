<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh-CN">
<% pageContext.setAttribute("path",request.getContextPath());%>
<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>小说章节编辑</title>
		<link rel="stylesheet" href="${path}/static/css/auth/work_edit.css">
		<script src="${path}/static/js/jquery-3.4.1.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/static/js/auth/HandyEditor.min.js"></script>
		<script src="${path}/static/js/auth/work_catalog_update.js" type="text/javascript"></script>
</head>
<body>
<div id="title_content">
		<input type="hidden" id="novelname" value="${novelname}">
		<input type="hidden" id="content" value="${content}">
		<input type="hidden" id="catalog" value="${catalog}">
		<div id="catalog_title"><span >章节标题：</span></div>
		<input type="text" id="catalog_input" value="${catalog}">
		<button id="save_catalog">更新章节</button>
</div>
<textarea id="editor" name="editor" rows="5" style="display: none;" ></textarea>
<br>

</body>
</html>
