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
    companyTel: "^((0\\d{2,3})-)(\\d{7,8})(-(\\d{3,}))?$",//公司电话
   // idcard: "^([1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3})|([1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X))$", //身份证 兼容15位 18位 17+X
    idcard: "/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/", //身份证
    ip4: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", //ip地址
    letter: "^[A-Za-z]+$", //字母
    letter_l: "^[a-z]+$", //小写字母
    letter_u: "^[A-Z]+$", //大写字母
    mobile: "^0?(13|15|17|18|14)[0-9]{9}$", //手机
    notempty: "^\\S+$", //非空
    password: "^.*[A-Za-z0-9\\w_-]+.*$", //密码
    fullNumber: "^[0-9]+$", //数字
    picture: "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", //图片
    qq: "^[1-9]*[1-9][0-9]*$", //QQ号码
    rar: "(.*)\\.(rar|zip|7zip|tgz)$", //压缩文件
    tel: "^[0-9\-()（）]{7,18}$", //电话号码的函数(包括验证国内区号,国际区号,分机号)
    url: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", //url
    username: "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$", //户名
    deptname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$", //单位名
    zipcode: "^\\d{6}$", //邮编
    realname: "[\\u4E00-\\u9FA5A-Za-z0-9]+", // 真实姓名
    companyname: "^[A-Za-z0-9_()（）\\.\\-\\u4e00-\\u9fa5]+$",//公司名称
    companyaddr: "^[A-Za-z0-9_()（）\\.\\#\\-\\u4e00-\\u9fa5]+$",//公司地址
    companysite: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&#=]*)?$",//公司网站
    bussId: "^\\d{15}$",
    /*bussDate: "^\\d{4}-\\d{2}-\\d{2}$",*/
    bussDeptNo: "^[a-zA-Z0-9]{8}\\-[a-zA-Z0-9]$",
    bankNo: "^(\\d{19}|\\d{16})$"	,
    username1: "^[A-Za-z0-9_]{4,20}$", //户名
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
    isCompanyTel: function(str) {
        return new RegExp(validateRegExp.companyTel).test(str);
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

var validateUI = {
    showOk: function(options, msg) {
        var _this = $(options.elem + 'Tip'),
            _elem = $(options.elem);
        _this.removeClass('alertTipError onfocus').addClass('alertTip').show();
        _this.find('span').html(msg ? msg : options.onCorrect);
        _elem.removeClass('error').attr('sta', '2');
       // _this.fadeOut(1000);
        return true;
    },
    showErr: function(options, msg) {
        var _this = $(options.elem + 'Tip'),
            _elem = $(options.elem);
            _this.removeClass('alertTip onfocus').addClass('alertTipError').show();
            _this.find('span').html(msg);
            _elem.addClass('error').attr('sta', '1');
        return false;
    },
    showFocus: function(options) {
        var _this = $(options.elem + 'Tip');
        _this.removeClass('alertTipError alertTip').show().addClass('onfocus');
        _this.find('span').html(options.onFocus);
    },
    hideTip: function(options) {
        alert("23");
        $(options.elem + 'Tip').hide();
        $(options.elem).attr('sta', '2');
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
                    alert(options);
                    return validateUI.hideTip(options);
                } else {
                    return validateUI.showErr(options, options.isNull);
                }
            }
            if (options.regExp) {//是否符合传入规则
                //new RegExp() 匹配身份证正则有问题  这里判断是否验证身份证 如果是就重新写表达式 单独验证
                if(options.regExp=="/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/"){
                    //身份证正则
                    var idcard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                    //不合法
                    if(!idcard.test(_val)){
                        return validateUI.showErr(options, options.onError.badFormat);
                    }
                }else{
                    //不是身份证验证
                    if (!new RegExp(options.regExp).test(_val)) {
                        return validateUI.showErr(options, options.onError.badFormat);
                    }
                }

            }
            if(options.elem == '#customerUsername') {
                $.ajax({
                    type: 'post',
                    url: 'checkUsernameExitOrNot.htm?customerUsername='+_val,
                    success:function(data) {
                        if(data > 0) {
                            return validateUI.showErr(options, options.onCheck.badFormat);
                        }else {
                            return validateUI.showOk(options, options.onCorrect);
                        }
                    }
                });
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
                if ($(elements[i]).attr("sta") == '2') {
                    bool = true;
                } else {
                    bool = false;
                    break;
                }
        }
        return bool;
    }
};