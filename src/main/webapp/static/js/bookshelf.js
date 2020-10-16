$(function () {
		$(".deletebtn").on("click", deletebookfromshelf);
})

function deletebookfromshelf() {
		var name = $(this).attr("name");
		$.post("/wfRead/reader/deletbookfromshelf",
			"id=" + name,
			function (data) {
					if (data.flag) {
							alert("删除成功!")
							location.reload();
					} else {
							alert(data.message);
					}
			},
			"json")
}