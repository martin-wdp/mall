//初始化省市区
function initAddress(id, addressType,beans) {
    //初始化省
    var url = "";
    var htmlstr = "";
    var newType = "";
    var basePath = $("#basePath").val();
    //初始化省份
    if (id == "" || addressType == "") {
        url = "simpleGetAllProvince.htm";
        newType = "provicen";
    }
    //初始化市
    if (id != "" && addressType == "provicen") {
        url = "simpleGetCityByPid.htm?parentId=" + id;
        newType = "city";
        $('input[name="'+beans[0]+'"]').val(id);
    }
    //初始化街道
    if (id != "" && addressType == "city") {
        url = "simpleGetDistrictByCid.htm?parentId=" + id;
        newType = "county";
        $('input[name="'+beans[1]+'"]').val(id);
    }
    if (id != "" && addressType == "county") {
        $('input[name="'+beans[2]+'"]').val(id);
    }
    //ajax向后台请求数据
    if (url.length == 0) {
        return false;
    }
    var newUrl = url;
    if (basePath.length > 0) {
        newUrl = basePath + "/" + url;
    }
    $.ajax({
        type: 'post',
        url: newUrl,
        async: false,
        success: function (data) {
            if (data != null && data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    htmlstr += '<li data-address = "' + newType + '" data-id="' + data[i].addressId + '">' + data[i].addressName + '</li>';
                }
            }
        }
    });
    $("#addressInit").html("");
    $("#addressInit").html(htmlstr);
}