function authRegist() {
		$.post("authregist",
			$('#authregistForm').serialize(),
			function (data) {
					console.log(data.flag);
					if (data.flag) {
							alert(data.message);
							window.top.location.href = "/wfRead";
					}
			}, "json");
}

function readRegist() {
		$.post("readregist",
			$('#readerregistForm').serialize(),
			function (data) {
					console.log(data.flag);
					if (data.flag) {
							alert(data.message);
							window.top.location.href = "/wfRead";
					}
			}, "json");
}