var basePath = $('input[id="basePath"]').val();
//密码
$("#pass").blur(function () {
    if ($(this).val() != "") {
        Write.Must.Ok(this);
    } else {
        Write.Must.No(this);
        if ($(this).val() != "") {
            Write.Error(this, "请输入密码！");
        }
    }
});
function checkMobile(mobile) {
    var reg = /^1(([0-9]{2})+\d{8})$/;
    if (reg.test(mobile)) {
        return true;
    } else {
        return false;
    }
}
function checkForm() {
    var mobile=$('input[id="mobile"]').val();
    var pass=$('input[id="pass"]').val();
    if (mobile != "" && mobile.length == 11 && checkMobile(mobile)) {
        $("#mobile").parent().attr("num", "1");
    }
    if (pass != "" && pass.length >= 6) {
        $("#pass").parent().attr("num", "1");
    }
}
$("#sub").bind("click", function () {
    checkForm();
    Sub(function () {
        $("input[type='text']").next().hide();
        /* var basePath = $('input[id="basePath"]').val();
         if(basePath!=""&&basePath.length>0){
         basePath += "/";
         }*/
        var isMerPwd = $('input[id="check"]').is(":checked") ? "1" : "0";
        var url = basePath + "/checklogin.htm?username=" + $('input[id="mobile"]').val() + "&password=" + $('input[id="pass"]').val() + "&isMerPwd=" + isMerPwd + "&url=" + basePath + $("#urlhide").val();
        $.ajax({
            url: url,
            success: function (data) {
//				if (data == 0) {
//					$("#log_psd").next().show().addClass('tips_error').html(
//							'密码和账户不匹配，请重新输入');
//				} else if (isNaN(data)) {
//					window.location.href = data;
//				} else if (data == 2) {
//					$("#log_user").next().show().addClass('tips_error').html(
//							'你输入的用户不存在，请重新输入');
//				}
                if (isNaN(data) && data != "0") {
                    window.location.href = basePath + $("#urlhide").val();
                } else {
                    $("#login_err").show();
                }
            }
        });
    });
});