var validateRegExp = {
    decmal: "^([+-]?)\\d*\\.\\d+$", //浮点数
    decmal1: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$", //正浮点数
    decmal2: "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$", //负浮点数
    decmal3: "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$", //浮点数
    decmal4: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$", //非负浮点数（正浮点数 + 0）
    decmal5: "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$", //非正浮点数（负浮点数 + 0）
    intege: "^-?[1-9]\\d*$", //整数
    intege1: "^[1-9]\\d*$", //正整数
    intege2: "^-[1-9]\\d*$", //负整数
    num: "^([+-]?)\\d*\\.?\\d+$", //数字
    num1: "^[1-9]\\d*|0$", //正数（正整数 + 0）
    num2: "^-[1-9]\\d*|0$", //负数（负整数 + 0）
    ascii: "^[\\x00-\\xFF]+$", //仅ACSII字符
    chinese: "^[\\u4e00-\\u9fa5]+$", //仅中文
    color: "^[a-fA-F0-9]{6}$", //颜色
    date: "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$", //日期
    email: "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
    idcard: "^[1-9]([0-9]{14}|[0-9]{17})$", //身份证
    ip4: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", //ip地址
    letter: "^[A-Za-z]+$", //字母
    letter_l: "^[a-z]+$", //小写字母
    letter_u: "^[A-Z]+$", //大写字母
    mobile: "^0?(13|15|17|18|14)[0-9]{9}$", //手机
    notempty: "^\\S+$", //非空
    password: "^.*[A-Za-z0-9\\w_-]{6,20}.*$", //密码
    fullNumber: "^[0-9]+$", //数字
    picture: "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", //图片
    qq: "^[1-9]*[1-9][0-9]*$", //QQ号码
    rar: "(.*)\\.(rar|zip|7zip|tgz)$", //压缩文件
    tel: "^[0-9\-()（）]{7,18}$", //电话号码的函数(包括验证国内区号,国际区号,分机号)
    url: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", //url
    username: "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$", //户名
    deptname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$", //单位名
    zipcode: "^\\d{6}$", //邮编
    realname: "^[A-Za-z\\u4e00-\\u9fa5]+$", // 真实姓名
    companyname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",//公司名称
    companyaddr: "^[A-Za-z0-9_()（）\\#\\-\\u4e00-\\u9fa5]+$",//公司地址
    companysite: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&#=]*)?$",//公司网站
    bussId: "^\\d{15}$",
    bussDate: "^\\d{4}-\\d{2}-\\d{2}$",
    bussDeptNo: "^[a-zA-Z0-9]{8}\\-[a-zA-Z0-9]$",
    bankNo: "^(\\d{19}|\\d{16})$"	,
    username1: "^[A-Za-z0-9_]{4,20}$", //户名
    addressas: "^[A-Za-z0-9\\u4e00-\\u9fa5]+$", // 地址别名
};

//验证规则
var validateRules = {
    strLength: function(str) {
        var len = 0;
        for (var i = 0; i < str.length; i++) {
            var c = str.charCodeAt(i);
            if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
                len++;
            } else {
                len += 2;
            }
        }
        return len;
    },
    isNull: function(str) {
        return (str == "" || typeof str != "string");
    },
    betweenLength: function(str, min, max) {
        var _len = validateRules.strLength(str);
        return (_len >= min && _len <= max);
    },
    isUid: function(str) {
        return new RegExp(validateRegExp.username).test(str);
    },
    isZipcode: function(str) {
        return new RegExp(validateRegExp.zipcode).test(str);
    },
    fullNumberName: function(str) {
        return new RegExp(validateRegExp.fullNumber).test(str);
    },
    isPwd: function(str) {
        return /^.*([\W_a-zA-z0-9-])+.*$/i.test(str);
    },
    isPwdRepeat: function(str1, str2) {
        return (str1 == str2);
    },
    isEmail: function(str) {
        return new RegExp(validateRegExp.email).test(str);
    },
    isTel: function(str) {
        return new RegExp(validateRegExp.tel).test(str);
    },
    isMobile: function(str) {
        return new RegExp(validateRegExp.mobile).test(str);
    },
    checkType: function(element) {
        return (element.attr("type") == "checkbox" || element.attr("type") == "radio" || element.attr("rel") == "select");
    },
    isRealName: function(str) {
        return new RegExp(validateRegExp.realname).test(str);
    },
    isCompanyname: function(str) {
        return new RegExp(validateRegExp.companyname).test(str);
    },
    isCompanyaddr: function(str) {
        return new RegExp(validateRegExp.companyaddr).test(str);
    },
    isCompanysite: function(str) {
        return new RegExp(validateRegExp.companysite).test(str);
    }
};
var mFlag = false;
var validateUI = {
    showOk: function(options, msg) {
    	if(options.elem == "#mob"){
    		var _this = $(options.elem).parent().next(),
            _elem = $(options.elem);
	        _this.html(msg ? msg : options.onCorrect);
	        _elem.parent().parent().removeClass('has-error').attr('sta', '2');
	        if(!mFlag){
		        $(options.elem).parent().next().html("验证中，请稍等...");
		    		var url="checkExistCustomerUsername.htm",
		    		datas="1=1";
		    		datas+="&customerUsername="+$("#mob").val(),
		    		eFlag = true;
		    		$.ajax({
		    	         type: 'post',
		    	         url:url,
		    	         data:datas,
		    	         async : false,
		    	         timeout: 3000,
		    	         success: function(data) {
		    	        	 if (data > 0){
		    	        		 eFlag = false;
		    	        		 $(options.elem).parent().next().html("用户名已存在,请更换你的用户名");
		    	        		 _elem.parent().parent().addClass('has-error').attr('sta', '2');
		    	        	 }else{
		    	        		 $(options.elem).parent().next().html("用户名可用");
		    	        		 _elem.parent().parent().removeClass('has-error').attr('sta', '2');
		    	        	 }
		    	         },
		    	         error: function(){
		    	        	 $(options.elem).parent().next().html("网络异常，请稍后再试！");
		    	     	 }
		    		 });
		    		if(eFlag && !mFlag){
		    			mFlag = true;
		    			var mobile = $("#mob").val();
		    			var datas = "moblie=" + mobile;
		    			$("#mc_btn").attr("disabled","disabled");
		    			$.ajax({
		    				type: 'post',
		    		        url:'sendcode.htm',
		    		        data:datas,
		    		        async:true,
		    		        success: function(data) {
		    		        	if(data==1) {
		    		        		$("#mc_btn").html('59秒');
		    						$("#mc_btn").attr('data-t','59');
		    						setTimeout(countDown, 1000);
		    						$("#mc_btn").attr("disabled","disabled");
		    						$("#mob").attr("readonly","readonly");
		    						$("#mc_btn").attr('class','btn btn-default');
		    					}else if(data==0){
		    						//网络异常
		    						$("#mc_btn").removeAttr("disabled");
		    					}else if(data==-1){
		    						
		    					}
		    		        },
		    		        error : function() {
		    		        	//网络异常
//		    		        	$("#mc_btn").html('59秒');
//	    						$("#mc_btn").attr('data-t','59');
//	    						setTimeout(countDown, 1000);
//	    						$("#mc_btn").attr("disabled","disabled");
//	    						$("#mob").attr("disabled","disabled");
//	    						$("#mc_btn").attr('class','btn btn-default');
		    		        	$("#mc_btn").removeAttr("disabled");
		    					$("#mc_btn").html("获取验证码");
		    					$("#mob").removeAttr("readonly");
		    					$("#mc_btn").attr('class','btn btn-warning');
		    		    	}
		    		    });
		    			
		    			function countDown(){
		    				var time = $("#mc_btn").attr('data-t');
		    				$("#mc_btn").html((time - 1)+"秒");
		    				$("#mc_btn").attr('data-t',(time - 1));
		    				if (time == 1) {
		    					$("#mc_btn").removeAttr("disabled");
		    					$("#mc_btn").html("获取验证码");
		    					$("#mob").removeAttr("readonly");
		    					$("#mc_btn").attr('class','btn btn-warning');
		    					mFlag = false;
		    				} else {
		    					setTimeout(countDown, 1000);
		    				}
		    			}
		    			
		    		}
	        }
	        return true;
    	}else{
    		var _this = $(options.elem).next(),
            _elem = $(options.elem);
	        _this.html(msg ? msg : options.onCorrect);
	        _elem.parent().removeClass('has-error').attr('sta', '2');
	        return true;
    	}
        
    },
    showErr: function(options, msg) {
    	if(options.elem == "#mob"){
    		var _this = $(options.elem).parent().next(),
            _elem = $(options.elem);
            _this.html(msg);
            _elem.parent().parent().addClass('has-error').attr('sta', '1');
            return false;
    	}else{
    		var _this = $(options.elem).next(),
            _elem = $(options.elem);
            _this.html(msg);
            _elem.parent().addClass('has-error').attr('sta', '1');
            return false;
    	}
        
    },
    showFocus: function(options) {
    	if(options.elem == "#mob"){
    		var _this = $(options.elem).parent().next();
            _this.html(options.onFocus);
    	}else{
    		var _this = $(options.elem).next();
            _this.html(options.onFocus);
    	}
    	
    },
    hideTip: function(options) {
        //$(options.elem).next().hide();
        //$(options.elem).parent().removeClass('has-error').attr('sta', '2');
    }
};


var validateChkFun = {
    init: function(fun) {
        fun.call(this);
    },
    defaultChk: function(options) {
        var _this = $(options.elem);
        _this.bind('focus', function() {
            validateUI.showFocus(options);
        })
        .bind('blur', function() {
            var _val = $.trim(_this.val());
            if (validateRules.isNull(_val)) {//为空
                if (options.inputChk) {
                    return validateUI.hideTip(options);
                } else {
                    return validateUI.showErr(options, options.isNull);
                }
            }
            if (options.regExp) {//是否符合传入规则
                if (!new RegExp(options.regExp).test(_val)) {
                    return validateUI.showErr(options, options.onError.badFormat);
                }
            }
            validateUI.showOk(options);
        });
    },
    bussDateChk:function(options){
    	 var _this = $(options.elem);
         _this.bind('focus', function() {
             validateUI.showFocus(options);
         })
         .bind('blur', function() {
             var _val = $.trim(_this.val());
             if (options.regExp) {
                 if (!new RegExp(options.regExp).test(_val)) {
                	 var _th = $("#bussDateTip"),
                     _elem = $(options.elem);
                	 _th.removeClass('alertTip onfocus').addClass('alertTipError').show();
                	 _th.find('span').html(options.onError.badFormat);
                     _elem.addClass('error').attr('sta', '1');
                     return false;
                 }
             }
             var end = $(options.elem).val().split("-");
             var start = $(options.elems).val().split("-");
             var starttime = new Date(start[0], start[1], start[2]);
             var endtime = new Date(end[0], end[1], end[2]);

             if (starttime.getTime() >= endtime.getTime()) {
            	 var _th = $("#bussDateTip"),
                 _elem = $(options.elem);
            	 _th.removeClass('alertTip onfocus').addClass('alertTipError').show();
            	 _th.find('span').html(options.onError.overFormat);
                 _elem.addClass('error').attr('sta', '1');
            	 return false;
             }
             validateUI.showOk(options);
         });
    },
    cateChk:function(options){
    	var _this = $(options.elem);
    	_this.bind('blur', function() {
    		var _val = $.trim(_this.val());
    		if (validateRules.isNull(_val)) {//为空
                if (options.inputChk) {
                    return validateUI.hideTip(options);
                } else {
                    return validateUI.showErr(options, options.isNull);
                }
    		}
    		validateUI.showOk(options);
    	});
    },
    formSubmit: function (elements) {
        var bool = true;
        $(elements.join(",")).trigger('blur');
        for (var i = 0; i < elements.length; i++) {
        	if(elements[i] == '#mob'){
        		if ($(elements[i]).parent().parent().attr("sta") == '2') {
                    bool = true;
                } else {
                    bool = false;
                    break;
                }
        	}
            if ($(elements[i]).parent().attr("sta") == '2' && elements[i] != '#mob') {
                bool = true;
            } else {
            	if(elements[i] != '#mob'){
           		 	bool = false;
                    break;
            	}
            }
        }
        return bool;
    }
};