$(function() {
	//验证码绑定onclick事件
	$("#checkCodeA").click(
		function(){
			$("#checkCodeImg").click();
		}
	);
	$("#code").focus(function(){
		$(this).parent().find(".vd_tip").show().html("请输入验证码").css("color","#dd6330");
		getPatcha();
	});
	$("#code").blur(function(){
		$(this).parent().find(".vd_tip").hide().html("验证码错误").css("color","#dd6330");
	});
	
	$("#username").focus(function(){
		$(this).parent().find(".vd_tip").show().html("请输入用户名").css("color","#dd6330");
	});
	$("#username").blur(function(){
		$(this).parent().find(".vd_tip").hide().html("验证中,请稍后...").css("color","#dd6330");
	});
	$("#mcode").focus(function(){
		$(this).parent().find(".vd_tip").show().html("请输入手机验证码").css("color","#dd6330");
	});
	$("#mcode").blur(function(){
		$(this).parent().find(".vd_tip").hide().html("手机验证码错误").css("color","#dd6330");
	});
	
	$("#npwd").focus(function(){
		$(this).parent().find(".vd_tip").show().html("请输入新的登录密码").css("color","#dd6330");
	});
	$("#npwd").blur(function(){
		$(this).parent().find(".vd_tip").hide().html("密码格式不正确,请输入6-20位长度的密码!").css("color","#dd6330");
	});
	$("#repwd").focus(function(){
		$(this).parent().find(".vd_tip").show().html("请再次输入登录密码").css("color","#dd6330");
	});
	$("#repwd").blur(function(){
		$(this).parent().find(".vd_tip").hide().html("您两次输入的密码不相同!").css("color","#dd6330");
		checkRePwd();
	});
	
	$("#code_tip").hide();
	$(".timeleft").css("color","#e4393c");
	$(".timeleft").css("font-weight","bold");
});
function gofindpwd(){
	if($("#username").val().trim().length == 0){
		$("#username").parent().find(".vd_tip").show().html("请输入用户名").css("color","#dd6330");
		return;
	}
	if(! checkCurrentCode()){
		return;
	}
	
	var datas = "&username=" + $("#username").val();
	jQuery.ajax({
		type : "get",
		url : "checkusernameflag.htm",
		data : datas,
		timeout : 10000,
		success : function(html) {
			if (html == 1) {
				$(".username_tip").hide();
				location.href="validuser.html";
			}else{
				$(".username_tip").show().html("用户名不存在!");
				$("#checkCodeImg").click();
				getPatcha();
			}
		},
		error:function(){
			
		}
	});
}

var emp = null;
function getPatcha(){
	$.ajax({
		url: "patchcaSession.htm", 
		context: document.body, 
		success: function(data){
			emp = data;
		}});
}
function checkCurrentCode(){
	var checkCode = $("#code").val();
	if(checkCode == emp){
		$(".code_tipnew").hide().css("color","#dd6330");
		$(".code_tipnew").html("请输入验证码");
		return true;
	}else{
		$(".code_tipnew").show().css("color","#dd6330");
		$(".code_tipnew").html("验证码错误");
		/*如果失败修更新验证码*/
		$("#checkCodeImg").click();
		getPatcha();
		$(".code_text").blur();
		return false;
	}
}





function checkAuthCode(){
	var checkCode = $("#code").val();
	if(checkCode.trim().length==0){
		$(".code_tipnew").show().css("color","#dd6330");
		$(".code_tipnew").html("请输入验证码");
		return false;
	}
	$(".code_tipnew").hide().css("color","#dd6330");
	$(".code_tipnew").html("验证码错误");
	$("#checkCodeImg").click();
	getPatcha();
	return true;
}
function checkMCode(){
	var checkCode = $("#mcode").val();
	if(checkCode != ""){
		$(".mcode_tip").hide().css("color","#dd6330");
		$(".mcode_tip").html("请输入手机验证码");
		return true;
	}else{
		$(".mcode_tip").show().css("color","#dd6330");
		$(".mcode_tip").html("手机验证码错误");
		return false;
	}
}

function checkMobile(){
	var mobile = $("#mobile").val();
	if (!mobile) {
		$(".mobile_tip").show();
		$(".mobile_tip").html("请输入手机号");
		return;
	}
	var re = /^1{1}[3,4,5,8]{1}\d{9}$/; // 判断是否为数字的正则表达式
	if (!re.test(mobile)) {
		$(".mobile_tip").show();
		$(".mobile_tip").html("请输入正确的手机号码");
		return false;
	}
	//$(".mobile_tip").hide();
	$(".mobile_tip").html("&nbsp;");
	return true;
}

function getCode(){
	
	/*if(! checkMobile()){
		return;
	}
	var mobile = $("#mobile").val();
	var datas = "moblie=" + mobile;*/
	$("#sendCode").attr("disabled","disabled");
	$.ajax({
        type: 'get',
        url:'sendcodefindpwd.htm',
        //data:datas,
        async:true,
        success: function(data) {
        	if(data==1) {
				$(".timeleft").text(59);
				$("#code_tip").show();
				setTimeout(countDown, 1000);
				$("#mcode").removeAttr("disabled");
				$(".sub_btn").removeAttr("disabled");
				$("#mobile").attr("disabled","disabled");
				$("#sendCode").attr("disabled","disabled");
			}else if(data==0){
				//网络异常
				dia(2);
				$("#sendMobileCode").removeAttr("disabled");
			}else if(data==-1){
				$("#code_tip").show();
				$(".timeleft").text(59);
				if($(".timeleft").text()==59){
					$(".resend").text("");
					$("#code_tip").find(".resend").remove();
					$("#code_tip").find("br").remove();
					$("#code_tip").html("<span class='resend'>60秒内只能获取一次验证码,请稍后重试</span>");
				}else{
					$(".resend").text("");
					$("#code_tip").find(".resend").remove();
					$("#code_tip").find("br").remove();
					$("#code_tip").append("<br/><span class='resend'>60秒内只能获取一次验证码,请稍后重试</span>");
				}
				$("#mcode").removeAttr("disabled");
			}
        },
        error : function() {
        	//网络异常
			$("#submitCode").removeAttr("disabled");
			dia(2);
    	}
    });
	function countDown(){
		var time = $(".timeleft").text();
		$(".timeleft").text(time - 1);
		if (time == 1) {
			$("#code_tip").hide();
			$("#sendCode").removeAttr("disabled");
			$("#mobile").removeAttr("disabled");
		} else {
			setTimeout(countDown, 1000);
		}
	}
}


function checkNewPwd(){
	var x=$("#npwd").val();
	if(x.length < 6 || x.length > 20){
		$("#npwd").parent().find(".vd_tip").show().html("密码格式不正确,请输入6-20位长度的密码!");
		return false;
	}
	$(this).parent().find(".vd_tip").hide().html("请输入登录密码").css("color","gray");
	return true;
}
function checkRePwd(){
	var x = $("#npwd").val();
	var y = $("#repwd").val();
	if($("#npwd").val().trim().length == 0 && $("#repwd").val().trim().length == 0 ){
		$("#repwd").parent().find(".vd_tip").show().html("请输入登录密码!");
		return false;
	}
	if(x != y){
		$("#repwd").parent().find(".vd_tip").show().html("您两次输入的密码不相同!");
		return false;
	}
	$(this).parent().find(".vd_tip").hide().html("请输入登录密码").css("color","gray");
	return true;
}
function checkEmail(){
	var email = $("#email").val();
	if(!email){
		$(".email_tip").show();
		$(".email_tip").html("请输入您的常用邮箱");
		return false;
	}
	var reg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
	if (email.replace(/[^\x00-\xff]/g, "**").length <= 4 || !reg.test(email) || email.length > 50) {
		$(".email_tip").show();
		$(".email_tip").html("邮箱格式不正确，请重新输入,长度在4-50之间");
		return false;
	}
	if($("#e_hide").val()==email){
		$(".email_tip").show();
		$(".email_tip").html("检测到此邮箱为当前使用邮箱,请更换!");
		return false;
	}
	$(".email_tip").hide();
	$(".email_tip").html("");
	return true;
}

function tofindpwd(type){
	
	if(! checkMobile()){
		return;
	}
	if(! checkMCode()){
		return;
	}
	if(! checkCurrentCode()){
		return;
	}
	if(! checkAuthCode()){
		return;
	}
	if(! checkMCode()){
		return;
	}
	var res=0;
	var checkCode = $("#mcode").val();
	$.ajax({
		url: "validate/getMCode.htm?code="+checkCode, 
		context: document.body, 
		async:false,
		success: function(data){
			res=data;
	}});
	if(res == 1){
		$(".mcode_tip").hide();
		$(".mcode_tip").html("请输入手机验证码");
	}else if(res == -1){
		$(".mcode_tip").show();
		$(".mcode_tip").html("手机验证码已经失效");
		return;
	}else{
		$(".mcode_tip").show();
		$(".mcode_tip").html("手机验证码错误");
		return;
	}
	location.href="resetpwd.html";
}

function updatePwd(){
	var flag = true;
	if(! checkNewPwd()){
		flag =false;
//		return;
	}
	if(! checkRePwd()){
		flag =false;
//		return;
	}
	if(! checkCurrentCode()){
		flag =false;
//		return;
	}
	if(!flag){
		return;
	}
	var datas = "1=1";
	datas += "&customerPassword=" + $("#npwd").val();
	jQuery.ajax({
		type : "post",
		url : "setpwd.htm",
		data : datas,
		timeout : 10000,
		success : function(html) {
			if (html == 1) {
				location.href="updatesucess.html";
			} else {
				dia(2);
			}
		},
		error:function(){
			dia(2);
		}
	});
}

function sendEmail(){
	if(! checkCurrentCode()){
		return;
	}
	//var datas="email="+$(".set_password dl:eq(0) dd input").val();
	$.ajax({
        type: 'post',
        url:'sendeamil.htm',
        //data:datas,
        async:true,
        success: function(obj) {
        	if(obj==1) {
        		location.href="sendsuccess.html";
			}else{
				dia(2);
			}
        },
        error : function() {
			dia(2);
    	}
    });
}