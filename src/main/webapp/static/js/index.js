function searchbtn() {
		var model = $(':selected').text();
		var searchtext = $('#searchbbox').val();
		if (searchtext != "") {
				if (model == "小说") {
						window.location.href = "/wfRead/search/searchbookname?bookname=" + searchtext;
				}
				if (model == "作者") {
						window.location.href = "/wfRead/search/searchauthname?authname=" + searchtext;
				}
		} else {
				alert("不能搜索空的" + model + "哦!");
		}
}

function showPic(index) {
		//获取展示大图的标签
		var focusImg = $('.currentImg_show');

		// 获取所有缩略图标签
		var lis = $('.readyImg ul li');

		var msgs = $(".currentImgMsg ul li");
		var imgSrc = $('.readyImg ul li a img')[index - 1].src;
		var img_a = $('.readyImg ul li a')[index - 1].href;
		//更换轮播图片
		$('.currentImg_show').attr("src", imgSrc);
		for (var i = 0; i < lis.length; i++) {
				lis[i].className = "normal";
				msgs[i].className = "normal_msg";
		}
		lis[index - 1].className = "current";
		msgs[index - 1].className = "current_msg";
		$(".currentImg a")[0].href = img_a;
}

function getCurrentIndex() {
		var sign = 0;

		var lis = $('.readyImg ul li');
		for (var i = 0; i < lis.length; i++) {
				if (lis[i].className == "current") {
						sign = i + 1;
						break;
				}
		}
		return sign;
}

function setCurrentPic() {
		var sign = 0;
		sign = getCurrentIndex();
		sign++;
		if (sign == 5) {
				sign = 1;
		}
		showPic(sign);

}

window.onload = function () {
		showPic(1);
		$(".readyImg li").hover(function () {
					$(".current")[0].className = "normal";
					$(this)[0].className = "current";
					showPic(getCurrentIndex());
			},
			function () {

			});
		/*请求轮播数据*/
		getshownovel();

		/*获取编辑强推数据*/
		editorpush();

		/*获取周推荐数据*/
		weekrecommend();

		/*获取首页榜单数据*/
		gethomepagelist();
}

function getshownovel() {
		var now = new Date();
		var colum = now.getDate();
		var index = now.getMonth() + 1;
		var a_img = $(".readyImg a");
		var titles = $(".currentImgMsg a");
		var msgs = $(".currentImg_msg");

		$.post("novel/getshownovel",
			"colum=" + colum + "&index=" + index,
			function (data) {
					if (data.flag) {
							var booklist = data.data;
							$(".currentImg img").eq(0).attr("src", "static/picture/" +
								escape(booklist[0].book_name).replaceAll("%", "_") + ".jpg");
							for (var i = 0; i < booklist.length; i++) {
									var book = booklist[i];
									var src = "static/picture/" + escape(book.book_name).replaceAll("%", "_") + ".jpg";
									$(".readyImg img").eq(i).attr("src", src);
									var title = book.book_name;
									titles[i].text = title;
									titles[i].href = "/wfRead/novel/book/" + title;
									a_img[i].href = "/wfRead/novel/book/" + title;
									var msg = book.book_intro.replace(" ", "");
									if (msg.length > 70)
											msg = msg.substring(0, 70) + "..";
									msgs.eq(i).text(msg);
							}
					}
			}, "json");

}

function editorpush() {
		var now = new Date();
		var colum = now.getDate();
		$.post("novel/editorpush",
			"colum=" + colum + "&index=" + 4,
			function (data) {
					if (data.flag) {
							var booklist = data.data;
							var links = $(".wrap_12_mian a");
							for (var i = 0; i < 4; i++) {

									var j = i + 1;
									$("#recommend_" + j + " a").text(booklist[i].book_name);
									links[i].href = "/wfRead/novel/book/" + booklist[i].book_name;
									var texts = booklist[i].book_intro.replace(" ", "");
									if (texts.length > 63)
											texts = texts.substring(0, 63) + "...";
									$("#recommend_" + i + " p").text(texts);
							}
					}
			}, "json");
}

function weekrecommend() {
		$.post("novel/weekrecommend",
			"",
			function (data) {
					if (data.flag) {
							var booklist = data.data;
							for (var i = 0; i < 10; i++) {
									$("#wrap_13_list a").eq(i).text(booklist[i].book_name);
									$("#wrap_13_list a")[i].href = "/wfRead/novel/book/" + booklist[i].book_name;
							}
					}
			}, "json");
}

function gethomepagelist() {
		$.post("novel/gethomepagelist",
			"",
			function (data) {
					if (data.flag) {
							var booklist = data.data;
							for (var i = 0; i < 80; i++) {
									$(".list_bookname a").eq(i).text(booklist[i].book_name);
									$(".list_bookname a")[i].href = "/wfRead/novel/book/" + booklist[i].book_name;
							}
					}
			}, "json");
}

window.setInterval("setCurrentPic()", 2000);
