$(function () {
		$('#firstpage').on("click",goToHeadPage);
		$(".fllist-item dd").click(function () {
				var text = $(this).find('span').text();
				var dl = $(this).parent();
				dl.find('span.fb').removeClass('fb');
				var spans = dl.find('span');
				for (var i = 0; i < spans.length; i++) {
						if (spans.eq(i).text() == text) {
								spans[i].className = "fb";
								break;
						}
				}
				//刷新过滤结果
				refreshdata();
		});

		function refreshdata() {
				var res = $("span.fb");
				var restext = $("#results").text();
				$("#results").empty();
				var flag = true;
				for (var i = 0; i < res.length; i++) {
						if (res.eq(i).text() != "全部" && res.eq(i).text() != "默认") {
								var text;
								if (flag) {
										text = $("#results").text() + res.eq(i).text();
										flag = false;
								} else {
										text = $("#results").text() + ' > ' + res.eq(i).text();
								}
								$("#results").text(text);
						}
				}
				if (!flag) {
						$('#results').attr("style", 'color:red');
				} else {
						$('#results').attr("style", 'color:black');
						$('#results').text("全部");
				}

				//查询数据
				findData();
				goToHeadPage();
		}

		//清空过滤条件
		$("#cleanfilter").click(function () {
				$("span.fb").removeClass('fb');
				var items = $(".fllist-item");
				for (var i = 0; i < items.length; i++) {
						items.eq(i).find('span')[0].className = 'fb';
				}
				refreshdata();
		});
		//跳转首页
		function goToHeadPage() {
				if ($('.current_page').val() != 1) {
						$('.active_page')[0].className = "stay_page";
						$('.current_page').val(1);
						var alist = $(".stay_page")
						for (var i = 0; i < 5; i++) {
								alist.eq(i).text(i + 1);
						}
						alist[0].className = "active_page";
						//加载首页数据
						loadPageData(1);
				}
		}

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
						loadPageData(lastpage);
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
				loadPageData($(".current_page").eq(0).val());
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
				loadPageData($(".current_page").eq(0).val());
		})
		//选择页面
		$("#1th_page").click(function () {
				if ($(this)[0].className == "active_page") {
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
				loadPageData(index);
		})
		$("#2th_page").click(function () {
				if ($(this)[0].className == "active_page") {
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
				loadPageData(index);
		})
		$("#3th_page").click(function () {
				if ($(this)[0].className == "active_page") {
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
				loadPageData(index);
		})
		$("#4th_page").click(function () {
				if ($(this)[0].className == "active_page") {
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
				loadPageData(index);
		})
		$("#5th_page").click(function () {
				if ($(this)[0].className == "active_page") {
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
				loadPageData(index);
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
				loadPageData(text);
		})

		//加载第x页数据
		function loadPageData(paenum) {
				var items = $(".fllist-item");
				var booktype = items.eq(0).find('span.fb').text();
				var bookstate = items.eq(1).find('span.fb').text();
				var bookwords = items.eq(2).find('span.fb').text();
				var sorttype = items.eq(3).find('span.fb').text();
				if (booktype == "全部" || booktype == "默认") {
						booktype = "";
				}
				if (bookstate == "全部" || bookstate == "默认") {
						bookstate = "";
				}
				if (bookwords == "全部" || bookwords == "默认") {
						bookwords = "";
				} else {
						bookwords = wordsreplace(bookwords);
				}
				if (sorttype == "全部" || sorttype == "默认") {
						sorttype = "";
				} else {
						sorttype = sortrulereplace(sorttype);
				}
				$.post("novel/classification",
					"type=" + booktype + "&state=" + bookstate + "&words=" + bookwords + "&sort=" + sorttype + "&pagenum=" + paenum,
					function (data) {
							var allpage = data.message;
							$(".all_page").text(allpage);
							var data = data.data;
							$("tbody").empty();

							for (var i = 0; i < data.length; i++) {
									var booknmae = data[i].book_name;
									var words = data[i].book_words;
									var author = data[i].book_author;
									var myDate = new Date(data[i].update_time);
									var year = myDate.getFullYear(); //获取当前年
									var mon = myDate.getMonth() + 1; //获取当前月
									var date = myDate.getDate(); //获取当前日
									var updatetime = year + "-" + mon + "-" + date;
									var state = data[i].book_state;
									booktype = data[i].book_type[0];
									/*for (var n in data[i].book_type){
											console.log(i +" ="+data[i].book_type[n]);
									}*/
									var temp = "<tr class=\"bg1\">\n" +
										"                    <td class=\"td1\">" + parseInt(i + 1) + "</td>\n" +
										"                    <td class=\"td2\">" + booktype + "</td>\n" +
										"                    <td class=\"td3\">\n" +
										"                        <span><a class=\"jt\" href=\"" + "/wfRead/novel/book/" + booknmae + "\" target=\"_blank\">" + booknmae + "</a></span>\n" +
										"                    </td>\n" +
										"                    <td class=\"td5\">" + words + "</td>\n" +
										"                    <td class=\"td6\">\n" +
										"                        <a href=\"\" target=\"_blank\" title=\"" + booknmae + "\">" + author + "</a>\n" +
										"                    </td>\n" +
										"                    <td class=\"td7\">" + updatetime + "</td>\n" +
										"                    <td class=\"td8\"><em class=\"fc2\">" + state + "</em></td>\n" +
										"                </tr>";
									$("tbody").append(temp);
							}

					}, "json")

		}

		//按条件搜索
		function findData() {
				loadPageData(1);
		}

		//字数替换
		function wordsreplace(bookwords) {
				bookwords = bookwords.replace("-", "");
				var searup = new RegExp("万以上");
				var seardown = new RegExp("万以下");
				if (searup.test(bookwords)) {
						bookwords = bookwords.replace("万以上", "");
						bookwords = parseInt(bookwords) * 10000;

						console.log(bookwords)
						return bookwords;
				}
				if (seardown.test(bookwords)) {
						bookwords = bookwords.replace("万以上", "");
						bookwords = parseInt(bookwords) * 10000 + "";
						bookwords = "0" + "-" + bookwords;
						console.log(bookwords)
						return bookwords;
				}
				var words = bookwords.split("万");
				bookwords = "";
				if (words[0] != '') {
						bookwords += parseInt(words[0]) * 10000 + "";
				}
				if (words[1] != '') {
						bookwords += "-" + parseInt(words[1]) * 10000;
				}
				return bookwords;
		}

		//排序规则替换
		function sortrulereplace(sorttype) {
				if (sorttype == "总字数") {
						sorttype = "book_words";
				} else if (sorttype == "周点击") {
						sorttype = "weekly_collections";
				} else if (sorttype == "月点击") {
						sorttype = "monthly_collections";
				} else if (sorttype == "总点击") {
						sorttype = "total_collections";
				} else if (sorttype == "周收藏") {
						sorttype = "weekly_collections";
				} else if (sorttype == "月收藏") {
						sorttype = "monthly_collections";
				} else if (sorttype == "总收藏") {
						sorttype = "total_collections";
				}
				return sorttype;
		}

		findData();
})