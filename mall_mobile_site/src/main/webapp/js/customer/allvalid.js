

$(function(){
	
    $(".sub_add").click(function() {
    	var flag= validateChkFun.formSubmit(['#as','#ae','#mo','#ad','#az']);
    	if(flag){
    		if($("#ad").val().trim().length < 75){
    			$(this).parents("form").submit();
    		}else{
    			$("#ad").parent().addClass("has-error");
    			$("#ad").next().html('地址超出长度限制,不要超过75个字符!');
    		}
    	}
    });
    $(".sub_up").click(function() {
    	var flag= validateChkFun.formSubmit(['#as','#ae','#mo','#ad','#az']);
    	if(flag){
    		if($("#ad").val().trim().length < 75){
    			$(this).parents("form").submit();
    		}else{
    			$("#ad").parent().addClass("has-error");
    			$("#ad").next().html('地址超出长度限制,不要超过75个字符!');
    		}
    	}
    });
    var num=0;
    $(".reg").click(function() {
    	var flag= validateChkFun.formSubmit(['#mob','#mc','#pd']);
    	if(flag&&num==0){
    		if($("#pdt").val()==$("#pd").val()){
    			$("#pdt").parent().removeClass("has-error");
    			$("#pdt").next().html("");
    			var checkCode = $("#mc").val();
    			$.ajax({
    				type: 'post',
    				url: "validate/getMCode.htm?code="+checkCode, 
    				context: document.body, 
    				async:false,
    				success: function(data){
    					if(data == 1){
                            num+=1;
    						$(".reg").parent().attr('action',"customer/addcustomer.htm");
    						$(".reg").parent().submit();
    					}else{
    						$("#mc").parent().addClass("has-error");
    						$("#mc").next().html("手机验证码错误或者已经失效");
    					}
    			}});
    		}else{
    			$("#pdt").parent().addClass("has-error");
    			$("#pdt").next().html("你两次输入的密码不相同");
    		}
    	}
    });
    $("#mc_btn").click(function() {
    	$("#mob").blur();
    });
    validateChkFun.init(function() {
        validateChkFun.defaultChk({
            elem: '#as',
            isNull: '请输入地址别名',
            onCorrect: '',
            onlyNotNullChk: true,
            regExp: validateRegExp.addressas,
            onError: {
                badFormat: "地址别名有误"
            }
        });
        validateChkFun.defaultChk({
        	elem: '#ae',
        	isNull: '请输入姓名',
        	onCorrect: '',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.realname,
        	onError: {
        		badFormat: "姓名有误"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#mo',
        	isNull: '请输入手机或者固话',
        	onCorrect: '',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.tel,
        	onError: {
        		badFormat: "手机或者固话有误"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#ad',
        	isNull: '请输入详细地址',
        	onCorrect: '',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.companyaddr,
        	onError: {
        		badFormat: "详细地址有误"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#az',
        	isNull: '请输入邮编',
        	onCorrect: '',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.zipcode,
        	onError: {
        		badFormat: "邮编有误"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#mob',
        	isNull: '请输入手机号码',
        	onCorrect: '',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.mobile,
        	onError: {
        		badFormat: "请输入正确的手机号码！"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#mc',
        	isNull: '请输入短信验证码',
        	onCorrect: '',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.intege1,
        	onError: {
        		badFormat: "短信验证有误"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#pd',
        	isNull: '请输入密码',
        	onCorrect: '',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.password,
        	onError: {
        		badFormat: "密码格式错误"
        	}
        });
    });
});
