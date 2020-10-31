$(function () {
		$("#close_window").on("click", closewindow);
		$("#quit").on("click", closewindow);
		$("#updata").on("click", updateNovel);

})

function update(index) {
		$("#mask").css("display", "block");
		var bookname = $(".bookName")[index].innerText;
		var booktype = $(".bookType")[index].innerText;
		var bookstatus = $(".bookStatus")[index].innerText;
		var bookintro = $(".bookIntro")[index].innerText;

		$("#bookid").val(index);
		$("#update_name").val(bookname)
		$("#update_type").val(booktype);
		$("#update_intro").val(bookintro);
		var radios = $(".update_status");
		for (let i = 0; i < radios.length; i++) {
				if (bookstatus == radios[i].value) {
						radios[i].checked = true
				} else if (bookstatus == "完本") {
						radios[1].checked = true
				}
		}
}

function closewindow() {
		$("#mask").css("display", "none");
		$("#update_name").val("")
		$("#update_type").val("");
		$("#update_intro").val("");
		$(".update_status")[0].checked = false;
		$(".update_status")[1].checked = false;
}

function updateNovel() {
		let book_name = $("#update_name").val();
		let book_type = $("#update_type").val();
		let book_intro = $("#update_intro").val();
		let book_status = $(".update_status:checked").val();
		let index = $("#bookid").val();
		console.log("book_name="+book_name+"&book_type="+book_type+"&book_intro="+book_intro+"&book_status="+book_status);
		console.log(index);
		$.post("workupdate",
			"book_name="+book_name+"&book_type="+book_type+"&book_intro="+book_intro+"&book_status="+book_status,
			function (data) {
				alert(data.message);
				if (data.flag){
						 $(".bookStatus")[index].innerText=book_status;
						 $(".bookIntro")[index].innerText=book_intro;
						closewindow();
				}
			},
			"json");
}

