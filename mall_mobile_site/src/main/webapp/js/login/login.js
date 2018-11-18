/**
 * 
 */

function login() {
	if (checkInput()) {
		$("input[type='text']").next().hide();
		var url = "checklogin.htm?username=" + $("#log_user").val() + "&password="
				+ $("#log_psd").val() + "&url=" + $("#urlhide").val();
		$.ajax({
			url : url,
			success : function(data) {
//				if (data == 0) {
//					$("#log_psd").next().show().addClass('tips_error').html(
//							'密码和账户不匹配，请重新输入');
//				} else if (isNaN(data)) {
//					window.location.href = data;
//				} else if (data == 2) {
//					$("#log_user").next().show().addClass('tips_error').html(
//							'你输入的用户不存在，请重新输入');
//				}
				if (isNaN(data)) {
					window.location.href = data;
				} else {
					$("#log_psd").parent().addClass("has-error");
					$("#log_user").parent().addClass("has-error");
					$("#log_user").next().show().html('');
					$("#log_psd").next().show().html('用户或者密码错误，请重新输入');
				}
			}
		});
	}
}

//注册协议
function xieyi(){
	window.location.href='getXieYi.htm';
}
function checkInput() {
	var flag = false;
	var x = $("#log_user").val();
	if (x == '') {
		$("#lu_err").text("请输入账号/邮箱/手机号码");
	} else if (!isUser(x)) {
		$("#lu_err").text("请输入账号/邮箱/手机号码");
	} else {
		$("#lu_err").text("");
		flag = true;
	}
	if (!flag) {
		$("#log_user").parent().addClass("has-error");
	}else{
		$("#log_user").parent().removeClass("has-error");
	}
	var flagP = false;
	x = $("#log_psd").val();
	if (x == '') {
		$("#pwd_err").text("请输入密码");
	} else {
		$("#pwd_err").text("");
		flagP = true;
	}
	if (!flagP) {
		$("#log_psd").parent().addClass("has-error");
	}else{
		$("#log_psd").parent().removeClass("has-error");
	}
	return flag && flagP;
}
function isUser(x) {
	var reg = /^\w+[@\.]?$/;
	if ((x.length >= 4) && (x.length <= 20)) {
		return true;
	} else {
		return false;
	}
}
document.onkeydown = function(event) {
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if (e && e.keyCode == 13) { // enter 键
		login();
	}
};