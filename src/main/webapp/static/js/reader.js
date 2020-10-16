$(function () {
		$(".group6 .tabs a").on("click", changetabs);
		$(".siderBar li").on("click", manuechange);
		$("#imgupload").on("click", upLoadImg);
		$(".tab1_sub").on("click", updataReader);
		$("")
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
							console.log(msg)
							if (msg.nick_name != "") {
									console.log(msg.nick_name);
									$("#nick_nameerror").text(msg.nick_name);
							} else {
									$("#nick_nameerror").text("");
							}
							if (msg.birthday != "") {
									console.log(msg.birthday);
									$("#birthdayerror").text(msg.birthday);
							} else {
									$("#birthdayerror").text("");
							}
							if (msg.intro != "") {
									console.log(msg.intro);
									$("#introerror").text(msg.intro);
							} else {
									$("#introerror").text("");
							}
							if (msg.address != "") {
									console.log(msg.address);
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
		} else if (obj.text() == "账务中心") {
				return 2;
		} else if (obj.text() == "我的书评") {
				return 4;
		} else if (obj.text() == "会员中心") {
				return 5;
		} else if (obj.text() == "个人资料") {
				return 6;
		}
}

function upLoadImg() {
		var fileObj = $(".fileup")[0].files[0]; // js 获取文件对象
		console.log(fileObj);
		if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
				alert("请选择上传的图片！");
				return;
		} else if (fileObj.size > 1024 * 10240) {
				alert("上传文件大小不超过10MB!");
		}
		var formFile = new FormData();
		formFile.append("file", fileObj);
		var data = formFile;
		console.log(data)
		/*$.post("/wfRead/reader/imgupload",
			data,
			function (data) {
				var urls = data.data;
				alert(data.message);
				if (data.flag){
					console.log(urls);
				}
			},"json")*/
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
								console.log(urls);
								$("#tabTarget2 img").attr("src", "/" + urls[0]);
						}
				}
		});
}