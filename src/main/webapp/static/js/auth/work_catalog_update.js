$(function () {
		var he = HE.getEditor('editor');
		$("#editor").text($("#content").val());
		$("#editor").css("font-size","24px");
		$("#save_catalog").on("click",updateCatalog);
		function getHtml(){
				alert(he.getHtml());
		}
		function getText(){
				console.log(he.getText())
				alert(he.getText());
		}
})
function updateCatalog() {
		var cont = $(".HandyEditor_editor").text();
		console.log(cont);
		var novelname = $("#novelname").val();
		console.log(novelname);
		var newname = $("#catalog_input").val();
		console.log(newname);
		var oldname = $("#catalog").val();
		console.log(oldname)

		$.post("/wfRead/auth/updatacatalog",
			"novelName="+novelname+"&newCatalogName="+newname+"&oldCatalogName="+oldname+"&content="+cont,
			function (data) {
					alert(data.message);
					if (data.flag){
							window.location.href="wfRead/auth/workUI";
							window.close();
					}
			},"json")
}
