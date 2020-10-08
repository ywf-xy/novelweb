function searchbtn() {
    var model = $(':selected').text();
    var searchtext = $('#searchbbox').val();
    if(searchtext!=""){
        if (model=="小说"){
            window.open("/wfRead/search/searchbookname?bookname="+searchtext);
        }
        if(model=="作者"){
            window.open("/wfRead/search/searchauthname?authname="+searchtext);
        }
    }
    else{
        alert("不能搜索空的"+model+"哦!");
    }
}