$(function () {
		$("input:checkbox").on("click", checkedAction);
		$("#back_list").on("click", backList);
})

function checkedAction() {
		var ck = $("input:checkbox:checked");
		if (ck.length >= 3) {
				$(this)[0].checked = false;
				alert("最多只能选两个")
		}
		ck = $("input:checkbox:checked");

		var list = [];
		for (let i = 0; i < ck.length; i++) {
				list[i] = ck[i].value;
		}
}

function addNovel() {
		var ck = $("input:checkbox:checked");
		var list = [];
		for (let i = 0; i < ck.length; i++) {
				list[i] = ck[i].value;
		}
		var book_name = $("#novelName").val();
		var book_state = $("#novelStatus").val();
		var book_author = $("#novelAuthor").val();
		var book_intro = $("#novelIntro").val();

		$.post("/wfRead/auth/addnovel",
			"book_name=" + book_name + "&book_author=" + book_author + "&book_state=" + book_state + "&book_intro=" + book_intro + "&types=" + list,
			function (data) {
					alert(data.message)
					if (data.flag) {
							window.location = "/wfRead/auth/workUI";
					}
			}, "json")
}

function showCatalog(index, novelName) {
		let catalogs;
		//打开写作div
		$("#work_list").css("display", "none");
		$("#catalog_list").css("display", "block");
		$(".catalog_list_top h4").text(novelName);

		$.post("/wfRead/novel/loadbookcatlog", "bookname=" + novelName, function (data) {
				if (data.flag) {
						catalogs = data.data;
						console.log(catalogs[0].novale_catalog)
						var trs = '';
						for (var i = 0; i < catalogs.length; i += 3) {
								var tr = '<tr>\n';
								if (i < catalogs.length) {
										tr += '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td><a href="/wfRead/auth/tocatalogupdata?novelName='+novelName+'&novelCatalog='+catalogs[i].novale_catalog+'" target="_blank">' + catalogs[i].novale_catalog + '</a></td>\n'
								}
								if (i + 1 < catalogs.length) {
										tr += '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td><a href="/wfRead/auth/tocatalogupdata?novelName='+novelName+'&novelCatalog='+catalogs[i+1].novale_catalog+'" target="_blank">' + catalogs[i + 1].novale_catalog + '</a></td>\n'
								} else {
										tr += '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td><a href="">' + "暂无章节" + '</a></td>\n'
								}
								if (i + 2 < catalogs.length) {
										tr += '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td><a href="/wfRead/auth/tocatalogupdata?novelName='+novelName+'&novelCatalog='+catalogs[i+2].novale_catalog+'" target="_blank">' + catalogs[i + 2].novale_catalog + '</a></td>\n'
								} else {
										tr += '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td><a href="">' + "暂无章节" + '</a></td>\n'
								}
								tr += '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>';
								trs += tr;
								console.log(tr)
						}
						$(".catalog_list_content tbody").append(trs);
				}
		})

}

function backList() {
		$(".catalog_list_content tbody").empty()
		$("#work_list").css("display", "block");
		$("#catalog_list").css("display", "none");
}