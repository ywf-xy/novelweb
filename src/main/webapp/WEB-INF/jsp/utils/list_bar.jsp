<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("path",request.getContextPath()); %>
<html>
<head>
		<script type="text/javascript" src="${path}/static/js/jquery-3.4.1.min.js"></script>
		<script type="text/javascript" src="${path}/static/js/utils/list_bar.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/utils/list_bar.css">
</head>
<body>
<!--分页-->
<div class="page_nav">
		<div class="page_nav_content">
				<ul class="page_nav_list">
						<li><a id="firstpage">首页</a></li>
						<li><a id="prepage">上一页</a></li>
						<li><a class="active_page" id="1th_page">1</a></li>
						<li><a class="stay_page" id="2th_page">2</a></li>
						<li><a class="stay_page" id="3th_page">3</a></li>
						<li><a class="stay_page" id="4th_page">4</a></li>
						<li><a class="stay_page" id="5th_page">5</a></li>
						<li><a id="nextpage">下一页</a></li>
						<li><a id="lastpage">尾页</a></li>
				</ul>

				<div class="page_opt">
						<input type="text" class="current_page" value="1"/> /
						<span class="all_page">999</span>&nbsp;页
						<a id="gotopage">GO</a>
				</div>
		</div>
</div>
<script>
		$(function () {
				//以下是绑定分页工具栏的js
				$("#gotopage").on("click",turnPage);
				$("#1th_page").on("click",page1);
				$("#2th_page").on("click",page2);
				$("#3th_page").on("click",page3);
				$("#4th_page").on("click",page4);
				$("#5th_page").on("click",page5);
				$("#prepage").on("click",prepage);
				$("#nextpage").on("click",nextpage);
				$("#firstpage").on("click",firstpage);
				$("#lastpage").on("click",lastpage);
		})
		//跳转首页
		function firstpage() {
				if ($('.current_page').val() != 1) {
						$('.active_page')[0].className = "stay_page";
						$('.current_page').val(1);
						var alist = $(".stay_page")
						for (var i = 0; i < 5; i++) {
								alist.eq(i).text(i + 1);
						}
						alist[0].className = "active_page";
						//加载首页数据
						var currentPage =$(".current_page").eq(0).val();//当前第几页
						var pageSize = 10;//每页多少条
						//这里写加载数据函数xxx(currentPage,pageSize,其他条件)
				}
		}
		//跳转尾页
		function lastpage() {
				var lastpage = parseInt($(".all_page").text())
				if ($('.current_page').val() != lastpage) {
						$('.active_page')[0].className = "stay_page";
						$('.current_page').val(lastpage);
						var alist = $(".stay_page")

						if (lastpage>=5){
								for (var i = 0; i < 5; i++) {
										alist.eq(i).text(lastpage - 4 + i);
								}
								alist[4].className = "active_page";
						}else {
								alist[lastpage-1].className = "active_page";
						}

						//加载尾页数据
						var currentPage =$(".current_page").eq(0).val();//当前第几页
						var pageSize = 10;//每页多少条
						//这里写加载数据函数xxx(currentPage,pageSize,其他条件)
				}
		}
		//上一页
		function prepage() {
				if ($(".active_page").eq(0).text() == 1) {
						return;
				}
				if ($(".active_page").eq(0).text() > 3 && $(".active_page").eq(0).text() <= $(".all_page").text() - 2) {
						$('.active_page')[0].className = "stay_page";
						var lis = $(".stay_page");
						for (var i = 0; i < lis.length; i++) {
								var num = parseInt(lis.eq(i).text());
								lis.eq(i).text(num - 1);
						}
						lis[2].className = "active_page";
						var currentpage = $(".active_page").eq(0).text();
						$(".current_page").eq(0).val(currentpage);
				} else {
						var ac = $(".active_page");
						$('.active_page')[0].className = "stay_page";
						var lis = $(".stay_page");
						var index = lis.index(ac);
						lis[index - 1].className = "active_page";
						lis[index].className = "stay_page";
						$(".current_page").eq(0).val($(".active_page").eq(0).text());
				}
				//加载当前页数据
				var currentPage =$(".current_page").eq(0).val();//当前第几页
				var pageSize = 10;//每页多少条
				//这里写加载数据函数xxx(currentPage,pageSize,其他条件)
		}
		//下一页
		function nextpage() {
				if ($(".active_page").eq(0).text() == $(".all_page").text()) {
						return;
				}
				if ($(".active_page").eq(0).text() >= 3 && $(".active_page").eq(0).text() < $(".all_page").text() - 2) {
						var index = $(".active_page").eq(0).text();
						$('.active_page')[0].className = "stay_page";
						var lis = $(".stay_page");
						for (var i = 0; i < lis.length; i++) {
								var num = parseInt(lis.eq(i).text());
								lis.eq(i).text(num + 1);
						}
						lis[2].className = "active_page";
						var currentpage = $(".active_page").eq(0).text();
						$(".current_page").eq(0).val(currentpage);
				} else {
						var ac = $(".active_page");
						$('.active_page')[0].className = "stay_page";
						var lis = $(".stay_page");
						var index = lis.index(ac);
						lis[index + 1].className = "active_page";
						lis[index].className = "stay_page";
						$(".current_page").eq(0).val($(".active_page").eq(0).text());
				}
				//加载当前页数据
				var currentPage =$(".current_page").eq(0).val();//当前第几页
				var pageSize = 10;//每页多少条
				//这里写加载数据函数xxx(currentPage,pageSize,其他条件)
		}
		//选择页面
		function page1() {
				if ($(this)[0].className == "active_page" || $(this).text() > $(".all_page").text()) {
						return;
				}

				var index = $(this).text();
				$('.active_page')[0].className = "stay_page";
				var text = parseInt($(this).text());
				if (text > 3 && text < $(".all_page").text() - 2) {
						var lis = $(".stay_page");
						lis.eq(2).text(text);
						lis.eq(0).text(text - 2);
						lis.eq(1).text(text - 1);
						lis.eq(3).text(text + 1);
						lis.eq(4).text(text + 2);
						lis[2].className = "active_page";
				} else {
						$(this)[0].className = "active_page";
				}
				var currentpage = $(".active_page").eq(0).text();
				$(".current_page").eq(0).val(currentpage);
				//加载数据
				var currentPage =index;//当前第几页
				var pageSize = 10;//每页多少条
				//这里写加载数据函数xxx(currentPage,pageSize,其他条件)
		}
		function page2() {
				if ($(this)[0].className == "active_page" || $(this).text() > $(".all_page").text()) {
						return;
				}
				var index = $(this).text();
				$('.active_page')[0].className = "stay_page";
				var text = parseInt($(this).text());
				if (text > 3 && text < $(".all_page").text() - 2) {
						var lis = $(".stay_page");
						lis.eq(2).text(text);
						lis.eq(0).text(text - 2);
						lis.eq(1).text(text - 1);
						lis.eq(3).text(text + 1);
						lis.eq(4).text(text + 2);
						lis[2].className = "active_page";
				} else {
						$(this)[0].className = "active_page";
				}
				var currentpage = $(".active_page").eq(0).text();
				$(".current_page").eq(0).val(currentpage);
				//加载数据
				var currentPage =index;//当前第几页
				var pageSize = 10;//每页多少条
				//这里写加载数据函数xxx(currentPage,pageSize,其他条件)
		}
		function page3() {
				if ($(this)[0].className == "active_page" || $(this).text() > $(".all_page").text()) {
						return;
				}
				var index = $(this).text();
				$('.active_page')[0].className = "stay_page";
				var text = parseInt($(this).text());
				if (text > 3 && text < $(".all_page").text() - 2) {
						var lis = $(".stay_page");
						lis.eq(2).text(text);
						lis.eq(0).text(text - 2);
						lis.eq(1).text(text - 1);
						lis.eq(3).text(text + 1);
						lis.eq(4).text(text + 2);
						lis[2].className = "active_page";
				} else {
						$(this)[0].className = "active_page";
				}
				var currentpage = $(".active_page").eq(0).text();
				$(".current_page").eq(0).val(currentpage);
				//加载数据
				var currentPage =index;//当前第几页
				var pageSize = 10;//每页多少条
				//这里写加载数据函数xxx(currentPage,pageSize,其他条件)
		}
		function page4() {
				if ($(this)[0].className == "active_page" || $(this).text() > $(".all_page").text()) {
						return;
				}
				var index = $(this).text();
				$('.active_page')[0].className = "stay_page";
				var text = parseInt($(this).text());
				if (text > 3 && text < $(".all_page").text() - 2) {
						var lis = $(".stay_page");
						lis.eq(2).text(text);
						lis.eq(0).text(text - 2);
						lis.eq(1).text(text - 1);
						lis.eq(3).text(text + 1);
						lis.eq(4).text(text + 2);
						lis[2].className = "active_page";
				} else {
						$(this)[0].className = "active_page";
				}
				var currentpage = $(".active_page").eq(0).text();
				$(".current_page").eq(0).val(currentpage);
				//加载数据
				var currentPage =index;//当前第几页
				var pageSize = 10;//每页多少条
				//这里写加载数据函数xxx(currentPage,pageSize,其他条件)
		}
		function page5() {
				if ($(this)[0].className == "active_page" || $(this).text() > $(".all_page").text()) {
						return;
				}
				var index = $(this).text();
				$('.active_page')[0].className = "stay_page";
				var text = parseInt($(this).text());
				if (text > 3 && text < $(".all_page").text() - 2) {
						var lis = $(".stay_page");
						lis.eq(2).text(text);
						lis.eq(0).text(text - 2);
						lis.eq(1).text(text - 1);
						lis.eq(3).text(text + 1);
						lis.eq(4).text(text + 2);
						lis[2].className = "active_page";
				} else {
						$(this)[0].className = "active_page";
				}
				var currentpage = $(".active_page").eq(0).text();
				$(".current_page").eq(0).val(currentpage);
				//加载数据
				var currentPage =index;//当前第几页
				var pageSize = 10;//每页多少条
				//这里写加载数据函数xxx(currentPage,pageSize,其他条件)
		}
		//跳转指定页面
		function turnPage() {
				if (isNaN($(".current_page").eq(0).val())) {
						alert("请输入数字！");
						$(".current_page").eq(0).val($('.active_page').eq(0).text());
				}
				if (parseInt($(".current_page").eq(0).val()) == parseInt($(".active_page").eq(0).text())) {
						return;
				}
				if (parseInt($(".current_page").eq(0).val()) > parseInt($(".all_page").eq(0).text())) {
						alert("对不起，没有这么多页！");
						$(".current_page").eq(0).val($('.active_page').eq(0).text());
						return;
				}
				$('.active_page')[0].className = "stay_page";
				var text = parseInt($(".current_page").eq(0).val());
				if (text > 3 && text < $(".all_page").text() - 2) {
						var lis = $(".stay_page");
						lis.eq(2).text(text);
						lis.eq(0).text(text - 2);
						lis.eq(1).text(text - 1);
						lis.eq(3).text(text + 1);
						lis.eq(4).text(text + 2);
						lis[2].className = "active_page";
				} else {
						var li = $(".stay_page");
						var index = text % 5;
						for (var i = 0; i < 5; i++) {
								if (text < 5) {
										li.eq(i).text(text - (index - i) + 1);
								} else
										li.eq(i).text(text - (index - i));
						}
						if (text < 5) {
								li[index - 1].className = "active_page";
						} else
								li[index].className = "active_page";
				}
				//加载页面数据
				var currentPage =text;//当前第几页
				var pageSize = 10;//每页多少条
				//这里写加载数据函数xxx(currentPage,pageSize,其他条件)
		}
</script>
</body>
</html>

