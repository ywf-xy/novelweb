$(function () {
		let srcs = $(".imgs");
		let imgP = $(".img_p");

		for (let i = 0; i <srcs.length ; i++) {

				var str = srcs[i].value;
				var src_cn = str.substr(str.indexOf("picture/"),str.indexOf("."));
				var cn=src_cn.replace("picture/","").replace(".jpg","");
				cn=decodeURI(cn);

				cn = escape(cn).replaceAll("%", "_");
				str = str.replace(src_cn,"picture/"+cn+".jpg");
				var image = '<img src="'+str+'" alt="404">'
				imgP[i].innerHTML=image;
		}
})