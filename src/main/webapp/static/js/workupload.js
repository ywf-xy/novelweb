$(function () {
		$("#up-img").on("click", imguploadUI);
		$("#up-novel").on("click", noveluploadUI);
		$("#imgupload").on("click", imgupload);
		$("#novelupload").on("click", novelupload);
})

function imguploadUI() {
		$("#imgDiv").css("display", "block");
		$("#novelDiv").css("display", "none");
}
function imgupload() {
		var fileObj = $(".imgfileup")[0].files[0]; // js 获取文件对象
		var filename = $(".filename").val()+"."+(fileObj.name).split(".")[1]
		console.log(filename);
		if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
				alert("请选择上传的图片！");
				return;
		} else if (fileObj.size > 1024 * 10240) {
				alert("上传文件大小不超过10MB!");
		}
		var formFile = new FormData();
		formFile.append("file", fileObj);
		formFile.append("filename",escape(filename).replaceAll("%","_"))
		var data = formFile;

		$.ajax({
				url: '/wfRead/auth/nimgupload',
				type: 'post',
				dataType: 'json',
				data: data,
				cache: false,   //上传文件无需缓存
				processData: false,   // 用于对参数进行序列化处理，这里必须设为false
				contentType: false, // 必须
				success: function (data) {
						alert(data.message);
						if (data.flag){
								$("#imgDiv").css("display", "none");
						}
				}
		});
}
function noveluploadUI() {
		$("#novelDiv").css("display", "block");
		$("#imgDiv").css("display", "none");
}
function novelupload() {
		var fileObj = $(".novelfileup")[0].files[0]; // js 获取文件对象
		if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
				alert("请选择TXT小说！");
				return;
		}
		var formFile = new FormData();
		formFile.append("file", fileObj);
		formFile.append("filename",fileObj.name);
		var data = formFile;

		$.ajax({
				url: '/wfRead/auth/novelupload',
				type: 'post',
				dataType: 'json',
				data: data,
				cache: false,   //上传文件无需缓存
				processData: false,   // 用于对参数进行序列化处理，这里必须设为false
				contentType: false, // 必须
				success: function (data) {
						alert(data.message);
						if (data.flag){
								$("#novelDiv").css("display", "none");
						}
				}
		});
}