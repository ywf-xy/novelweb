$(function(){
    $(".group6 .tabs a").on("click",changetabs);
    $(".siderBar li").on("click",manuechange);
    $("#imgupload").on("click",upLoadImg);
})
function changetabs(){
    var nowtab = $(" .group6 .checked").text()

    if("基本设置" == nowtab){
        $("#tabTarget2").css("display","block");
        $("#tabTarget1").css("display","none");
        $(" .group6 .checked").removeClass("checked");
        $("div.group6 > div > div.tabs > a:nth-child(2)").addClass("checked");
    }else if("头像设置"==nowtab){
        $("#tabTarget1").css("display","block");
        $("#tabTarget2").css("display","none");
        $(" .group6 .checked").removeClass("checked");
        $("div.group6 > div > div.tabs > a:nth-child(1)").addClass("checked");
    }
}
function manuechange(){

    var cl = $(this).attr("class");
    var groupid ;
    if(cl == ""){
        var act = $(".siderBar .active");
        groupid = getGroupId(act)
        act.removeClass("active");
        $(".group"+groupid).css("display","none");

        $(this).addClass("active");
        $(".group"+getGroupId($(this))).css("display","block");
    }

}
function getGroupId(obj){
    if(obj.text()=="首页"){
        return 1;
    }else if(obj.text()=="账务中心"){
        return 2;
    }else if(obj.text()=="我的书评"){
        return 4;
    }else if(obj.text()=="会员中心"){
        return 5;
    }else if(obj.text()=="个人资料"){
        return 6;
    }
}
function upLoadImg() {
    var fileObj = $(".fileup")[0].files[0]; // js 获取文件对象
    console.log(fileObj);
    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
        alert("请选择上传的图片！");
        return;
    }else if(fileObj.size > 1024*10240){
        alert("上传文件大小不超过10MB!");
    }
    var formFile = new FormData();
    formFile.append("file", fileObj);
    var data = formFile;
    console.log(data)
    /*$.post("/wfRead/reader/imgupload",
        data,
        function (data) {
            var urls = data.data;
            alert(data.message);
            if (data.flag){
                console.log(urls);
            }
        },"json")*/
    $.ajax({
        url : '/wfRead/reader/imgupload',
        type : 'post',
        dataType : 'json',
        data : data,
        cache: false,   //上传文件无需缓存
        processData: false,   // 用于对参数进行序列化处理，这里必须设为false
        contentType:false, // 必须
        success : function (data) {
            var urls = data.data;
            alert(data.message);
            if (data.flag){
                console.log(urls);
                $("#tabTarget2 img").attr("src","/"+urls[0]);
            }
        }
    });
}