function getCatLog(novelname, catlogname) {
		$.post("/wfRead/novel/loadbookcatlog",
			"bookname=" + novelname, function (data) {
					var list = data.data;
					$("#catlogList").empty();
					var positioncatlog = 0;
					for (var i = 0; i < list.length; i++) {
							var li = "<li id=\"li" + i + "\"><a href=\"" + "/wfRead/read/" + novelname + "/" + list[i].novale_catalog.toString() + "\">" + list[i].novale_catalog.toString() + "</a></li>";
							$("#catlogList").append(li);
							var str = list[i].novale_catalog.toString();
							if (catlogname == str) {
									positioncatlog = i;
									if (i != 0) {
											$(".lastCatlog")[0].href = "/wfRead/read/" + novelname + "/" + list[i - 1].novale_catalog.toString();
											$(".lastCatlog")[1].href = "/wfRead/read/" + novelname + "/" + list[i - 1].novale_catalog.toString();
									}

									$(".novelCatlog")[0].href = "/wfRead/novel/book/" + novelname;
									$(".novelCatlog")[1].href = "/wfRead/novel/book/" + novelname;

									if (i != list.length - 1) {
											$(".nextCatlog")[0].href = "/wfRead/read/" + novelname + "/" + list[i + 1].novale_catalog.toString();
											$(".nextCatlog")[1].href = "/wfRead/read/" + novelname + "/" + list[i + 1].novale_catalog.toString();
									}
									catlogname = "false";
							}
					}
					$("#catlogList").scrollTop(positioncatlog * 34);
					$("#li" + positioncatlog).css("background", "#CCE8CF");
			}, "json");
}

function getText(novelname, catlogname) {
		var context = $("#content");

		$(".bookname h1").text(catlogname);
		$(".menu_title").text(catlogname);
		$("#nav_novel").text(novelname);
		$.post("/wfRead/novel/readbycatlog",
			"novelname=" + novelname + "&catlogname=" + catlogname,
			function (data) {
					if (data.flag) {
							var text = data.data;

							context.empty();
							context.text(text);
							$("#content").css("height", $("#content").css("height"));
					}
			}, "json")
}

function light() {
		var value = $("#night").val();
		if (value == 0) {
				$("div").attr("style", "background-color:rgb(17, 17, 17); color: rgb(147, 147, 146)");
				font_ex();
				fontsize_ex()
				width_ex();
				color_ex();
				$("#night").val(1);
		} else if (value == 1) {
				var bgc = $("#bgcselect").val();
				$("div").removeAttr("style", "background-color:rgb(17, 17, 17); color: rgb(147, 147, 146)");
				bgc_ex();
				font_ex();
				fontsize_ex()
				width_ex();
				color_ex();
				$("#night").val(0);
		}
		saveConfig();
}

function bgc_ex() {
		var bgc = $("#bgcselect").val();
		if (bgc == "默认" || bgc == "背景") {
				bgc = "#E9FAFF";
		}
		$(".content_read").css("background-color", bgc);
		saveConfig();
}

function width_ex() {
		var width = $("#bcolor").val();
		if (width == "默认" || width == "宽度") {
				width = "95%";
		}
		$("#content").width(width);
		saveConfig();
}

function font_ex() {
		var font = $("#fontselect").val();
		if (font == "字体" || font == "默认") {
				font = "宋体";
		}

		$("#content").css("font-family", font);
		saveConfig();
}

function fontsize_ex() {
		var font = $("#fontsizeselect").val();
		if (font == "默认" || font == "大小") {
				font = "19pt";
		}
		$("#content").css("font-size", font);
		saveConfig();
}

function color_ex() {
		var color = $("#colorselect").val();
		if (color == "默认" || color == "颜色") {
				color = "#000";
		}

		$("#content").css("color", color);
		saveConfig();
}

function voteTicket() {

		//1、点击发送请求
		$.post("/wfRead//novel/voteticket",
			"bookname=" + $("#nav_novel").text(), function (data) {
					if (!data.flag) {
							alert(data.message);
					} else {
							alert("感谢你的票！");
					}

			}, "json");
}

$(function () {
		function loadData() {
				var url = window.location.href;
				var paths = url.split("/");
				var novelname = decodeURIComponent(paths[paths.length - 2]);
				var catlogname = decodeURIComponent(paths[paths.length - 1]);
				//加载小说目录
				getCatLog(novelname, catlogname);
				//加载章节
				getText(novelname, catlogname);
				$('.recommend').href = "blank";
		}

		$(".recommend").on("click", voteTicket);
		loadData();
		readConfig();
})

function saveConfig() {
		var url = decodeURIComponent(window.location.href);
		path = url.split("/")[url.split("/").length - 1]
		url = url.split(path)[0]
		console.log(url)
		//$.cookie('light',$("#night").val(), {path:url});
		document.cookie = 'night=' + $("#night").val() + '; path=' + url;
		//$("#bgcselect").val()
		document.cookie = 'bgcselect=' + $("#bgcselect").val() + '; path=' + url;
		//$("#bcolor").val()
		document.cookie = 'bcolor=' + $("#bcolor").val() + '; path=' + url;
		//$("#fontselect").val()
		document.cookie = 'fontselect=' + $("#fontselect").val() + '; path=' + url;
		//$("#fontsizeselect").val()
		document.cookie = 'fontsizeselect=' + $("#fontsizeselect").val() + '; path=' + url;
		//$("#colorselect").val()
		document.cookie = 'colorselect=' + $("#colorselect").val() + '; path=' + url;
}

function readConfig() {
		var cookies = document.cookie;
		console.log(cookies.split(";"));
		cookies = cookies.split(";");
		var flag = false;
		for (var i = 0; i < cookies.length; i++) {
				let kv = cookies[i].split("=");
				let key = kv[0].replace(" ", "");
				let value = kv[1];

				if (key == "night" && value != $("#" + key).val()) {
						value = $("#" + key).val()
						flag = true;
				}
				$("#" + key).val(value);
		}
		font_ex();
		fontsize_ex();
		color_ex();
		width_ex();
		bgc_ex();
		if (flag) {
				light();
		}
}