$(function () {
		function loadData() {
				var urladr = window.location.href;
				var bookname = urladr.split("/")[urladr.split("/").length - 1];
				loadbookmsg(bookname);
				loadbookcatlog(bookname);
				editorpush();
		}

		//加载小说信息
		function loadbookmsg(bookname) {
				$.post("/wfRead/novel/loadbookmsg",
					"bookname=" + bookname, function (data) {

							if (data.flag) {
									var novel = data.data;

									var name = novel.book_name;
									$(".msg_right h2 dt a").text("《" + name + "》");
									$(".box_header a").text("【" + name + "】");

									var types = novel.book_type;
									$(".msg_right dd a").eq(0).text("");
									for (var i = 0; i < 2; i++) {
											var temp = $(".msg_right dd a").eq(0).text();
											$(".msg_right dd a").eq(0).text(temp + " " + types[i]);
									}

									getDownloadRank($(".msg_right dd a").eq(0).text());
									var author = novel.book_author;
									$(".msg_right dd a").eq(1).text(author);
									loadauthwork();
									var state = novel.book_state;
									$(".msg_right dd a").eq(2).text(state);

									var intro = novel.book_intro;
									$(".bookintro p").html("<strong>" + name + ":</strong>" + intro);

									var words = novel.book_words;
									if (words / 10000 > 0)
											$(".msg_right dd a").eq(3).text(words / 10000 + "万");
									else
											$(".msg_right dd a").eq(3).text(words);

									var updatetime = novel.update_time;
									var myDate = new Date(updatetime);
									var year = myDate.getFullYear(); //获取当前年
									var mon = myDate.getMonth() + 1; //获取当前月
									var date = myDate.getDate(); //获取当前日
									var updatetime = year + "-" + mon + "-" + date;
									$(".msg_right dd a").eq(4).text(updatetime);

									var imgurl = "/wfRead/static/picture/" + escape(name).replaceAll("%", "_") + ".jpg";
									$(".msg_left img")[0].src = imgurl;

									$("#download")[0].href = "/wfRead/novel/download?novelname=" + name + ".txt";
							}
					}, "json");
				//loadauthwork();
		}

		//加载小说目录
		function loadbookcatlog(bookname) {
				$.post("/wfRead/novel/loadbookcatlog",
					"bookname=" + bookname, function (data) {
							var catalogs = data.data;
							//清空ul中的数据
							$("#bookmenu_list ul").empty();
							$(".newlist10 ul").empty();
							//将目录加载
							var read = $("#read")[0].href = "/wfRead/read/" + bookname + "/" + catalogs[0].novale_catalog;
							for (var i = 0; i < catalogs.length; i++) {
									var time = new Date(catalogs[i].updatetime);
									time = time.getFullYear() + "-" + time.getMonth() + "-" + time.getDay();
									if (i < 10) {
											$("#bookmenu_list ul").append("<li>\n" +
												"                                <a href=\"/wfRead/read/" + bookname + "/" + catalogs[i].novale_catalog + "\" target=\"_blank\">" + catalogs[i].novale_catalog + "-" + time + "</a>\n" +
												"                            </li>");
									} else {
											$("#bookmenu_list ul").append("<li style='display: none'>\n" +
												"                                <a href=\"/wfRead/read/" + bookname + "/" + catalogs[i].novale_catalog + "\" target=\"_blank\">" + catalogs[i].novale_catalog + "-" + time + "</a>\n" +
												"                            </li>");
									}

							}
							//加载最新10章
							for (var i = catalogs.length - 1; i > catalogs.length - 11; i--) {
									var time = new Date(catalogs[i].updatetime);
									time = time.getFullYear() + "-" + time.getMonth() + "-" + time.getDay();
									$(".newlist10 ul").append("<li>\n" +
										"                                <a href=\"/wfRead/read/" + bookname + "/" + catalogs[i].novale_catalog + "\" target=\"_blank\">" + catalogs[i].novale_catalog + "-" + time + "</a>\n" +
										"                            </li>");
							}
					}, "json")
		}

		//显示更多--加载
		function showMore() {
				$("#bookmenu_list ul li:hidden").attr("style", "display:block");
		}

		//加载作者其他作品
		function loadauthwork() {
				var authname = $(".msg_right dd a").eq(1).text();

				$.post("/wfRead/auth/getauthorworks",
					"authname=" + authname, function (data) {
							if (data.flag) {
									var auth = data.data;
									var works = auth.works;

									$("#page").empty();
									for (var i = 0; i < works.length; i++) {
											var words = works[i].book_words;
											if (words / 10000 > 0)
													words = works[i].book_words / 10000 + "万";
											var updatetime = works[i].update_time;
											var myDate = new Date(updatetime);
											var year = myDate.getFullYear(); //获取当前年
											var mon = myDate.getMonth() + 1; //获取当前月
											var date = myDate.getDate(); //获取当前日
											var updatetime = year + "-" + mon + "-" + date;

											var str = "<ul class=\"zznm\">\n" +
												"                            <li class=\"zzn1\"><a href=\"" + "/wfRead/novel/book/" + works[i].book_name + "\" target=\"_blank\">《" + works[i].book_name + "》全集下载</a></li>\n" +
												"                            <li class=\"zzn2\">字数：" + words + "</li>\n" +
												"                            <li class=\"zzn3\">更新：" + updatetime + "</li>\n" +
												"                        </ul>";

											$("#page").append(str);
									}
							}
					}, "json")
		}

		//点击显示/隐藏作品集
		function showBtn() {
				if ($("#page").css("display") == "block")
						$("#page").attr("style", "display:none");
				else if ($("#page").css("display") == "none")
						$("#page").attr("style", "display:block");
		}

		//获取下载榜
		function getDownloadRank(type) {
				type = type.split(" ")[1];
				$.post("/wfRead/novel/downloadrank",
					"type=" + "仙侠" + "&size=" + 10,
					function (data) {
							if (data.flag) {
									var booklist = data.data;
									var list = $(".rank_list_ul a");

									for (var i = 0; i < 10; i++) {
											var bookanme = "《" + booklist[i].book_name + "》全集";
											var url = "/wfRead/novel/book/" + booklist[i].book_name;
											list.eq(i).text(bookanme);
											list[i].href = url;
									}
							}
					}, "json");
		}

		//获取编辑推荐
		function editorpush() {
				var now = new Date();
				var colum = now.getDate();
				$.post("/wfRead/novel/editorpush",
					"colum=" + colum + "&index=" + 10,
					function (data) {
							if (data.flag) {
									var booklist = data.data;
									var list = $(".rank_list_ul a");

									for (var i = 0; i < 10; i++) {
											var bookanme = "《" + booklist[i].book_name + "》全集";
											var url = "/wfRead/novel/book/" + booklist[i].book_name;
											list.eq(i + 10).text(bookanme);
											list[i + 10].href = url;
									}
							}
					}, "json");
		}

		loadData();
		$("#showlist").click(showBtn);
		$(".more").click(showMore);
})