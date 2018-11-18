
//表单验证
var valid_flag = false;
$(function(){
   $(".Code_tips").hide();
});
//商家注册
function reg(){
    valid_flag = user_chk($("#act_user").val());  //用户名验证
    valid_flag = psd_chk($("#set_psd")) && valid_flag;  //密码验证
    valid_flag = psd_conf($("#conf_psd")) && valid_flag;  //再次输入密码验证
    valid_flag = checkExista()&& valid_flag;              //验证手机号码是否注册过
    valid_flag = checkNickname($("#customerNickname"))&&valid_flag;            //验证昵称
    return;
    if(valid_flag){
        $("#userform").submit();
    }else{

    }
}
/**
 * 验证昵称
 */
function checkNickname(obj){
    $("#nickname_tips").html('');
    var x = obj.val();
    var regS=/\s/g;
    if(x.length < 0 || x==''){
        $("#nickname_tips").html('昵称不能为空');
        obj.val('');
        return false;
    }
    if(regS.test(x)){
        $("#nickname_tips").html('请勿输入空格');
        obj.val('');
        return false;
    }
    return true;
}

/**
 * 手机号码是否注册过
 * propName 属性名
 * value 	属性值
 * url 		查询路径
 * tipStr	提示内容  checkExist("customerUsername",$("#customerUsername").val(),"checkExistCustomerUsername.htm","用户名");
 */
function checkExista(){
    var url="checkCustomerUsername.htm",
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
                $("#act_user").next().html("请填写11位有效手机号码");
                $("#act_user").next().hide();
                $("#act_user").next().removeClass("text_error");
                $("#act_user").next().next().html("该号码已被注册");
                $("#act_user").next().next().show();
            }else{
                $("#act_user").next().html("该号码可用");
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
/**
 * 再次输入密码验证
 * @param obj
 * @returns {boolean}
 */
function psd_conf(obj){
    $("#pwds_tips").html('');
    var x = obj.val();
    var regS=/\s/g;
    if(x.length < 0 || x==''){
        $("#pwds_tips").html('确认密码不能为空');
        obj.val('');
        return false;
    }
    if(regS.test(x)){
        $("#pwds_tips").html('请勿输入空格');
        obj.val('');
        return false;
    }
    if(x.length < 6 || x.length > 20){
        $("#pwds_tips").html('密码必须是6-20位');
        obj.val('');
        return false;
    }
    if(x != $('#set_psd').val()){
        $("#pwds_tips").html('两次密码不一致');
        obj.val('');
        return false;
    }
    return true;
}

/***
 * 密码验证
 * @param obj
 * @returns {boolean}
 */
function psd_chk(obj){
    $("#pwd_tips").html('');
    //注册密码
    var x = obj.val();
    var regS=/\s/g;
    if(x.length < 0 || x==''){
        $("#pwd_tips").html('密码不能为空');
        obj.val('');
        return false;
    }
    if(regS.test(x)){
        $("#pwd_tips").html('请勿输入空格');
        obj.val('');
        return false;
    }
    if(x.length < 6 || x.length > 20){
        $("#pwd_tips").html('密码必须是6-20位');
        obj.val('');
        return false;
    }
    return true;
}

/***
 * 用户名验证
 * @param obj
 * @returns {boolean}
 */
function user_chk(x) {
    $("#phone_tips").html("");
    var phone = /^1{1}[3,4,5,8]{1}\d{9}$/;
    var regS=/\s/g;
    if(regS.test(x)){
        $("#phone_tips").html('请勿输入空格');
        x.val('');
        return false;
    }else if(x==''||x.length<0){
        $("#phone_tips").html("手机号码不能为空");
        x.val('');
        return false;
    } else if(!phone.test(x)){
        $("#phone_tips").html("该号码无效，请输入有效的手机号码");
        x.val('');
        return false;
    }
    return true;
}
/**
 * 商家手机注册发送验证码
 */
function getCode(){
    var mobile = $("#act_user").val();
    if(!user_chk(mobile)){
        return;

    }
    var datas = "moblie=" + mobile;
    $("#sendCode").attr("disabled","disabled");
    $.ajax({
        type: 'get',
        url:'../thirdsendcode.htm',
        data:datas,
        async:true,
        success: function(data) {
            alert(data);
            if(data!=0){
                $(".Code_tips").show();  //验证码提示信息
                $(".timeleft").text(59);  //这只重发时间
                setTimeout(countDown, 1000); //调用计时器
                $("#act_user").attr("disabled","disabled"); //禁掉手机号码输入
                $("#sendCode").attr("disabled","disabled"); //禁掉获取按钮
                /*$(".login-btn").attr("disabled","disabled"); //禁掉注册按钮*/
            }else{

            }
           /* alert(data);
            if(data==1) {
                *//*$("#Code_tips").show();
                $(".Code_tips").text(59);
                setTimeout(countDown, 1000);
                $("#mcode").removeAttr("disabled");
                $(".login-btn").removeAttr("disabled");
                $("#mobile").attr("disabled","disabled");
                $("#sendCode").attr("disabled","disabled");*//*
            }else if(data==0){
                //网络异常
                dia(2);
                $("#sendMobileCode").removeAttr("disabled");
            }else if(data==-1){
                $("#m_tip").show();
                $(".mobile_tip").hide();
                $(".timeleft").text(59);
                if($(".timeleft").text()==59){
                    $(".resend").text("");
                    $("#m_tip").find(".resend").remove();
                    $("#m_tip").find("br").remove();
                    $(".resend").html("60秒内只能获取一次验证码,请稍后重试");
                }else{
                    $(".resend").text("");
                    $("#m_tip").find(".resend").remove();
                    $("#m_tip").find("br").remove();
                    $(".resend").html("60秒内只能获取一次验证码,请稍后重试");
                }
                //$("#mcode").removeAttr("disabled");
            }*/
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
            $(".Code_tips").hide();   //隐藏掉验证码提示
            /*恢复手机号码输入、获取按钮、注册按钮*/
            $("#act_user").removeAttr("disabled");
            $("#sendCode").removeAttr("disabled");
            $(".login-btn").removeAttr("disabled");
        } else {
            setTimeout(countDown, 1000);
        }
    }
}
