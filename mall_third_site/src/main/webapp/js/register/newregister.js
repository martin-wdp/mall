

$(function(){

    //验证码绑定onclick事件
    $("#checkCodeA").click(
        function(){
            $("#checkCodeImg").click();
        }
    );

    $("#act_user").focus(function(){
        $(this).next().next().next().hide();
        $(this).removeClass('n_error');
        $(this).next().next().html("请填写6-20位字母、数字或下划线组成的用户名");
        $(this).next().next().show();
    });
    $("input[type='password']").focus(function(){
        $(this).next().next().next().hide();
        $(this).removeClass('n_error');
        $(this).next().next().show();
    });

    //验证码
    $(".varification").focus(function(){
        $(this).next().next().hide();
        $(this).removeClass('n_error');
    });


    //用户名验证
    $("#act_user").blur(function(){
        $(this).next().next().hide();

            if(user_chk($(this))){
                if($.trim($(this).val()).length>=6){
                    checkExist();
                }
            }

    });

    //密码验证
    $(".checkpassword1").blur(function(){
        $(this).next().next().hide();
            psd_chk($(this));


    });

    //确认密码验证
    $(".checkrepassword1").blur(function(){
        $(this).next().next().hide();
        psd_conf($(this));


    });

    //手机验证
    $(".checkmobile1").blur(function(){
  $(".checkmobile3").hide();
        checkMobile();
    });

    $("#varification").focus(function(){
        $(".code_error").hide();
        $(this).removeClass('n_error');
        $(".code_tips").show();
    });

    $("#varification").blur(function(){
        $(".code_tips").hide();
    });


});

var checkmobile=false;

function checkMobile(){
    $(".varification").html('');
    checkmobile=true;
    var mobile = $(".checkmobile1").val();
    if (!mobile) {
        $(".checkmobile3").hide();
        $(".checkmobile2").show();
        $(".checkmobile2").html("请输入手机号");
        checkmobile= checkmobile&&false ;
        return checkmobile;
    }
    var re = /^1{1}[3,4,5,8,7]{1}\d{9}$/; // 判断是否为数字的正则表达式
    if (!re.test(mobile)) {
        $(".checkmobile3").hide();
        $(".checkmobile2").show();
        $(".checkmobile2").html("请输入正确的手机号码");
        checkmobile= checkmobile&&false ;
        return checkmobile;
    }
        $.ajax({
            type: 'post',
            url:'checkthirdmobileexist.htm?mobile='+mobile,
            async:false,
            success: function(obj) {
                if(obj>0) {
                    $(".checkmobile3").hide();
                    $(".checkmobile2").show();
                    $(".checkmobile2").html("检测到此手机已被使用,请更换!");
                    checkmobile= checkmobile&&false;
                    return checkmobile;
                }else{
                    $(".checkmobile2").hide();
                    checkmobile= checkmobile&&true;
                    return checkmobile;
                }
            },
            error : function() {
                $(".checkmobile3").hide();
                $(".checkmobile2").show();
                $(".checkmobile2").html("网络异常请稍后再试");
                checkmobile= checkmobile&&false;
                return checkmobile;
            }
        });

    return checkmobile;

}
//判断用户名是否合法
//1、判断是不是邮箱注册
//2、判断用户名是否符合正则表达式
//3、判断用户名长度是否够
function user_chk(obj){
    var x = obj.val();
    var eReg =  /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    var mReg = /^0?(13|15|17|18|14)[0-9]{9}$/;
    if(x.indexOf("@") != -1){
        if(!eReg.test(x)){
            obj.addClass('n_error');
            obj.next().next().hide();
            if(obj.find('.n_tips').length == 0){
                obj.next().next().next().show().html('邮箱格式不合法');
                return false;
            }
        }else{
            obj.removeClass('n_error');
            obj.next().next().hide();
            obj.next().next().next().hide();
            return true;
        }
    }else if(mReg.test(x)){
        obj.removeClass('n_error');
        obj.next().next().hide();
        obj.next().next().next().hide();
        return true;
    }else if(!isUser(x)){
        obj.addClass('n_error');
        obj.next().next().hide();
        if(obj.find('.n_tip').length == 0){
            obj.next().next().next().show().html('请输入6-20位字母、数字和下划线组成的用户名');
            return false;
        }
    } else{
        obj.removeClass('n_error');
        obj.next().next().hide();
        obj.next().next().next().hide();
        return true;
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

/**
 * 唯一验证
 * tipStr	提示内容  checkExist("customerUsername",$("#customerUsername").val(),"checkExistCustomerUsername.htm","用户名");
 */
function checkExist(){
    var flag=false;
    $("#act_user").next().next().html("验证中，请稍等...");
    $("#act_user").next().next().show();
    $("#act_user").removeClass("n_error");
    $("#act_user").next().next().next().hide();
    $("#act_user").next().next().next().html("请填写6-20位字母、数字或下划线组成的用户名");
    var url="checkUserNameExist.htm?customerUsername="+$("#act_user").val();
    $.ajax({
        type: 'post',
        url:url,
        async:false,
        success: function(data) {
            if (data > 0){
                $("#act_user").next().next().html("请填写6-20位字母、数字或下划线组成的用户名");
                $("#act_user").next().next().hide();
                $("#act_user").next().next().next().html("用户名已存在,请更换你的用户名");
                $("#act_user").next().next().next().show();
                flag= false;
            }else{
                $("#act_user").next().next().html("用户名可用");
                $("#act_user").next().next().show();
                $("#act_user").next().next().removeClass("n_error");
                $("#act_user").next().next().next().hide();
                flag=  true;
            }
        },
        error: function(){
            $("#act_user").next().next().hide();
            $("#act_user").next().next().next().html("网络异常，请稍后再试！").show();
            flag= false;
        }
    });
    return flag;
}

function checkReadMe() {
    if ($("#readme").prop("checked")) {
        $("#protocol_error").hide();
        return true;
    } else {
        $("#protocol_error").show();
        return false;
    }
}


function psd_chk(obj){
    var x = obj.val();
    var regS=/\s/g;
    if(regS.test(x)){
        obj.addClass('n_error');
        obj.next().next().hide();
        obj.next().next().next().show().html('请勿输入空格');
        obj.val('');
        return false;
    }
    else if(x.length < 6 || x.length > 20){
        obj.addClass('n_error');
        obj.next().next().hide();
        if(obj.find('.n_tips').length == 0){
            obj.next().next().next().show().html('密码必须是6-20位');
            return false;
        }
    }
    else if(checkPass(x)<2) {
        obj.addClass('n_error');
        obj.next().next().hide();
        if(obj.find('.n_tips').length == 0){
            obj.next().next().next().show().html('密码必须是数字和英文字母组合');
            return false;
        }
    }
    else{
        obj.removeClass('n_error');
        obj.next().next().hide();
        obj.next().next().next().hide();
        return true;
    }
}

function psd_conf(obj){
    var x = obj.val();
    var regS=/\s/g;
    if(regS.test(x)){
        obj.addClass('n_error');
        obj.next().next().hide();
        obj.next().next().next().show().html('请勿输入空格');
        obj.val('');
        return false;
    }
    else if(x.length==0){
        obj.next().next().hide();
        obj.addClass('n_error');
        obj.next().next().next().show().html('密码必须是6-20位');
        return false;
    }
    if(x != $('.checkpassword1').val()){
        obj.addClass('n_error');
        obj.next().next().hide();
        if(obj.find('.n_tips').length == 0){
            obj.next().next().next().show().html('两次填写的密码必须一致');
            return false;
        }
    }else{
        obj.removeClass('n_error');
        obj.next().next().hide();
        obj.next().next().next().hide();
        return true;
    }
}

//检查密码是否是数字加字母或特殊字符的组合
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

//得到手机验证码
function getCode(obj){

    //清空验证码提示信息
    $(".varification").html('');
    //验证码
    var enterValue = $("#varification").val();
    var valid_flag=true;
    //用户名
    valid_flag = valid_flag&&user_chk($("#act_user"));
    //密码
    valid_flag = psd_chk($(".checkpassword1")) && valid_flag;
    //确认密码
    valid_flag = psd_conf($(".checkrepassword1")) && valid_flag;

    if(enterValue != ''&&enterValue!=null &&valid_flag){
    $.ajax({
        url: "checkpatchca.htm?enterValue="+enterValue,
        context: document.body,
        async:false,
        success: function(data){

                if(data==0){
                    $("#varification").addClass("n_error");
                    $(".checkmobile2").html('请输入正确的验证码之后再发送短信');
                    $(".checkmobile2").show();
                }else{
                    var mobile = $(".checkmobile1").val();
                    if(checkMobile()){
                    $("#varification").removeClass("n_error");
                    $(".varification").html('');
                    $(obj).attr("disabled","disabled");
                    var mobile = $(".checkmobile1").val();
                    var datas = "mobile=" + mobile;
                    $.ajax({
                        type: 'get',
                        url:'sendcodetovalidate.htm',
                        data:datas,
                        async:false,
                        success: function(data) {
                            if(data==1) {

                                $(".varification").html('');
                                var info='校验码已发出您可以在';
                                info+='<font class="timeleft" style="color: rgb(228, 57, 60); font-weight: bold;">59</font>秒后重新发送'
                                $(".checkmobile2").html(info);
                                $(".timeleft").text(59);
                                $(".checkmobile2").show();
                                setTimeout(countDown, 1000);
                                $(obj).removeAttr("disabled");
                                $(".checkmobile1").attr("readonly","readonly");
                                $("#sendCode").attr("disabled","disabled");
                                $("#checkCodeImg").click();
                            }else if(data==0){
                                //网络异常
                                $(".checkmobile2").html('网络异常请稍后再试');
                                $(".checkmobile2").show();
                                $("#sendCode").removeAttr("disabled");
                            }else if(data==-1){

                                $(".checkmobile2").html('60秒内只能获取一次验证码,请稍后重试');
                                $(".checkmobile2").show();
                            }
                        },
                        error : function() {
                            //网络异常
                            $(".checkmobile2").html('网络异常请稍后再试');
                            $(".checkmobile2").show();
                        }
                    });
                   }

                else{
                        //$(".checkmobile3").hide();
                        //$(".checkmobile2").show();
                        //$(".checkmobile2").html("请输入手机号码");
                    }

                }

        }});
    }
else{
        if(enterValue == ''||enterValue==null){
            $(".varification").html('请输入验证码');
            $(".varification").show();
        }


    }
    function countDown(){
        var time = $(".timeleft").text();
        $(".timeleft").text(time - 1);
        if (time == 1) {
            $(".checkmobile2").hide();
            $("#sendCode").removeAttr("disabled");
            $(".checkmobile1").removeAttr("readonly");
        } else {
            setTimeout(countDown, 1000);
        }
    }
}


function reg(){
    //去掉手机号码不能修改
    $(".checkmobile1").removeAttr("readonly");
    var num=0;
    var valid_flag=true;
    var flag = "0";
    valid_flag = valid_flag&&user_chk($("#act_user"));
    //密码
    valid_flag = psd_chk($(".checkpassword1")) && valid_flag;
    //确认密码
    valid_flag = psd_conf($(".checkrepassword1")) && valid_flag;
    valid_flag = checkReadMe() && valid_flag;
    //$(".code_error").css({"display":"none"});
    //验证码
    var enterValue = $("#varification").val();
    //短信验证码
    var codetext=$(".dynamiccode1").val();
    var mobile = $(".checkmobile1").val();

    //验证码
    var enterValue = $("#varification").val();
    if(enterValue != ''&&enterValue!=null) {
        $.ajax({
            url: "checkpatchca.htm?enterValue=" + enterValue,
            context: document.body,
            async: false,
            success: function (data) {
                if(data==0){
                    $("#varification").addClass("n_error");
                    $(".varification").html('验证码不正确');
                    $(".varification").show();

                }else{
                    if(valid_flag&&checkMobile()&&checkExist()){
                        var dyna=$(".dynamiccode1").val();
                        if(dyna!=''&&dyna!=null) {
                            $.ajax({
                                url: "checkmobilecode.htm?codetext=" + codetext + "&mobile=" + mobile,
                                context: document.body,
                                success: function (data) {
                                    if (enterValue != '') {
                                        if (data == 0) {
                                            $(".checkmobile2").html('短信验证码不正确');
                                            $(".checkmobile2").show();
                                            $("#sendCode").removeAttr("disabled");
                                            //如果失败修更新验证码
                                            $("#checkCodeImg").click();
                                        } else {
                                            $(".varification").html('');
                                            if (valid_flag && num == 0) {
                                                num += 1;
                                                $("#userform").submit();
                                            }
                                        }
                                    } else {
                                        $(".varification").html('请输入验证码');
                                        $(".varification").show();
                                    }
                                }
                            });
                        }
                        else{
                            if(mobile!=''&&mobile!=null){
                                $(".checkmobile2").html('请输入短信验证码');
                                $(".checkmobile2").show();
                            }else{
                                $(".checkmobile2").html('请输入手机号码');
                                $(".checkmobile2").show();
                            }

                        }

                    }
                }
            }
        });
    }


}