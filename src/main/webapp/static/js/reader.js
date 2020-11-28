$(function () {
		$(".group6 .tabs a").on("click", changetabs);
		$(".siderBar li").on("click", manuechange);
		$("#imgupload").on("click", upLoadImg);
		$(".tab1_sub").on("click", updataReader);

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

function updataReader() {
		var nick_name = $("#nick_name").val();
		var birthday = $("#birthday").val();
		var sex = $('input[name="sex"]:checked').val();
		var intro = $("#introtext").val();
		var address = $("#addresstext").val();

		$(".erro").empty();
		$.post("/wfRead/reader/updatareader",
				"nick_name=" + nick_name + "&sex=" + sex + "&birthday=" + birthday + "&intro=" + intro + "&address=" + address,
				function (data) {
						var msg = data.fieldErrers;
						if (data.flag) {
								alert("更新成功！");
						} else {
								//console.log(msg)
								if (msg.nick_name != "") {
										//console.log(msg.nick_name);
										$("#nick_nameerror").text(msg.nick_name);
								} else {
										$("#nick_nameerror").text("");
								}
								if (msg.birthday != "") {
										//console.log(msg.birthday);
										$("#birthdayerror").text(msg.birthday);
								} else {
										$("#birthdayerror").text("");
								}
								if (msg.intro != "") {
										//console.log(msg.intro);
										$("#introerror").text(msg.intro);
								} else {
										$("#introerror").text("");
								}
								if (msg.address != "") {
										//console.log(msg.address);
										$("#addresserror").text(msg.address);
								} else {
										$("#addresserror").text("");
								}
						}
				},
				"json");
}

function changetabs() {
		var nowtab = $(" .group6 .checked").text()

		if ("基本设置" == nowtab) {
				$("#tabTarget2").css("display", "block");
				$("#tabTarget1").css("display", "none");
				$(" .group6 .checked").removeClass("checked");
				$("div.group6 > div > div.tabs > a:nth-child(2)").addClass("checked");
		} else if ("头像设置" == nowtab) {
				$("#tabTarget1").css("display", "block");
				$("#tabTarget2").css("display", "none");
				$(" .group6 .checked").removeClass("checked");
				$("div.group6 > div > div.tabs > a:nth-child(1)").addClass("checked");
		}
}

function manuechange() {

		var cl = $(this).attr("class");
		var groupid;
		if (cl == "") {
				var act = $(".siderBar .active");
				groupid = getGroupId(act)
				act.removeClass("active");
				$(".group" + groupid).css("display", "none");

				$(this).addClass("active");
				$(".group" + getGroupId($(this))).css("display", "block");
		}

}

function getGroupId(obj) {
		if (obj.text() == "首页") {
				return 1;
		} else if (obj.text() == "阅读记录") {
				//加载阅读记录
				//console.log($("#user_name").text());
				getPage( 1, 10);
				return 2;
		} else if (obj.text() == "我的书评") {
				return 4;
		} else if (obj.text() == "会员中心") {
				return 5;
		} else if (obj.text() == "个人资料") {
				return 6;
		}
}

//上传图片
function upLoadImg() {
		var fileObj = $(".fileup")[0].files[0]; // js 获取文件对象
		//console.log(fileObj);
		if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
				alert("请选择上传的图片！");
				return;
		} else if (fileObj.size > 1024 * 10240) {
				alert("上传文件大小不超过10MB!");
		}
		var formFile = new FormData();
		formFile.append("file", fileObj);
		var data = formFile;

		$.ajax({
				url: '/wfRead/reader/imgupload',
				type: 'post',
				dataType: 'json',
				data: data,
				cache: false,   //上传文件无需缓存
				processData: false,   // 用于对参数进行序列化处理，这里必须设为false
				contentType: false, // 必须
				success: function (data) {
						var urls = data.data;
						alert(data.message);
						if (data.flag) {
								$("#tabTarget2 img").attr("src", "/" + urls[0]);
								$(".headimage").attr("src", "/" + urls[0])
						}
				}
		});
}

//加载一页小说记录
function getPage(currentPage, pageSize) {
		$(".record_list tbody").empty();
		var username = $("#user_name").text();
		$.post("/wfRead/reader/userrecord",
				"username=" + username + "&currentPage=" + currentPage + "&pageSize=" + pageSize,
				function (data) {
						console.log(data);
						var list = data.list;
						$(".current_page").val(currentPage);
						$(".all_page").text(data.pages);
						for (var i = 0; i < data.size; i++) {
								//list[i] = list[i].replace("\\","");
								list[i] = JSON.parse(list[i]);
								//console.log("url="+list[i].url)
								var tr = '<tr>\n' +
										'                                <td>' + (i + 1) + '</td>\n' +
										'                                <td><a href="/wfRead/novel/book/' + list[i].novelname + '" target="_blank">' + list[i].novelname + '</a></td>\n' +
										'                                <td><a href="' + list[i].url + '" target="_blank">' + list[i].catalogname + '</a></td>\n' +
										'                                <td>' + list[i].readtime + '</td>\n' +
										'                            </tr>';
								$(".record_list tbody").append(tr);
						}
				}, "json");
}

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

				getPage(1,10);
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
				getPage(lastpage,10)
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

		getPage($(".current_page").eq(0).val(),10)
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
		getPage($(".current_page").eq(0).val(),10);
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
		getPage(index,10);
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
		getPage(index,10);
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
		getPage(index,10);
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
		getPage(index,10);
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
		getPage(index,10);
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

		getPage(text,10);
}
