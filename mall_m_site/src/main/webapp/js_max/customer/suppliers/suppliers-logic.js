//===========================
//		业务逻辑
//===========================

/*上传图片*/
$(".homePic span").bind("click", function () {
    var _this = this;
    var id = $(this).attr("data-id");
    Image.Select(this, function () {
        if (id == "#pic1") {
            Write.Must.Ok(_this);
        } else if (($("#pic5").val() != "" || $('input[class="picUpload-' + 'pic5' + '"]').val() != "") &&
            ($("#pic6").val() != "" || $('input[class="picUpload-' + 'pic6' + '"]').val() != "") &&
            ($("#pic7").val() != "") || $('input[class="picUpload-' + 'pic7' + '"]').val() != "") {
            Write.Must.Ok(_this);
        }
    });
});

$("#old").bind("click", function () {
    if ($("#pic4").val() != "") {
        Write.Must.Ok(this);
    } else {
        Write.Must.No(this);
    }
});
$("#new").bind("click", function () {
    if ($("#pic5").val() != "" && $("#pic6").val() != "" && $("#pic7").val() != "") {
        Write.Must.Ok(this);
    } else {
        Write.Must.No(this);
    }
});

$("input[type='text']").bind("blur", function () {
    var idVal = $(this).attr("id");
    if (idVal == "email" || idVal == "mobile") {
        return;
    }
    var con = $(this).parents(".must").text().split("*")[1].split("：")[0];
    if (Form.Nonull(this, con)) {
        Write.Must.Ok(this);
    }
});


//提交
$("#sub").click(function () {
    Sub(function () {
        var t = 1;
        var timer = setInterval(function () {
            if (t == 1) {
                t--;
                $("form").submit();
            }
        }, 1000);

    });
    Error([".genre", ".comPic", ".comCert"]);
});


$(".genre li").bind("click", function () {
    var n = $(this).index() + 1;
});
function Error(arr) {
    for (n in arr) {
        if ($(arr[n]).attr("num") != '1') {
            var mes = $(arr[n]).attr("data-mes");
            $(arr[n]).children("code").show().text(mes);
        }
    }
}
var beans = ["enterpriseProvince", "enterpriseCity", "enterpriseCounty"];
//初始化省
$("#area").bind("click", function () {
    //初始化省
    initAddress("", "", beans);
});
$("#addressInit").delegate("li","click", function () {
        var id = $(this).attr("data-id");
        var addressType = $(this).attr("data-address");
        initAddress(id, addressType, beans);
    }
);

$("#addressInit").delegate("li","click",function(){
    var n = parseInt($("#addressInit").attr("data-count"))+1;
    $("#addressInit").attr("data-count",n);
    var lt = $("#line").attr("data-left");
    $("#line").attr("data-left","74%").css("left",lt);//7% 40% 74%
    var name = $(this).text();
    if(n == 3){
        setTimeout(function(){
            $(".district").css("bottom","-50%");
        },30);
        setTimeout(function(){
            $(".addrBox").hide();
        },600);
    }
    switch(n){
        case 1: $("#Province").text($(this).text());break;
        case 2: $("#prefecture").text($(this).text());break;
        case 3: $("#county").text($(this).text());break;
    }
    event.stopPropagation();//阻止冒泡事件
});

/**
 * 异步保存地址为默认收货地址
 */
function saveDefaulAddr() {
    //准备信息添加收货地址并设置成默认
    var url = "addDefaultAddress.htm";
    if (url.length == 0) {
        return false;
    }
    var newUrl = url;
    if (basePath.length > 0) {
        newUrl = basePath + "/" + url;
    }
    var addressName = $('input[name="companyName"]').val();
    var addressMoblie = $('input[name="company_contact_moble"]').val();
    var addressDetail = $('input[name="companyContactAddr"]').val();
    var infoProvince = $('input[name="enterpriseProvince"]').val();
    var infoCity = $('input[name="enterpriseCity"]').val();
    var infoCounty = $('input[name="enterpriseCounty"]').val();
    if (addressName != "" &&
        addressMoblie != "" &&
        addressDetail != "" &&
        infoProvince != "" &&
        infoCity != "" &&
        infoCounty != "") {
        $.ajax({
            type: 'post',
            url: newUrl,
            async: false,
            data: {
                addressName: addressName, addressMoblie: addressMoblie, addressDetail: addressDetail,
                infoProvince: infoProvince, infoCity: infoCity, infoCounty: infoCounty
            },
            success: function (data) {
                //异步添加默认地址
            }
        });
    }
}

