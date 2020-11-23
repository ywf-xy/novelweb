$(function () {
		//跳转首页
		$('#firstpage').click(function () {
				if ($('.current_page').val() != 1) {
						$('.active_page')[0].className = "stay_page";
						$('.current_page').val(1);
						var alist = $(".stay_page")
						for (var i = 0; i < 5; i++) {
								alist.eq(i).text(i + 1);
						}
						alist[0].className = "active_page";
						//加载首页数据
						loadPageData(1, 0);
				}
		})

		//跳转尾页
		$("#lastpage").click(function () {
				var lastpage = parseInt($(".all_page").text())
				if ($('.current_page').val() != lastpage) {
						$('.active_page')[0].className = "stay_page";
						$('.current_page').val(lastpage);
						var alist = $(".stay_page")
						for (var i = 0; i < 5; i++) {
								alist.eq(i).text(lastpage - 4 + i);
						}
						alist[4].className = "active_page";
						//加载尾页数据
						loadPageData(lastpage, 0);
				}
		})
		//上一页
		$("#prepage").click(function () {
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
				loadPageData($(".current_page").eq(0).val(), 0);
		})
		//下一页
		$("#nextpage").click(function () {
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
				loadPageData($(".current_page").eq(0).val(), 0);
		})
		//选择页面
		$("#1th_page").click(function () {
				if ($(this)[0].className == "active_page"||$(this).text()>$(".all_page").text()) {
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
				loadPageData(index, 0);
		})
		$("#2th_page").click(function () {
				if ($(this)[0].className == "active_page"||$(this).text()>$(".all_page").text()) {
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
				loadPageData(index, 0);
		})
		$("#3th_page").click(function () {
				if ($(this)[0].className == "active_page"||$(this).text()>$(".all_page").text()) {
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
				loadPageData(index, 0);
		})
		$("#4th_page").click(function () {
				if ($(this)[0].className == "active_page"||$(this).text()>$(".all_page").text()) {
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
				loadPageData(index, 0);
		})
		$("#5th_page").click(function () {
				if ($(this)[0].className == "active_page"||$(this).text()>$(".all_page").text()) {
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
				loadPageData(index, 0);
		})
		//跳转指定页面
		$("#gotopage").click(function () {
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
				loadPageData(text, 0);
		})

		//加载第x页数据
		function loadPageData(paenum, flag) {
				if ($(this).text()>$(".all_page").text()){
						return ;
				}
				$.post("novel/lishi",
					"page=" + paenum + "&pagesize=20&flag=" + flag,
					function (data) {
							if (data.flag) {
									var novels;
									if (flag == 1) {
											novels = data.data[0];
									} else {
											novels = data.data;
									}
									var allpage = data.message;
									$(".all_page").text(allpage);
									$(".book_list").empty();
									for (var i = 0; i < novels.length; i++) {
											var myDate = new Date(novels[i].update_time);
											var year = myDate.getFullYear(); //获取当前年
											var mon = myDate.getMonth() + 1; //获取当前月
											var date = myDate.getDate(); //获取当前日
											var update_time = year + "-" + mon + "-" + date;
											var src = "/wfRead/static/picture/" + escape(novels[i].book_name).replaceAll("%", "_") + ".jpg";
											$(".book_list").append("<dl>\n" +
												"                    <dt><a href=\"" + "/wfRead/novel/book/" + novels[i].book_name + "\">" + novels[i].book_name + "</a></dt>\n" +
												"                    <dd><a href=\"" + "/wfRead/novel/book/" + novels[i].book_name + "\"><img src=" + src + " alt=\"404\"/></a></dd>\n" +
												"                    <dd>类别：<span class=\"book_type\">历史</span></dd>\n" +
												"                    <dd>作者：<span class=\"book_author\">" + novels[i].book_author + "</span></dd>\n" +
												"                    <dd>字数：<span class=\"book_words\">" + novels[i].book_words + "</span></dd>\n" +
												"                    <dd>更新日期：<span class=\"book_update\">" + update_time + "</span></dd>" +
												"                    <dd><a href=\"" + "/wfRead/novel/download?novelname=" + novels[i].book_name + ".txt" + "\">全本下载</a></dd>\n" +
												"                    <dd><a href=\"" + "/wfRead/novel/book/" + novels[i].book_name + "\">在线阅读</a></dd>\n" +
												"                </dl>");
									}
									if (flag == 1) {
											//侧边栏数据
											var download = data.data[1];
											var monthticket = data.data[2];
											var collection = data.data[3];
											var als = $(".rank_list_ul li a");
											for (var i = 0; i < 10; i++) {

													als.eq(i).text(download[i].book_name);
													als[i].href = "/wfRead/novel/book/" + download[i].book_name
													als.eq(i + 10).text(monthticket[i].book_name);
													als[i + 10].href = "/wfRead/novel/book/" + monthticket[i].book_name;
													als.eq(i + 20).text(collection[i].book_name);
													als[i + 20].href = "/wfRead/novel/book/" + collection[i].book_name;
											}
									}

							} else {
									alert(data.message);
							}
					}, "json");
				$('body,html').animate({scrollTop: 0},500);
		}

		loadPageData(1, 1);
})