function authLogining() {
		var nickname = $('#auth_nickname').val();
		var password = $('#auth_password').val();
		$.post("authlogin", $('#authLoginForm').serialize(),
			function (data) {
					var flag = data.flag;
					if (flag) {
							//alert("zz?")
							window.top.location.href = "/wfRead/auth/authorUI";
					} else {
							$("#auth p span").html(data.message);
					}
			}, "json");

}

function readerLogining() {
		var nickname = $('#reader_nickname').val();
		var password = $('#reader_password').val();

		$.post("readerlogin", $('#readerLoginForm').serialize(),
			function (data) {
					var flag = data.flag;
					if (flag) {
							window.top.location.href = "/wfRead/" + data.message;
					} else {
							$("#reader p span").html(data.message);
					}
			}, "json");

}