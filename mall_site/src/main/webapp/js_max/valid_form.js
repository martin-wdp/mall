//表单验证
var valid_flag = false;
$(function(){
	
	//这个是验证通过与否的标志
	$("input[type='text']").focus(function(){
		$(this).next().next().hide();
		$(this).removeClass('text_error');
		$(this).next().show();
	});
	$("input[type='password']").focus(function(){
		$(this).next().next().hide();
		$(this).removeClass('text_error');
		$(this).next().show();
	});
	$("input[type='text']").blur(function(){
		$(this).next().hide();
		if(isExist($(this).attr('class'),'requird')){
			valid_flag = requird($(this));
		}
		if(isExist($(this).attr('class'),'user_chk')){
			valid_flag = user_chk($(this));
			if(valid_flag){
				if($.trim($(this).val()).length>=4){
					checkExist();
				}
			}
		}
		if(isExist($(this).attr('class'),'name_chk')){
			valid_flag = name_chk($(this));
		}
		if(isExist($(this).attr('class'),'id_chk')){
			valid_flag = id_chk($(this));
		}
		if(isExist($(this).attr('class'),'contact_chk')){
			valid_flag = contact_chk($(this));
		}
	});
	$("input[type='password']").blur(function(){
		$(this).next().hide();
		if(isExist($(this).attr('class'),'psd_chk')){
			valid_flag = psd_chk($(this));
		}
		if(isExist($(this).attr('class'),'psd_conf')){
			valid_flag = psd_conf($(this));
		}
	});
	$("#log_psd").blur(function(){
		$(this).addClass('text_error');
		$(this).next().show().addClass('tips_error').html('密码和账户不匹配，请重新输入');
	});
	
	$("#varification").focus(function(){
		$(".code_error").css({"display":"none"});
	});
	$("#act_user").focus(function(){
		$("#act_user").next().html("请填写6-20位字母、数字或下划线组成的用户名");
	});
	$("#act_user").blur(function(){
		
	});
	
});

/**
 * 唯一验证
 * tipStr	提示内容  checkExist("customerUsername",$("#customerUsername").val(),"checkExistCustomerUsername.htm","用户名");
 */
function checkExist(){
	$("#act_user").next().html("验证中，请稍等...");
	$("#act_user").next().show();
	$("#act_user").next().removeClass("text_error");
	$("#act_user").next().next().hide();
	$("#act_user").next().next().html("请填写6-20位字母、数字或下划线组成的用户名");
		var url="checkExistCustomerUsername.htm",
		datas="1=1";
		datas+="&customerUsername="+$("#act_user").val();
		$.ajax({
	         type: 'post',
	         url:url,
	         data:datas,
	         timeout: 3000,
	         dataType:'json',
	         success: function(data) {
	        	 if (data > 0){
	        		$("#act_user").next().html("请填写6-20位字母、数字或下划线组成的用户名");
	        		$("#act_user").next().hide();
	        		$("#act_user").next().removeClass("text_error");
	        		$("#act_user").next().next().html("用户名已存在,请更换你的用户名");
	        		$("#act_user").next().next().show();
	        	 }else{
	        		$("#act_user").next().html("用户名可用");
		        	$("#act_user").next().show();
		        	$("#act_user").next().removeClass("text_error");
		        	$("#act_user").next().next().hide();
	        	 }
	         },
	         error: function(){
	        	 $("#act_user").next().hide();
	 	    	$("#act_user").next().next().html("网络异常，请稍后再试！").show();
	     	 }
		 });
}

function isExist(str,s){
	if(str.indexOf(s) > 0){
		return true;
	}
	else{
		return false;
	}
}
function isUser(x){
	var reg = /^\w+$/;
	if(reg.test(x) && (x.length >=6) && (x.length <= 20)){
		return true;
	}
	else{
		return false;
	}
}
function isName(x){
	var reg = /[\u4e00-\u9fa5]/;
	if(reg.test(x)){
		return true;
	}
	else{
		return false;
	}
}
function isNum(x){
	var reg = /^[0-9]+$/;
	if(reg.test(x)){
		return true;
	}
	else{
		return false;
	}
}
function isContact(x){
	var mobile = /^(?:13\d|15\d)\d{5}(\d{3}|\*{3})$/;
	var phone = /^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	if(mobile.test(x) || phone.test(x)){
		return true;
	}
	else{
		return false;
	}
}
function requird(obj){
	var x = obj.val();
	if(x == ''){
		obj.addClass('text_error');
		obj.next().hide();
		if(obj.find('.tips_error').length == 0){
			obj.next().next().show().html('输入框不能为空');
			return false;
		}
	}
	else{
		obj.removeClass('text_error');
		obj.next().show();
		obj.next().next().hide();
		return true;
	}
}
function user_chk(obj){
	var x = obj.val();
	var eReg =  /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	var mReg = /^0?(13|15|17|18|14)[0-9]{9}$/;
	if(x.indexOf("@") != -1){
		if(!eReg.test(x)){
			obj.addClass('text_error');
			obj.next().hide();
			if(obj.find('.tips_error').length == 0){
				obj.next().next().show().html('邮件格式不合法');
				return false;
			}
		}else{
			obj.removeClass('text_error');
			obj.next().hide();
			obj.next().next().hide();
			return true;
		}
	}else if(mReg.test(x)){
		obj.removeClass('text_error');
		obj.next().hide();
		obj.next().next().hide();
		return true;
	}else if(!isUser(x)){
		obj.addClass('text_error');
		obj.next().hide();
		if(obj.find('.tips_error').length == 0){
			obj.next().next().show().html('请输入6-20位字母、数字和下划线组成的用户名');
			return false;
		}
	} else{
		obj.removeClass('text_error');
		obj.next().hide();
		obj.next().next().hide();
		return true;
	}
}
function name_chk(obj){
	var x = obj.val();
	if(!isName(x)){
		obj.addClass('text_error');
		obj.next().hide();
		if(obj.find('.tips_error').length == 0){
			obj.next().next().show().html('姓名填写有误，请填写您的真实姓名');
			return false;
		}
	}
	else{
		obj.removeClass('text_error');
		obj.next().show();
		obj.next().next().hide();
		return true;
	}
}
function id_chk(obj){
	var x = obj.val();
	if((!isNum(x)) || (x.length != 18)){
		obj.addClass('text_error');
		obj.next().hide();
		if(obj.find('.tips_error').length == 0){
			obj.next().next().show().html('身份证号填写有误，请重新填写');
			return false;
		}
	}
	else{
		obj.removeClass('text_error');
		obj.next().show();
		obj.next().next().hide();
		return true;
	}
}
function contact_chk(obj){
	var x = obj.val();
	if(!isContact(x)){
		obj.addClass('text_error');
		obj.next().hide();
		if(obj.find('.tips_error').length == 0){
			obj.next().next().show().html('电话号码填写有误，请重新填写');
			return false;
		}
	}
	else{
		obj.removeClass('text_error');
		obj.next().show();
		obj.next().next().hide();
		return true;
	}
}
function psd_chk(obj){
	var x = obj.val();
    var regS=/\s/g;
    if(regS.test(x)){
        obj.addClass('text_error');
        obj.next().hide();
        obj.next().next().show().html('请勿输入空格');
        obj.val('');
        return false;
    }
    else if(x.length < 6 || x.length > 20){
		obj.addClass('text_error');
		obj.next().hide();
		if(obj.find('.tips_error').length == 0){
			obj.next().next().show().html('密码必须是6-20位');
			return false;
		}
	}
	else if(checkPass(x)<2) {
        obj.addClass('text_error');
        obj.next().hide();
        if(obj.find('.tips_error').length == 0){
            obj.next().next().show().html('密码必须是数字和英文字母组合');
            return false;
        }
    }
    else{
		obj.removeClass('text_error');
		obj.next().hide();
		obj.next().next().hide();
		return true;
	}
}

function checkPass(obj) {
    var is = 0;
    if(obj.match(/([0-9])+/)) {
        is++;
    }
    if(obj.match(/([a-z])+/)) {
        is++;
    }
    if(obj.match(/([A-Z])+/)) {
        is++;
    }
    if(obj.match(/[^a-zA-Z0-9]+/)) {
        is++;
    }
    return is;
}

function psd_conf(obj){
	var x = obj.val();
    var regS=/\s/g;
    if(regS.test(x)){
        obj.addClass('text_error');
        obj.next().hide();
        obj.next().next().show().html('请勿输入空格');
        obj.val('');
        return false;
    }
    else if(x.length==0){
		obj.next().hide();
		obj.addClass('text_error');
		obj.next().next().show().html('密码必须是6-20位');
		return false;
	}
	if(x != $('.psd_chk').val()){
		obj.addClass('text_error');
		obj.next().hide();
		if(obj.find('.tips_error').length == 0){
			obj.next().next().show().html('两次填写的密码必须一致');
			return false;
		}
	}
	else{
		obj.removeClass('text_error');
		obj.next().hide();
		obj.next().next().hide();
		return true;
	}
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
$(function(){
	//验证码绑定onclick事件
	$("#checkCodeA").click(
		function(){
			$("#checkCodeImg").click();
		}
	);
});


//推广链接 会员ID解密
function Base64() {
	 
	// private property
	_keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
 
	// public method for encoding
	this.encode = function (input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;
		input = _utf8_encode(input);
		while (i < input.length) {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output +
			_keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
			_keyStr.charAt(enc3) + _keyStr.charAt(enc4);
		}
		return output;
	}
 
	// public method for decoding
	this.decode = function (input) {
		var output = "";
		var chr1, chr2, chr3;
		var enc1, enc2, enc3, enc4;
		var i = 0;
		input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
		while (i < input.length) {
			enc1 = _keyStr.indexOf(input.charAt(i++));
			enc2 = _keyStr.indexOf(input.charAt(i++));
			enc3 = _keyStr.indexOf(input.charAt(i++));
			enc4 = _keyStr.indexOf(input.charAt(i++));
			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;
			output = output + String.fromCharCode(chr1);
			if (enc3 != 64) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64) {
				output = output + String.fromCharCode(chr3);
			}
		}
		output = _utf8_decode(output);
		return output;
	}
 
	// private method for UTF-8 encoding
	_utf8_encode = function (string) {
		string = string.replace(/\r\n/g,"\n");
		var utftext = "";
		for (var n = 0; n < string.length; n++) {
			var c = string.charCodeAt(n);
			if (c < 128) {
				utftext += String.fromCharCode(c);
			} else if((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			} else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}
 
		}
		return utftext;
	}
 
	// private method for UTF-8 decoding
	_utf8_decode = function (utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;
		while ( i < utftext.length ) {
			c = utftext.charCodeAt(i);
			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i+1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i+1);
				c3 = utftext.charCodeAt(i+2);
				string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	}
}



function setCookie(name,value) 
{ 
    var Days = 30; 
    var exp = new Date(); 
    exp.setTime(exp.getTime() + Days*24*60*60*1000); 
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
}

function getCookie(name) 
{ 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
 
    if(arr=document.cookie.match(reg))
 
        return unescape(arr[2]); 
    else 
        return null; 
} 








//判断注册是否是会员推送的链接
function rsgister(){
	//对获取的会员ID进行解密处理
	var b = new Base64(); 
	//当前注册页面url
	var registerUrl = window.location.href; 
	//截取会员ID
	var index =  registerUrl.substring(registerUrl.indexOf("?")+1,registerUrl.length);
	//对会员ID解密
	index = b.decode(index);  
	//获取等号的索引
	var denghao  = index.indexOf("=")+1;
	//截取会员ID
	index = index.substring(denghao,index.length-1);
	return index;
}

//返回正常注册页面
function registerUrl(){
	var url = window.location.href;
	var index = url.indexOf('?');
	var registerUrl = url.substring(0,index);
	window.location.href = registerUrl;
}
//注册
function reg(){
	var flag = "0";
	var id = rsgister();
	valid_flag = user_chk($("#act_user"));
	valid_flag = psd_chk($(".psd_chk")) && valid_flag;
	valid_flag = psd_conf($(".psd_conf")) && valid_flag;
	valid_flag = checkReadMe() && valid_flag;
	$(".code_error").css({"display":"none"});
	var enterValue = $(".code_text").val();
	if(valid_flag){
		if(isNaN(id)){ //正常注册用户
			if(enterValue == emp){
				if(checkExista()){
					$(".hi_name").val($("#act_user").val());
					$(".hi_pwd").val($(".psd_chk").val());
					if(valid_flag){
						$("#userform").submit();
					}
				}else{
					//如果失败修更新验证码
					$("#checkCodeImg").click();
					getPatcha();
					$(".code_text").blur();
				}
			}else{
				$("#varification").next().hide();
				$(".code_error").css({"display":"block"});
				如果失败修更新验证码
				$("#checkCodeImg").click();
				getPatcha();
				$(".code_text").blur();
			}
		}else{//推荐注册
			$('#cusId').val(id);
			if(enterValue == emp){
				if(checkExista()){
				$.ajax({
					type:'post',
					url:'checkRegsiter.htm',
					success:function(data){
						if(getCookie("ip")==null){
							setCookie("ip",data);
							$(".hi_name").val($("#act_user").val());
							$(".hi_pwd").val($(".psd_chk").val());
							if(flag=='0'){
								flag='1';
								$("#userform").submit();
							}
							
						}else{
							if(getCookie("ip")==data){
								dia(2);
							}else{
								$(".hi_name").val($("#act_user").val());
								$(".hi_pwd").val($(".psd_chk").val());
								if(flag=='0'){
									flag='1';
									$("#userform").submit();
								}
							}
							
						}
					}
				});
			}	
         }
	  }
	}
}		
	

/**
 * propName 属性名
 * value 	属性值
 * url 		查询路径
 * tipStr	提示内容  checkExist("customerUsername",$("#customerUsername").val(),"checkExistCustomerUsername.htm","用户名");
 */
function checkExista(){
	$("#act_user").next().html("验证中，请稍等...");
	$("#act_user").next().show();
	$("#act_user").next().removeClass("text_error");
	$("#act_user").next().next().hide();
	$("#act_user").next().next().html("请填写6-20位字母、数字或下划线组成的用户名");
		var url="checkExistCustomerUsername.htm",
		datas="1=1",flag=false;
		datas+="&customerUsername="+$("#act_user").val();
		$.ajax({
	         type: 'post',
	         url:url,
	         data:datas,
	         timeout: 3000,
	         async:false,
	         dataType:'json', 
	         success: function(data) {
	        	 if (data > 0){
	        		$("#act_user").next().html("请填写6-20位字母、数字或下划线组成的用户名");
	        		$("#act_user").next().hide();
	        		$("#act_user").next().removeClass("text_error");
	        		$("#act_user").next().next().html("用户名已存在,请更换你的用户名");
	        		$("#act_user").next().next().show();
	        	 }else{
	        		$("#act_user").next().html("用户名可用");
		        	$("#act_user").next().show();
		        	$("#act_user").next().removeClass("text_error");
		        	$("#act_user").next().next().hide();
		        	flag= true;
	        	 }
	         },
	         error: function(){
	 	    	$("#act_user").next().html("网络异常，请稍后再试！");
	     	 }
		 });
		return flag;
}

function checkReadMe() {
    if ($("#readme").prop("checked")) {
        $("#protocol_error").hide();
        return true;
    } else {
        $("#protocol_error").addClass("tips_error").removeClass("hide").show();
        return false;
    }
}

function agreeonProtocol() {
    if ($("#readme").attr("checked") == true) {
        $("#protocol_error").removeClass().addClass("error hide");
        return true;
    }
}