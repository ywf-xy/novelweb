$(function () {
		$("#save_catalog").on("click",saveCatalog);
})
function saveCatalog() {
		var cont = $(".HandyEditor_editor").text();
		console.log(cont);
		var novelname = $("#novelname").val();
		console.log(novelname);
		var catalogname = $("#catalog_input").val();
		console.log(catalogname);

		$.post("addcatalog","novelName="+novelname+"&novelCatalog="+catalogname+"&content="+cont,
			function (data) {
					if (data.flag){
							window.location.href="wfRead/auth/workUI";
							window.close();
					}else {
							alert(data.message);
					}
			},"json");
}