$(function () {
	$("#btn_pwd").on("click",updatePassword);
	$("#img_up_btn").on("click",uploadHeadImg);
})
function updatePassword() {
		var old_pwd = $("#inputSuccess1").val();
		var new_pwd = $("#inputSuccess2").val();
		var cfm_pwd = $("#inputSuccess3").val();
		//alert("old_pwd="+old_pwd)
		/*http://localhost:8080/wfRead/auth/updatepasswrod?old_pwd=123456&cfm_pwd=1&new_pwd=1*/
		$.post("/wfRead/auth/updatepasswrod",
			"old_pwd="+old_pwd+"&new_pwd="+new_pwd+"&cfm_pwd="+cfm_pwd,
			function (data) {

				if (data.flag){
						alert(data.message);
						$("#pwd_erro").text("");
						$("#inputSuccess1").val("");
						$("#inputSuccess2").val("");
						$("#inputSuccess3").val("");
				}else{
						if ("对不起请登录！"==data.message){
								alert(data.message);
								window.location.href="/wfRead/loginAndRegist/login";
						}else{
								$("#pwd_erro").text(data.message);
						}
				}
			},"json")
}

function uploadHeadImg() {
		var fileObj = $("#imgInput")[0].files[0]; // js 获取文件对象
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

		$.ajax({
				url: '/wfRead/auth/authheadupload',
				type: 'post',
				dataType: 'json',
				data: data,
				cache: false,   //上传文件无需缓存
				processData: false,   // 用于对参数进行序列化处理，这里必须设为false
				contentType: false, // 必须
				success: function (data) {
						var urls = data.data;
						console.log(urls)
						alert(data.message);
						if (data.flag) {
								$("#big_headimage").attr("src", "/" + urls[0]);
								$(".headimage").attr("src","/" + urls[0])
						}
				}
		});
}