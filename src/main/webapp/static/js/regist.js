function authRegist() {
		$.post("authregist",
			$('#authregistForm').serialize(),
			function (data) {
					console.log(data.flag);
					if (data.flag) {
							alert(data.message);
							window.top.location.href = "/wfRead";
					}else{
							$("#auth p span").text(data.message);
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
					}else{
							$("#reader p span").text(data.message);
					}

			}, "json");
}