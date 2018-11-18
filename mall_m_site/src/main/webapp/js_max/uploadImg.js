$(function () {
    win();
    $(window).resize(function () {
        win();
    });
});
//图片路径
var img_src = $("#customer_imgs").attr("src");

/**
 *  修改头像
 */

    //选择上传文件后触发

 function uploadAjax() {
    if ($(this).val() != "") {
        var customerId = $(this).attr("customer_id");
        var url = $(this).attr("url");
        /*$("#upload_form").submit();*/
        $.ajaxMutiUpload({
            url: url,
            fileElementId: "pic1",
            async: false,
            success: function (data) {
                //alert(data);
                if (data != null) {
                    clearTip();
                    location.reload();
                    //$('#applyBrand').modal('show');
                } else {
                    clearTip();
                }
            }
        });
    }
}


/**
 * 上传文件成功后，触发。
 * @param msg
 */
function callback(msg) {
    //上传失败
    if (msg.split(",").length < 2) {
        if (msg == '101') {
//			showAlert("操作提示","每张图片不超过4M!","warn");
            $("#titleerr").text("每张图片不超过4M!");
            dia(4);
            $("#customer_imgs").attr("src", img_src);
        } else if (msg == '102') {
//			alert("操作提示","图片格式不正确!","warn");
            $("#titleerr").text("图片格式不正确!");
            dia(4);
            $("#customer_imgs").attr("src", img_src);
        }
        return;
    }
    //上传成功
    var imageName = msg.split(",")[0];
    //alert(111);
    $("#useravatar").css("background-image", "url(" + imageName + ")");
    //img_src = $("#customer_imgs").attr("src");
}

function win() {
    var _wd = $(window).width();
    var _hd = $(window).height();
    $(".s_dia").css("top", (_hd - $(".dialog").height()) / 2).css("left",
        (_wd - $(".s_dia").width()) / 2);

};

function dia(n) {
    $(".mask").fadeIn();
    $(".dia" + n).fadeIn();
};

function cls() {
    $(".dialog").fadeOut();
    $(".mask").fadeOut();
};